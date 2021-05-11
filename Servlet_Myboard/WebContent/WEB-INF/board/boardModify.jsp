<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVO bv = (BoardVO)request.getAttribute("bv");
	String msg = request.getParameter("msg") == null ? "": request.getParameter("msg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<form action="/Servlet_Myboard/BoardUpdateServlet" method="post" id="">
		<table>
			<tr>
				<td>번	호:</td>
				<td><input type="text" id="board_no" name="board_title" value="<%=bv.getBoard_no()%>"></td>
			</tr>
			<tr>
				<td>제	 목:</td>
				<td><input type="text" name="board_title" value="<%=bv.getBoard_title()%>"></td>
			</tr>
			<tr>
				<td>작성자:</td>
				<td><input type="text" name="board_writer" value="<%=bv.getBoard_writer()%>"></td>
			</tr>
			<tr>
				<td>내	 용:</td>
				<td><textarea rows="30" cols="30" name="board_content" value="<%=bv.getBoard_content()%>"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="게시글 수정">
		<button type="button" onclick="delete()">삭제</button>
	</form>
</body>
<script type="text/javascript">
$(document).ready(function(){
	function delete(){
		if(confirm("정말로 삭제하시겠습니까?")){
			alert("삭제되었습니다.");
			document.getElementById("board_no").value = "<%=bv.getBoard_no() %>";
			var fm = document.getElementById("fm");
			fm.method = "post";
			fm.action = "/Servlet_Myboard/BoardDeleteServlet";
			fm.submit();
		} else {
			return;
		}
	}
});
</script>
</html>