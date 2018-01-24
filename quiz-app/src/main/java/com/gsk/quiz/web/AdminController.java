package com.gsk.quiz.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.gsk.quiz.dto.QuizDTO;
import com.gsk.quiz.model.QuizResult;
import com.gsk.quiz.repository.QuizConstants;
import com.gsk.quiz.repository.QuizResultRepository;
import com.gsk.quiz.service.QuizService;
import com.gsk.quiz.util.MyLoggerFactory;

@Controller
public class AdminController extends QuizConstants {

	private static final Logger logger = MyLoggerFactory.getLogger();

	@Autowired
	QuizService quizService;
	
	@Autowired
	QuizResultRepository quizResultRepository;

	@RequestMapping("/admin/uploadQuiz")
	public String uploading(Model model) {

		return "uploadQuiz";
	}

	@RequestMapping(value = "/admin/createQuiz", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uploadgQuiz(@ModelAttribute("quiz") QuizDTO quizDTO, ModelMap model, HttpServletRequest request)
			throws IOException {
		MultipartFile uploadedFile = quizDTO.getQuizCsvFIle();

		File finalFile = null;

		if (!uploadedFile.isEmpty()) {
			try {
				String uploadsDir = "/uploads/";
				String realPathtoUploads = request.getServletContext().getRealPath(uploadsDir);
				if (!new File(realPathtoUploads).exists()) {
					new File(realPathtoUploads).mkdir();
				}

				logger.info("realPathtoUploads = {}", realPathtoUploads);

				String orgName = uploadedFile.getOriginalFilename();
				String filePath = realPathtoUploads + orgName;
				finalFile = new File(filePath);
				uploadedFile.transferTo(finalFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		quizService.save(quizDTO, finalFile);

		return REDIRECT_ADMIN_DASHBOARD;
	}

	@RequestMapping("/admin/admin-dashboard")
	public String userDashboard(Model model) {

		return "admin-dashboard";
	}

	@RequestMapping("/admin/quizResult")
	public String quizResult(Model model) {
		
		List<QuizResult> quizResultList =  quizResultRepository.findAll();
		
		model.addAttribute("quizResultList", quizResultList);
		return "admin-quizResult";
	}

}
