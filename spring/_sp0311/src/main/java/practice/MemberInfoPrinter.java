package practice;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberInfoPrinter {
	@Autowired
	private MemberDao memberDao;

	public void InfoPrint() {
		memberDao.process();
		System.out.println("memberInfoPrinter()");
	}
}
