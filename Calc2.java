package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletContext application = request.getServletContext(); // 어플리케이션 저장을 위한 구문 : url 전역 접속시 사용되는 저장공간
		HttpSession session = request.getSession(); // 세션 저장을 위한 생성 : 현제 접속한 사용자의 저장공간
		Cookie[] cookies = request.getCookies(); // 사용자로 부터 쿠키를 받아오기 위한 생성, 배열로 오기에 반복문을 통해 찾음
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		
		if(!v_.equals("")) {
			v = Integer.parseInt(v_);
		}
		
		if(op.equals("=")) { // 값을 계산
			
			// int x = (Integer)application.getAttribute("value"); // 저장한 값 불러오기, 오브젝트로 불러오니 정수로 바꾸어 줌
			
			// int x = (Integer)session.getAttribute("value"); // 세션으로 저장한 값 불러오기
			
			int x = 0;
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue()); // 문자열로 들어오기에 정수로 변환
					break; // 쿠키값 찾으면 for문 탈출
				}
			}
			int y = v;
			
			// String operator = (String)application.getAttribute("op");
			
			// String operator = (String)session.getAttribute("op");
			
			String operator = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("op")) {
					operator = c.getValue(); // 문자열로 들어오기에 정수로 변환
					break; // 쿠키값 찾으면 for문 탈출
				}
			}

			int result = 0;
			
			if(operator.equals("+")) {
				result = x + y;
			} else {
				result = x - y;
			}	
			response.getWriter().printf("result is %d\n", result);
			
		} else { // 값을 저장
			// application.setAttribute("value", v); // 저장할 값 지정
			// application.setAttribute("op", op); // 저장할 값 지정
			
			// session.setAttribute("value", v); // 세션으로 저장할 값 지정
			// session.setAttribute("op", op);
			
			Cookie valueCookie = new Cookie("value", String.valueOf(v)); // 쿠키로 보낼 값을 지정, 문자열만 가능하여 변환
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setPath("/calc2"); // 어느 경우에 쿠키를 받을지 설정, / : 모든 url에 대해
			valueCookie.setMaxAge(60 * 60 * 24); // 쿠키의 만료 기간 설정, 1초 단위, 만료를 안쓰면 브라우저 닫을 시 만료
			opCookie.setPath("/calc2");
			response.addCookie(valueCookie); // 사용자에게 쿠키 전달
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2.html"); // 계산 실행 후 사용자에게 해당 페이지를 보여줌
			
		}
		
	}

}
