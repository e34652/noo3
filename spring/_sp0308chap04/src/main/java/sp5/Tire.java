package sp5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class Tire {

	private String position;

	public void roll() {
		System.out.println(position + ", ");
		System.out.println("바퀴가 돈다");
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}
