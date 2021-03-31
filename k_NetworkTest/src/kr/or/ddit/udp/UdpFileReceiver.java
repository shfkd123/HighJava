package kr.or.ddit.udp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

//파일 받는 놈.
public class UdpFileReceiver {
	public static final int DEFAULT_BUFFER_SIZE = 1000; //버퍼 사이즈 1000
	
	public static void main(String[] args) throws IOException {
		int port = 8888;
		long fileSize  = 0;
		long totalReadBytes = 0;
		
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		
		int readBytes = 0;
		System.out.println("파일 수신 대기중 ...");
		
		DatagramSocket ds = new DatagramSocket(port);
		FileOutputStream fos = null;
		fos = new FileOutputStream("d:/D_Other/aaa.jpg"); 
		
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp); //맨 처음 바이트 배열로 start가 전달 옴. 
		
		String str = new String(dp.getData()).trim();
		
		if(str.equals("start")) { //sender에서 전송을 시작한 경우...
			dp = new DatagramPacket(buffer, buffer.length);
			
			ds.receive(dp); //파일 사이즈 전달 옴.
			
			str = new String(dp.getData()).trim(); //사이즈가 string 타입으로 저장
			fileSize = Long.parseLong(str); //다시 파일 사이즈를 long타입으로 저장
			
			double startTime = System.currentTimeMillis();
			
			while(true) {
				ds.receive(dp); //패킷의 파일 데이터 전달 받음. 
				str = new String(dp.getData()).trim();
				readBytes = dp.getLength(); //현재 읽어온 바이트 길이
				fos.write(dp.getData(), 0, readBytes);
				totalReadBytes += readBytes;
				
				System.out.println("In progress : " + totalReadBytes + "/" + fileSize + " Bytes ("
						+ (totalReadBytes * 100 / fileSize) + "%)"); //현재 진행상황 출력
				
				if(totalReadBytes >= fileSize) { //전체 파일 사이즈가 같거나 누적시킨 사이즈가 크면 다 읽은 것. 
					break; //무한 루프 빠져나옴. 
				}
			}
			
			double endTime = System.currentTimeMillis();
			double diffTime = (endTime - startTime) / 1000;
			double transferSpeed = (fileSize / 1000) / diffTime;

			System.out.println("걸린시간 : " + diffTime + " 초");
			System.out.println("평균전송속도 : " + transferSpeed + " KB/s");
			
			System.out.println("수신 처리 완료 ...");
			
			fos.close();
			ds.close();			
		}else {
			System.out.println("수신 처리 불가!!!");
			fos.close();
			ds.close();
		}
	}
}
