package service;

import java.util.Map;

import vo.Board;

public interface IBoardService {
	
	//반환값 : List<Board>, int lastPage
	Map<String, Object> getBoardList(int rowPerPage,int currentPage);
	Board getBoardOne(int boardNo);
	boolean addBoard(Board board);

}
