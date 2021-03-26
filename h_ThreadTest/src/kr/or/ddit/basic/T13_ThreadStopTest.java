package kr.or.ddit.basic;

public class T13_ThreadStopTest {
/**
 * Thread의 stop()메서드를 호출하면 스레드가 바로 멈춘다.
 * 이때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어서 
 * 나중에 실행되는 프로그램에 영향을 줄 수 있다. 
 * 그래서 현재는 stop()메서드는 비추천(Deprecated)으로 되어 있다.
 */	
	public static void main(String[] args) {
		ThreadStopEx1 th = new ThreadStopEx1();
		th.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		//th.stop(); //비추천 -실행중인 컴퓨터의 코드를 그냥 뽑아버린 것과 같다.
		th.setStop(true); //우리가 stop 시키기 위해 만든 메서드
						 //상태 플래그 값을 이용한 종료방법
		
		//interrupt() 메서드를 이용한 스레드 멈추기
		ThreadStopEx2 th2 = new ThreadStopEx2();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		th2.interrupt(); //인터럽트 호출
		//스레드는 interrupt()메서드를 가지고 있음. 메인스레드가 th2 메서드 방해
	}
}

class ThreadStopEx1 extends Thread {
	private boolean stop;


	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	public void run() {   
		          
		while(!stop) { //stop이 true일때까지 
			System.out.println("스레드 처리 중...");
		}
		System.out.println("자원 정리 중...");
		System.out.println("실행 종료.");
	}	
}

//interrupt()메서드를 이용하여 스레드를 멈추게 하는 방법
class ThreadStopEx2 extends Thread {
	
	/*@Override
	public void run() {
		
		 * 방법 1 => sleep()메서드나 join()메서드 등을 사용했을 때 interrupt()메서드를 
		 * 			호출하면 InterruptedException이 발생한다.
		 
		try {//무한루프 => 예외가 발생하면서 무한루프에서 빠져나옴. 
			while(true) {
				System.out.println("스레드 처리 중....");
				Thread.sleep(1); //예외가 발생할 수 있는 부분
			}
		}catch (InterruptedException ex) {} //누가 너 자는동안 깨울 수도 있어! 라는 예외
		System.out.println("자원 처리 중...");
		System.out.println("실행 종료.");
	}*/
	
	//방법 2 => interrupt() 메서드가 호출되었는지 검사하기
	@Override
	public void run() {
		while(true) {//걸렸으면 빠져나오고, 안걸렸으면 안빠져 나오고 수동으로 검사!
			System.out.println("스레드 처리 중...");
			
			/*//검사방법1 => 스레드의 인스턴스 객체용 메서드를 이용하는 방법
			if(this.isInterrupted()) { //interrupt()메서드가 호출되면  true
				//this ->현재 스레드 
				System.out.println("인스턴스용 isInterrupted()");
				break;                
			}*/
			
			//검사방법2 => 스레드의 정적 메서드를 이용하는 방법 (누구나 호출 가능) 
			if(Thread.interrupted()) { //interrupted()가 호출되면 true
				//정적 메서드를 호출하면 값이 한번 true된다음 다시 false로 되돌아 간다.(다시 호출하면 인터럽트가 안걸린것처럼 행동)
				System.out.println("정적 메서드 interrupted()");
				break;
			}
		}
		System.out.println("자원 처리 중...");
		System.out.println("실행 종료.");
		
	}	
} 