package practice;

import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	private static Logger LOGGER = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DbConfig.class,
				DbQueryConfig.class);
		Scanner scan = new Scanner(System.in);
		MemberDao dbQuery = ctx.getBean(MemberDao.class);
		boolean repeat = true;
		Member dto = null;
		int memberno = 0;
		String id = null;
		String pw = null;
		String name = null;
		
		while (repeat) {
			System.out.println("수행할 기능");
			int choice = Integer.parseInt(scan.nextLine());
			switch (choice) {
			case 1:
				System.out.println("총 회원 수");
				int count = dbQuery.count();
				LOGGER.info("인원 {}명", count);
				break;
			case 2:
				System.out.println("회원 조회");
				memberno = Integer.parseInt(scan.nextLine());
				String memberInfo = dbQuery.searchMemberName(memberno);
				LOGGER.info(memberno + "번 회원님의 이름은 {}입니다", memberInfo);
				break;
			case 3:
				System.out.println("회원 등록");
				System.out.println("id 입력");
				id = scan.nextLine();
				System.out.println("pw 입력");
				pw = scan.nextLine();
				System.out.println("name 입력");
				name = scan.nextLine();
				dto = new Member(id, pw, name);
				dbQuery.insert(dto);
				LOGGER.info("등록되었습니다");
				break;

			case 4:
				System.out.println("회원 갱신");
				System.out.println("pw 입력");
				pw = scan.nextLine();
				System.out.println("name 입력");
				name = scan.nextLine();
				dto = new Member(pw, name);
				dbQuery.update(dto);
				LOGGER.info("갱신되었습니다");
				break;

			case 5:
				System.out.println("삭제할 회원번호");
				memberno = Integer.parseInt(scan.nextLine());
				dbQuery.delete(memberno);
				LOGGER.info(memberno + "번 회원정보가 삭제되었습니다");
				break;

			case 7:
				List<Member> list = dbQuery.selectAll();
				System.out.println("회원목록");
				for (Member member : list) {
					LOGGER.info("{}, {}, {}, {}", member.getMemberno(), member.getId(), member.getPw(),
							member.getName());
				}
				break;
			case 8:
				System.out.println("종료합니다");
				System.exit(0);
			}
		}
	}
}
