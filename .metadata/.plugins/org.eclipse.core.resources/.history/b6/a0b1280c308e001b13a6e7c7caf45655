package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.SqlMapClientUtil;

public class BoardServiceImpl implements IBoardService {

	//사용할 DAO의 객체변수를 선언한다.
	private IBoardDao boardDao;
	private SqlMapClient smc;
	
	public static IBoardService service;
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
		smc = SqlMapClientUtil.getInstance();
	}
	
	public static IBoardService getInstance() {
		if (service == null) {
			service = new BoardServiceImpl();
		}
		return service;
	}
	@Override
	public int insertBoard(BoardVO bv) {
		
		int cnt = 0;
		try {
			smc.startTransaction();
			cnt = boardDao.insertBoard(smc, bv);
			smc.commitTransaction();
		} catch (SQLException e) {
			try {
				smc.endTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public boolean checkBoard(String board_no) {
		boolean chk = false;

		try {
			chk = boardDao.checkBoard(smc, board_no);
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return chk;
	}

	@Override
	public List<BoardVO> AllBoardList() {
		List<BoardVO> boardList = new ArrayList<>();

		try {
			boardList = boardDao.AllBoardList(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;

		try {
			cnt = boardDao.updateBoard(smc, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(String board_no) {
		int cnt = 0;

		try {
			cnt = boardDao.deleteBoard(smc, board_no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getSearchBoard(String board_title) {
		List<BoardVO> memList = new ArrayList<>();

		try {
			memList = boardDao.getSearchBoard(smc, board_title);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return memList;
	}

}
