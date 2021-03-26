package IO;

import java.io.FileOutputStream;
import java.io.IOException;

public class T13_DataIOStreamTest {
	public static void main(String[] args) {
		try {
			
			FileOutputStream fos = new FileOutputStream("d:/D_Other/test.dat");
			
		}catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
