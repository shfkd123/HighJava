  package assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentSort {

	/*
	 * 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다. 생성자는 학번, 이름, 국어,
	 * 영어, 수학 점수만 매개변수로 받아서 처리한다.
	 * 
	 * 이 Student객체들은 List에 저장하여 관리한다. List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과 총점의
	 * 역순으로 정렬하는 부분을 프로그램 하시오. (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.) (학번 정렬기준은 Student클래스
	 * 자체에서 제공하도록 하고, 총점 정렬기준은 외부클래스에서 제공하도록 한다.)
	 *
	 */

	public static void main(String[] args) {
		List<Student> stdList = new ArrayList<Student>();
		stdList.add(new Student("20201234", "김민지", 100, 99, 67));
		stdList.add(new Student("20191235", "정영인 ", 67, 26, 34));
		stdList.add(new Student("20181236", "서주형", 40, 60, 76));
		stdList.add(new Student("20201237", "전재수", 90, 10, 83));
		stdList.add(new Student("20071238", "변형균", 70, 72, 16));
		stdList.add(new Student("20091239", "김현태", 88, 28, 93));
		stdList.add(new Student("20161240", "박효정", 55, 19, 27));
		stdList.add(new Student("20161241", "강구름", 29, 77, 70));
		stdList.add(new Student("20331242", "김하늘", 10, 82, 40));
		stdList.add(new Student("20111243", "김현우", 99, 46, 70));

		System.out.println("=========================================");
		Student.Rank(stdList);

		System.out.println("학번/총점 => 정렬하기 전 ");
		for (Student std : stdList) {
			System.out.println(std);
		}

		System.out.println("=========================================");

		// 학번 정렬 오름차순 출력
		Collections.sort(stdList);
		System.out.println("번호의 오름차순으로 정렬 후  : ");

		for (Student std : stdList) {
			System.out.println(std);
		}
		System.out.println("=========================================");

		// 총점 역순출력
		Collections.sort(stdList, new ScoreSum());
		System.out.println("총점 내림차순 정렬 후 : ");
		for (int i = 0; i < stdList.size(); i++) {
			stdList.get(i).setRank(i + 1);
			System.out.println(stdList.get(i).toString());
		}
	}
}

// 총점 내림차순 //정렬 기준을 따로 만든다 -> Comparator
class ScoreSum implements Comparator<Student> {

	@Override
	public int compare(Student sum1, Student sum2) {
		if (sum1.getTotal_score() > sum2.getTotal_score()) {
			return -1;
		} else if (sum1.getTotal_score() == sum2.getTotal_score()) {
			return -1; 
		} else {
			return 1;
		}
	}
}

// 객체 자체에 정렬되는 기능을 사용 -> Comparable
class Student implements Comparable<Student> {
	private String number; // 학번
	private String name; // 이름
	private int korean; // 국어점수
	private int english; // 영어점수
	private int math; // 수학점수

	private int total_score; // 총점
	private int rank = 1; // 등수

	public Student(String number, String name, int korean, int english, int math) {

		this.number = number;
		this.name = name;
		this.korean = korean;
		this.english = english;
		this.math = math;

		setTotal_score();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorean() {
		return korean;
	}

	public void setKorean(int korean) {
		this.korean = korean;
	}

	public int getEnglish() {
		return english;
	}

	public void setEnglish(int english) {
		this.english = english;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getTotal_score() {
		return total_score;
	}

	public void setTotal_score() {
		this.total_score = this.korean + this.math + this.english;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [number=" + number + ", name=" + name + ", korean=" + korean + ", english=" + english
				+ ", math=" + math + ", total_score=" + total_score + ", rank=" + rank + "]";
	}

	// 학번의 오름차순으로 정렬하기
	@Override
	public int compareTo(Student num) {
		return getNumber().compareTo(num.getNumber());
	}

	// rank구하기
	public static void Rank(List<Student> stdList) {
		for (Student std1 : stdList) {
			int rank = std1.rank;
			for (Student std2 : stdList) {
				if (std1.getTotal_score() < std2.getTotal_score()) {
					rank++;
				}
			}
			std1.setRank(rank);
		}
	}

}
