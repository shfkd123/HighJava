package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.SqlMapClientUtil;

public class BoardDaoImpl implements IBoardDao {
	
	private static IBoardDao boardDao;
	
	private BoardDaoImpl() {
		SqlMapClientUtil.getInstance();
	}
	
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}

	@Override
	public int insertBoard(SqlMapClient smc, BoardVO bv) throws SQLException {
		
		int cnt = 0;

		Object obj = smc.insert("board.insertBoard", bv);

		if (obj == null) {
			cnt = 1; // 성공
		}
		return cnt;
	}

	@Override
	public boolean checkBoard(SqlMapClient smc, String board_no) throws SQLException {
		
		boolean chk = false; // 중복되면 false

		int cnt = (int) smc.queryForObject("board.getBoard", board_no);

		if (cnt > 0) {
			chk = true; // 중복이 아니면 true
		}
		return chk;
	}

	@Override
	public List<BoardVO> AllBoardList(SqlMapClient smc) throws SQLException {
		
		List<BoardVO> boardList = smc.queryForList("board.getBoardAll");

		return boardList;
	}

	@Override
	public int updateBoard(SqlMapClient smc, BoardVO bv) throws SQLException {
		int cnt = 0;

		cnt = smc.update("board.updateBoard", bv);
		return cnt;
	}

	@Override
	public int deleteBoard(SqlMapClient smc, String board_no) throws SQLException {
		
		int cnt = smc.delete("board.deleteBoard", board_no);

		return cnt;
	}

	@Override
	public List<BoardVO> getSearchBoard(SqlMapClient smc, String board_title) throws SQLException {
		List<BoardVO> boardList = smc.queryForList("board.getSearchBoard", board_title);
		return boardList;
	}

}
