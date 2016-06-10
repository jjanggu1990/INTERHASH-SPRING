package mybatis;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.MemberCommand;

@Repository
public class MemberDAO {
	@Autowired
	SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}
	
	public int insertMember(MemberCommand command){
		return session.insert("member.insert", command);
	}
	
	public MemberCommand modify(String email){
		return session.selectOne("member.selectMember", email);
		
	}
	
	public int modifyPro(MemberCommand command){
		return session.update("member.updateMember", command);
	}
	
	public int deleteMember(String email){
		return session.delete("member.delete", email);
	}
	
	public String checkDelete(String email){
		return session.selectOne("member.checkdelete",email);
	}
	public String findPassword(MemberCommand info){
		return session.selectOne("UserInfo.findPassword",info);
	}
}
