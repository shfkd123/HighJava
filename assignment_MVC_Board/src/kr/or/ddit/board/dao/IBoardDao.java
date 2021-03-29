package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성하여 서비스에 전달하는 DAO 인터페이스
 * @author pc24
 *
 */
public interface IBoardDao {

	
	/**
	 * 게시글 추가 
	 * @param conn
	 * @param bv
	 * @return
	 * @throws SQLException
	 */
	public int insertBoard(Connection conn, BoardVO bv) 
 			throws SQLException;

	/**
	 * 게시글 수정
	 * @param conn
	 * @param bv
	 * @return
	 * @throws SQLException
	 */
	public int updateBoard(Connection conn, BoardVO bv)
			throws SQLException;

	/**
	 * 게시글 삭제
	 * @param conn
	 * @param board_no
	 * @return
	 * @throws SQLException
	 */
	public int deleteBoard(Connection conn, String board_no)
			throws SQLException;

	/**
	 * 게시글 검색 
	 * @param conn
	 * @param board_title
	 * @return
	 * @throws SQLException
	 */
	public List<BoardVO> findBoardList(Connection conn, String board_title) throws SQLException;
	
	
	/**
	 * 게시글 전체 출력
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public List<BoardVO> getAllBoardList(Connection conn)
				throws SQLException;

	/**
	 * 게시글 번호 체크 
	 * @param conn
	 * @param board_no
	 * @return
	 * @throws SQLException
	 */
	public boolean checkBoard(Connection conn, String board_no)
			throws SQLException;
}
