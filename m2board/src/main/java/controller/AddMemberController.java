package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.IMemberService;
import service.MemberService;
import vo.Member;

@WebServlet("/AddMember")
public class AddMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IMemberService memberService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AddMember 컨트롤러 doGet시작");
		
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AddMember 컨트롤러 doPost시작");
		
		String memberId = request.getParameter("id");
		String memberPw = request.getParameter("pw");
		//받은 파라미터 멤버객체에
		Member paramMember = new Member();
		paramMember.setMemberId(memberId);
		paramMember.setMemberPw(memberPw);
		
		this.memberService = new MemberService();
		if(memberService.addMember(paramMember)) { //회원가입에 성공한다면
			System.out.println("회원가입 성공");
			response.sendRedirect(request.getContextPath()+"/LoginController");
		} else {
			System.out.println("회원가입 실패");
			response.sendRedirect(request.getContextPath()+"/AddMember?errMsg=join Fail");
		}
		
		
	}

}
