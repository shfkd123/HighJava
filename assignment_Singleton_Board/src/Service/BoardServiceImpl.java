package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Dao.BoardDao;
import Dao.BoardDaoImpl;
import Util.JDBCUtil;
import VO.BoardVO;

public class BoardServiceImpl implements BoardService {

	private BoardDao bDao;
	private Connection conn;
	private Statement stmt;	
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public static BoardService service;

	private BoardServiceImpl() {
		bDao = BoardDaoImpl.getInstance();
	}	
	
	public static BoardService getInstance() {
		if (service == null) {
			service = new BoardServiceImpl();
		}
		
		return service;
	}
	
	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;

		try {
			conn = JDBCUtil.getConnection();
			cnt = bDao.insertBoard(conn, bv);

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}

		return cnt;
	}


	@Override
	public List<BoardVO> AllBoardList() {
		List<BoardVO> boardList = new ArrayList<>();

		try {
			conn = JDBCUtil.getConnection();
			boardList = bDao.AllBoardList(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}

		return boardList;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;

		try {
			conn = JDBCUtil.getConnection();
			cnt = bDao.updateBoard(conn, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}

		return cnt;
	}

	@Override
	public int deleteBoard(String board_no) {
		int cnt = 0;

		try {
			conn = JDBCUtil.getConnection();
			cnt = bDao.deleteBoard(conn, board_no);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}

		return cnt;
	}


	@Override
	public List<BoardVO> findBoardList(String board_title) {
		List<BoardVO> boardList = new ArrayList<>();

		try {
			conn = JDBCUtil.getConnection();
			boardList = bDao.findBoardList(conn, board_title);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}

		return boardList;
	}


	@Override
	public boolean checkBoard(String board_no) {
		boolean chn = false;

		try {
			conn = JDBCUtil.getConnection();
			chn = bDao.checkBoard(conn, board_no);
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}

		return chn;
	}

}
