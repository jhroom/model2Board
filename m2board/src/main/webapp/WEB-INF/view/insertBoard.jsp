<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	System.out.println();
	System.out.println("insertBoard  시작");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<div>
		<form action="AddBoard" method="post">
			<div>
				제목<input type="text" name="title">
				작성자<input type="text" name="writer" value="${loginMember.memberId}" readonly>
			</div>
			
			<div>내용<textarea rows="5" cols="50" name="content"></textarea></div>
			
			<button type="submit">글쓰기</button>
		</form>
	</div>
</body>
</html>