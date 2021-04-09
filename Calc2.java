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
		
		ServletContext application = request.getServletContext(); // ���ø����̼� ������ ���� ���� : url ���� ���ӽ� ���Ǵ� �������
		HttpSession session = request.getSession(); // ���� ������ ���� ���� : ���� ������ ������� �������
		Cookie[] cookies = request.getCookies(); // ����ڷ� ���� ��Ű�� �޾ƿ��� ���� ����, �迭�� ���⿡ �ݺ����� ���� ã��
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset = UTF-8");
		
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		
		if(!v_.equals("")) {
			v = Integer.parseInt(v_);
		}
		
		if(op.equals("=")) { // ���� ���
			
			// int x = (Integer)application.getAttribute("value"); // ������ �� �ҷ�����, ������Ʈ�� �ҷ����� ������ �ٲپ� ��
			
			// int x = (Integer)session.getAttribute("value"); // �������� ������ �� �ҷ�����
			
			int x = 0;
			for(Cookie c : cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue()); // ���ڿ��� �����⿡ ������ ��ȯ
					break; // ��Ű�� ã���� for�� Ż��
				}
			}
			int y = v;
			
			// String operator = (String)application.getAttribute("op");
			
			// String operator = (String)session.getAttribute("op");
			
			String operator = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("op")) {
					operator = c.getValue(); // ���ڿ��� �����⿡ ������ ��ȯ
					break; // ��Ű�� ã���� for�� Ż��
				}
			}

			int result = 0;
			
			if(operator.equals("+")) {
				result = x + y;
			} else {
				result = x - y;
			}	
			response.getWriter().printf("result is %d\n", result);
			
		} else { // ���� ����
			// application.setAttribute("value", v); // ������ �� ����
			// application.setAttribute("op", op); // ������ �� ����
			
			// session.setAttribute("value", v); // �������� ������ �� ����
			// session.setAttribute("op", op);
			
			Cookie valueCookie = new Cookie("value", String.valueOf(v)); // ��Ű�� ���� ���� ����, ���ڿ��� �����Ͽ� ��ȯ
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setPath("/calc2"); // ��� ��쿡 ��Ű�� ������ ����, / : ��� url�� ����
			valueCookie.setMaxAge(60 * 60 * 24); // ��Ű�� ���� �Ⱓ ����, 1�� ����, ���Ḧ �Ⱦ��� ������ ���� �� ����
			opCookie.setPath("/calc2");
			response.addCookie(valueCookie); // ����ڿ��� ��Ű ����
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2.html"); // ��� ���� �� ����ڿ��� �ش� �������� ������
			
		}
		
	}

}
