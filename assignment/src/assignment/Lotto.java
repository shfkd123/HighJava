package assignment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Lotto {
	static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		while (true) {
			System.out.println("===============================");
			System.out.println("\tLotto 프로그램");
			System.out.println("-------------------------------");
			System.out.println("\t1. Lotto 구입");
			System.out.println("\t2. 프로그램 종료");
			System.out.println("===============================");
			System.out.println("입력>");
			int input = in.nextInt();
			switch (input) {
			case 1:LottoBuy(); break;
			case 2:System.out.println("감사합니다 . 프로그램 종료합니다.=)"); System.exit(0); break;
			}
		}
	}// main

	private static void LottoBuy() {
		int price = 0;
		int n = 1;
		int changes = 0;
		System.out.println("메뉴 선택 : 1");
		System.out.println("[Lotto 구입  시작]");
		System.out.println("1000원에 로또번호 하나입니다.");
		System.out.print("금액 입력: " );
		price = in.nextInt();
		n = price / 1000;
		System.out.println(n + "장 구매되셨습니다.");
		changes = price - (1000 * n);
		
		System.out.println("행운의 로또번호는 아래와 같습니다.\n");
		for (int i = 1; i <= n; i++) {
			HashSet<Integer> intRnd = new HashSet<Integer>();
			while (intRnd.size() < 6) {
				int num = (int) (Math.random() * 45 + 1);
				intRnd.add(num);
			}
			//
			System.out.println("로또번호 : " + i + intRnd);
		}
		System.out.println("받은 금액은 " + price + "원이고, 거스름돈은 " + changes + "원입니다.\n");

	}

}
