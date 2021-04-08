package com.newlecture.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*") // 모든 url에 대한 필터 어노테이션
public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException { // class 생성시 add에서 Filter(servlet) 추가
		
		request.setCharacterEncoding("UTF-8"); // 필터를 통과하기 전 할일
		
		chain.doFilter(request, response); // 필터

	}

}
