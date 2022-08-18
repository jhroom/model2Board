package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.IMemberService;
import service.MemberService;
import vo.Member;

@WebServlet("/LoginController")

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IMemberService memberService;
       
	//login form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\r\nloginController DoGET 시작");
		
		if(request.getParameter("errMsg") != null) {
			request.getParameter("errMsg");
		}
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			System.out.println("세션이 있다");
			response.sendRedirect(request.getContextPath()+"/Index");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		rd.forward(request, response);
	}

	//login action
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\r\nloginController DoPOST 시작");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/Index");
			return;
		}
		
		request.setCharacterEncoding("utf8");
		String id = request.getParameter("memberId"); System.out.println(id);
		String pw = request.getParameter("memberPw"); System.out.println(pw);
		Member paramMember = new Member();
		paramMember.setMemberId(id);
		paramMember.setMemberPw(pw);
		
		memberService = new MemberService();
		Member member = memberService.getMemberLogin(paramMember);
		
		if(member.getMemberId() == null) {
			System.out.println("로그인 실패");
			response.sendRedirect(request.getContextPath()+"/LoginController?errMsg=login Fail");
			return;
		}
		
		//브라우저가 톰캣이 연결되면 세션생성 하고 request를 쓸 수 있다.
		session.setAttribute("loginMember", member);
		response.sendRedirect(request.getContextPath()+"/Index");

	}

}
