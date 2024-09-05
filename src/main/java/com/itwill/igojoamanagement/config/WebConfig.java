//package com.itwill.igojoamanagement.config;
//
//import com.itwill.igojoamanagement.interceptor.AuthenticationInterceptor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Autowired
//    private AuthenticationInterceptor authenticationInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authenticationInterceptor)
//                .addPathPatterns("/**")  // 보호된 경로 설정 (예: 관리자 페이지)
//                .excludePathPatterns("/admin/signIn", "/css/**", "/js/**");  // 로그인 및 공개 경로는 제외
//    }
//
//}


