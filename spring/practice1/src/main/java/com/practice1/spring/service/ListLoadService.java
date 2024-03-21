package com.practice1.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice1.spring.dao.MemberDao;
import com.practice1.spring.dto.Member;

@Service
public class ListLoadService {
	private MemberDao mDao;

	@Autowired
	private void memberDao(MemberDao memberDao) {
		this.mDao = memberDao;
	}

	public List<Member> loadList() {
		List<Member> list = mDao.selectAll();
		return list;
	}
}

