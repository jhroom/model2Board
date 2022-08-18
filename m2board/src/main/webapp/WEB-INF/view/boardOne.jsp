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
	<h3>게시글 상세보기</h3>
		<table border="1">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>내용</th>
					<th>작성일</th>
					<th>조회</th>
					<th>좋아요</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${board.boardNo}</td>
					<td>${board.boardTitle}</td>
					<td>${board.boardWriter}</td>
					<td>${board.boardDetail}</td>
					<td>${board.createDate}</td>
					<td>${board.viewer}</td>
					<td>${board.nice}</td>
				</tr>
			</tbody>
		</table>
		<div>
			<form action="Nice" method="post">
				<input type="hidden" name="no" value="${board.boardNo}">
				<input type="hidden" name="id" value="${loginMember.memberId }">
				
				<button type="submit">좋아요</button>
			</form>
		</div>
		<div>
			<a href="${pageContext.request.contextPath }/BoardList">글목록으로가기</a>
		</div>
	</div>
</body>
</html>