package IO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T03_ByteArrayIOTest {
	/**
	 * 스트림
	 * 스트림은 자료의 입출력을 위해 통로를 만드는 작업을 하는 것.
	 * 
	 * 보조스트림 
	 * 보조스트림은 스트림을 이용하여 사용 목적에 맞게 효율성을 고려한 기능
	 * 보조스트림은 정식 스트림은 아니고, 반드시 스트림을 구현하는 클래스와 함께 상 ㅛㅇ해야 한다.
	 * 
	 * 자바에서 제공하는 IN/OUT stream 인터페이스
	 * 
	 * 바이트 단위 ) IN : InputStream /OUT : OutputStream
	 * 문자 단위 ) IN : Reader/ OUT : Writer
	 * 
	 * 스트림의 종류
	 * - 파일 입출력을 위한 스트림
	 *  => 파일 시스템에 접근하여 입출력하기 위해 사용
	 *  => 바이트 단위의 입출력과 문자 단위의 입출력이 존재한다.
	 *  => FileInputStream, FileOutputStream
	 *  => FileReader, FileWriter
	 *	
	 * - byte[] 또는 char[] 배열 입출력하기 위한 스트림
	 *  => 주로 파일 또는 문자열을 받아 와서 '바이트 배열' 또는 '문자열 배열'을 이용하는 곳에서 사용
	 *  => ByteArrayInputStream, ByteArrayOutputStream
	 *  => CharArrayReader, CharArrayWriter
	 *  
	 *  
	 * - String에 입출력을 위한 스트림
	 *  => String 타입으로 입출력을 위한 클래스이며 직접적인 사용보다 String의 값을 Reader 또는
	 *  	Writer타입의 객체를 사용하는 함수에 사용될 수 있다.
	 *  => StringReader, StringWriter
	 *  
	 * - 쓰레드간의 입출력을 위한 스트림
	 *  => 쓰레드 간의 값을 구현하고자 하는 사용된 클래스
	 *  => PipedInputStream, PipedOutputStream
	 */
	
	public static void main(String[] args) {
		byte[] inSrc = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, };
		byte[] outSrc = null;
		/**
		 * [바이트 배열 전환]
		 * ByteArrayInputStream
		 * ByteArrayOutputStream
		 * 메모리 또는 네트워크로부터 들어오는 바이트 배열을 메모리로 입출력하기 위해 사용
		 */
		
		//스트림 선언 및 객체 생성
		ByteArrayInputStream bais = null; // 스트림 선언
		bais = new ByteArrayInputStream(inSrc); //객체 생성
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int date; //읽어온 자료를 저장할 변수
		//read() 메서드 => byte단위로 자료를 읽어와 int형으로 변환한다.
		//			 => 더 이상 읽어올 자료가 없으면 -i을 반환
		
		while((date = bais.read()) != -1) {
			baos.write(date);
		}
		
		//출력된 스트림 값들을 배열로 변환해서 반환하는 메서드
		 outSrc = baos.toByteArray();
		 System.out.println("inSrc => " + Arrays.toString(inSrc));
		 System.out.println("outSrc => " + Arrays.toString(outSrc));
		 
		 try {
			bais.close();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
	}
}
