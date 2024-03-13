package anno2;

import anno1.MyAnnotation;

public class Target {
	@MyAnnotation
	@MyAnno(value = "ㅇㅅㅇㅅㅇ")
	public void sub() {
		System.out.println("Target");
	}
}
