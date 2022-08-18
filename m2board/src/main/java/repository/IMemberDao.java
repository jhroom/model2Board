package repository;

import java.sql.Connection;
import java.sql.SQLException;

import vo.Member;

public interface IMemberDao {
	
	//세션에 저장될 멤버의 정보일부 리턴  id,pw받고
	Member selectMemberByLogin(Member paramMember, Connection conn) throws SQLException;
	
	int insertMember(Member paramMember, Connection conn) throws SQLException;

}
