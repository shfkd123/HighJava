<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 등록</title>
</head>
<body>
	<form action="/Servlet_Myboard/BoardInsertServlet" method="post">
		<table>
			<!-- board_no, board_title, board_writer, board_date, board_content  -->
			<tr>
				<td>제	 목:</td>
				<td><input type="text" name="board_title" value=""></td>
			</tr>
			<tr>
				<td>작성자:</td>
				<td><input type="text" name="board_writer" value=""></td>
			</tr>
			<tr>
				<td>내	 용:</td>
				<td><textarea rows="30" cols="30" name="board_content"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="게시글 등록">
	</form>
</body>
</html>