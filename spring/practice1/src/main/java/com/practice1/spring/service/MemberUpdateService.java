package com.practice1.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice1.spring.dao.MemberDao;
import com.practice1.spring.dto.Member;

@Service
public class MemberUpdateService {
	private MemberDao mDao;
	
	@Autowired
	private void memberDao(MemberDao memberDao) {
		this.mDao = memberDao;
	}

	public void MemberUpdate(Member member) {
		mDao.update(member);
	}
}
