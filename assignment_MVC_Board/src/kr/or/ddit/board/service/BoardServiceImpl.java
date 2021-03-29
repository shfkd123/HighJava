package kr.or.ddit.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.JDBCUtil3;

public class BoardServiceImpl implements IBoardService {

	// 사용할 DAO의 객체 변수를 선언한다.
	private IBoardDao boardDao;
	private Connection conn;

	public BoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}

	@Override
	public int insertBoard(BoardVO bv) {

		int cnt = 0;

		try {
			conn = JDBCUtil3.getConnection();
			cnt = boardDao.insertBoard(conn, bv);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}

		return cnt;
	}

	@Override
	public boolean checkBoard(String board_no) {

		boolean chk = false;

		try {
			conn = JDBCUtil3.getConnection();
			chk = boardDao.checkBoard(conn, board_no);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}

		return chk;
	}

	@Override
	public List<BoardVO> getAllBoardList() {

		List<BoardVO> boardList = new ArrayList<>();

		try {
			conn = JDBCUtil3.getConnection();
			boardList = boardDao.getAllBoardList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}

		return boardList;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;

		try {
			conn = JDBCUtil3.getConnection();
			cnt = boardDao.updateBoard(conn, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}

		return cnt;
	}

	@Override
	public int deleteBoard(String board_no) {
		int cnt = 0;

		try {
			conn = JDBCUtil3.getConnection();
			cnt = boardDao.deleteBoard(conn, board_no);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}

		return cnt;
	}

	@Override
	public List<BoardVO> findBoardList(String board_title) {
		List<BoardVO> boardList = new ArrayList<>();

		try {
			conn = JDBCUtil3.getConnection();
			boardList = boardDao.findBoardList(conn, board_title);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}

		return boardList;
	}
}
