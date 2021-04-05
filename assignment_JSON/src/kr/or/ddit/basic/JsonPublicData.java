package kr.or.ddit.basic;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JsonPublicData {

	private String svcKey = "Grid_20180109000000000575_1"; // 음식점 업종별 매출액 분포
	private String apiKey = "d0027a85b2edbf32c7f69e6292fe7a42ec8342df2d090c10b7abb0f254b75a20"; // 개인별 발급.
	private String startIdx = "1"; // 요청시작위치
	private String endIdx = "5";
	// 요청종료위치
	private int year = 2015; //년도
	 
	/**
	 * JSON
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	private JSONObject getJSONObject() throws IOException, ParseException {
		URL url = new URL("http://211.237.50.150:7080/openapi/" + apiKey + "/json/" + svcKey + "/" + startIdx + "/"
				+ endIdx + "?YEAR=" + 2015);
		URLConnection urlConnection = url.openConnection();

		System.out.println(url.toString());
		
		JSONParser parser = new JSONParser();

		Object obj = parser.parse(new InputStreamReader(urlConnection.getInputStream()));

		return (JSONObject) obj;
	}

	/**
	 * 시작
	 * 
	 * @throws IOException
	 * @throws ParseException
	 */
	public void start() throws IOException, ParseException {

		JSONObject jsonfile = getJSONObject(); // getJSONObject()를 두번 호출하는 이유 : 한번에 API데이터 안에 있는 길이, 즉 얼만큼 있는지 몰라서 한번
												// 날려서 길이를 알아낸 다음 두번째에 다시 날린다.

		JSONObject rootObj = (JSONObject) jsonfile.get(svcKey); // svcKey : value값

		long totalCnt = (long) rootObj.get("totalCnt"); // 전체 자료수

		endIdx = totalCnt + ""; 
		// -----------------------------------------------------------------------
		// 구해온 전체 재료수를 이용하여 다시 요청함.

		jsonfile = getJSONObject(); // 진짜 호출 (2번째)

		rootObj = (JSONObject) jsonfile.get(svcKey);

		JSONObject result = (JSONObject) rootObj.get("result");
		String code = (String) result.get("code");

		if (code.equals("INFO-000")) { // 정상 상태코드 INFO-000

			JSONArray list = (JSONArray) rootObj.get("row"); // row안에는 json배열이 들어있다.

			@SuppressWarnings("unchecked")
			Iterator<JSONObject> it = list.iterator();

			while (it.hasNext()) {

				JSONObject tempJson = it.next(); // row안에서 꺼내온 json배열

				System.out.println("분류 : " + tempJson.get("CL"));
				System.out.println("사례수 :" + tempJson.get("CASE_CO"));
				System.out.println("5천만원 미만 : " + tempJson.get("FIVE_TH_WON_BELO"));
				System.out.println("5천만원 ~1억미만  : " + tempJson.get("ONE_HM_BELO"));
				System.out.println("1억 ~ 5억 미만  : " + tempJson.get("FIVE_HM_BELO"));
				System.out.println("5억 이상  : " + tempJson.get("FIVE_HM_ABNRML"));
				System.out.println("평균(만원)  : " + tempJson.get("AVRG_AMOUNT"));
				System.out.println("단위  : " + tempJson.get("UNIT"));

				System.out.println("-------------------------");
			}
		}
	}

	public static void main(String[] args) throws IOException, ParseException {
		new JsonPublicData().start();
	}
}
