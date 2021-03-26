package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class T05_ThreadTest {
	
	//코드의 의도: 10초동안 아무거나 입력해야 한다.
	//현재의 코드는 싱글 스레드로 동시에 실행되지 않는다.
	public static void main(String[] args) {

		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		System.out.println("입력한 값은" + str + "입니다.");

		for (int i = 10; i >= 1; i--) {
			System.out.println(i);

			try {
				Thread.sleep(1000); // 1초동안 잠시 멈춘다.
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
