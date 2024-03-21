package com.practice1.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice1.spring.dao.MemberDao;

@Service
public class MemberDeleteService {

private MemberDao mDao;
	
	@Autowired
	private void memberDao(MemberDao memberDao) {
		this.mDao = memberDao;
	}

	public void DeleteButton(long id) {
		mDao.delete(id);
	}

}
