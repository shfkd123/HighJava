<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardVO> boardList = (List<BoardVO>)request.getAttribute("boardList");
	
	String msg = request.getParameter("msg") == null ? "": request.getParameter("msg");



%>    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록</title>
</head>
<body>
	<table border="1">
	board_no/board_title/board_writer/board_date/board_content
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>글쓴이</td>
			<td>날짜</td>
			<td>내용</td>
		</tr>
		<%
		int boardSize = boardList.size();
		
		if(boardSize > 0){
			for(int i =0; i< boardSize; i++){
		 %>
		 <tr>
		 	<td><%=boardList.get(i).getBoard_no() %></td>
		 	<td><%=boardList.get(i).getBoard_title() %></td>
		 	<td><%=boardList.get(i).getBoard_writer() %></td>
		 	<td><%=boardList.get(i).getBoard_date() %></td>
		 	<td><%=boardList.get(i).getBoard_content() %></td>
		 </tr>

		 <%
			}
		}else {
			%>
			<tr>
				<td colspan="5">게시판을 찾을 수 없습니다.</td>
			</tr>
			<%
		}
		
	%>
		<tr align="center">
			<td colspan="5"><a href="insertBoardServlet.do">[게시글 등록]</a></td>
		</tr>
	</table>
	
	<%
		if(msg.equals("성공")){ //성공메시지가 전달되면
	%>
		<script>
			alert("정상적으로 처리되었습니다.");
		</script>
	<%
		}
		
	%>
</body>
</html>