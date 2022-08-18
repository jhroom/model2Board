<%@page import="service.MemberService"%>
<%@page import="service.IMemberService"%>
<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	System.out.println("loginAction 시작");

	request.setCharacterEncoding("utf8");
	String memberId = request.getParameter("memberId");
	String memberPw = request.getParameter("memberPw");
	
	Member paramMember = new Member();
	paramMember.setMemberId(memberId);
	paramMember.setMemberPw(memberPw);
	
	Member member = new Member();
	IMemberService memberService;
	memberService = new MemberService();
	
	member = memberService.getMemberLogin(paramMember);
	if( member != null){
		session.setAttribute("loginMember", member);
		response.sendRedirect(request.getContextPath()+"/LoginController");
		
	} else {
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/LoginController");
	}
	
%>		
