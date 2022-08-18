package repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import vo.Board;

public interface IBoardDao {
	
	List<Board> selectBoardListByPage(int beginRow, int rowPerPage, Connection conn) throws SQLException;
	int selectBoardCnt(Connection conn)throws SQLException ;
	
	Board selectBoardOne(int boardNo,Connection conn) throws SQLException;
	
	int insertBoard(Board board,Connection conn)throws SQLException;
	
	int updateBoardViewer(int boardNo, Connection conn)throws SQLException;

}
