package kr.or.ddit.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

@WebServlet("/BoardUpdateServlet")
public class BoardUpdateServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/WEB-INF/board/boardModify.jsp";
		String board_no = request.getParameter("board_no");
		
		IBoardService service = BoardServiceImpl.getInstance();
		
		BoardVO bv = service.getBoardInfo(board_no);
		
		request.setAttribute("bv", bv);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_title = request.getParameter("board_title");
		String board_writer = request.getParameter("board_writer");
		String board_content = request.getParameter("board_content");
		String board_date = "";
		
		IBoardService service = BoardServiceImpl.getInstance();

		BoardVO bv = new BoardVO();
		
		bv.setBoard_title(board_title);
		bv.setBoard_writer(board_writer);
		bv.setBoard_date(board_date);
		bv.setBoard_content(board_content);
		
		int cnt = service.updateBoard(bv);
		
		response.sendRedirect("/Servlet_Myboard/BoardListServlet");

	}

}
