package kr.or.ddit.basic;

/*
 *  singleton 패턴 => 객체(인스턴스)를 한 개만 만들어지게 하는 프로그래밍 방법 => 한개만 만드는 이유는 메모리의 사용을 효율적으로 하기 위해 
 *  => 외부에서 new명령을 사용하지 못하고 동일한 객체(인스턴스)를 반환하는 클래스
 *  
 *  -singleton 클래스를 구성하는 방법
 *  1. 자기자신 class의 참조변수를(담을 수 있는) 멤버변수로 선언한다.
 *  (이 변수는 private static으로 지정한다.)
 *  
 *  2. 생성자를 private으로 한다.
 *  (외부에서 생성자에 접근을 못하게 하기 위해서 즉, 외부에서 new명령을 사용하지 못하게 하기 위해서)
 *  
 *  3. 객체(인스턴스)는 내부에서 생성해서 이 생성된 객체를 반환하는 메서드를 만든다.
 *  (이 메서드의 이름은 보통 getInstance()로 지정한다. 이 메서드는 static으로 지정한다)
 *  
 */
public class MySingleton {
	// 자기 자신의 class의 참조값을 저장하는 멤버변수 선언 (static - 공유의 목적)
	private static MySingleton single;

	// 생성자를 private으로 지정한다. => 외부에서 객체생성을 절대 할 수 없다. 
	private MySingleton() {
		System.out.println("생성자 입니다.");
	}

	public static MySingleton getInstance() {
		if (single == null) {
			single = new MySingleton(); //private이어도 나 자신은 나의 생성자 호출 가능
										//null이 아니면 만든 객체가 존재하기 때문에 객체를 리턴함.(새로 생성 X)
		}
		return single;
	}

	// 나머지 내용들은 이 클래스로 처리할 내용들을 기술한다.
	public void displayText() {
		System.out.println("안녕하세요  싱글톤 객체입니다.");
	}
}
