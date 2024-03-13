package practice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainForSpring {

	public static void main(String args[]) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Appctx.class);
		MemberRegisterService mrs = ctx.getBean("memberRegSvc",MemberRegisterService.class);
		mrs.regist();
		
		ChangePasswordService cps = ctx.getBean("changePwdSvc", ChangePasswordService.class);
		cps.changePassword();
		
		
		MemberInfoPrinter mip = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		mip.InfoPrint();
		ctx.close();
	}
}
