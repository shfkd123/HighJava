package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * 소켓을 통해서 메시지를 보내는 역할을 담당한다.
 */
public class Sender extends Thread {
	private Scanner scan;
	private String name; //채팅의 id느낌
	private DataOutputStream dos; //문자열을 utf-8로 보내기 위해 보조스트림 사용
	
	public Sender(Socket socket) {
		name = "[" + socket.getInetAddress() + " : " + socket.getLocalPort() + "]";
		scan = new Scanner(System.in);
		
		try {
			dos = new DataOutputStream(socket.getOutputStream()); //getOutputStream -> socket에 있는 기반 스트림 
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(dos != null) { 
			try {
				dos.writeUTF(name + " >>> " + scan.nextLine()); //채팅을 치고 엔터를 누를 때 마다 writeUTF을 계속 날린다.(한 줄 한줄) 
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
