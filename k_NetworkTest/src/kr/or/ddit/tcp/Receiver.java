package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread {
	private DataInputStream dis;
	
	public Receiver(Socket socket) { //한줄 한줄 사용자가 입력한 데이터를 받음. 
		
		try {
			dis = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		while(dis != null) { 
			try {
				System.out.println(dis.readUTF());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
}
