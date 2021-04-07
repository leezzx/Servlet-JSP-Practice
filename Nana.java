package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hi") // ������̼��� ���� url ����
public class Nana extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.setCharacterEncoding("UTF-8"); // �ѱ� �����ϵ��� �����ڵ� ���ڵ� 
		response.setContentType("text/html; charset = UTF-8"); // �� �������� ��� ������ ���� = html, UTF-8
		
		PrintWriter out = response.getWriter(); // ���ڸ� ����� ���
		
		String cnt_ = request.getParameter("cnt"); // ������Ʈ���� ���� ���� ����
		int cnt = 10; // ����ڰ� �����ϴ� �������� ���� ��� �⺻��
		if(cnt_ != null && !cnt_.equals("")) { // ����ڰ� ��Ȯ�� ���� �� ���
			cnt = Integer.parseInt(cnt_); // ������Ʈ�� Ȱ�� : ����ڰ� �Է��� ��ŭ �ݺ��ϵ��� ��, ���ڿ��� ���������� ��ȯ
		}
		
		for(int i = 0; i < cnt; i++) { // localhost:8080/hi?cnt=2 ó�� �������� ��� ����
			out.println((i + 1) + ": �ȳ� Servlet!!<br >"); // <br>�� ���� ��������
		}
		
	}
	
}
