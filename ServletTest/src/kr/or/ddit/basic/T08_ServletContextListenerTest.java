package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T08_ServletContextListenerTest extends HttpServlet{
	//ServletContextListener - 만든 객체들을 관리,보관 하기 위한 객체 애플리케이션(우리가 만든) 하나당 한개만 생성
	//						 - 사용자별로 보관하는 기능은 없음

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().setAttribute("ATTR1", "속성1"); //속성값 추가
		
		req.getServletContext().setAttribute("ATTR1", "속성11"); //속성값 변경
		
		req.getServletContext().setAttribute("ATTR1", "속성2"); //속성값 추가
		
		this.getServletContext().removeAttribute("ATTR1"); //속성값 제거
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
