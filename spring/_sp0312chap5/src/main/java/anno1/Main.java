package anno1;

import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) {
		Method[] methodList = MyObject.class.getMethods(); // 메서드 다 불러오기

        for (Method method : methodList) { // 하나씩 조건문 적용
        	System.out.println(method);
            if(method.isAnnotationPresent(MyAnnotation.class)) { // 메소드에 MyAnnotation이라는 이름의 어노테이션이 존재한다면 
                MyAnnotation annotation=
                		method.getDeclaredAnnotation(MyAnnotation.class); // 메소드에 있는 어노테이션을 불러옴 
                String value=annotation.value(); // 불러온 어노테이션의 value를 문자열로 대입
                System.out.println(method.getName() + ":" + value); // 해당 값을 출력
            }
        }

	}

}
