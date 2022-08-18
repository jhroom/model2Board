<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	System.out.println("index.jsp 시작");
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
		<p>${loginMember.memberId}님</p>
		<p>가입날짜 : ${loginMember.createDate }</p>
	</div>
	<a href="BoardList">글목록가기</a>
</body>
</html>