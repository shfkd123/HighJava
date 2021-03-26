package IO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class T04_ByteArrayIOTest {
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4]; //자료를 읽을 때 사용할 배열
		
		ByteArrayInputStream bais = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			while(bais.available() > 0) {
				//실제 읽어온 byte수를 반환한다.
				int len = bais.read(temp);
				
				//배열의 내용 중에서 0번째부터 len개수 만큼 출력한다.
				//(읽고자 하는 바이트 배열, 배열의 인덱스 시작 위치, 배열 인덱스 시작 위치의 길이)
				baos.write(temp, 0, len);
				
				System.out.println("temp : " + Arrays.toString(temp));
			}
			 System.out.println();
			 outSrc = baos.toByteArray();
			 
			 System.out.println("inSrc => "+ Arrays.toString(inSrc));
			 System.out.println("outSrc => "+ Arrays.toString(outSrc));
			 
			 baos.close();
			 bais.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
