package com.javaex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**") // 경로- 요청주소가 api로 시작해야한다.는 뜻
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedOrigins("http://localhost:8080")//visual에 있는 서버랑 동일해야함.
				.allowedHeaders("*") // 모든 요청해더
				.exposedHeaders("Authorization")// 노출시킬 헤더
				.allowCredentials(true); // 쿠키허용

	}
	
	//파일 드라이브뒤지도록 하는 메소드
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	registry.addResourceHandler("/upload/**")// '/upload/'로 요청이 오면 C드라이블 뒤질 수 있도록 우리가 가상으로 만든거임.
	.addResourceLocations("file:C:\\javaStudy\\upload\\");
	}

}
