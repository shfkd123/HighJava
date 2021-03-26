package Service;

import java.util.List;

import VO.BoardVO;

public interface BoardService {
	//새글작성, 수정, 삭제, 검색, 전체 출력
	
	/**
	 * 새글 작성
	 * @param bv DB에 insert할 자료가 저장된 BoardVO객체
	 * @return DB작업이 성공하면 1이상의 값이 반환, 실패하면 0 반환
	 */
	public int insertBoard(BoardVO bv);
	
	/**
	 * 전체 게시글정보 조회 
	 * @return 게시글정보를 담고있는 List객체
	 */
	public List<BoardVO> AllBoardList();
	
	/**
	 * 하나의 게시글정보를 수정
	 * @param bv Board객체
	 * @return 작업성공 1, 작업실패 0
	 */
	public int updateBoard(BoardVO bv);
	
	/**
	 * 게시글 삭제
	 * @param board_no 삭제할 게시글 번호
	 * @return 작업 성공 1, 작업실패 0
	 */
	public int deleteBoard(String board_no);
	
	/**
	 * 게시글 검색
	 * @param board_title
	 * @return 작업성공1, 작업실패 0
	 */
	public List<BoardVO> findBoardList(String board_title);

	/**
	 * 게시글 체크
	 * @param board_no
	 * @return 작업성공1, 작업실패 0 
	 */
	public boolean checkBoard(String board_no);
}
 