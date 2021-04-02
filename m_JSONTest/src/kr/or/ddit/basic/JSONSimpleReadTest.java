package kr.or.ddit.basic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONSimpleReadTest {
	
	public static void main(String[] args) throws IOException, ParseException {
		
		FileReader fr = new FileReader("d:/D_Other/myJsonFile.txt"); //문자기반 스트림을 읽기위해 FileReader
		
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(fr); //파싱의 결과를 obj에게 넣어준다.(JSON형태의 파일)
		JSONObject jsonFile = (JSONObject) obj;
		
		String name = (String)jsonFile.get("name");
		String job = (String)jsonFile.get("job");
		long age = (long)jsonFile.get("age");
		String addr = (String) jsonFile.get("addr");
		
		System.out.println("name : " + name);
		System.out.println("job : " + job);
		System.out.println("age : " + age);
		System.out.println("addr : " + addr);
		
		JSONArray list = (JSONArray) jsonFile.get("singerList");
		Iterator<JSONObject> it = list.iterator();
		
		JSONObject singer;
		while(it.hasNext()) {
			singer = it.next();
			System.out.printf("이름: %s, \t성별: %s, \t나이:%d\n", 
								singer.get("name"),
								singer.get("gender"),
								singer.get("age"));
		}
	}
}
