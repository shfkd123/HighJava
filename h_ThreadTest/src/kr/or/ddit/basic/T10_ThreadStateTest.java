package kr.or.ddit.basic;

import javax.net.ssl.ExtendedSSLSession;

public class T10_ThreadStateTest {
/**
 * <스레드 상태>
 * NEW : 스레드가 생성되고 아직 start()가 호출되지 않은 상태
 * RUNNABLE : 실행 중 또는 실행 가능한 상태
 * BLOCKED : 동기화 블럭에 의해서 일시 정지된 상태 (Lock이 풀릴때까지 기다리는 상태)
 * WAITING, TIMED_WAITING : 스레드의 작업이 종료되지는 않았지만 실행가능하지 않은 일시정지 상태. 
 * 							TIMED_WAITING은 일시정지 시간이 지정된 경우임.
 * TERMINATED : 스레드의 작업이 종료된 상태
 */
	
	public static void main(String[] args) {
		//스레드 상태  출력 StatePrintThread 이 클래스가 볼 스레드는 TargetThread
		StatePrintThread spt = new StatePrintThread(new TargetThread());
		spt.start();
	}
}

//스레드의 상태를 출력하는 클래스 (이 클래스도 스레드로 작성 )
class StatePrintThread extends Thread{
	private Thread targetThread; //상태를 출력할 스레드가 지정될 변수
	
	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		while(true) {
			//Thread의 상태 구하기 (getState() 메서드 이용)
			Thread.State state = targetThread.getState();
			//Thread.State 타입은 enum 타입 
			System.out.println("타겟 스레드의 상태값: " + state);
			
			// NEW 상태인지 검사
			if(state == Thread.State.NEW) {
				//실행하기 전인 NEW (생성)상태이면 start 메소드 호출해서 실행 
				targetThread.start();
			}
			
			// 종료(TERMINATED) 상태인지 검사
			if(state == Thread.State.TERMINATED) {
				break; //쓰레드의 상태는 무조건 순환
			}
			
			try {
				Thread.sleep(200);//TargetThread의 상태를 알기위한 잠시 시간 sleep
				//TIMED_WAITING상태로 0.5초 동안 일시정지 
			}catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}


//Target 스레드
class TargetThread extends Thread {
	@Override
	public void run() {
		for (int i = 1; i < 1000000000L; i++) {} //시간 지연용
		try {
			Thread.sleep(1500);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}	
		for (int i = 1; i < 1000000000L; i++) {} //시간 지연용
	}
}