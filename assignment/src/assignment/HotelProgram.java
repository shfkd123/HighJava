package assignment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class HotelProgram {
	static Scanner in = new Scanner(System.in);

	private Map<String, Hotel> hotelMap; // Map 객체 변수 생성
	
	public HotelProgram() {
		hotelMap = new HashMap<String, Hotel>();
	}
	public static void main(String[] args) {
		new HotelProgram().start();
	}

	private void start() {
		System.out.println("================");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("================");
		
		while (true) {
			System.out.println("======================================");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1.체크인\t2.체크아웃\t3.객실상태\t4.업무종료");
			System.out.println("======================================");
			int action = in.nextInt();
			switch(action) {
			case 1: checkin(); break;
			case 2: checkout(); break;
			case 3: room_state(); break;
			case 4: System.out.println("호텔 문을 닫았습니다.업무 종료합니다."); System.exit(0); break;
			default :
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			}
		}
	}
	//객실상태
	private void room_state() {
		Set<String> keySet = hotelMap.keySet();
		System.out.println("=============================");
		System.out.println("번호\t방번호\t투숙객");
		System.out.println("=============================");
		// keySet의 size가 0이면 Map에 들어있는 번호가 없다.
		if (keySet.size() == 0) {
			System.out.println("현재 체크인된 방이 없습니다.");
		} else {
			Iterator<String> it = keySet.iterator();
			int cnt = 0;
			while (it.hasNext()) {
				cnt++;
				String name = it.next();
				Hotel h = hotelMap.get(name);
				System.out.println(" " + cnt + "\t" + h.getRoom_num() + "\t" + h.getName());
			}
		}
		System.out.println("==================================");
		System.out.println(">>객실 상태");
	}
	//체크아웃
	private void checkout() {
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.println("방번호 입력: ");
		String num = in.next();
		
		// remove(key) => 삭제 성공하면 삭제된 value값을 반환하고 실패하면 null반환
		if (hotelMap.remove(num) == null) {
			System.out.println(num + "방에는 체크인한 사람이 없습니다.");
		} else {
			System.out.println(num + "방이 체크아웃이 되었습니다.");
		}
	}
	
	//체크인
	private void checkin() {
		System.out.println("어느방을 체크인 하시겠습니까?");
		
		System.out.println("방번호 입력: ");
		String num = in.next();
		
		//이미 등록방이 있는지 검사
		if(hotelMap.get(num) != null) {
			System.out.println(num + "방에는 이미 사람이 있습니다.");
			return;
		}
		
		System.out.println("체크인 하실 고객님의 성함을 입력해주세요: ");
		in.nextLine();
		String name = in.nextLine();
		
		hotelMap.put(num, new Hotel(num, name)); 
		System.out.println("체크인 완료되었습니다!");
	
	}

}
class Hotel{
	private String room_num; //방번호
	private String name; //투숙객 이름
		
	//생성자
	public Hotel(String room_num, String name) {
		this.room_num = room_num;
		this.name = name;
	}
	
	public String getRoom_num() {
		return room_num;
	}
	public void setRoom_num(String room_num) {
		this.room_num = room_num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}