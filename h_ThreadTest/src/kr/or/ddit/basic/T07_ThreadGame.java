package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/**
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고, 
 * 사용자의 가위 바위 보는 showInputDialog()메서드를 이용하여 입력받는다.
 * 
 * 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
 * 5초안에 입력이 완료되면 승패를 출력한다.
 * 
 * 결과예시)
 * === 결과 ===
 * 컴퓨터 : 가위
 * 당   신 : 바위
 * 결   과 : 당신이 이겼습니다. 
 *
 */
public class T07_ThreadGame {
	
	//입력 체크
	public static boolean inputCheck = false; 
	
	public static void main(String[] args) {
		Thread th1 = new UserInput();
		Thread th2 = new CountDown2();
		
		th1.start();
		th2.start();
;	}
}

//가위 바위 보 사용자 입력
class UserInput extends Thread {
	
	@Override
	public void run() {
		
		String[] computer = {"가위", "바위", "보"};
		
		int com_r = (int)(Math.random()*3);
		/*computer[com_r]*/
	
		String str = JOptionPane.showInputDialog("가위 바위 보를 입력하세요.");
		
		T07_ThreadGame.inputCheck = true; //사용자 입력 완료
		
		while(str.equals("가위")) {
			if(com_r == 0) {
				System.out.println("컴퓨터 : " + computer[com_r]);
				System.out.println("당   신 : " + str);
				System.out.println("결   과 : 비겼습니다.");
				break;
			}else if(com_r == 1) {
				System.out.println("컴퓨터 : " + computer[com_r]);
				System.out.println("당   신 : " + str);
				System.out.println("결   과 : 컴퓨터가 이겼습니다.");
				break;
			}else if(com_r == 2) {
				System.out.println("컴퓨터 : " + computer[com_r]);
				System.out.println("당   신 : " + str);
				System.out.println("결   과 : 당신이 이겼습니다.");
				break;
			}else
				System.out.println("비정상적인 접근입니다.");
				break;
		}
		
		while(str.equals("바위")) {
			if(com_r == 0) {
				System.out.println("컴퓨터 : " + computer[com_r]);
				System.out.println("당   신 : " + str);
				System.out.println("결   과 : 당신이 이겼습니다.");
				break;
			}else if(com_r == 1) {
				System.out.println("컴퓨터 : " + computer[com_r]);
				System.out.println("당   신 : " + str);
				System.out.println("결   과 : 비겼습니다.");
				break;
			}else if(com_r == 2) {
				System.out.println("컴퓨터 : " + computer[com_r]);
				System.out.println("당   신 : " + str);
				System.out.println("결   과 : 당신이 이겼습니다.");
				break;
			}else
				System.out.println("비정상적인 접근입니다.");
				break;
		}
		
		while(str.equals("보")) {
			if(com_r == 0) {
				System.out.println("컴퓨터 : " + computer[com_r]);
				System.out.println("당   신 : " + str);
				System.out.println("결   과 : 컴퓨터가 이겼습니다.");
				break;
			}else if(com_r == 1) {
				System.out.println("컴퓨터 : " + computer[com_r]);
				System.out.println("당   신 : " + str);
				System.out.println("결   과 : 당신이 이겼습니다.");
				break;
			}else if(com_r == 2) {
				System.out.println("컴퓨터 : " + computer[com_r]);
				System.out.println("당   신 : " + str);
				System.out.println("결   과 : 비겼습니다.");
				break;
			}else
				System.out.println("비정상적인 접근입니다.");
				break;
		}
		
	}
	
}


//카운트 다운
class CountDown2 extends Thread {
	@Override
	public void run() {
		for (int i = 5; i >= 1; i--) {
			
			if (T07_ThreadGame.inputCheck == true) {
				return;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		//5초가 경과되었는데도 입력이 없으면 프로그램을 종료한다.
		System.out.println("5초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0); //프로그램을 종료시키는 명령
	}
}
