package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dto.LoginRequest;
import service.GetMemberNumService;

@Controller
public class MyController {
	
	@Autowired
	private GetMemberNumService getMemberNumService;
	
	@GetMapping("/") //처음 요청이 들어오면
	public String root() {
		return "index"; // 메인 페이지로 
	}
	
	@GetMapping("/index1") //처음 요청이 들어오면
	public String index() {
		return "redirect:/"; // 메인 페이지로 
	}
	
	@GetMapping("/hello") //요청 패턴
	public String hello() { // 메소드 이름 상관 X
		return "hello"; // view의 이름
	}
	@GetMapping("/loginForm") // 요청 패턴
	public String loginForm() { // 메소드 이름 상관 X
		return "loginForm"; // view의 이름
	}

	@GetMapping("/result") // 요청 패턴
	// 스프링은 암묵적으로 매개변수로 전달된 객체를 자동으로 모델에 추가하여 포워딩함
	// 명시적으로 하려면 직접 model객체에 .addAttribute를 해야함
	public String result(LoginRequest loginRequest, Model model) {
	System.out.println(getMemberNumService.getNumRecords());
	System.out.println(loginRequest.getId() + loginRequest.getEmail());
	String str = "가나다"; // 지역변수이므로 괄호가 끝나면 사라질 운명
	model.addAttribute("str", str); //model 객체에 넣어서 포워딩해줌
	
	return "result";
	}
	
	
//	@GetMapping("/result") // 요청 패턴
//	public String result(@RequestParam String id, String email) {
//	System.out.println(id + email);
//	return "result";
//	}
//	public String result(HttpServletRequest request) { // 메소드 이름 상관 X
//		String id = request.getParameter("id");
//		String email = request.getParameter("email");
//		System.out.println(id + email);
//		return "result"; // view의 이름
//	}
}
