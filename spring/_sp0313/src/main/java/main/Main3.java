package main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import config.AppCtx;
import spring.Client;



public class Main3 {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		Client client1 = ctx.getBean(Client.class);
		Client client2 = ctx.getBean(Client.class);
		System.out.println(client1 == client2);
		// true가 나온다 = 싱글톤이므로 같은 인스턴스가 호출됨


	}

}
