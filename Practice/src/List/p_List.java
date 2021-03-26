package List;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class p_List {

	public static void main(String[] args) {
/*		
		//1.ArrayList 객체 생성
		//List list1 = new ArrayList();
		ArrayList list1 = new ArrayList(); //List는 ArrayList의 상위 타입이므로 형 변환이 자동으로 된다. 
		
		//2.LinkedList 객체 생성
		List list2 = new LinkedList(); //List는 LinkedList의 상위 타입이므로 형 변환이 자동으로 된다.
		
		//3.제네릭스를 이용한 ArrayList 객체 생성
		List<String> list3 = new ArrayList<String>();
		
		//4.제네릭스를 이용한 LinkedList 객체 생성
		List<String> list4 = new LinkedList<String>();
		
		*/
		
		//객체 생성
		List<String> list= new ArrayList<String>();
		System.out.println("객체생성" + list);
		
		//자료 등록
		list.add("a");
		list.add("b");
		list.add("c");
		System.out.println("자료 등록" + list);
		
		//중간 자료 등록
		list.add(0, "d");
		System.out.println("중간 자료 등록" + list);
		
		//자료조회
		System.out.println("자료 조회");
		for (int i = 0; i < list.size(); i++) {
			String data = list.get(i);
			System.out.println("인덱스" + i + " : " + data);
			
		}
		
		//향상된 for문
		System.out.println("자료 조회");
		for(String data : list) {
			System.out.println("자료 : " + data);
		}
		
		//자료 수정
		System.out.println("자료 수정 set()");
		list.set(0, "A");
		list.set(1, "B");
		System.out.println("자료 수정 결과 : " + list);
		
		//자료 삭제
		System.out.println("자료 삭제 remove()");
		list.remove(0);//인덱스 0에 있는 자료 삭제
		System.out.println("자료 삭제 결과 : "+ list);
		
		list.remove("c");
		System.out.println("'c'삭제 결과 : " + list);
		
		//자료 포함 여부
		System.out.println("자료 포함 여부 contains()");
		boolean contains = list.contains("B");
		System.out.println("포함여부 결과 : " + contains);
		
		
		System.out.println("=================기타 사용예제 =================");
		//<기타 사용 예제>
		
		
		//List객체 생성 및 자료등록
		List<String> list1 = new ArrayList<String>();
		list1.add("a");
		list1.add("b");
		list1.add("c");
		list1.add("d");
		
		//자료 추출
		List<String> subList1 = list1.subList(0, 2);
		System.out.println("1.자료 추출");
		System.out.println("list1.subList(0,2) : " + subList1); //0번인덱스부터 2개
		System.out.println("list1 : " + list1);
		System.out.println();
		
		//일괄 자료 등록
		List<String> list2 = new ArrayList<String>();
		list2.add("a");
		list2.add("b");
		list2.add("e");
		list2.add("j");
		list2.add("k");
		
		list1.addAll(list2);
		System.out.println("2. 일괄자료등록");
		System.out.println("list2" + list2);
		System.out.println("list1.addAll(list2)= " + list1); //list1 + list2
		
		//일괄자료포함여부
		List<String> list3 = new ArrayList<String>();
		list3.add("a");
		list3.add("b");
		list3.add("c");
		System.out.println("3. 일괄 자료포함 여부");
		System.out.println("list3" + list3);
		boolean containsAll1 = list1.containsAll(list3); //list1에 list3자료가 존재?
		System.out.println("list1.containsAll(list3) : " + containsAll1);
		System.out.println("list1 : " + list1);
		System.out.println();
		
		//일괄자료삭제
		List<String> list4 = new ArrayList<String>();
		list4.add("a");
		list4.add("b");
		list4.add("c");
		list1.removeAll(list4);
		System.out.println("4. 일괄자료삭제");
		System.out.println("list4 : " + list4);
		System.out.println("list1 : " + list1);
		System.out.println("list1.removeAll(list4) : " + list1); //list1에서 list4와 같은 자료 삭제
		System.out.println();
		
		//공통자료만 남기기
		List<String> list5 = new ArrayList<String>();
		list5.add("d");
		list5.add("e");
		list5.add("c");
		list1.retainAll(list5);
		System.out.println("5. 공통자료만 남기기");
		System.out.println("list1 : " + list1);
		System.out.println("list5" + list5);
		System.out.println("list1.retainAll(list5) : " + list1); //list1에 list5와 같은 자료만 남기고 다 삭제
		
		
		
		
	}

}
