package service;

import java.sql.Connection;
import java.sql.SQLException;

import commons.DbUtil;
import repository.INiceDao;
import repository.NiceDao;
import vo.Nice;

public class NiceService implements INiceService {
	private INiceDao niceDao;
	
	@Override
	public boolean addNice(Nice nice) {
		boolean result = false;
		int row = 0;
		Connection conn = null;
		
		try {
			conn = DbUtil.getConnection();
			conn.setAutoCommit(false);
			this.niceDao = new NiceDao();
			row = niceDao.insertNice(nice, conn);
			if( row == 1) {	//좋아요 올리기 성공한다면
				result = true;
				conn.commit();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try { conn.rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
		} finally {
			try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return result;
	}

}
