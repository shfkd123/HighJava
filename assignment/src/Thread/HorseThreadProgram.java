package Thread;

public class HorseThreadProgram {
/*	10마리의 말들이 경주하는 경마 프로그램 작성하기

	말은 Horse라는 이름의 클래스로 구성하고,
	이 클래스는 말이름(String), 등수(int)를 멤버변수로 갖는다.
	그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
	기능이 있다.( Comparable 인터페이스 구현)

	경기 구간은 1~50구간으로 되어 있다.

	경기 중 중간중간에 각 말들의 위치를 >로 나타내시오.
	예)
	1번말 --->------------------------------------
	2번말 ----->----------------------------------
	...

	경기가 끝나면 등수를 기준으로 정렬하여 출력한다.*/
	

	public static void main(String[] args) {
		Horse[] horse = new Horse[] {
			new Horse("1번말", 1),
			new Horse("2번말", 1),
			new Horse("3번말", 1),
			new Horse("4번말", 1),
			new Horse("5번말", 1),
			new Horse("6번말", 1),
			new Horse("7번말", 1),
			new Horse("8번말", 1),
			new Horse("9번말", 1),
			new Horse("10번말", 1)
		};
		
		for (int i = 0; i < horse.length; i++) {
			horse[i].start();
		}
		
		for (Horse h : horse) {
			try {
				h.join(); //10개의 쓰레드가 다 끝날 때까지 기다림
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		System.out.println("경기 끝");
		System.out.println("---------------------");
		System.out.println("\t[경기 결과]");
		System.out.println("순위 : ");
		
		System.out.println("---------------------");
	}

}

class Horse extends Thread {
	private String HorseName;
	private int HorseRank = 1;
      
	//생성자
	public Horse(String horseName, int horseRank) {
		this.HorseName = horseName;
		this.HorseRank = horseRank;
	}
	
	@Override
	public void run() {
		
	}	 
}
