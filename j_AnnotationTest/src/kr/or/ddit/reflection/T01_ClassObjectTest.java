package kr.or.ddit.reflection;

/**
 *	Java Reflection에 대하여 ...
 *	1. 리플렉션은 런타임 시점에 클래스 또는 멤버변수, 메서드, 생성자에 대한 정보를 가져오거나 
 *		수정할 수 있고, 새로운 객체를 생성하거나, 메서드를 실행할 수 있다. 
 *
 *	2. Reflection API는 java.lang.reflect 패키지와 java.lang.Class를 통해 제공.
 *	3. java.lang.Class의 주요 메서드
 *	- getName(), getSuperclass(), getInterface(), getModifiers() 등.
 *	4. java.lang.reflect 패키지의 주요 클래스
 *	- Field, Method, Constructor, Modifier 등
 */

/**
 * (가장 기본적인 Class 오브젝트(오브젝트 내부의 세부정보를 담고 있는)를 생성하기 
 */
public class T01_ClassObjectTest {
	public static void main(String[] args) throws ClassNotFoundException {
		//첫번째 방법 : Class.forName() 메서드 이용
		Class<?> klass = Class.forName("kr.or.ddit.reflection.T01_ClassObjectTest"); 
		//우리가 접근하고자 하는 클래스 명을 forName(파라미터)로 넘겨주고 정보를 얻음. 
		//class는 예약어여서 klass로 사용.
		
		//두번째 방법 : getClass() 메서드 이용
		T01_ClassObjectTest obj = new T01_ClassObjectTest();
		klass = obj.getClass();
		//모든 객체는 getClass를 가지고 있음. 
		
		//세번째 방법 : .class 이용
		klass = T01_ClassObjectTest.class;
		
	}
}
