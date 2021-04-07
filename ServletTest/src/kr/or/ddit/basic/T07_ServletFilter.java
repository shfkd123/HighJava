package kr.or.ddit.basic;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T07_ServletFilter implements Filter{
	/**
	 * 서블릿 필터에 대하여...
	 * 
	 * 1. 사용목적
	 * - 클라이언트의 요청을 수행하기 전에 가로채 필요한 작업을 수행할 수 있다.
	 * - 클라이언트에 응답정보를 제공하기 전에 응답정보에 필요한 작업을 수행할 수 있다.
	 * 
	 * 2. 사용 예
	 * - 인증필터 ex)로그인
	 * - 데이터 압출필터 ex)데이터를 압축해서 resp 해줄 때
	 * - 인코딩 필터 ex) utf-8
	 * - 로깅 및 감사처리 필터 ex) log 저장
	 * - 이미지 변환 필터 등 ex) 특정한 이미지인 경우 다른 이미지 형태로 변환
	 */

	@Override
	public void destroy() {
		//필터 객체가 웹컨테이너에 의해 서비스로부터 제거되기 전에 호출됨.
		System.out.println("destroy() 호출됨.");
		
	}

	@Override 
	public void init(FilterConfig fc) throws ServletException {
		System.out.println("init() 메서드 호출됨.");
		
		//초기화 파라미터정보 가져오기
		String initParam = fc.getInitParameter("init-param");
		
		System.out.println("init-param : " + initParam);
		
	}

	 //HttpServletRequest req, HttpServletResponse resp 보다 더 상위 객체 -Http 서버만 사용하는 것이 아니기 때문! 
	//우리가 사용할 때는 Http req, resp가 넘어온다.
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("T07_ServletFilter 시작.");
		
		//클라이언트의 IP주소 가져오기
		String ipAddress = req.getRemoteAddr();
		
		System.out.println("IP주소 : " + ipAddress 
							+ "\n포트번호 : " + req.getRemotePort()
							+ "\n현재 시간 : " + new Date().toString());
		//필터체인을 실행한다. (req, resp객체 전달)
		fc.doFilter(req, resp);
		//필터가 여러개일 때 다음 필터로 가고, 없다면 바로 사용자가 요청한 서블릿 객체로 간다.
		
		//---- 이 구간은 필터가 다시 돌아갈 때 로직 reps-----//
		System.out.println("T07_ServletFilter 완료.");
		
	}
}
