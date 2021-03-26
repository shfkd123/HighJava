package kr.or.ddit.basic;

public class T03_GenericClassTest {
	/*
	 * 제너릭 클래스를 만드는 방법
	 * 
	 * 형식) 
	 *	class 클래스명<제네릭 타입글자> {
	 *		제네릭 타입글자 변수명;// 변수 선언에 제너릭을 사용할 경우
	 *			...
	 *		제너릭타입글자 메서드명() { //반환값이 있는 메서드에서 사용 
	 *			...
	 *			return값;
	 *		}
	 *		...
	 *	}
	 *
	 *---제너릭 타입 글자---
	 *T => Type
	 *K => Key
	 *V => Value
	 *E => Element(자료구조에 들어갑는 것들을 나타낼 때 사용)
	 */
	public static void main(String[] args) {
		NonGenericClass ng1 = new NonGenericClass();
		ng1.setVal("가나다라");
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		
		String rtnNg1 = (String) ng1.getVal(); 
		// 값을 읽어올 땐  Object로 읽어오기 때문에  String 타입으로 다운캐스팅 해줘서 가져와야 한다. 
		// 제네릭을 사용하지 않으면, 캐스팅을 항상 해줘야 한다. 
		System.out.println("문자열 반환값 rtnNg1 => " + rtnNg1);
		
		Integer irtnNg2 = (Integer) ng2.getVal();
		System.out.println("문자열 반환값 rtnNg2 => " + irtnNg2);
		System.out.println();
		
		//제네릭 타입 String으로 지정
		MyGeneric<String> mg1 = new MyGeneric<>();
		//제네릭 타입 Integer으로 지정
		MyGeneric<Integer> mg2 = new MyGeneric<Integer>();
		
		mg1.setVal("우리나라");
		mg2.setVal(500);
		
		rtnNg1 = mg1.getVal(); //따로 형변환을 하지 않아도 값을 읽어올 수 있다. 
		irtnNg2 = mg2.getVal();
		
		System.out.println("제네릭 문자열 반환값: " + rtnNg1);
		System.out.println("제네릭  정수 반환값: " + irtnNg2);
		
	}
}
//제네릭이 존재하지 않는  class
class NonGenericClass {
	private Object val;

	public Object getVal() {
		return val; //return type Object
	}

	public void setVal(Object val) {
		this.val = val;
	}
}

//Type 제네릭 존재 클래스 
class MyGeneric<T> {
	private T val;

	public T getVal() {
		return val;
	}

	public void setVal(T val) {
		this.val = val;
	}
}
