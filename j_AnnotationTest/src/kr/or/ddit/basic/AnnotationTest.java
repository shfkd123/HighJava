package kr.or.ddit.basic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
	//출력 결과의 순서는 항상 다름 
	//어노테이션을 사용하는 이유 : 자바 (현재 환경)에서 바로 정의 가능
	//어노테이션의 단점: 자바 소스내에서 정보를 가져오고, 정의하니까 수정할 때마다 컴파일 필요 
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
		//PrintAnnotation의 static변수값 출력하기
		System.out.println(PrintAnnotation.id);
		
		//Reflection API를 이용하여 메서드 정보 추출 및 실행 
		Method[] methodArr = Service.class.getDeclaredMethods();
		
		for (Method m : methodArr) {
			System.out.println(m.getName()); //메서드명 출력
			
			//어노테이션 객체 가져오기
			PrintAnnotation printAnn = m.getDeclaredAnnotation(PrintAnnotation.class);
			
			for (int i = 0; i < printAnn.count(); i++) { //count값 만큼...
				System.out.print(printAnn.value()); //value값 출력
			}
			System.out.println(""); //줄바꿈 처리
			
			//방법1) new라는 키워드를 이용해서 객체 생성  
			//m.invoke(new Service()); //메서드 실행
			
			//방법2) reflection API를 이용해서 객체 생성 
			Class<Service> clazz = Service.class;
			Service service = (Service) clazz.newInstance();
			
			m.invoke(service);
		}
	}
}
