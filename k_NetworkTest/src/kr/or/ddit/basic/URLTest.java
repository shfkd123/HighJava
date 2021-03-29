package kr.or.ddit.basic;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class URLTest {
	public static void main(String[] args) throws MalformedURLException, URISyntaxException  {
		//URL 클래스 => 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 주소를 관리하는 클래스 
		
		//http://ddit.or.kr:80/index.html?ttt=123
		URL url = new URL("http", "ddit.or.kr", 80, "/main/index.html?ttt=123#kkk");
		System.out.println("전체 URL 주소 : " + url.toString());
		
		System.out.println("protocol : " + url.getProtocol());
		System.out.println("host : " + url.getHost());
		System.out.println("query : " + url.getQuery());
		System.out.println("file : " + url.getFile()); //요청한 파일정보 + 쿼리정보
		System.out.println("path : " + url.getPath()); //파일에서 쿼리정보 제거한 부분
		System.out.println("port : " + url.getPort()); //포트번호
		System.out.println("ref : " + url.getRef()); //참조(레퍼런스)
		System.out.println();
		
		System.out.println(url.toExternalForm()); //url 객체의 내용을 toExternalForm() 형태로 출력
		System.out.println(url.toString()); //url 객체의 내용을 toString() 형태로 출력
		System.out.println(url.toURI().toString()); //url 객체의 내용을 URI로 바꿔서 toString() 형태로 출력
	}
}
