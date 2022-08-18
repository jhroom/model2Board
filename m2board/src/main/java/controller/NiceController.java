package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.INiceService;
import service.NiceService;
import vo.Nice;

@WebServlet("/Nice")

public class NiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	INiceService niceService;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\r\nNiceController DoGET 시작");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\r\nNiceController DoPOST 시작");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		
		//좋아요 눌른 글의 번호와 세션에있는 로그인된 멤버 아이디 받기
		int boardNo = Integer.parseInt(request.getParameter("no"));  System.out.println("좋아요누른글no : " + boardNo);
		String memberId = request.getParameter("id"); System.out.println("좋아요누른멤버id : " + memberId);
		
		Nice nice = new Nice();
		nice.setBoardNo(boardNo);
		nice.setMemberId(memberId);
		
		
		this.niceService = new NiceService();
		
		if(niceService.addNice(nice)) { //좋아요 누른것이 성공한다면
			response.sendRedirect(request.getContextPath()+"/BoardOne?boardNo="+boardNo);
		} else {
			response.sendRedirect(request.getContextPath()+"/BoardOne?boardNo="+boardNo);
			System.out.println("좋아요는 한번만 누를 수 있습니다.");
		}
		
		
	}

}
