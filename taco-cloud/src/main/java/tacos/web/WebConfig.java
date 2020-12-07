package tacos.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 모델 데이터나 사용자 입력 처리하지 않는 간단한 컨트롤러의 경우 뷰에 요청만 하면 된다.
// 뷰 컨트롤러 선언 
@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	}

}
