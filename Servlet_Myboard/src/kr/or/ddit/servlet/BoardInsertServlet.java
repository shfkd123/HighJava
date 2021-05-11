package kr.or.ddit.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

@WebServlet("/BoardInsertServlet")
public class BoardInsertServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher =  request.getRequestDispatcher("/WEB-INF/board/insertBoardForm.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_title = request.getParameter("board_title");
		String board_writer = request.getParameter("board_writer");
		String board_content = request.getParameter("board_content");
		String board_no = "";
		String board_date = "";
		
		IBoardService service = BoardServiceImpl.getInstance();

		BoardVO bv = new BoardVO();
		
		bv.setBoard_no(board_no);
		bv.setBoard_title(board_title);
		bv.setBoard_writer(board_writer);
		bv.setBoard_date(board_date);
		bv.setBoard_content(board_content);
		
		
		int cnt = service.insertBoard(bv);
		
		String msg = "";
		
		if(cnt > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		String redirectUrl = request.getContextPath() + 
				"/BoardListServlet?msg=" + URLEncoder.encode(msg, "UTF-8"); 
		response.sendRedirect(redirectUrl);
	}

}
