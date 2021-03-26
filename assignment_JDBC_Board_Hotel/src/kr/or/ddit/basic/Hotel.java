package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil;

public class Hotel {

	private Connection conn; // 데이터베이스 연결 Connection 객체

	private Statement stmt; // 쿼리 실행을 위한 Statement객체

	private PreparedStatement pstmt; // 쿼리 실행을 위한 PreparedStatement객체
	// 보안상의 문제로 PreparedStatement 객체 사용을 권한다.

	private ResultSet rs; // 객체가 있을 경우 종료

	private Scanner scan = new Scanner(System.in);

	
	public static void main(String[] args) {
		
		Hotel h = new Hotel();
		h.start();
	}

	
	//업무 메뉴 출력
	public void displayMenu() {
		System.out.println("======================================");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인\t2.체크아웃\t3.객실상태\t4.업무종료");
		System.out.println("======================================");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	private void start() {

		while (true) {
			displayMenu();
			int action = scan.nextInt();
			switch(action) {
			case 1: checkin(); break;
			case 2: checkout(); break;
			case 3: room_state(); break;
			case 4: System.out.println("호텔 문을 닫았습니다.업무 종료합니다."); System.exit(0); break;
			default :
				System.out.println("잘못 입력했습니다. 다시입력하세요.");
			}
		}
	}

	//객실 상태 - 전체 출력 
	private void room_state() {
		System.out.println("=============================");
		System.out.println("번호\t방번호\t투숙객");
		System.out.println("=============================");
		try {
			conn = JDBCUtil.getConnection();
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from hotel_mng");
			
			while(rs.next()) {
				String room_num = rs.getString("room_num");
				String guest_name = rs.getString("guest_name");
				System.out.println(room_num + "\t" + guest_name);
			}
			System.out.println("=============================");
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}

	//체크 아웃 - 삭제
	private void checkout() {
		room_state();
		System.out.println();
		System.out.println("체크아웃할 방번호를 입력하세요.");
		String room_num = scan.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from hotel_mng where room_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room_num);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("체크아웃 성공!!");
			}else {
				System.out.println("체크아웃 실패!!");
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
	}

	//체크인 - 추가
	private void checkin() {
		boolean chk = false;
		String room_num = null;
		
		do {
			System.out.println();
			System.out.println("어느방을 체크인 하시겠습니까?");
			System.out.println("방번호 입력: ");
			room_num = scan.next();
			
			chk = checkRoomNum(room_num);
			
			if(chk == true) {
				System.out.println(room_num + "에는 이미 예약한 회원이 존재합니다.");
				System.out.println("다른 방을 체크인 해주세요.");
			}
		}while(chk == true);
		
		System.out.println("성함 입력: ");
		String guest_name = scan.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "insert into hotel_mng (room_num, guest_name) values (?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room_num);
			pstmt.setString(2, guest_name);

			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("체크인 성공!!");
			}else {
				System.out.println("체크인 실패 !!");
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}
	
	
	//방번호 체크 HOTEL_MNG - ROOM_NUM / GUEST_NAME 
	private boolean checkRoomNum(String room_num) {
		boolean chn = false;

		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select count(*) as cnt from hotel_mng where room_num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, room_num);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}if(cnt > 0) {
				chn = true;
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
		return chn;
	}
}
