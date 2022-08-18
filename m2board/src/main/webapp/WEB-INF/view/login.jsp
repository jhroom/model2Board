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
		<h3>로그인페이지</h3>
		<form action="LoginController" method="post">
			ID<input type="text" name="memberId">
			PW<input type="text" name="memberPw">
			<button type="submit">로그인</button>
		</form>
	</div>
	<div>
		${errMsg}
	</div>
</body>
</html>