package IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class T05_FileStreamTest {
	public static void main(String[] args) throws IOException {
		/*- 파일 입출력을 위한 스트림
		   => 파일 시스템에 접근하여 입출력하기 위해 사용
		   => 바이트 단위의 입출력과 문자 단위의 입출력이 존재한다.
		   => FileInputStream, FileOutputStream
		   => FileReader, FileWriter*/
		
		//FileInputStream 객체를 이용한 파일 내용 읽기
		FileInputStream fis = null; // 변수 선언
		
		//방법 1 (파일 정보를 문자열로 지정하기)
		//fis = new FileInputStream("d:/D_Other/test.txt"); //생성
		
		//방법 2 (파일 정보를 File객체를 이용하여 지정하기)
		File file = new File("d:/D_Other/test.txt");
		fis = new FileInputStream(file); //생성
		
		int c; //읽어온 데이터를 저장할 변수
		
		while((c = fis.read()) != -1) {
			System.out.println((char)c);
		}
		
		fis.close(); 
	}
}
