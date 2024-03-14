package config;

import javax.servlet.RequestDispatcher;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	// 포워딩 할 view 페이지의 경로(URL)와 그 형식(jsp)을 정의
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// 서블릿 컨트롤러에서 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/boardview/boardList.jsp");
		// 이렇게 직접 view페이지의 경로를 지정하던 것을, view 페이지 파일의 경로와 확장자의 기본값만 셋팅해두고
		// 컨트롤러 페이지에서 return 되는 view 페이지의 이름을 받아와 추적함 
		registry.jsp("/WEB-INF/view/", ".jsp");
	}

}
