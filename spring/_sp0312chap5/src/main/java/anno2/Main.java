package anno2;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 타입을 특정해 new로 직접 객체생성 << class를 내 패키지에 가지고 있어야함 
		// 같은 패키지 내에 위치해야 하며 컴파일 시점에 해당 클래스를 참조할 수 있어야함 = 해당 클래스의 정보가 필요함
		// 정적으로 클래스를 참고하므로 클래스 정보가 변경되면 코드가 수정되어야함
		Target ta = new Target();
		ta.sub();
	
		// 클래스 이름을 문자열로 받아와서 동적으로 객체를 생성 << 외부 라이브러리나 플러그인을 사용할 때 유용함
		// 컴파일 시점에 클래스의 정보를 알 필요가 없음 = 코드 작성시 클래스의 이름만 알면 됨 
		// 리플렉션을 활용한 방식
		String className = "anno2.Target";
		Class<?> cls = Class.forName(className); // 그런데 결국 Target a로 받도록 코드를 짜면 타입을 안다는 뜻이 아닌가?
		Target a = (Target) cls.getDeclaredConstructor().newInstance();
		a.sub();

		//전체 메서드의 특정 어노테이션 가져오기
		Method[] methods = cls.getDeclaredMethods();

		for (int i = 0; i < methods.length; i++)
			if (methods[i].isAnnotationPresent(MyAnno.class)) {
				MyAnno myAnno = methods[i].getAnnotation(MyAnno.class);
				System.out.println(myAnno.value());
			}

		// 특정메서드의 어노테이션 가져오기
		Method method = Target.class.getMethod("sub");// 메서드 특정
		Annotation[] annotations = method.getDeclaredAnnotations(); // 해당 메서드의 어노테이션 불러오기

		for (Annotation anno : annotations) {// for문으로 돌리며 출력
			MyAnno myAnno = (MyAnno) anno;
			System.out.println(myAnno.value());
		}
	}

}
