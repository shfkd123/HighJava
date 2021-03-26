package kr.or.ddit.basic;

public class T18_WaitNotifyTest {
	//WaitNotifyTest - Object의 메서드
	
	/*
	  	wait() 메서드 => 동기화 영역에서 락을 풀고 wait-set영역(공유객체별 존재)으로 이동시킨다.
	  	
	  	notify() 또는 notifAll() 메서드 => wait-set영역에 있는 스레드를 깨워서 실행될 수 있도록 한다.
	  									(notify()는 하나, norifyAll()은 모두 깨운다.)
	  									
		=> wait()과 notify(), notifyAll()메서드는 동기화 영역에서만 실행할 수 있고, 
			Object클래스에서 제공하는 메서드이다.
	  	
	 */
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		Thread th1 = new ThreadA(workObj);
		Thread th2 = new ThreadB(workObj);
		
		th1.start();
		th2.start();
	}
}

//공통으로 사용할 객체
class WorkObject {
	public synchronized void methodA() {
		System.out.println("methodA() 메서드 작업 중....");
		
		notify(); //notify() => 대기하고 있는 놈(B)이 있으면 깨움. 
		
		try {
			wait(); //지금 들어온 쓰레드 A가 대기실로 감.
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public synchronized void methodB() {
		System.out.println("methodB() 메서드 작업 중....");
		
		notify(); //notify() => 대기하고 있는 놈(A)이 있으면 깨움.
		
		try {
			wait(); //지금 들어온 쓰레드 B가 대기실로 감.
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
	
	//A와 B가 서로 반복적으로 대기하고 깨우고 함. 맨 마지막에 대기실로 들어간 쓰레드는 깨어나지 못하고 종료 됨.
}

//workObj의 methodA()메서드만 호출하는 스레드
class ThreadA extends Thread {
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <=10; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료");
	}
}

//workObj의 methodB()메서드만 호출하는 스레드
class ThreadB extends Thread {
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <=10; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료");
	}
}