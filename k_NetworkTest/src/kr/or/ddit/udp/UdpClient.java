package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UdpClient {
	public void start() throws IOException {
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress serverAddress = InetAddress.getByName("127.0.0.1"); //로컬ip주소
		
		// 데이터가 저장될 공간으로 byte배열을 생성한다. (패킷 수신용)
		byte[] msg = new byte[100];
		
		DatagramPacket outPacket = new DatagramPacket(msg, 1, serverAddress, 8888); 
		//보낼 때 1바이트 보낸다. 상대방에게 나의 아이피주소와 포트번호를 알려주기 위해 아무 의미 없는 1바이트를 보냈다.
		
		DatagramPacket inPacket = new DatagramPacket(msg, msg.length);
		//나중에 받을 때 사용
		
		datagramSocket.send(outPacket); //전송
		//1byte를 날리고 기다린다.
		
		//이때 서버에서는 받은 패킷을 분석해서, 서버 시간 정보를 보내준다. 
		
		datagramSocket.receive(inPacket); //수신 
		
		System.out.println("현재 서버 시간 => " + new String(inPacket.getData()));
		//new String(inPacket.getData()) + 바이트 계열의 데이터를 String 타입의 객체로 변환
		
		datagramSocket.close(); //소켓 종료
		
	}
	public static void main(String[] args) throws IOException {
		new UdpClient().start();
	}
}
