package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Util.JDBCUtil;
import VO.BoardVO;

public class BoardDaoImpl implements BoardDao {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static BoardDao boardDao;
	
	private  BoardDaoImpl() {
		
	}

	public static BoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}
	@Override
	public int insertBoard(Connection conn, BoardVO bv) throws SQLException {
		int cnt = 0;
		try {
			String sql = "insert into jdbc_board (board_no, board_title, board_writer, board_date, board_content) "
					+ " values (board_seq.nextval, ?, ?, sysdate, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_writer());
			pstmt.setString(3, bv.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<BoardVO> AllBoardList(Connection conn) throws SQLException {

		List<BoardVO> boardList = new ArrayList<BoardVO>();

		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("select * from jdbc_board");
			
			while (rs.next()) {
				BoardVO bv = new BoardVO();
				// board_no/board_title/board_writer/board_date/board_content
				String board_no = rs.getString("board_no");
				String board_title = rs.getString("board_title");
				String board_writer = rs.getString("board_writer");
				String board_date = rs.getString("board_date");
				String board_content = rs.getString("board_content");
				
				bv.setBoard_no(board_no +"\t");
				bv.setBoard_tilte(board_title +"\t");
				bv.setBoard_writer(board_writer +"\t");
				bv.setBoard_date(board_date +"\t");
				bv.setBoard_content(board_content +"\t");

				boardList.add(bv);

			}
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
		return boardList;
	}

	@Override
	public int updateBoard(Connection conn, BoardVO bv) throws SQLException {
		int cnt = 0;
		try {
			String sql = "update jdbc_board" 
						+ " set board_title = ? " 
						+ ", board_writer = ? " 
						+ ", board_content = ? "
						+ " where board_no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoard_title());
			pstmt.setString(2, bv.getBoard_writer());
			pstmt.setString(3, bv.getBoard_content());
			pstmt.setString(4, bv.getBoard_no());
			
			cnt = pstmt.executeUpdate();
			
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}

		return cnt;
	}

	@Override
	public int deleteBoard(Connection conn, String board_no) throws SQLException {
		int cnt = 0;
		try {
			String sql = "delete from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_no);
			
			cnt = pstmt.executeUpdate();
			
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}

		return cnt;
	}

	@Override
	public List<BoardVO> findBoardList(Connection conn, String board_title) throws SQLException {

		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			stmt = conn.createStatement();			
			rs = stmt.executeQuery("select * from jdbc_board where board_title like '%" + board_title + "%'");
			
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}

		return boardList;
	}

	@Override
	public boolean checkBoard(Connection conn, String board_no) throws SQLException {
		boolean chn = false;
		try {
			String sql = "select count(*) as cnt from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_no);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			while (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if (cnt > 0) {
				chn = true;
			}
			
		}finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}

		return chn;
	}

}
