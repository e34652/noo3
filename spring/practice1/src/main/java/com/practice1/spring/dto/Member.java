package com.practice1.spring.dto;

import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {

	private Long id;
	private String name;
	private int age;
	private String email;
	private String memo;
	
	public Member(String name, int age, String email, String memo) {
	this(0, name, age, email, memo);
	}

	public Member(long id, String name, int age, String email, String memo) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.memo = memo;
	}
}
