package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import controller.HelloController;

@Configuration
public class ControllerConfig {
	//서블릿 MVC2에서는 ContextPath마다 조건문으로 나누어 맵핑했다면
	//스프링에서는 어노테이션을 통해 각 동작을 메서드 별로 나누어 bean객체화하여 맵핑함, 등록된 bean객체는 spring container에서 관리됨  
	//이곳에서 bean메서드를 통해 bean객체를 등록함, 구체적인 동작은 핸들러처럼 컨트롤러 클래스에 작성함
	//서로 연관된 코드를 한 컨트롤러 클래스에 묶어 작성할 수도 있고 전부 다른 컨트롤러 클래스에 따로 작성할 수도 있음 

		@Bean  
		public HelloController helloController() {
			return new HelloController();
		}
}
