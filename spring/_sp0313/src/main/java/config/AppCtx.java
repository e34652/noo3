package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import spring.Client;
import spring.Client2;

@Configuration
public class AppCtx {

	@Bean
	@Scope("prototype") // bean 객체가 싱글톤이 아니고 프로토타입이 되고 라이프 사이클이 따로 돌아 따로  
	public Client client() {
		Client client = new Client();
		client.setHost("Host");

		return client;
	}
	
	//직접 정의 
	@Bean(initMethod = "connect", destroyMethod = "close")
	public Client2 client2() {
		Client2 client = new Client2();
		client.setHost("Host2");
		return client;
	}
}
