package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.Member;

public class Memberdao implements IMemberDao {

	@Override
	public Member selectMemberByLogin(Member paramMember, Connection conn) throws SQLException {
		Member member = new Member();
		String sql ="SELECT member_id, member_pw, create_date"
				+ " FROM member WHERE member_id = ? AND member_pw = password(?)";
		PreparedStatement stmt = null;
		ResultSet rest = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			rest = stmt.executeQuery();
			if(rest.next()) {
				member.setMemberId(rest.getString("member_id"));
				member.setMemberPw(rest.getString("member_pw"));
				member.setCreateDate(rest.getString("create_date"));
			}
			
		} finally {
			if(rest != null) { rest.close(); }
			if(stmt != null) { stmt.close(); }
		}
		
		return member;
	}

	@Override
	public int insertMember(Member paramMember, Connection conn) throws SQLException {
		int result = 0;
		String sql = "INSERT INTO member (member_id,member_pw,create_date) VALUES (?,PASSWORD(?),now())";
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, paramMember.getMemberId());
			stmt.setString(2, paramMember.getMemberPw());
			result = stmt.executeUpdate();
			
		} finally {
			if(stmt != null) { stmt.close(); }
		}
		return result;
	}

}
