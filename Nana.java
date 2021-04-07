package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hi") // 어노테이션을 통한 url 맵핑
public class Nana extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setCharacterEncoding("UTF-8"); // 한글 가능하도록 유니코드 인코딩 
		response.setContentType("text/html; charset = UTF-8"); // 웹 페이지를 어떻게 읽을지 제시 = html, UTF-8
		
		PrintWriter out = response.getWriter(); // 글자를 출력할 경우
		
		String cnt_ = request.getParameter("cnt"); // 쿼리스트링을 위한 변수 생성
		int cnt = 10; // 사용자가 전달하는 쿼리값이 없을 경우 기본값
		if(cnt_ != null && !cnt_.equals("")) { // 사용자가 정확한 값을 줄 경우
			cnt = Integer.parseInt(cnt_); // 쿼리스트링 활용 : 사용자가 입력한 만큼 반복하도록 함, 문자열을 정수형으로 변환
		}
		
		for(int i = 0; i < cnt; i++) { // localhost:8080/hi?cnt=2 처럼 쿼리값을 줘야 가능
			out.println((i + 1) + ": 안녕 Servlet!!<br >"); // <br>을 통해 내려쓰기
		}
		
	}
	
}
