package kr.or.ddit.basic;

//어노테이션 사용 예제

//Reflection API : ex)기존의 member라는 클래스 -> 객체 생성 그러나 멤버 클래스에 대한 객체 정보는 모름. 접근만 가능
//										  -> 이때 Reflection API를 사용하면 객체의 정보를 모르는 상태일때 정보를 얻을 수 있다. 


public class Service {
	
	@PrintAnnotation
	public void method1() {
		System.out.println("메서드1에서 출력되었습니다.");
	}
	
	//value만 있는 경우에는 value는 생략 가능
	@PrintAnnotation(value = "%")
	public void method2() {
		System.out.println("메서드2에서 출력되었습니다.");
	}
	
	//count값까지 있기때문에 생략 불가 
	@PrintAnnotation(value = "#", count = 30)
	public void method3() {
		System.out.println("메서드3에서 출력되었습니다.");
	}
}
