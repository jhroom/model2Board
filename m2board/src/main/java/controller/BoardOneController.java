package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.IBoardService;
import vo.Board;

@WebServlet("/BoardOne")
public class BoardOneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IBoardService boardService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\r\nBoardOne Controller 시작");
		
		int boardNo = 0;
		if( request.getParameter("boardNo") != null) {
			boardNo = Integer.parseInt(request.getParameter("boardNo"));
			System.out.println("boardNo : " + boardNo);
		} else {
			System.out.println("잘못된 접근");
			response.sendRedirect(request.getContextPath()+"/BoardList?errMsg=InvalidAcess");
			return;
		}
		
		boardService = new BoardService();
		Board board = new Board();
		board = boardService.getBoardOne(boardNo);
		
		request.setAttribute("board", board);
		
		request.getRequestDispatcher("/WEB-INF/view/boardOne.jsp").forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
