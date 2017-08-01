package com.tiexue.cms.manage.exception;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
@ControllerAdvice
public class CmsExceptionHandler {

	private static Logger logger = Logger.getLogger(CmsExceptionHandler.class);

	/**
	 * 错误信息
	 */
	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ModelAndView processUnauthenticatedException(NativeWebRequest request, UnauthorizedException e) {
		/*
		 * Map<String, String> exception = new HashMap<String, String>();

		 * logger.error("unauthorized Access to the API: " + e.getMessage(), e);
		 * exception.put("code", "401"); exception.put("reason",
		 * e.getMessage()); return exception;
		 */
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e);
		mv.setViewName("mcpAdmin/unauthorized");
		return mv;
	}
}
