package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
	public static void main(String[] args) throws UnknownHostException {
		
		//ip => 네트워크주소 / 호스트주소
		//URI(User Resource Identifier) 식별자 -> 중복X
		//URL(User Resource Locator)을 알고 있으면 해당 리소스로 접근해서 정보를 가져올 수 있음
		//InetAddress 클래스 => IP주소를 다루기 위한 클래스
		
		//naver사이트의 ip정보 가져오기
		System.out.println("=========naver사이트의 ip정보 가져오기=========");
		InetAddress naverIp = InetAddress.getByName("www.naver.com"); //-->도메인 네임. 
		//getByName : 이름  객체를 만들 때 파라미터로 원하는 이름을 셋팅하면  이름에 해당하는 inetAddress를 알아서 반환
		System.out.println("Host Name => " + naverIp.getHostName()); //호스트 이름 
		System.out.println("Host Address => " + naverIp.getHostAddress());
		
		System.out.println();
		
		//자기 자신 컴퓨터의  IP정보 가져오기
		System.out.println("=========자기 자신 컴퓨터의  IP정보 가져오기=========");
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("내 컴퓨터의 Host Name => " + localIp.getHostName()); //-->내 자신의 호스트 이름
		System.out.println("내 컴퓨터의 Host Address => " + localIp.getHostAddress());
		
		System.out.println();
		
		//ip주소가 여러개인 호스트의 정보 가져오기 => 서버 여러개 분산 해 놓았을 수도 있음! 
		System.out.println("=========ip주소가 여러개인 호스트의 정보 가져오기=========");
		InetAddress[] naverIps = InetAddress.getAllByName("www.naver.com");
		//getAllByName 이름에 해당하는거 다 가져와라! 배열타입으로 리턴
		for (InetAddress nIp : naverIps) {
			System.out.println(nIp.toString());
		}
	}
}
