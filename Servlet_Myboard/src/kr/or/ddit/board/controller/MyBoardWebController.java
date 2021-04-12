package kr.or.ddit.board.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.impl.activation.CommandHandler;

//사용자 요청을 받아서 처리하는 컨트롤러 구현하기
public class MyBoardWebController extends HttpServlet {
	
	// 매핑정보 저장 (핸들러 객체 저장용 맵)
	private Map<String, CommandHandler> cmmHandlerMap = new HashMap<>();
	
	/**
	 * 서블릿의 초기화
	 * 서블릿은 클라이언트로부터 최초 요청시 단 한번 초기화되며 생성된다.
	 * WAS 내부의 서블릿 컨테이너에서 서블릿 객체 생성 후 초기화 시에 init() 메서드를 호출하는데 
	 * 이 과정을 서블릿 로딩이라 한다.
	 * 이후 해당 서블릿 객체는 서블릿 컨테이너에서 대기하다가 동일한 요청이 있을 시 service() 메서드를 
	 * 통해 요청을 처리한다. 
	 * 
	 * init() 초기화 메서드
	 * : HttpServlet의 최상위 클래스인 Servlet 인터페이스에 정의되어 있다. 
	 * 
	 * ServletConfig 
	 * : 서블릿에 대한 초기화 작업기능
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		String configFilePath = config.getInitParameter("handler-config");
		
		Properties handlerProp = new Properties();
		
		// 설정파일을 읽어서 대응되는 핸들러 객체를 생성하여 맵에 등록하기
		String configFileRealPath = config.getServletContext().getRealPath(configFilePath);
		
		FileReader fr;
		
		try {
			fr = new FileReader(configFileRealPath);
			handlerProp.load(fr);
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		
		for (Object key : handlerProp.keySet()) {
			String command = (String) key;
			
			try {
				Class<?> klass = Class.forName(handlerProp.getProperty(command));
				CommandHandler handler = (CommandHandler) klass.newInstance();
			}catch (Exception ex) {
				ex.printStackTrace();
				throw new ServletException();
			}
		}
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	/**
	 * 요청 처리 메서드
	 * @param req
	 * @param resp
	 */
	private void process(HttpServletRequest req, HttpServletResponse resp) {
		
		/**
		 * getRequestURI() 함수 : 프로젝트 + 파일경로
		 * 
		 * 예) http://localhost:8080/project/list.jsp
		 * 		==>  /project/list.jsp 를 가져온다. 
		 * 
		 */
		String reqURI = req.getRequestURI(); 
		
		/**
		 * getContextPath() 함수 : 프로젝트 path만 가져온다.
		 * 예) http://localhost:8080/project/list.jsp
		 *		==> /project
		 * 
		 */
		if(reqURI.indexOf(req.getContextPath()) == 0) {
			reqURI = reqURI.substring(req.getContextPath().length());
		}
		
		CommandHandler handler = cmmHandlerMap.get(reqURI);
		
		if(handler == null) {
	         handler = new NullHandler();
	      }
		
	}

}
