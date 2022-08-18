package repository;

import java.sql.Connection;
import java.sql.SQLException;

import vo.Nice;

public interface INiceDao {
	
	int insertNice(Nice nice, Connection conn) throws SQLException;

}
