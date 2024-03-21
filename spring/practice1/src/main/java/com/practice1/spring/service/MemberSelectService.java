package com.practice1.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice1.spring.dao.MemberDao;
import com.practice1.spring.dto.Member;

@Service
public class MemberSelectService {
	private MemberDao mDao;

	@Autowired
	private void memberDao(MemberDao memberDao) {
		this.mDao = memberDao;
	}

	public Member selectMember(long id) {
		Member mDto = mDao.selectById(id);
		return mDto;
	}
}
