package kr.or.ddit.board.vo;
/**
 * DB 테이블에 있는 컬럼을 객체화한 클래스
 * @author pc24
 * 
 * DB테이블의 '컬럼'이 이 클래스 '멤버변수'가 된다.
 * DB테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할을 수행한다. 
 * 
 * VO(Value Object)란?
 * - 값 객체 패턴(Value object pattern) 즉, 객체를 값처럼 쓸 수 있고 
 * 	 , 제약 사항 중 하나는 객체의 인스턴스 변수가 생성자를 통해서 일단 설정된 후에는
 * 	 결코 변하지 않음을 보장
 * 
 *  ---> setter 필요없다. ?
 */
public class BoardVO {
	//JDBC_BOARD
	// board_no/board_title/board_writer/board_date/board_content
	private String board_no;
	private String board_title;
	private String board_writer;
	private String board_date;
	private String board_content;
	
	public String getBoard_no() {
		return board_no;
	}
	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	
	@Override
	public String toString() {
		return "BoardVO [board_no=" + board_no + ", board_tilte=" + board_title + ", board_writer=" + board_writer
				+ ", board_date=" + board_date + ", board_content=" + board_content + "]";
	}

	
	
}
