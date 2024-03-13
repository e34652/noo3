package anno1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})// MyAnnotation이 붙을 수 있는 타입
@Retention(RetentionPolicy.RUNTIME) // 컴파일러에 어느 시점까지 영향을 끼치는지
public @interface MyAnnotation { // 인터페이스와 유사하지만 내용이 있음
	String value() default "MyAnnotation : ㅇㅅㅇ"; // 문자열을 리턴해주는 메소드 
}
