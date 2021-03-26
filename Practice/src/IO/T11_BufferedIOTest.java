package IO;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class T11_BufferedIOTest {
	public static void main(String[] args) {
		
		//입출력 성능 향상을 위해서 버퍼를 이용하는 보조 스트림
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			fos = new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			//버퍼의 크기가 5인 스트림 생성
			bos = new BufferedOutputStream(fos, 5);
			for (int i = '1'; i < '9'; i++) {
				bos.write(i);
			}
			
			bos.flush(); //작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력
			bos.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
}
