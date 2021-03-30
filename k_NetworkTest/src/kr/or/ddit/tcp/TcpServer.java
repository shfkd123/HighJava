package kr.or.ddit.tcp;

//상대방에게 보내기 OutputStream
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static void main(String[] args) throws IOException {

		//TCP 소켓 통신을 하기 위해 ServerSocket 객체 생성
		ServerSocket server = new ServerSocket(7777); //7777 포트번호 ==> 1024까지는 예약어 이후의 번호는 사용 가능(65000(int갑의 최대)7777은 거의 쓰지 않았을 것같아서 사용.)
		System.out.println("서버가 접속을 기다립니다.");
		
		//accept()메서드는 client에서 연결 요청이 올 때까지 계속 기다린다.
		//연결 요청이 오면 Socket객체를 생성해서 Client의 Socket과 연결한다.
		Socket socket = server.accept(); //클라이언트의 요청을 받아들임 (accept) ->  client에서 연결 요청이 올 때까지 계속 기다린다.
										//정상적으로 소켓이 생성되면 리턴 
		
		//이 이후는 클라이언트와 연결된 후의 작업을 진행하면 된다.
		System.out.println("접속한 클라이언트 정보");
		System.out.println("주소 : " + socket.getInetAddress()); //socket객체를 통해 주소를 알아냄.
		
		//client에 메시지 보내기
		
		//Outputstream객체를 구성하여 전송한다.
		//접속한 Socket의 getOutputStream()메서드를 이용하여 구한다.
		OutputStream out = socket.getOutputStream(); //데이터를 주고 받는것은 stream을 통해 한다. 
		
		DataOutputStream dos = new DataOutputStream( out); //보조스트림 -기본 타입(int, double, float...등등) 의 사이즈를 잘 조절하기 위해 
		dos.writeUTF("어서 오세요..."); //메시지 보내기
		System.out.println("메시지를 보냈습니다.");
		
		dos.close();
		
		server.close();
	}
}
