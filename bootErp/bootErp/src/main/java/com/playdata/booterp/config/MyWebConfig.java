package com.playdata.booterp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//@Configuration어노테이션을 선언하고 WebMvcConfigurer를 상속해서 작업
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
	//정적리소스의 경로를 설정하는 경우
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 특정path(/download)로 요청하면 실제 파일이 저장된 위치를 연결해서 리소스를 가져올 수 있도록 처리
//		ResourceHandlerRegistration obj =  registry.addResourceHandler("/download/**");
//		obj.addResourceLocations("file:///C:/back_MSA/upload/");
		registry.addResourceHandler("/download/**")
		        .addResourceLocations("file:///C:/back_MSA/upload/");
	}
	
	
	
	
}
