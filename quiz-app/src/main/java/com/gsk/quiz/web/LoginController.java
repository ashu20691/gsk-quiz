package com.gsk.quiz.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gsk.quiz.repository.QuizConstants;
import com.gsk.quiz.repository.UserRepository;

@Controller
public class LoginController extends QuizConstants {
	protected static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = { "", "/" }, method = { RequestMethod.GET, RequestMethod.POST })
	public String rootUrl(HttpServletRequest request) {
		logger.debug("Root url entered.. forwarding to home page...");
		logger.debug("request uri: before forwarding--" + request.getRequestURI());
		return REDIRECT_HOME;
	}

	

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
    
	/**
	 * Redirects to home page
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/home", method = { RequestMethod.GET, RequestMethod.POST })
	public String showHome(ModelMap model, HttpServletRequest requestAttributes) {
		logger.debug("Received request to show Home Page");
		try {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			String username = user.getUsername();

			com.gsk.quiz.model.User custUser = userRepository.findByEmail(username);
			
			logger.info("currently logged in username : " + username);
			model.addAttribute("user", user);
			if (custUser.getRole().getRoleId() == 1) {
				logger.info("redirecting to admin dashboard");
				return REDIRECT_ADMIN_DASHBOARD;
			} else if (custUser.getRole().getRoleId() == 2) {
				logger.info("redirecting to user dashboard");
				return REDIRECT_USER_DASHBOARD;
			}
		} catch (Exception e) {
			logger.error("Exception occured on home page", e);
		}
		return ERROR_PAGE;
	}

}
