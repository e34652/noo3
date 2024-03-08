package main;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppConf1;
import config.AppConfImport;

public class MainForSpring4 {

	private static ApplicationContext ctx = null;

	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppConfImport.class);
		
	//@Configuration된 객체도 Bean 취급
	AppConf1 appConf1 = ctx.getBean(AppConf1.class);
	System.out.println(appConf1 != null);
	
	AppConf1 appConf2 = ctx.getBean(AppConf1.class);
	System.out.println(appConf2 != null);
	
	}
}