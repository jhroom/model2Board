package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import vo.Nice;

public class NiceDao implements INiceDao {

	@Override
	public int insertNice(Nice nice, Connection conn) throws SQLException {
		int result = 0;
		String sql = "INSERT INTO nice (board_no,member_id,create_date)"
				+ "VALUES (?, ?, NOW())";
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, nice.getBoardNo());
			stmt.setString(2, nice.getMemberId());
			result = stmt.executeUpdate();
			
		} finally {
			if(stmt != null) { stmt.close(); }
		}
		
		return result;
	}

}
