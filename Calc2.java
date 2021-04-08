package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext application = request.getServletContext(); // 어플리케이션 저장을 위한 구문
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		
		if(!v_.equals("")) {
			v = Integer.parseInt(v_);
		}
		
		if(op.equals("=")) { // 값을 계산
			int x = (Integer)application.getAttribute("value"); // 저장한 값 불러오기, 오브젝트로 불러오니 정수로 바꾸어 줌
			int y = v;
			String operator = (String)application.getAttribute("op");		
			int result = 0;
			
			if(operator.equals("+")) {
				result = x + y;
			} else {
				result = x - y;
			}	
			response.getWriter().printf("result is %d\n", result);
		} else { // 값을 저장
			application.setAttribute("value", v); // 저장할 값 지정
			application.setAttribute("op", op); // 저장할 값 지정
		}
		
		
		
		
	}

}
