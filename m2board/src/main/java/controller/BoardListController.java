package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.IBoardService;

@WebServlet("/BoardList")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IBoardService boardService;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("\r\nBoardList Controller doGet 시작");
		
		//컨트롤러 1) 요청 받아 분석
		int rowPerPage = 10;
			if(request.getParameter("rowPerPage") != null) {
				rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
			}
		
		int currentPage = 1;
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
		
		// 2) 서비스 레이어를 요청(메서드 호출) -> 모델값(자료구조) 구하기 위함
		// new
		boardService = new BoardService();
		Map<String, Object> map = boardService.getBoardList(rowPerPage, currentPage);
		System.out.println(map.get("list"));
		System.out.println("lastPage : " + map.get("lastPage"));
		
		request.setAttribute("lastPage", map.get("lastPage"));
		request.setAttribute("list",     map.get("list"));
		request.setAttribute("currentPage", currentPage);
		
		request.getRequestDispatcher("/WEB-INF/view/boardList.jsp").forward(request, response);
		
		
	}


}
