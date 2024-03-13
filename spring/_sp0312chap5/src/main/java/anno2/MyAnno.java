package anno2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//적용 대상 = method
@Target(ElementType.METHOD)
// runtime중에도 적용됨(JVM이 참조함)
@Retention(RetentionPolicy.RUNTIME)

public @interface MyAnno {
	public String value();
}
