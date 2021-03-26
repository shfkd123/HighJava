package kr.or.ddit.basic;

public class T02_ThreadTest {
	public static void main(String[] args) {
		//멀티 쓰레드 프로그램 방식
		//업무를 동시에 처리하기 때문에 동시에 출력된다!!! 
	
		//방법1 : Thread클래스를 상속한 class의 인스턴스를 생성한 후 
		//		이 인스턴스의 start()메서드를 호출한다.
		
		MyThread1 th1 = new MyThread1(); //쓰레드 객체 생성
		th1.start(); //쓰레드 구동 메소드 -> 독자적인 쓰레드로 실행됨.
		
		//방법2 : Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후
		//		이 인스턴스를 Thread객체의 인스턴스를 생성 할 때 생성자의 
		// 		매개변수로 넘겨준다.
		// 		이때 생성된 Thread객체의 인스턴스의 start()메서드를 호출한다.
		MyThread2 r1 = new MyThread2();
		Thread th2 = new Thread(r1);
		th2.start();
		
		//방법3 : 익명클래스를 이용하는 방법
		//Runnable 인터페이스를 구현한 익명클래스를 Thread 인스턴스를 생성할 때 매개변수로 넘겨준다.
		Thread th3 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i < 200; i++) {
					System.out.print("@");
				}
				try {
					// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
					// 시간은 밀리세컨트 단위를 사용한다.
					// 즉, 1000은 1초를 의미한다.
					Thread.sleep(100);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}

			}

		});
		th3.start();
		System.out.println("main메서드 작업 끝....");
	}
}

class MyThread1 extends Thread{
	@Override
	public void run() { //run()은 Thread객체가 꼭 가져야 할 메소드 
		for (int i = 1; i <=200; i++) {
			System.out.print("*");
			
			try {
				// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(100); //0.1

			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}

//자바의 언어 특성 상 상속을 받을 때 멀티 상속이 불가능하다.
//ex) Child가 Parent를 상속을 받은 후에는 Thread를 상속 받지 못한다.
class MyThread2 implements Runnable {
	@Override
	public void run() {
		for (int i = 1; i <=200; i++) {
			System.out.print("$");
			
			try {
				// Thread.sleep(시간) => 주어진 시간동안 작업을 잠시 멈춘다.
				// 시간은 밀리세컨드 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(100);

			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
