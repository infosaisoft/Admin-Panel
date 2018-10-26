package com.harbor.interceptor;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	/*@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		RequestDispatcher rd = null;
		System.out.println("Request URL::" + request.getRequestURL().toString());
		System.out.println(request.getRealPath("home"));
		if (request.getRealPath("home").equals("home")) {
			String result = (String) request.getAttribute("result");

			if (result.equals("success")) {
				System.out.println("home page");
				rd = request.getRequestDispatcher("home.html");
				rd.forward(request, response);

			} else {
				rd = request.getRequestDispatcher("login.html");
			}

		}

	}
*/

}