package kr.or.ddit.board;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

public class Board {

	//서비스 객체 멤버 변수 선언
	private IBoardService boardService;

	public Board() {
		boardService = new BoardServiceImpl();
	}
	
	
	private Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		Board b = new Board();
		b.start();
	}
	//메뉴 출력
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === BOARD ===");
		System.out.println("  1. 새글 작성");
		System.out.println("  2. 작성글 수정");
		System.out.println("  3. 작성글 삭제");
		System.out.println("  4. 작성글 검색");
		System.out.println("  5. 전체 자료 출력");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	private void start() {
		int input = 0;
		while(true) {
			displayMenu(); //메뉴 출력 함수 호출
			input = scan.nextInt();
			switch (input) {
			case 1:
				insertBoard();
				break;
			case 2:
				updateBoard();
				break;
			case 3:
				deleteBoard();
				break;
			case 4:
				findBoard();
				break;
			case 5:
				AllBoard();
				break;
			case 6:
				System.out.println("게시판을 종료합니다.");
				System.exit(0);
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
				
			}
		}
		
	}

	//전체 게시판 출력
	private void AllBoard() {
		System.out.println();
		System.out.println("-----------------------------------------------------------------");
		System.out.println(" NO\t제목\t작성자\t날짜\t\t\t내용");
		System.out.println("-----------------------------------------------------------------");
		
		List<BoardVO> boardList = boardService.getAllBoardList();
		
		for (BoardVO bv : boardList) {
			System.out.println(bv.getBoard_no() + "\t" + bv.getBoard_title() + "\t"
								+ bv.getBoard_writer() + "\t" + bv.getBoard_date() + "\t" + bv.getBoard_content());
		}
		
		System.out.println("-----------------------------------------------------------------");
	}

	//게시판 검색
	private void findBoard() {
		System.out.println("검색할 제목 입력 >>");
		
		String title = scan.next();
		System.out.println();
		System.out.println("-----------------------------------------------------------------");
		System.out.println(" NO\t제목\t작성자\t날짜\t\t\t내용");
		System.out.println("-----------------------------------------------------------------");
		
		List<BoardVO> boardList = boardService.findBoardList(title);

		for (BoardVO bv : boardList) {
			System.out.println(bv.getBoard_no() + "\t" + bv.getBoard_title() +  "\t" +bv.getBoard_writer() +  "\t" +bv.getBoard_date()
			+  "\t" + bv.getBoard_content());
		}
	}

	
	//게시판 삭제
	private void deleteBoard() {
		AllBoard();
		System.out.println();
		System.out.println("삭제할 게시판 번호를 입력하세요.");
		String board_no = scan.next();
		
		int cnt = boardService.deleteBoard(board_no);
		
		if(cnt > 0) {
			System.out.println("삭제성공!!");
		}else {
			System.out.println("삭제 실패!!");
		}
	}

	//게시판 수정
	private void updateBoard() {
		boolean chn = false;
		String board_no = null;
		
		do {
			AllBoard();
			System.out.println();
			System.out.println("수정할 게시글 번호를 입력하세요.");
			board_no = scan.next();
			
			chn = boardService.checkBoard(board_no);
			
			if(chn == false) {
				System.out.println("게시글 번호로 확인된 게시글이 없습니다.");
				System.out.println("다시 입력해주세요.");
			}
		}while(chn == false);

		System.out.println("변경할 제목 입력 >>");
		String title = scan.next();
		
		System.out.println("변경할 작성자 입력 >>");
		String writer = scan.next();
		
		scan.nextLine(); // 입력버퍼 비우기
		System.out.println("변경할 내용 입력 >>");
		String content = scan.next();
		
		BoardVO bv = new BoardVO();
		bv.setBoard_no(board_no);
		bv.setBoard_title(title);
		bv.setBoard_writer(writer);
		bv.setBoard_content(content);
		
		int cnt = boardService.updateBoard(bv);
		
		if (cnt > 0) {
			System.out.println("수정 성공!!");
		} else {
			System.out.println("수정 실패");
		}
		
		
	}

	//게시판 추가
	private void insertBoard() {
		
		System.out.println("제목 >>");
		String title = scan.next();
		System.out.println("글쓴이 >>");
		String writer = scan.next();
		System.out.println("내용 >>");
		String content = scan.next();
		
		BoardVO bv = new BoardVO();
		bv.setBoard_title(title);
		bv.setBoard_writer(writer);
		bv.setBoard_content(content);
		
		int cnt = boardService.insertBoard(bv);
		
		
		if (cnt > 0) { 
			System.out.println("게시글 작성 성공!!");
		}else {
			System.out.println("게시글 작성 실패!!");
		}
		
	}
	

}
