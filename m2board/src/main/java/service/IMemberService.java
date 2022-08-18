package service;

import vo.Member;

public interface IMemberService {
	
	
	Member getMemberLogin(Member paramMember);
	
	boolean addMember(Member paramMember);

}
