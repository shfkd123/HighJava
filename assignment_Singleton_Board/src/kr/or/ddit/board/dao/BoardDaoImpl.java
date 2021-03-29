package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao{

	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	private static IBoardDao boardDao;
	
	private BoardDaoImpl() {
		
	}
	
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}
	
	@Override
	public int insertBoard(Connection conn, BoardVO bv) throws SQLException {
		
		String sql = "insert into jdbc_board (board_no, board_title, board_writer, board_date, board_content) "
			     + " values (board_seq.nextval, ?, ?, sysdate, ?)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bv.getBoard_title());
		pstmt.setString(2, bv.getBoard_writer());
		pstmt.setString(3, bv.getBoard_content());
		
		int cnt = pstmt.executeUpdate();
		/*executeUpdate()
		* => 등록, 수정, 삭제 명령을 하는 쿼리에서 사용하며, 자료에 영향을 받은 행의 수를 반환한다.
		* => PreparedStatement와 같이 이미 쿼리가 등록된 경우에는 파라미터가 없는  executeUpdate()함수를 사용할 수 있다.  
		*/
		
		return cnt;
	}

	@Override
	public int updateBoard(Connection conn, BoardVO bv) throws SQLException {
		String sql = "update jdbc_board" +
				" set board_title = ? " +
				" ,board_writer = ? " +
				" ,board_content = ? " +
				" where board_no = ? ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bv.getBoard_title()); //첫번째 물음표에 들어갈 값 지정
		pstmt.setString(2, bv.getBoard_writer()); //두번째 물음표에 들어갈 값 지정
		pstmt.setString(3, bv.getBoard_content()); //세번째 물음표에 들어갈 값 지정
		pstmt.setString(4, bv.getBoard_no()); //네번째 물음표에 들어갈 값 지정
			
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	@Override
	public int deleteBoard(Connection conn, String board_no) throws SQLException {
		String sql = "delete from jdbc_board where board_no = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, board_no);
		
		int cnt = pstmt.executeUpdate();
		return cnt;
	}

	@Override
	public List<BoardVO> findBoardList(Connection conn, String board_title) throws SQLException {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery("select * from jdbc_board where board_title like '%" + board_title + "%'"); 
		
		while(rs.next()) {
			BoardVO bv = new BoardVO();
			
			String board_no = rs.getString("board_no");
			String title = rs.getString("board_title");
			String board_writer = rs.getString("board_writer");
			String board_date = rs.getString("board_date");
			String board_content = rs.getString("board_content");
			
			bv.setBoard_no(board_no);
			bv.setBoard_title(title);
			bv.setBoard_writer(board_writer);
			bv.setBoard_date(board_date);
			bv.setBoard_content(board_content);
			
			boardList.add(bv);
			
		}
		
		return boardList;
	}

	@Override
	public List<BoardVO> getAllBoardList(Connection conn) throws SQLException {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery("select * from jdbc_board");
		
		while(rs.next()) {
			BoardVO bv = new BoardVO();
			
			String board_no = rs.getString("board_no");
			String board_title = rs.getString("board_title");
			String board_writer = rs.getString("board_writer");
			String board_date = rs.getString("board_date");
			String board_content = rs.getString("board_content");
			
			bv.setBoard_no(board_no);
			bv.setBoard_title(board_title);
			bv.setBoard_writer(board_writer);
			bv.setBoard_date(board_date);
			bv.setBoard_content(board_content);
			
			boardList.add(bv);
			
		}
		
		return boardList;
	}

	@Override
	public boolean checkBoard(Connection conn, String board_no) throws SQLException {
		boolean chk = false;
		
		String sql = "select count(*) as cnt from jdbc_board where board_no = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, board_no);
		
		rs = pstmt.executeQuery();
		
		int cnt = 0;
		while(rs.next()) {
			cnt = rs.getInt("cnt");
		}
		
		if(cnt > 0) {
			chk = true;
		}
		return chk;
	}

}
