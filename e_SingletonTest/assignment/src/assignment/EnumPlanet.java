package assignment;

public class EnumPlanet {
	/*문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오.
	(단, enum 객체 생성시 반지름을 이용하도록 정의하시오.) 

	예) 행성의 반지름(KM):
	수성(2439), 
	금성(6052), 
	지구(6371), 
	화성(3390), 
	목성(69911), 
	토성(58232), 
	천왕성(25362), 
	해왕성(24622)*/
	
	public enum Planet_KM {
		수성(2439), 금성(6052), 지구(6371), 화성(3390), 목성(69911), 토성(58232), 천왕성(25362), 해왕성(24622);

		private double km; //외부에서 접근할 수 없도록 private로 지정

		//생성자 파라미터를 전역변수와 연결한다. 
		private Planet_KM(int km) {
			this.km = km;
		}
		
		//값에 접근할 수 있도록 getter 함수 생성
		//값을 변경할 수 없도록 setter 함수는 정의하지 않음. 
		public double getKm() {
			return km;
		}

	}//enum Planet_KM


	public static void main(String[] args) {
		for (Planet_KM p : Planet_KM.values()) {
			System.out.println(p + "의 반지름 출력 : " + (int)p.getKm());
		}
		System.out.println("===========================================================");
		// 면적 구하기 
		for (Planet_KM p : Planet_KM.values()) {
			System.out.println(p + "면적 구하기 (PI * r^2): " + 3.14 *(p.getKm()*p.getKm()));
		}
	}
}
