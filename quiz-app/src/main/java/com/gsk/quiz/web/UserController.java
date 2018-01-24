package com.gsk.quiz.web;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gsk.quiz.dto.UserAnswersDTO;
import com.gsk.quiz.model.Question;
import com.gsk.quiz.model.User;
import com.gsk.quiz.repository.QuestionRepository;
import com.gsk.quiz.repository.QuizConstants;
import com.gsk.quiz.repository.UserRepository;
import com.gsk.quiz.service.QuizResultService;
import com.gsk.quiz.service.RoleService;
import com.gsk.quiz.service.SecurityService;
import com.gsk.quiz.service.UserAnswersService;
import com.gsk.quiz.service.UserService;
import com.gsk.quiz.util.MyLoggerFactory;
import com.gsk.quiz.validator.UserValidator;

@Controller
public class UserController extends QuizConstants {
    private static final Logger logger = MyLoggerFactory.getLogger();

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private QuestionRepository quesRepository;
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserAnswersService userAnswerService;
    
    @Autowired
    private QuizResultService quizResultService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        
        userForm.setRole(roleService.findByName("ROLE_USER"));
        userService.save(userForm);
        securityService.autologin(userForm.getEmail(), userForm.getPasswordConfirm());

        return REDIRECT_USER_DASHBOARD;
    }
    
    
    @RequestMapping("/user/user-dashboard")
    public String userDashboard(Model model) {
    	
    	List<Question> quesListAll = quesRepository.findAll();
    	
    	org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String username = user.getUsername();

		com.gsk.quiz.model.User curentUser = userRepository.findByEmail(username);
		
		Collections.shuffle(quesListAll);
	    List<Question> quesList = quesListAll;

		if(quesListAll.size() > 5 ) {
			 quesList = quesListAll.subList(0, 5);
		}

    	model.addAttribute("quesList", quesList);
    	model.addAttribute("userAnswers", new UserAnswersDTO());
    	model.addAttribute("userid", curentUser.getUserId());
    	
    	return "user-dashboard";
    }
   /* 
    @RequestMapping(value="/user/submitUserQuiz", method=RequestMethod.POST)
    public String submitUserQuiz(@ModelAttribute("userAnswers") UserAnswersDTO userAnswers, BindingResult userloginResult, Model model) {
    	
    	List<Question> quesList = quesRepository.findAll();

    	model.addAttribute("quesList", quesList);
    	
    	return "user-dashboard";
    }*/
    
    @RequestMapping(value="/all/submitUserQuizJson", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public String submitUserQuizJson(@RequestBody UserAnswersDTO userAnswers) {
    	
    	logger.info("answers submmited by user");
    	
    	userAnswerService.save(userAnswers);
    	
    	quizResultService.save(userAnswers);
    	logger.info("redirect to user-congrats");
    	return REDIRECT_USER_CONGRATS;
    }
    

    @RequestMapping("/user/user-congrats")
    public String userCongrats(Model model) {
    	logger.info("in user congrats");
    	return "user-congrats";
    }
    
    
}
