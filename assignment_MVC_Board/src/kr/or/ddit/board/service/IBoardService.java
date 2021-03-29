package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {

	/**
	 * 게시글 등록 메서드 
	 * @param bv
	 * @return 성공 1, 실패 0
	 */
	public int insertBoard(BoardVO bv);
	
	
	/**
	 * 게시글 번호 체크 
	 * @param board_no
	 * @return 존재 true, 존재하지 않으면  false
	 */
	public boolean checkBoard(String board_no);
	
	/**
	 * 전체 게시글 출력
	 * @return 게시글정보를 담고 있는 List객체 
	 */
	public List<BoardVO> getAllBoardList();
	
	
	/**
	 * 게시글 정보 수정
	 * @param bv
	 * @return 성공 1, 실패 0
	 */
	public int updateBoard(BoardVO bv);
	
	/**
	 * 게시글 삭제
	 * @param board_no
	 * @return 성공1, 실패 0
	 */
	public int deleteBoard(String board_no);
	
	/**
	 * 게시글 검색
	 * @return 게시글정보를 담고 있는 List객체 
	 */
	public List<BoardVO> findBoardList(String board_title);
	
	
}
