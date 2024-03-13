package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	// url 패턴, get 타입 요청
	// Get또는 Post Mapping 어노테이션이 붙은 메서드의 return 값은 ViewResolver에 의해 처리됨
	// ViewResolver는 return값을 뷰 이름으로 취급하여 설정값을 기반으로 실제 뷰 페이지 파일을 찾아내 렌더링함 
	//서블릿 MVC2에서 ContextPath에 따라 조건문으로 나누던 방식을 메서드로 나눔
	@GetMapping("/hello") 

	// model = 전달할 데이터를 담을 객체, HttpServletRequest또는 ModelAndView 등을 통해 전달
	public String hello(Model model) {
		model.addAttribute("str", "한글 나오나");
		// view 이름 << ViewResolver는 리턴값을 바탕으로 해당하는 view 파일을 찾아냄
		return "hello";
	}
}
