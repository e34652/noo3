package optional;

import java.util.Optional;

public class OptionalEx {

	public static void main(String[] args) {
		//비지 않은 Optional 객체
		String str = "Hello, World";
		Optional<String> optionalStr = Optional.of(str);
		System.out.println(optionalStr);

		//Null Optional 객체
		String nullStr = null;
		Optional<String> optionalNullStr = Optional.ofNullable(nullStr);
		System.out.println(optionalNullStr);

		// 빈 Optional 객체
		Optional<String> emptyOptional = Optional.empty(); 
		System.out.println(emptyOptional);
	}

}
