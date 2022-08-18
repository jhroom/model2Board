<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	
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
		<h3>회원가입 페이지</h3>
		
		<form action="AddMember" method="post">
			ID<input type="text" name="id">
			PW<input type="password" name="pw">
		
			<button type="submit">회원가입</button>
		</form>
		
	</div>
</body>
</html>