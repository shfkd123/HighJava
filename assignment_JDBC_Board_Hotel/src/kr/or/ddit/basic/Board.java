package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.JDBCUtil;

public class Board {
	
	private Connection conn; //데이터베이스 연결 Connection 객체 
	
	private Statement stmt; //쿼리 실행을 위한 Statement객체
	
	private PreparedStatement pstmt; //쿼리 실행을 위한 PreparedStatement객체
	//보안상의 문제로 PreparedStatement 객체 사용을 권한다.
	
	private ResultSet rs; //객체가 있을 경우 종료 
	
	private Scanner scan = new Scanner(System.in); 
	
	
	/*위의 테이블을 작성하고 게시판을 관리하는
		다음 기능들을 구현하시오.

		기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 */
	public static void main(String[] args) {
		
		Board board = new Board();
		board.start();
	}
	
	//메뉴 출력
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === BOARD ===");
		System.out.println("  1. 새글 작성");
		System.out.println("  2. 작성글 수정");
		System.out.println("  3. 작성글 삭제");
		System.out.println("  4. 작성글 검색");
		System.out.println("  5. 전체 자료 출력");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	private void start() {
		int input = 0;
		while(true) {
			displayMenu(); //메뉴 출력 함수 호출
			input = scan.nextInt();
			switch (input) {
			case 1:
				insertBoard();
				break;
			case 2:
				updateBoard();
				break;
			case 3:
				deleteBoard();
				break;
			case 4:
				findBoard();
				break;
			case 5:
				AllBoard();
				break;
			case 6:
				System.out.println("게시판을 종료합니다.");
				System.exit(0);
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
				
			}
		}
		
	}

	//전체 게시판 출력
	private void AllBoard() {
		//board_no/board_title/board_writer/board_date/board_content
		System.out.println();
		System.out.println("-----------------------------------------------------------------");
		System.out.println(" NO\t제목\t작성자\t날짜\t\t\t내용");
		System.out.println("-----------------------------------------------------------------");
		
		try {
			conn = JDBCUtil.getConnection();
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from jdbc_board");
			
			while(rs.next()) {
				String board_no = rs.getString("board_no");
				String board_title = rs.getString("board_title");
				String board_writer = rs.getString("board_writer");
				String board_date = rs.getString("board_date");
				String board_content = rs.getString("board_content");
				System.out.println(board_no + "\t" + board_title + "\t" + board_writer + "\t"
									+ board_date + "\t" + board_content);
			}
			System.out.println("-----------------------------------------------------------------");
			
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
	}
	
	//게시판 검색
	private void findBoard() {
		//제목으로 검색
		System.out.println("검색할 제목 입력 >>");
		String title = scan.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from jdbc_board where board_title like '%" + title + "%'");
			
			System.out.println();
			System.out.println("-----------------------------------------------------------------");
			System.out.println(" NO\t제목\t작성자\t날짜\t\t\t내용");
			System.out.println("-----------------------------------------------------------------");
			while(rs.next()) {
				String board_no = rs.getString("board_no");
				String board_title = rs.getString("board_title");
				String board_writer = rs.getString("board_writer");
				String board_date = rs.getString("board_date");
				String board_content = rs.getString("board_content");
				System.out.println(board_no + "\t" + board_title + "\t" + board_writer + "\t"
									+ board_date + "\t" + board_content);
			}
			System.out.println("-----------------------------------------------------------------");
		
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}

	}

	//게시판 삭제
	private void deleteBoard() {
		AllBoard();
		System.out.println();
		System.out.println("삭제할 게시글 번호를 입력하세요.");
		String board_no = scan.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_no);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("삭제성공!!");
			}else {
				System.out.println("삭제 실패!!");
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
	}

	//게시판 수정
	private void updateBoard() {
		boolean chn = false;
		String board_no = null;
		
		do {
			AllBoard();
			System.out.println();
			System.out.println("수정할 게시글 번호를 입력하세요.");
			board_no = scan.next();
			
			chn = checkBoardNo(board_no);
			
			if(chn == false) {
				System.out.println("게시글 번호로 확인된 게시글이 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
		}while(chn == false);

		System.out.println("변경할 제목 입력 >>");
		String title = scan.next();
		System.out.println("변경할 작성자 입력 >>");
		String writer = scan.next();
		System.out.println("변경할 내용 입력 >>");
		String content = scan.next();
		
		try {
			conn = JDBCUtil.getConnection();
				
			//board_no/board_title/board_writer/board_date/board_content
			String sql = "update jdbc_board" +
						" set board_title = ? " +
						" ,board_writer = ? " +
						" ,board_content = ? " +
						" where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title); //첫번째 물음표에 들어갈 값 지정
			pstmt.setString(2, writer); //두번째 물음표에 들어갈 값 지정
			pstmt.setString(3, content); //세번째 물음표에 들어갈 값 지정
			pstmt.setString(4, board_no); //네번째 물음표에 들어갈 값 지정
				
			int cnt = pstmt.executeUpdate();
			if (cnt > 0) {
				System.out.println("수정 성공!!");
			} else {
				System.out.println("수정 실패");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}

	/**
	 * 게시글 번호를 확인하여 게시글 확인
	 * @param board_no
	 * @return 존재하면  true, 없으면 false
	 */
	private boolean checkBoardNo(String board_no) {
		
		boolean chn  = false;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select count(*) as cnt from jdbc_board where board_no = ?";
			//?에 들어갈 값은 입력한 게시글 번호 , 똑같은 게시글 번호가 있으면 cnt는 1임, 없으면 0
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_no); //변경된 정보
			
			rs = pstmt.executeQuery(); //자료에 영향을 받은 행의 수를 반환
			
			int cnt = 0;
			while(rs.next()) {
				//next() 함수의 결과값이 'false'일 때까지 무한 루프로 반복 처리 
				cnt  = rs.getInt("cnt");
			}
			if(cnt > 0) {
				chn = true;
			}			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		return chn; //존재하면 true, 없으면  false 
	}

	//게시판 글쓰기 board_no/board_title/board_writer/board_date/board_content
	private void insertBoard() {

		System.out.println("제목 >>");
		String title = scan.next();
		System.out.println("글쓴이 >>");
		String writer = scan.next();
		System.out.println("내용 >>");
		String content = scan.next();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "insert into jdbc_board (board_no, board_title, board_writer, board_date, board_content) "
					     + " values (board_seq.nextval, ?, ?, sysdate, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			
			int cnt = pstmt.executeUpdate();
			
			/*executeUpdate()
			* => 등록, 수정, 삭제 명령을 하는 쿼리에서 사용하며, 자료에 영향을 받은 행의 수를 반환한다.
			* => PreparedStatement와 같이 이미 쿼리가 등록된 경우에는 파라미터가 없는  executeUpdate()함수를 사용할 수 있다.  
			*/
			if (cnt > 0) { //등록에 성공하면 pstmt는 자료에 영항을 받은 행의 수를 반환하기 때문에 0보다 큰수가 나온다.
				System.out.println("게시글 작성 성공!!");
			}else {
				System.out.println("게시글 작성 실패!!");
			}
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally { //자원반납
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
	}

}
