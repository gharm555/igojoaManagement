package com.itwill.igojoamanagement.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(false);

		// 세션에 인증된 사용자 정보가 없으면 로그인 페이지로 리디렉션
		if (session == null || session.getAttribute("authenticatedAdmin") == null) {
			response.sendRedirect("/admin/signIn");
			return false; // 요청을 중단하고 리디렉션
		}

		// 인증된 사용자가 있는 경우 요청을 계속 진행
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// 요청 처리 후 추가 작업이 필요할 경우 구현
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// 응답 후 작업이 필요할 경우 구현
	}
}

