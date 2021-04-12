package kr.or.ddit.member.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.handler.CommandHandler;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

public class InsertMemberHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/WEB-INF/view/member/insertForm.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return false;
		}else if(req.getMethod().equals("POST")) {
			//redirect
			return true;
		}
		return false; 
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		if (req.getMethod().equals("GET")) { // GET
			return VIEW_PAGE;
		} else if (req.getMethod().equals("POST")) { //Post방식인 경우isRedirect를 한다.
			// 1. 요청파라미터 정보 가져오기
			String memId = req.getParameter("memId");
			String memName = req.getParameter("memName");
			String memTel = req.getParameter("memTel");
			String memAddr = req.getParameter("memAddr");

			// 2. 서비스 객체 생성하기
			IMemberService memberService = MemberServiceImpl.getInstance();

			// 3. 회원정보 등록하기
			MemberVO mv = new MemberVO();
			mv.setMemId(memId);
			mv.setMemName(memName);
			mv.setMemTel(memTel);
			mv.setMemAddr(memAddr);

			int cnt = memberService.insertMember(mv);

			String msg = "";

			if (cnt > 0) {
				msg = "성공";
			} else {
				msg = "실패";
			}
			
			//4. 목록 조회화면으로 이동 - Redirect
			String redirectUrl = req.getContextPath() + 
					"/member/list.do?msg=" + URLEncoder.encode(msg, "UTF-8"); 
			
			return redirectUrl;

		}
		return null;

	}

}
