package com.vendertool.sitewebapp.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.vendertool.sharedtypes.core.Account;
import com.vendertool.sharedtypes.core.AccountSecurityQuestion;
import com.vendertool.sharedtypes.core.Language;
import com.vendertool.sharedtypes.error.Errors;
import com.vendertool.sharedtypes.rnr.AskSecurityQuestionsRequest;
import com.vendertool.sharedtypes.rnr.ErrorResponse;
import com.vendertool.sitewebapp.util.MenuBuilder;
import com.vendertool.sitewebapp.util.MockDataUtil;

@Controller
public class AskSecurityQuestionsController {
	private static final Logger logger = Logger.getLogger(AskSecurityQuestionsController.class);
	
	@RequestMapping(value="askSecurityQuestions", method=RequestMethod.POST)
	public String getAskQuestionsView(Model model, HttpServletRequest request){
		logger.info("getAskQuestionsView POST controller invoked");
		
		Locale locale = RequestContextUtils.getLocale(request);
		
		List<AccountSecurityQuestion> questions = MockDataUtil.getUsersAccountSecurityQuestions();
		
		AskSecurityQuestionsRequest questionsReq = new AskSecurityQuestionsRequest();
		questionsReq.setQuestions(questions);

		model.addAttribute("questionsReq", questionsReq);
		model.addAttribute("languages", Language.getLanguages());
		model.addAttribute("langOptions", MenuBuilder.getLanguageOptions(locale));
		model.addAttribute("selectedLang", request.getParameter("lang"));
		
		return "askSecurityQuestions/askSecurityQuestions";
	}
	
	@RequestMapping(value="answerSecurityQuestions", method=RequestMethod.POST)
	public String answerSecurityQuestions(ModelMap modelMap, 
			HttpServletRequest req,
			HttpServletResponse res,
			@ModelAttribute("AskSecurityQuestionsRequest") AskSecurityQuestionsRequest questionsReq) {
		logger.info("answerSecurityQuestions POST controller invoked");
		
		//
		// Validate answers
		//
		ErrorResponse errorResponse = validateAnswers(questionsReq.getQuestions());
		if (errorResponse != null) {
			modelMap.addAttribute("errorResponse", errorResponse);
			
			List<AccountSecurityQuestion> questions = MockDataUtil.getUsersAccountSecurityQuestions();
			questionsReq.setQuestions(questions);
			modelMap.addAttribute("questionsReq", questionsReq); // echo back the questions
		}
		else {
			return "redirect:changePassword";
		}

		return "askSecurityQuestions/answerSecurityQuestions";
	}
	
	//
	// Replace with real errorResponses
	//
	private ErrorResponse validateAnswers(List<AccountSecurityQuestion> questions) {
		
		if (questions != null) {
			for (AccountSecurityQuestion q : questions) {
				if (q.getAnswer() == null || q.getAnswer().trim().length() == 0) {
					ErrorResponse resp = new ErrorResponse();
					resp.addFieldBindingError(
						Errors.REGISTRATION.MISSING_SECURITY_ANSWER,
						q.getClass().getName(),
						"newEmail");

					return resp;
				}
			}
		}
		
		
		return null;
	}
	
}



