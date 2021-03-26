package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

/*
 * 와일드 카드 예제
 * - 뭔지 모른다. ?로 넣어도 정상적인 코드 ?도  타입으로 인식 단지 어떤 타입인지 모르는 것.
 * 
 * <? extends T> => 와일드 카드의 상한 제한. T와 그 자손들만 가능.
 *
 * <? super T> 	 => 와일드 카드의 하한 제한. T와 그 조상들만 가능
 * 	=> ex) List<? super Integer> aaa = new ArrayList<Number>();

 * <?> 		   	 => 모든 타입이 가능<? extends Object>와 동일
 */
public class T06_WildcardTest {

	public static void main(String[] args) {
		//List<?> aaa = new ArrayList<String>();
		//?는 어떤 타입인지 현재 모르는 상황에서 쓰인다. 
		//?에는 무엇이든지 들어올 수 있다. 
		//List<?> aaa = new ArrayList<Integer>();
		//List<?> aaa = new ArrayList<Object>();
		//List<? extends String> aaa = new ArrayList<String>();
		//	=> String을 extends하는  그 어떤 타입

		FruitBox<Fruit> fruitBox = new FruitBox<>(); //과일상자
		FruitBox<Apple> appleBox = new FruitBox<Apple>(); //사과상자
		
		fruitBox.add(new Apple());
		fruitBox.add(new Grape());
		
		appleBox.add(new Apple());
		appleBox.add(new Apple());
		
		//주스 만들기 호출
		Juicer.makeJuice(fruitBox); //과일 상자인 경우에는 아무런 문제없음
		//Juicer.makeJuice(appleBox);
		
	}

}
//과일 클래스
class Fruit {
	private String name; //과일 이름

	public Fruit(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "과일(" + name + ")";
	}
}

//과일을 상속받는 사과 클래스 
class Apple extends Fruit{
	public Apple() {
		super("사과");
	}
}

//과일을 포도받는 사과 클래스 
class Grape extends Fruit{
	public Grape() {
		super("포도");
	}
}

//쥬스 만드는 클래스
class Juicer {
	static void makeJuice(FruitBox<Fruit> box) {
	//static <T> void makeJuice(FruitBox<T> box) 
	//		=> 이렇게 하면 과일 박스 말고도 다른 박스도 올 수 있다. (38번줄)
		String fruitListStr = ""; //과일 목록
		int cnt = 0;
		for (Fruit f : box.getFruitList()) {
			if(cnt == 0) {
				fruitListStr += f;
			}else {
				fruitListStr += "," + f;
			}
			cnt++;
		}
		System.out.println(fruitListStr + "=> 쥬스 완성!!");
	}
}

class FruitBox<T> {
	private List<T> fruitList;
	
	public FruitBox() {
		fruitList = new ArrayList<>();
	}
	
	public List<T> getFruitList(){
		return fruitList;
	}
	
	public void setFruitList(List<T> fruitList) {
		this.fruitList = fruitList;
	}
	
	public void add(T fruit) {
		fruitList.add(fruit);
	}
}