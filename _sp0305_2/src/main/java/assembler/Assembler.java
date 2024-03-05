package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

public class Assembler {
	private MemberDao memberDao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	
	public Assembler() {
		memberDao = new MemberDao();
		//Assembler라는 상위 클래스에서 필요한 하위 객체(의존성)를 직접 생성하지 않고 주입받아 의존객체를 생성하므로 결합도가 줄어듦
		regSvc = new MemberRegisterService(memberDao);// << 하위 클래스의 객체는 외부에서 생성하여 주입함
		pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao);
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public MemberRegisterService getMemberRegisterService() {
		return regSvc;
	}

	public ChangePasswordService getChangePasswordService() {
		return pwdSvc;
	}
	
}
