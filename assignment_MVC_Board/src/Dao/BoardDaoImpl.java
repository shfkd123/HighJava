package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import VO.BoardVO;

public class BoardDaoImpl implements BoardDao {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public int insertBoard(Connection conn, BoardVO bv) throws SQLException {
		String sql = "insert into jdbc_board (board_no, board_title, board_writer, board_date, board_content) "
				+ " values (board_seq.nextval, ?, ?, sysdate, ?)";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bv.getBoard_title());
		pstmt.setString(2, bv.getBoard_writer());
		pstmt.setString(3, bv.getBoard_content());

		int cnt = pstmt.executeUpdate();
		return 0;
	}

	@Override
	public List<BoardVO> AllBoardList(Connection conn) throws SQLException {

		List<BoardVO> boardList = new ArrayList<BoardVO>();

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

			bv.setBoard_no(board_no);
			bv.setBoard_tilte(board_title);
			bv.setBoard_writer(board_writer);
			bv.setBoard_date(board_date);
			bv.setBoard_content(board_content);

			boardList.add(bv);
		}
		return boardList;
	}

	@Override
	public int updateBoard(Connection conn, BoardVO bv) throws SQLException {
		String sql = "update jdbc_board" + " set board_title = ? " + " ,board_writer = ? " + " ,board_content = ? "
				+ " where board_no = ? ";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bv.getBoard_title());
		pstmt.setString(2, bv.getBoard_writer());
		pstmt.setString(3, bv.getBoard_content());
		pstmt.setString(4, bv.getBoard_no());

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

		return boardList;
	}

	@Override
	public boolean checkBoard(Connection conn, String board_no) throws SQLException {
		boolean chn = false;

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

		return chn;
	}

}
