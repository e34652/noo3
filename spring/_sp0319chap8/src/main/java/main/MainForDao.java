package main;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import config.AppCtx;
import spring.Member;
import spring.MemberDao;

public class MainForDao {
	private static Logger LOGGER = LoggerFactory.getLogger(MainForDao.class);

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);

		MemberDao memberDao = ctx.getBean(MemberDao.class);
		Member member = new Member("6623@523", "523", "523", null);
		memberDao.insert2(member);
		LOGGER.info("새로 입력된 회원의 ID: {}", member.getId());
//		LOGGER.info("입력 완료");
//		Member member3 = new Member("123@123", "321", "321", null);
//		memberDao.update(member3);
//		LOGGER.info("수정 완료");
//
//			LOGGER.info("아이디:{}, 이메일:{}, 패스워드:{}, 이름:{}, 등록일:{}",
//				member.getId(),
//				member.getEmail(),
//				member.getPassword(),
//				member.getName(),
//				member.getRegisterDateTime());
	
		List<Member> list = memberDao.selectAll();
		LOGGER.info("{}", list);
		LOGGER.info("---Select all---");
		for(Member member1 : list) {
			LOGGER.info(member1.toString());
		}
		LOGGER.info("---Select By Email---");
		Member member2 = memberDao.selectByEmail2("madvirus@madvirus.net");
		LOGGER.info("{}", member2);
		
		int count = memberDao.count();
		LOGGER.info("{}", count);
		ctx.close();
	}
}
