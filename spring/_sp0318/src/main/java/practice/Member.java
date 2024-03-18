package practice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	
	private int memberno;
	private String id;
	private String pw;
	private String name;
	
	public Member(String id, String pw, String name) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
	}

	public Member(int memberno, String id, String pw, String name) {
		super();
		this.memberno = memberno;
		this.id = id;
		this.pw = pw;
		this.name = name;
	}

	public Member(String pw, String name) {
		super();
		this.pw = pw;
		this.name = name;
	}
	
}
