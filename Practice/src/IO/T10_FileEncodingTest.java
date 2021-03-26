package IO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class T10_FileEncodingTest {
	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in); // 콘솔로부터 문자 변환

		// 파일 출력용
		FileOutputStream fos1 = new FileOutputStream("d:/D_Other/out_utf8.txt");
		FileOutputStream fos2 = new FileOutputStream("d:/D_Other/out_ansi.txt");

		// 보조스트림 객체 생성
		OutputStreamWriter osw1 = new OutputStreamWriter(fos1, "utf-8");
		OutputStreamWriter osw2 = new OutputStreamWriter(fos1, "ms949");
		int c;

		System.out.println("아무거나 입력하세요.");

		while ((c = isr.read()) != -1) {
			osw1.write(c);
			osw2.write(c);
		}

		System.out.println("작업 완료...");

		isr.close(); // 보조스트림을 닫으면, 기반 스트림을 자동으로 닫힌다.
		osw1.close();
		osw1.close();
	}
}
