package IO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

//FileWriter(문자기반 스트림)
public class T07_08_FileWriterTest {
	/* 스트림의 종류
	  - 파일 입출력을 위한 스트림
	   => 파일 시스템에 접근하여 입출력하기 위해 사용
	   => 바이트 단위의 입출력과 문자 단위의 입출력이 존재한다.
	   => FileInputStream, FileOutputStream
	   => FileReader, FileWriter*/
	
	public static void main(String[] args) throws IOException {
		//사용자가 입력한 내용을 그대로 파일로 저장하기 
		
		//콘솔(표준 입력장치)과 연결된 입력용 문자 스트림 생성
		//InputStreamReader => 바이트 기반 스트림을 문자기반 스트림으로 변환해주는 보조 스트림
		//보조 스트림을 사용하려면 항상 기반 스트림이 존재해야 한다
		
		InputStreamReader isr = new InputStreamReader(System.in);
		
		FileWriter fw = null; //파일 출력용 문자 기반 스트림
		
		FileReader fr = null;
		fr = new FileReader("d:/D_Other/testChar.txt");
		int c;
		while((c= fr.read()) != -1) {
			System.out.println((char)c);
		}
		fr.close();

		try {
			//파일 출력용 문자 스트림 객체 생성
			fw = new FileWriter("d:/D_Other/testChar.txt");
			int c1;
			System.out.println("아무거나 입력하세요 ");
			
			while((c1 = isr.read()) != -1) {
				fw.write(c1);
			}
			System.out.println("작업 끝..");
			
			isr.close();
			fw.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
}
