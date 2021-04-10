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
<h1>게시글 목록</h1>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>글쓴이</td>
			<td>날짜</td>
			<td>내용</td>
			<td align="center" colspan="2">게시글관리</td>
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
		 	<td><button type="button">게시글 수정</button></td>
		 	<td><button type="button">게시글 삭제</button></td>
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
			<td colspan="7"><a href="boardList.do">[게시글 목록]</a></td>
		</tr>
		<tr align="center">
			<td colspan="7"><a href="insertBoard.do">[게시글 글쓰기]</a></td>
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