package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import service.IBoardService;
import vo.Board;

@WebServlet("/AddBoard")
public class AddBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IBoardService boardService;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\r\nAddBoard Controller DoGET 시작");
		
		// 로그인 된사람만 글 쓰기
		HttpSession session = request.getSession();
		session.getAttribute("loginMember");
		
		if(session.getAttribute("loginMember") == null) {
			response.sendRedirect(request.getContextPath()+"/LoginController");
			return;
		}
		//로그인멤버의 정보를 가지고 글쓰기페이지로 가기
		request.getRequestDispatcher("/WEB-INF/view/insertBoard.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\r\nAddBoard Controller DoPOST 시작");
		
		request.setCharacterEncoding("utf8");
		String title = request.getParameter("title");     System.out.print("title : " + title);
		String writer = request.getParameter("writer");   System.out.print(" writer : " + writer);
		String content = request.getParameter("content"); System.out.println(" content : " + content);
		
		Board addBoard = new Board();	//insertBoard에서 받아온 파라미터 객체화
		addBoard.setBoardTitle(title);
		addBoard.setBoardWriter(writer);
		addBoard.setBoardDetail(content);
		
		this.boardService = new BoardService();	//의존성 줄이기
		if(boardService.addBoard(addBoard)) {	//파라미터 받아온 객체들 add에 성공하면
			response.sendRedirect(request.getContextPath()+"/BoardList");
		} else {
			System.out.println("보드 입력 실패");
		}
		
	}

}
