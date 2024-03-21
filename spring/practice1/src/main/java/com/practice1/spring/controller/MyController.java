package com.practice1.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practice1.spring.dao.MemberDao;
import com.practice1.spring.dto.Member;
import com.practice1.spring.service.ListLoadService;
import com.practice1.spring.service.MemberDeleteService;
import com.practice1.spring.service.MemberInsertService;
import com.practice1.spring.service.MemberSelectService;
import com.practice1.spring.service.MemberUpdateService;


@Controller
public class MyController {
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberDeleteService mDelSvc;
	
	@Autowired
	private MemberUpdateService mUpdSvc;
	
	@Autowired
	private MemberInsertService mInsSvc;
	
	@Autowired
	private MemberSelectService mSelSvc;
	
	@Autowired
	private ListLoadService mListLoder;
	
	
	@GetMapping("/")
	public String mainPage(Model model) {
		List<Member> mList = mListLoder.loadList();
		model.addAttribute("MemberList" , mList);
		return "root";
	}
	
	@GetMapping("/mDeleteButton")
	public String clickDel(@RequestParam("id")long id, Model model) {
		
		mDelSvc.DeleteButton(id);
		List<Member> mList = mListLoder.loadList();
		model.addAttribute("MemberList" , mList);
		return "root";
	}
	
	@GetMapping("/mEditButton")
	public String clickEdit(@RequestParam("id") long id, Model model) {
		Member member = mSelSvc.selectMember(id);
		List<Member> mList = mListLoder.loadList();
		model.addAttribute("SelectedMember" , member);
		model.addAttribute("MemberList" , mList);
		return "root";
	}
	
	@PostMapping("/mUpdateButton")
	public String clickUpdate(
			@RequestParam("memo") String memo,
			@RequestParam("age") int age,
			@RequestParam("name") String name, 
			@RequestParam("email") String email, Model model) {
		Member member = new Member(name, age, email, memo); 
		mUpdSvc.MemberUpdate(member);
		List<Member> mList = mListLoder.loadList();
		model.addAttribute("MemberList" , mList);
		return "root";
	}
	
	@PostMapping("/mInsertButton")
	public String clickInsert(
			@RequestParam("memo") String memo,
			@RequestParam("age") int age,
			@RequestParam("name") String name, 
			@RequestParam("email") String email, Model model) {
		Member member = new Member(name, age, email, memo); 
		mInsSvc.InsertButton(member);
		List<Member> mList = mListLoder.loadList();
		model.addAttribute("MemberList" , mList);
		return "root";
	}
}
