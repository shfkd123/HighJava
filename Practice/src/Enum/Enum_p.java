package Enum;

public class Enum_p {

	// enum 타입의 정의
	public enum GenderType {
		MAN, WOMAN
	};
	/*
	enum의 생성 방식이며 속성은 콤마(,)로 연결한다.
	속성의 값은 변경이 불가하여 상수처럼 취급이 된다.
	
	*/

	// GenderType 전역변수 생성
	private GenderType genderType;

	// genderType getter 함수생성
	public GenderType getGenderType() {
		return genderType;
	}

	// genderType setter 함수생성
	public void setGenderType(GenderType genderType) {
		this.genderType = genderType;
	}


	public static void main(String[] args) {
		Enum_p e = new Enum_p(); // 객체 생성
		
		//자료의 입력은 '타입.속성명'형태로 입력을 한다.
		e.setGenderType(GenderType.WOMAN);
		//e.setGenderType(GenderType.MAN);
		
		/*
		 * Gender Type의 객체는 'GenderType.속성' 형태로 객체를 호출한다.
		 * new 연산자를 이용하여 객체 생성하지 않는다.
		 * 타입.속성으로 호출되기 때문에 enum은 static의 특성을 갖는다.
		 * enum은 속성의 값이 변경되지 않기 때문에 final의 특성을 갖는다. 
		 */
		
		//비교는  연산자 '=='로 할 수 있다.
		if(e.getGenderType() == GenderType.MAN) {
			System.out.println("객체비교 e.getGenderType() [남자]");
			/*
			 * enum 타입 객체 비교는 '=='연산자를 이용한다.
			 * 참조형으로써 메모리 주소 비교를 한다. 
			 */
		}else if(e.getGenderType() == GenderType.WOMAN) {
			System.out.println("객체비교 e.getGenderType() [여자]");
		}else {
			System.out.println("비교불가");
		}
	}
	
}
