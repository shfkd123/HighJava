package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {
	/**
	 * 게시글 추가하는 메서드
	 * @param bv DB에 insert할 자료가 저장된 BoardVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환된다.
	 */
	public int insertBoard(BoardVO bv);

	/**
	 * 주어진 board_no(게시글 번호)가 존재하는지 여부를 알아내는 메서드
	 * @param board_no 게시글 번호
	 * @return 해당 게시글 번호가 존재하면 true, 존재하지 않으면 false;
	 */
	public boolean checkBoard(String board_no);
	
	/**
	 * 전체 게시글을 조회 메서드
	 * @return 게시글 정보를 담고있는 List 객체
	 */
	public List<BoardVO> AllBoardList();

	/**
	 * 하나의 게시글정보를 수정하는 메서드
	 * @param bv 게시글정보 객체
	 * @return 작업 성공: 1, 작업 실패 : 0
	 */
	public int updateBoard(BoardVO bv);

	/**
	 * 회원정보를 삭제하는 메서드 
	 * @param board_no 삭제할 게시글 번호
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	public int deleteBoard(String board_no);

	/**
	 * 게시글 검색
	 * @param board_title
	 * @return 작업성공 : 1, 작업실패 : 0
	 */
	List<BoardVO> getSearchBoard(String board_title);

}
