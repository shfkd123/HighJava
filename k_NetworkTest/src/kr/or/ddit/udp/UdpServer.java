package kr.or.ddit.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UdpServer {

	private DatagramSocket socket;
	
	public void start() throws IOException {
		
		//포트 8888번을 사용하는 소켓을 생성한다.
		socket = new DatagramSocket(8888);
		
		//패킷 송수신을 위한 객체변수 선언
		DatagramPacket inPacket, outPacket;
		
		byte[] inMsg = new byte[1]; //패킷 수신용    => 들어오는거 1byte로 만듦 (바이트 데이터를 저장할 배열 )

		byte[] outMsg; 				//패킷 송신용
		
		while(true) {
			//데이터를 수신하기 위한 패킷을 생성한다.
			inPacket = new DatagramPacket(inMsg, inMsg.length);
			
			System.out.println("패킷 수신 대기중 ...");
			
			//패킷을 통해 데이터를 수신(receive)한다.
			socket.receive(inPacket); //(inPacket)패킷을 통해 아이피주소, 포트번호를 알아냄. udp는 비연결성이라 보낼 사람의 아이피주소, 포트번호를 알아야 보낼 수 있음
									//상대방이 패킷을 던져줄 때 까지 블락 상태 
			
			System.out.println("패킷 수신 완료"); //누군가가 보냈다면 "패킷 수신 완료" 문구 뜸.
			
			//수신한 패킷으로 부터 client의 IP주소와 Port번호를 얻어온다.
			InetAddress addr = inPacket.getAddress();
			int port = inPacket.getPort();
			//이 정보를 바탕으로 현재 서버시간을 반환해준다. ⬇ 
			
			//서버의 현재 시간을 시분초 형태([hh:mm:ss])로 반환한다.
			SimpleDateFormat sdf = new SimpleDateFormat("[hh:mm:ss]");
			String time = sdf.format(new Date());
			
			//시간 문자열을 byte배열로 변환한다.
			outMsg = time.getBytes(); //해당 스트링 데이터를 바이트 배열로 변환해준다. (getBytes())
			
			//패킷을 생성해서 client에게 전송(send)한다.
			outPacket = new DatagramPacket(outMsg, outMsg.length, addr, port); //변환한것을 패킷에 담아서 전송한다. 
			socket.send(outPacket); //send : 담겨진 패킷을 실제로 보내주는 역할을 한다.
			//물론 udp이기 때문에 상대방이 receive 상태면 받고, 아니라면 보낸 패킷은 날라감 (비연결성) 
		}
	}
	
	public static void main(String[] args) throws IOException {
		new UdpServer().start();
	}
}
