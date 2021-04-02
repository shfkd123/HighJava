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
	private String restaurant_Type = "치킨전문점"; //알고싶은 치킨전문점의 매출액
	 
	

	/**
	 * JSON
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	private JSONObject getJSONObject() throws IOException, ParseException {
		URL url = new URL("http://211.237.50.150:7080/openapi" + apiKey + "/json/" + svcKey + "/" + startIdx + "/"
				+ endIdx + "?RESTAURANT_TYPE=" + restaurant_Type);
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

		long totalCnt = (long) rootObj.get("totalCnt"); // 전체 레시피 재료 수

		endIdx = totalCnt + ""; // 레시피 재료 마지막 순번 설정
		// -----------------------------------------------------------------------
		// 구해온 전체 재료수를 이용하여 다시 요청함.

		jsonfile = getJSONObject(); // 진짜 호출 (2번째)

		rootObj = (JSONObject) jsonfile.get(svcKey);

		JSONObject result = (JSONObject) rootObj.get("result");
		String code = (String) result.get("code");

		if (code.equals("INFO-000")) { // 정상 상태이면...

			JSONArray list = (JSONArray) rootObj.get("row"); // row안에는 json배열이 들어있다.

			// for(Object tempObj : list) {
			//
			// JSONObject tempJson = (JSONObject) tempObj;
			//
			// System.out.println("순번 : " + tempJson.get("ROW_NUM"));
			// System.out.println("레시피ID : " + tempJson.get("RECIPE_ID"));
			// System.out.println("재료명 : " + tempJson.get("IRDNT_NM"));
			// System.out.println("용량 : " + tempJson.get("IRDNT_CPCTY"));
			// System.out.println("재료구분 : " + tempJson.get("IRDNT_TY_NM"));
			//
			// System.out.println("-------------------------");
			// }

			@SuppressWarnings("unchecked")
			Iterator<JSONObject> it = list.iterator();

			while (it.hasNext()) {

				JSONObject tempJson = it.next(); // row안에서 꺼내온 json배열

				System.out.println("년도 : " + tempJson.get("YEAR"));
				System.out.println("서비스 유형 :" + tempJson.get("SVC_TY"));
				System.out.println("업체수 : " + tempJson.get("ENTRPS_CO"));
				System.out.println("매출랙 대비 영업이익율 : " + tempJson.get("BSN_PROFITRT"));
				System.out.println("매출대비 식재료 및 인건비 : " + tempJson.get("PLTCHRGE_LBCST_RT"));

				System.out.println("-------------------------");
			}
		}
	}

	public static void main(String[] args) throws IOException, ParseException {
		new JsonPublicData().start();
	}
}
