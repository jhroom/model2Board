package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Board;

public class BoardDao implements IBoardDao {

	@Override
	public List<Board> selectBoardListByPage(int beginRow, int rowPerPage, Connection conn) throws SQLException {
		List<Board> list = new ArrayList<Board>();
		Board board = null;

		String sql = "SELECT b.board_no, b.board_title, b.board_writer, b.create_date, b.viewer, IFNULL( t.cnt,0) cnt"
				+ " From board b LEFT join\r\n"
				+ " (SELECT board_no, COUNT(*) cnt\r\n"
				+ " FROM nice\r\n"
				+ " GROUP BY board_no) t"
				+ " ON b.board_no = t.board_no"
				+ " LIMIT ?,?";
		PreparedStatement stmt = null;
		ResultSet rest = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rest = stmt.executeQuery();
			
			while(rest.next()) {
				board = new Board();
				
				board.setBoardNo(rest.getInt("board_no"));
				board.setBoardTitle(rest.getString("board_title"));
				board.setBoardWriter(rest.getString("board_writer"));
				board.setCreateDate(rest.getString("create_date"));
				board.setViewer(rest.getInt("viewer"));
				board.setNice(rest.getInt("cnt"));
				list.add(board);
				System.out.print("list : " + list);
			}
			
		} finally {
			if(rest != null) { rest.close(); }
			if(stmt != null) { stmt.close(); }
		}
		
		return list;
	}

	@Override
	public int selectBoardCnt(Connection conn) throws SQLException {
		int count = 0;
		String sql = "SELECT COUNT(*) count FROM board";
		PreparedStatement stmt = null;
		ResultSet rest = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			rest = stmt.executeQuery();
			
			if(rest.next()) {
				count = rest.getInt("count");
			}
			
		} finally {
			if(rest != null) { rest.close(); }
			if(stmt != null) { stmt.close(); }
		}
		return count;
	}

	@Override
	public Board selectBoardOne(int boardNo, Connection conn) throws SQLException {
		Board board = new Board();
		String sql = "SELECT b.board_no, b.board_title, b.board_writer, b.board_detail, b.create_date"
				+ ", b.viewer, ifnull(t.cnt,0) cnt"
				+ " From board b left JOIN"
				+ " (SELECT board_no, COUNT(*) cnt"
				+ " FROM nice"
				+ " GROUP BY board_no) t"
				+ " ON b.board_no = t.board_no"
				+ " WHERE b.board_no = ? ";
		PreparedStatement stmt = null;
		ResultSet rest = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			rest = stmt.executeQuery();
			if(rest.next()) {
				board.setBoardNo(rest.getInt("board_no"));
				board.setBoardTitle(rest.getString("board_title"));
				board.setBoardWriter(rest.getString("board_writer"));
				board.setBoardDetail(rest.getString("board_detail"));
				board.setCreateDate(rest.getString("create_date"));
				board.setViewer(rest.getInt("viewer"));
				board.setNice(rest.getInt("cnt"));
			}
		} finally {
			if(rest != null) { rest.close(); }
			if(stmt != null) { stmt.close(); }
		}
		return board;
	}

	@Override
	public int insertBoard(Board board, Connection conn) throws SQLException {
		int result = 0;
		String sql = "INSERT INTO board (board_title, board_detail, board_writer,create_date)"
				+ " VALUES (?,?,?,now())";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, board.getBoardTitle());
			stmt.setString(2, board.getBoardDetail());
			stmt.setString(3, board.getBoardWriter());
			result = stmt.executeUpdate();
			
		} finally {
			if(stmt != null) { stmt.close(); }
		}
		return result;
	}

	@Override
	public int updateBoardViewer(int boardNo, Connection conn) throws SQLException {
		int result = 0;
		String sql = "UPDATE board SET viewer=viewer+1 WHERE board_no = ?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, boardNo);
			result = stmt.executeUpdate();
		} finally {
			if(stmt != null) { stmt.close(); }
		}
		return result;
	}

}
