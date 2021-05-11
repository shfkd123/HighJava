package kr.or.ddit.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

@WebServlet("/BoardDeleteServlet")
public class BoardDeleteServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/WEB-INF/board/boardModify.jsp";
		String board_no = request.getParameter("board_no");
		
		IBoardService service = BoardServiceImpl.getInstance();
		
		BoardVO bv = service.getBoardInfo(board_no);
		
		request.setAttribute("bv", bv);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String board_no = request.getParameter("board_no");
		
		IBoardService service = BoardServiceImpl.getInstance();

		BoardVO bv = new BoardVO();
		
		bv.setBoard_no(board_no);
		
		int cnt = service.deleteBoard(board_no);
		
		String msg = "";
		if(cnt > 0){
			msg = "성공";
		} else {
			msg = "실패";
		}
		request.getRequestDispatcher("/Servlet_Myboard/BoardListServlet?msg=" 
		+ URLEncoder.encode(msg, "UTF-8")).forward(request, response);
	}

}
