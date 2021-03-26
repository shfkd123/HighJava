package IO;

import java.io.FileOutputStream;
import java.io.IOException;

public class T06_FileStreamTest {
	public static void main(String[] args) {
		//우리가 작성한 데이터를 어떻게 파일로 관리할 것인지?
		//파일에 저장하고 싶음 => FileOutputStream
		//파일에 출력하기
		
		FileOutputStream fos = null;
		
		try {
			//출력용 OutputStream 객체 생성
			fos = new FileOutputStream("d:/D_Other/out.txt");
			
			for (char ch = 'a'; ch <= 'z'; ch++) { //char 타입의 ch는 2바이트 'a' 1바이트로도 충분히 처리가 가능하다.
				fos.write(ch);
			}
			System.out.println("파일에 쓰기 작업 완료...");
			
			fos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
}
