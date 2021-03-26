package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import VO.BoardVO;

public interface BoardDao {
	
	public int insertBoard(Connection conn, BoardVO bv) throws SQLException;
	
	public List<BoardVO> AllBoardList(Connection conn) throws SQLException;
	
	public int updateBoard(Connection conn, BoardVO bv) throws SQLException;
	
	public int deleteBoard(Connection conn, String board_no) throws SQLException;
	
	public List<BoardVO> findBoardList(Connection conn, String board_title) throws SQLException;

	public boolean checkBoard(Connection conn, String board_no) throws SQLException;
	
}
