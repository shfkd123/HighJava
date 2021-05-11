package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {
	
	/**
	 * BoardVO 객체에 담겨진 자료를 DB에  insert하는 메서드
	 * @param smc SqlMapClient 객체
	 * @param bv DB에 insert할 자료가 저장된 BoardVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환된다.
	 * @throws SQLException JDBC관련 예외객체 발생
	 */
	public int insertBoard(SqlMapClient smc, BoardVO bv) throws SQLException;

	/**
	 * 주어진 board_no가 존재하는지 여부를 알아내는 메서드
	 * @param smc SqlMapClient 객체
	 * @param board_no 게시판 번호
	 * @return 해당 게시판 번호가 존재하면 true, 존재하지 않으면  false
	 * @throws SQLException JDBC관련 예외객체 발생
	 */
	public boolean checkBoard(SqlMapClient smc, String board_no) throws SQLException;

	/**
	 * DB의 jdbc_board테이블의 전체 레코드를 가져와서 List에 담아 반환하는 메서드
	 * @param smc SqlMapClient 객체
	 * @return 회원정보를 담고 있는 List 객체
	 * @throws SQLException JDBC 관련 예외 객체
	 */
	public List<BoardVO> AllBoardList(SqlMapClient smc) throws SQLException;

	/**
	 * 하나의 게시글 정보를 이용하여 DB를 update하는 메서드
	 * @param smc SqlMapClient 객체
	 * @param bv 회원정보 객체
	 * @return 작업성공 : 1, 작업실패: 0
	 * @throws SQLException JDBC 예외 객체
	 */
	public int updateBoard(SqlMapClient smc, BoardVO bv) throws SQLException;

	/**
	 * 게시글 번호를 매개변수로 받아서 그 게시글 정보를 삭제하는 메서드
	 * @param smc SqlMapClient 객체
	 * @param board_no 삭제할 게시글 번호
	 * @return 작업성공 :1, 작업실패 : 0
	 * @throws SQLException JDBC 관련 예외 객체
	 */
	public int deleteBoard(SqlMapClient smc, String board_no) throws SQLException;

	/**
	 * BoardVO 객체 담긴 자료를 이용하여 게시글을 검색하는 메서드
	 * @param smc SqlMapClient 객체
	 * @param board_title 제목으로 게시글 검색
	 * @return 검색된 결과를 담은 List
	 * @throws SQLException JDBC 관련 예외 객체 
	 */
	public List<BoardVO> getSearchBoard(SqlMapClient smc, String board_title) throws SQLException;
	
	/**
	 * 게시글 하나 조회
	 * @param smc
	 * @param board_no
	 * @return
	 * @throws SQLException
	 */
	public BoardVO getBoardInfo(SqlMapClient smc, String board_no) throws SQLException;

}
