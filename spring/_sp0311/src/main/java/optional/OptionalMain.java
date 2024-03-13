package optional;

import java.util.Optional;

class Aaa {
	void print(Bbb bb) {
		bb.print();
	}
}

class Bbb {
	int x;
	public Bbb(int x) {
		this.x = x;
	}
	void print() {
		System.out.println("Bbb" + x);
	}

}

public class OptionalMain {

	public static void main(String[] args) {
		Aaa aa = new Aaa();
		Bbb bb = new Bbb(100); // null일 때와 비교
//		if (bb == null) {
//			System.out.println("bb = null");
//		} else {
//			aa.print(null);
//		}
		Optional<Bbb> bb1 = Optional.ofNullable(bb);
		System.out.println(bb1);
		aa.print(bb1.orElseGet(()->new Bbb(0)));
	}
}
