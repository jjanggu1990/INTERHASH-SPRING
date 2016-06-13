package mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.AdminCommand;
import spring.model.MemberCommand;
import spring.model.WithdrawalCommand;

@Repository
public class AdminDAO {
	
	
	@Autowired
	SqlSession session;
	
	public void setSession(SqlSession session){
		this.session=session;
	}
	
	public List<AdminCommand> selectHash(){
		return session.selectList("admin.admincount");
	}
	
	public void deleteMember(String value){

		String [] tmp = value.substring(1, value.length()-1).trim().split(",");
		for (int i = 0 ; i < tmp.length;i++){	
			session.delete("admin.deleteMember",tmp[i]);
		}

	}
	
	public List<MemberCommand> selectMember(){
		return session.selectList("member.selectAll");
	}
	
	public void adminInsert(String value){
		String [] tmp = value.substring(1, value.length()-1).trim().split(",");
		for (int i = 0 ; i < tmp.length;i++){	
			session.insert("admin.insert_content", tmp[i]);
		}
	}
	
	
	public int insertMember(WithdrawalCommand members){
		return session.insert("admin.withinsert", members);
	}
	
	public List<WithdrawalCommand> selectWithdrawal(){
		return session.selectList("admin.withselect");
	}
}