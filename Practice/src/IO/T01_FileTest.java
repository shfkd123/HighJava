package IO;

import java.io.File;
import java.io.IOException;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class T01_FileTest {
	/*
	 * 파일 객체 만들기 연습	
	 * 1. new File(String 파일 또는 경로) 
	 * 
	 */
	public static void main(String[] args) throws IOException {
		File file = new File("d:\\D_Other\\test.txt"); 
		//경로 안에 있는 test.txt를 핸들링하기 위한 File객체 생성
		
		System.out.println("파일명 : " + file.getName());
		System.out.println("파일 여부 : "+ file.isFile());
		System.out.println("디렉토리(폴더)여부) : " + file.isDirectory());
		
		System.out.println("--------------------------------------");
		
		File file2 = new File("d:/D_Other");
		System.out.println(file2.getName() + "은");
		if(file2.isFile()) {
			System.out.println("파일");
		} else if(file2.isDirectory()) {
			System.out.println("디렉토리 (폴더)");
		}
		System.out.println("---------------------------------------");
		
		//2. new File(File parent, String child)
		// => 'parent' 디렉토리 안에 있는 'child'파일 또는 디렉토리를 말한다
		File file3 = new File(file2, "test.txt");
		File file4 = new File("d:/D_Other", "test.txt");
		
		System.out.println(file3.getName() + "의 용량 크기: " + file.length() + "bytes");
		//file.length => 파일의 용량 단위 [bytes]
		
		//경로
		System.out.println("경로 : " + file4.getPath());
		//절대경로
		System.out.println("절대 경로: " + file4.getAbsolutePath());
		//표준 경로
		System.out.println("표준 경로: " + file4.getCanonicalPath());
		//현재 클래스의  URL
		System.out.println("현재 클래스의 URL : " + T01_FileTest.class.getResource("T01_FileTest.class"));
		
		System.out.println("현재 클래스의 절대경로 가져오기 : " + T01_FileTest.class.getResource("").getPath());
		
		//디렉토리(폴더) 만들기
		// 1.mkdir() => File객체의 경로 중 마지막 위치의 디렉토리를 만든다.
		//			 => 중간의 경로가 모두 만들어져 있어야 한다.
		
		// 2.mkdirs() => 중간의 경로가 없으면 중간의 경로도 새롭게 만든 후 마지막 위치의 디렉토리를 만들어 준다.
		
		// mkdir(), mkdirs() 둘다 만들기를 성공하면 true, 실패하면  false 반환
		
		File file5 = new File("d:/D_Other/연습용");
		if(file5.mkdir()) {
			System.out.println(file.getName() + "만들기 성공!");
		}else {
			System.out.println(file.getName() + "만들기 실패!");
		}
		System.out.println();
		
		File file6 = new File("d:/D_Other/test/java/src");
		if(file6.mkdir()) {
			System.out.println(file6.getName() + " 만들기 성공!");
		}else {
			System.out.println(file6.getName() + " 만들기 실패!");
		}
	}	
	
}
