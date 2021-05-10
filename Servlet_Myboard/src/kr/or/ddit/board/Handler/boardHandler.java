package kr.or.ddit.board.Handler;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.comm.handler.CommandHandler;
import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.util.FileUploadRequestWrapper;
import sun.print.resources.serviceui;

public class boardHandler implements CommandHandler{

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		String flag = (String)req.getParameter("flag");
		
		if("C".equals(flag) || "U".equals(flag)) {
			if(req.getMethod().equals("GET")) { 
				return false;
			}else { 
				return true;
			}
		}
		if("D".equals(flag)) {
			return true;
		}
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		String flag = (String) req.getParameter("flag");

		if("C".equals(flag)) { // 게시글 작성
			if(req.getMethod().equals("GET")) {
				return "/WEB-INF/view/board/BoardBoardInsert.jsp";
			} else {

				BoardVO bv = new BoardVO(); 
				
				
				IBoardService service = BoardServiceImpl.getInstance();
				
				String board_title = req.getParameter("board_title");
				String board_content = req.getParameter("board_content");
				String board_writer = req.getParameter("board_writer");
				bv.setBoard_title(board_title);
				bv.setBoard_content(board_content);
				bv.setBoard_writer(board_writer);
				
				if((String)req.getParameter("attachFile") == "") {
					FileItem item = ((FileUploadRequestWrapper)req).getFileItem("atchFileId");
					AtchFileVO atchFileVO = new AtchFileVO();
					
					IAtchFileService fileService = AtchFileServiceImpl.getInstance();
					atchFileVO = fileService.saveAtchFile(item);
					
					bv.setAtchFileId(atchFileVO.getAtchFileId());
				}
				
				int cnt = service.insertBoard(bv);

				String msg = "";
				
				if(cnt > 0) {
					msg = "성공";
				} else {
					msg = "실패";
				}
				String redirectUrl = req.getContextPath() 
						+ "/board/BoardBoard.do?msg="
						+ URLEncoder.encode(msg, "UTF-8");
				return redirectUrl;
			}
			
		} else if("U".equals(flag)) { // 게시글 수정
			
			
			FileItem item = ((FileUploadRequestWrapper)req).getFileItem("atchFileId");
			
			AtchFileVO atchFileVO = new AtchFileVO();
			
			atchFileVO.setAtchFileId(req.getParameter("atchFileId") == null ?
					-1 : Long.parseLong(req.getParameter("atchFileId")));
			
			if(item !=null && !item.getName().equals("")) {
				
				IAtchFileService fileService = AtchFileServiceImpl.getInstance();
				
				atchFileVO = fileService.saveAtchFile(item); // 첨부파일 저장
			}
			
			IBoardService service = BoardServiceImpl.getInstance();
			
			BoardVO bv = new BoardVO();
			
			bv.setBoard_no(req.getParameter("board_no"));
			bv.setBoard_title(req.getParameter("board_title"));
			bv.setBoard_content(req.getParameter("board_content"));
			bv.setAtchFileId(atchFileVO.getAtchFileId());
			
			int cnt = service.updateBoard(bv);
			
			String msg = "";
			if(cnt > 0) {
				msg = "성공";
			}else {
				msg = "실패";
			}
			String redirectUrl = req.getContextPath() + "/board/BoardBoard.do?msg=" + URLEncoder.encode(msg, "UTF-8");
			return redirectUrl;	
			
		} else if("D".equals(flag)) { // 게시글 삭제
			BoardVO bv = new BoardVO();
			
			bv.setBoard_no(req.getParameter("board_no"));
			
			IBoardService service = BoardServiceImpl.getInstance();
			
			int cnt = service.deleteBoard(board_no);
			
			String msg = "";
			if(cnt > 0){
				msg = "성공";
			} else {
				msg = "실패";
			}
			String redirectUrl = req.getContextPath() + "/board/BoardBoard.do?msg=" + URLEncoder.encode(msg, "UTF-8");
			
			return redirectUrl;
			
		} else if("SEL".equals(flag)) { // 한 게시글 조회
			String BoardNm = req.getParameter("BoardNm");
			
			IBoardService service = BoardServiceImpl.getInstance();
			
			BoardVO bv = service.
			
			if(bv.getAtchFileId() > 0) { // 첨부파일이 존재할 때
				AtchFileVO fileVO = new AtchFileVO();
				
				fileVO.setAtchFileId(bv.getAtchFileId());
				
				IAtchFileService fileService = AtchFileServiceImpl.getInstance();
				
				List<AtchFileVO> atchFileList = fileService.getAtchFileList(fileVO);
				
				req.setAttribute("atchFileList", atchFileList);
			}
			
			req.setAttribute("bv", bv);

			
			return "/WEB-INF/view/board/BoardBoardSelect.jsp";
			
		} else if("SCH".equals(flag)) { // 게시글 검색
			String board_title = "";

			board_title = req.getParameter("board_title");
			
			IBoardService service = BoardServiceImpl.getInstance();
			
			List<BoardVO> list = service.getSearchBoard(board_title);
			
			req.setAttribute("board_title", board_title);
			req.setAttribute("list", list);
			
			return "/WEB-INF/view/board/BoardBoardList.jsp";
			
		} else if("INS".equals(flag)) {
			return "/WEB-INF/view/board/BoardBoardInsert.jsp";
		} else if("UPD".equals(flag)) {
			String BoardNm = (String)req.getParameter("BoardNm");
			
			IBoardService service = BoardServiceImpl.getInstance();
			
			BoardVO bv = service
			
			if(bv.getAtchFileId() > 0) {
				AtchFileVO fileVO = new AtchFileVO();
				fileVO.setAtchFileId(bv.getAtchFileId());
				
				IAtchFileService atchFileService = AtchFileServiceImpl.getInstance();
				List<AtchFileVO> atchFileList = atchFileService.getAtchFileList(fileVO);
				
				req.setAttribute("atchFileList", atchFileList);
			}
			
			req.setAttribute("bv", bv);
			
			return "/WEB-INF/view/board/BoardBoardUpdate.jsp";
		}
		
		// 모든 게시글 조회
		
		IBoardService service = BoardServiceImpl.getInstance();
		List<BoardVO> list = service.AllBoardList();
		req.setAttribute("list", list);	
		return "/WEB-INF/view/board/BoardBoardList.jsp";
	}

}
