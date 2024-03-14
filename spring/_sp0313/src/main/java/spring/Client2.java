package spring;

public class Client2 {
	
	private String host;
	
	public void send() {
	System.out.println("Client2.send() to " + host);
	}
	public void setHost(String host) {
		System.out.println("setHost2 실행");
		this.host = host;
	}

	public void close() {
	System.out.println("c2 close() 실행");
	}
	
	public void connect() {
		System.out.println("c2 connect() 실행");
	}

}
