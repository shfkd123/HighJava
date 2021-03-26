package List;

import java.util.ArrayList;
import java.util.List;

public class p2__clone_List {

	public static void main(String[] args) {
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("a");
		list1.add("b");
		list1.add("c");
		list1.add("d");
		System.out.println("list1 : " + list1);
		System.out.println();
		
		//객체복사 Shallow Copy list2
		List<String>list2 = list1;
		System.out.println("list2 자료복사(ShallowCopy) : ");
		System.out.println("list2 : " + list2);
		System.out.println("list1 == list2 : " + (list1 == list2));
		System.out.println();
		
		
		//객체복사 Deep Copy list3
		ArrayList list3 = (ArrayList) list1.clone();
		System.out.println("list3 자료복사 (Deep Copy)");
		System.out.println("list3 : " + list3);
		System.out.println("list1 == list3 : " + (list1 == list3));
		System.out.println();
		
		//자료추가 시 Shallow Copy와 Deep Copy
		list1.add("e");
		System.out.println("list1에 'e' 자료추가 ");
		System.out.println("list1" + list1);
		System.out.println("list2" + list2);
		System.out.println("list3" + list3);
		

	}

}
