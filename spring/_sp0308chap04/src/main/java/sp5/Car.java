package sp5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class Car {
	private Engine engine;
	@Autowired
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	@Autowired
	private Tire tire1;
	@Autowired
	private Tire tire2;
	@Autowired
	private Tire tire3;
	@Autowired
	private Tire tire4;

	public void run() {
		engine.engineRunning();
		tire1.setPosition("왼쪽 앞");
		tire1.roll();
		tire2.setPosition("오른쪽 앞");
		tire2.roll();
		tire3.setPosition("왼쪽 뒤");
		tire3.roll();
		tire4.setPosition("오른쪽 뒤");
		tire4.roll();
	}
}
