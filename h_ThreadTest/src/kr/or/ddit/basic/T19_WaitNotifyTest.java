package kr.or.ddit.basic;

public class T19_WaitNotifyTest {
	public static void main(String[] args) {
		
		DataBox dataBox = new DataBox();
		
		ProducerThread pth = new ProducerThread(dataBox);
		ConsumerThread cth = new ConsumerThread(dataBox);
		
		pth.start();
		cth.start();
	}
}

// 데이터를 공통으로 사용하는 클래스
class DataBox {
	private String data;
	
	//data가 null이 아닐 때 data값을 반환하는 메서드
	public synchronized String getData() {
		if(data == null) { //데이터가 세팅되지 않음. 꺼내올 데이터가 없음.
						  //꺼내올 데이터가 생길 때까지 기다림
			try {
				wait(); 
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		String returnData = data;
		System.out.println("읽어온 데이터 : " + returnData);
		data =  null; //꺼내왔단 의미로 데이터를 null로 바꿈. 
		System.out.println(Thread.currentThread().getName()
							+ " notify() 호출");
		notify();
		
		return returnData; //꺼내온 데이터로 리턴
	}
	
	//data가 null일 경우에만 자료를 세팅하는 메서드
	public synchronized void setData(String data) {
		if(this.data != null) {//데이터가 있을때 != null 
			try {
				wait(); //데이터가 있으면 세팅할 필요가 없어서 wait()을 호출
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		this.data = data; //데이터가 없으면 세팅함.
		System.out.println("세팅한 데이터 : " + this.data);
		System.out.println(Thread.currentThread().getName()
							+ " notify() 호출");
		notify();
	}
}

//dataBox에 있는 데이터를 세팅만 하는 스레드 
class ProducerThread extends Thread {
	private DataBox dataBox;
	
	public ProducerThread(DataBox dataBox) {
		super("ProducerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			String data = "Data-" + i; //data 생성
			System.out.println("dataBox.setData(" + data + ") 호출");
			dataBox.setData(data);
		}
	}
}

//데이터를 읽어만 오는 스레드
class ConsumerThread extends Thread {
	private DataBox dataBox;
	
	public ConsumerThread(DataBox dataBox) {
		super("ConsumerThread");
		this.dataBox = dataBox;
	}
	
	@Override
	public void run() {
		for (int i = 1; i <= 10; i++) {
			String data = dataBox.getData();
			System.out.println("dataBox.getData() : " + data);
		}
	}
}