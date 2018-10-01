package com.zycus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zycus.customExceptions.EntityNotFoundInDatabaseException;
import com.zycus.myenums.UserRole;
import com.zycus.service.UserService;

@Controller
public class CommonFrontController {
	@Autowired
	private UserService userService;

	// This is for checking login credentials of user
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ModelAndView userLogin(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			int userId = Integer.parseInt(request.getParameter("id"));
			UserRole role = userService.login(userId, request.getParameter("password"));
			HttpSession session = request.getSession();
			if (role == UserRole.ADMIN) {
				session.setAttribute("userid", userId);
				modelAndView.setViewName("/admin/AdministratorHome");
			} else if (role == UserRole.USER) {
				session.setAttribute("userid", userId);
				modelAndView.setViewName("/user/userHome");
			}

		} catch (Exception e) {
			modelAndView.getModel().put("message", "Invalid username or password");
			modelAndView.setViewName("/errorPages/error");
		}

		return modelAndView;

	}

	// To logout
	@RequestMapping(value = "/user/logout")
	public ModelAndView logout(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.removeAttribute("userid");

		ModelAndView modelAndView = new ModelAndView("redirect:/home");
		return modelAndView;

	}

}
