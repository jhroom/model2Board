package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.DbUtil;
import repository.BoardDao;
import repository.IBoardDao;
import vo.Board;

public class BoardService implements IBoardService {
	private IBoardDao boardDao;

	@Override
	public Map<String, Object> getBoardList(int rowPerPage, int currentPage) {
		System.out.print("service");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Board> list = null;
		Connection conn = null;
		int lastPage = 0;
		int beginRow = (currentPage-1)*rowPerPage;
		
		
		try {
			conn = DbUtil.getConnection();
			this.boardDao = new BoardDao();
			list = boardDao.selectBoardListByPage(beginRow, rowPerPage, conn);
			lastPage = boardDao.selectBoardCnt(conn);
			lastPage = (int)Math.ceil(lastPage/(double)rowPerPage);
			map.put("list", list);
			map.put("lastPage", lastPage);
			System.out.print("boardService : map = " + map.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return map;
	}

	@Override
	public Board getBoardOne(int boardNo) {
		Board board = new Board();
		int result = 0;
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setAutoCommit(false);
			
			this.boardDao = new BoardDao();
			result = boardDao.updateBoardViewer(boardNo, conn);
			board = boardDao.selectBoardOne(boardNo, conn);
			
			if( result ==0 || board == null ) {
				throw new Exception();
			}
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("조회수나 보드one받아오기 오류");
			try { conn.rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
			
		} finally {
			try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return board;
	}

	@Override
	public boolean addBoard(Board board) {
		boolean result = false;
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DbUtil.getConnection();
			this.boardDao = new BoardDao();
			row = boardDao.insertBoard(board, conn);
			
			if( row == 1 ) { // 글쓰기 성공하면
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return result;
	}

}
