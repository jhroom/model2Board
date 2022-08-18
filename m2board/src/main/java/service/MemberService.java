package service;

import java.sql.Connection;
import java.sql.SQLException;

import commons.DbUtil;
import repository.IMemberDao;
import repository.Memberdao;
import vo.Member;

public class MemberService implements IMemberService {
	IMemberDao memberDao;
	
	@Override
	public Member getMemberLogin(Member paramMember) {
		Member member = new Member();
		Connection conn = null;
		
		try {
			conn = DbUtil.getConnection();
			memberDao = new Memberdao();
			
			//paramMember id,pw로 로그인된 member의 정보
			member = memberDao.selectMemberByLogin(paramMember, conn);
			System.out.println("로그인된 멤버 : " + member);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		
		return member;
	}

	@Override
	public boolean addMember(Member paramMember) {
		boolean result = false;
		Connection conn = null;
		try {
			conn = DbUtil.getConnection();
			conn.setAutoCommit(false);
			memberDao = new Memberdao();
			int row = memberDao.insertMember(paramMember, conn);
			if( row != 1) { //멤버가입에 실패한다면
				throw new Exception();
			}
			conn.commit();
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			try { conn.rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
		} finally {
			try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return result;
	}

}
