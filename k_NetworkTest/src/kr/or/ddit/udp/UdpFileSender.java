package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpFileSender {
	public static final int DEFAULT_BUFFER_SIZE = 1000;

	public static void main(String[] args) {
		//아이피주소 포트번호 설정
		String serverIp = "127.0.0.1";
		int port = 8888;

		File file = new File("d:/D_Other/Tulips.jpg");

		//소켓 생성
		DatagramSocket ds = null;
		if (!file.exists()) {
			System.out.println("파일이 존재하지 않습니다.");
			System.exit(0); //파일이 없으면 종료
		}

		long fileSize = file.length(); //바이트 단위의 파일 사이즈(길이)
		long totalReadBytes = 0; //총 바이트 길이

		double startTime = 0;
		try {
			ds = new DatagramSocket(); //객체 생성
			
			InetAddress serverAddr = InetAddress.getByName(serverIp); //파라미터로 InetAddress를 요구하기 때문에 이것도 객체 생성
			
			startTime = System.currentTimeMillis(); //현재 시간 정보
			
			String str = "start"; // 전송 시작 알림.
			
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);
			//길이, 상대방 ip주소, 포트번호
			ds.send(dp); //실제로 보낼 패킷 객체 if누군가 패킷을 받고(기다리고)있을 거라고 가정하고 
			//여기서는 start를 보내줌. 받을 사람에게 보낼거라고 그냥 알려준거임.
			
			FileInputStream fis = new FileInputStream(file);
			
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];

			// 총 파일 사이즈 정보를 알려줌.
			str = String.valueOf(fileSize); //파일 사이즈를 String으로 바꿨음. 상대방에게 알려주기 위해
			
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);
			
			ds.send(dp);//상대방에게 파일 사이즈 보냄

			while (true) {
				try {
					Thread.sleep(10); // 패킷 전송간의 간격을 주기 위해서.... 
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int readBytes = fis.read(buffer, 0, buffer.length); //버퍼 사이즈 만큼 읽어옴 현재 버퍼 사이즈 10000
				
				if (readBytes == -1) { //더 이상 읽을 게 없을 때 -1 무한루프 빠져나옴. 
					break;
				}
				
				// 읽어온 파일내용 패킷에 담기
				dp = new DatagramPacket(buffer, readBytes, serverAddr, port);
				//length는 1000byte전체를 읽지 못할 수도 있어서 readBytes
				
				
				ds.send(dp);

				totalReadBytes += readBytes; //읽어온 바이트 수를 누적
				
				System.out.println("In progress : " + totalReadBytes + "/" + fileSize + " Bytes ("
						+ (totalReadBytes * 100 / fileSize) + "%)"); //현재 진행상황 출력
			}
			
			double endTime = System.currentTimeMillis();
			double diffTime = (endTime - startTime) / 1000;
			double transferSpeed = (fileSize / 1000) / diffTime;

			System.out.println("걸린시간 : " + diffTime + " 초");
			System.out.println("평균전송속도 : " + transferSpeed + " KB/s");

			/*str = "end"; // 전송이 완료되었음을 알림. //end라는 스트링을 담아서 상대방에게 보내줌. start처럼 끝났다는 표시 

			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAddr, port);

			ds.send(dp);*/
			System.out.println("전송 완료....");

			fis.close();
			ds.close();
		
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
