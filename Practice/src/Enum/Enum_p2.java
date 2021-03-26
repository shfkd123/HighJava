package Enum;

public class Enum_p2 {
	public enum Gender {
		MAN(1, "남자"), WOMAN(2, "여자");
		/*
		 * enum 자료에 값을 설정할 수 있다.
		 * 첫 번째 파라미터 : int
		 * 두 번째 파라미터 : String
		 */

		// 외부에서 name, value에 접근할 수 있도록 public 전역변수 생성
		// -> public에서 private으로 변셩한다.
		private int value;
		private String name;
		/*
		 * 외부에서 접근할 수 없도록  private으로 지정한다.
		 */

		// 생성자 파라미터를 전역변수와 연결한다.
		private Gender(int value, String name) {
			this.value = value;
			this.name = name;
		}
		/*
		 * 자료에 입력된 파라미터의 타입과 수가 생성자함수와 일치해야 한다.
		 * 생성자 함수는 private 연산자를 사용한다.
		 * 외부 new 연산을 이용한 객체생성 차단하기 위함이다. 
		 */
		
		//getter 함수 생성
		//전역변수를 private으로 변경 후  setter 함수 없이 getter 함수만 생성한다.
		public int getValue() {return value;}
		public String getName() {return name;}
		/*
		 * 값에 접근할 수 있도록 getter 함수를 정의
		 * 값을 설정할 수 없도록  setter 함수는 정의하지 않음.
		 */
	}
	
	public static void main(String[] args) {
		//Gender.MAN 자료 선택
		Gender g1 = Gender.MAN;
		int value1 = g1.getValue();
		String name1 = g1.getName();
		System.out.println("Gender.MAN value=" + value1);
		System.out.println("Gender.MAN name=" + name1);
		
		// Gender.WOMAN 자료 선택
		Gender g2 = Gender.WOMAN;
		int value2 = g2.getValue();
		String name2 = g2.getName();
		System.out.println("Gender.WOMAN value=" + value2);
		System.out.println("Gender.WOMAN name=" + name2);
	}
	
	//enum 타입의 특징
	/*
	 * - 클래스와 같이 속성을 정의할 수 있으며 함수를 정의할 수 있다.
	 * - 클래스와 같이 'private' 생성자 함수를 정의할 수 있다.
	 *    => new를 이용하여 객체 생성을 할 수 없도록 하기 위함.   
	 */
}
