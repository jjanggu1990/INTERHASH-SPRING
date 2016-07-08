package mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.AdminCommand;
import spring.model.AlarmCommand;
import spring.model.ContentCommand;
import spring.model.MemberCommand;
import spring.model.MessageCommand;
import spring.model.WithdrawalCommand;

@Repository
public class AdminDAO {
	
	
	@Autowired
	SqlSession session;
	
	public void setSession(SqlSession session){
		this.session=session;
	}
	
	public List<ContentCommand> selectHash(){
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
	
	public List<ContentCommand> report(){
		return session.selectList("admin.report");
	}
	
	public int memberCount(){
		return session.selectOne("member.memberCount");
	}
	
	public int contentCount(){
		return session.selectOne("admin.contentcount");
	}
	
	public int likeCount(){
		return session.selectOne("admin.likecount");
	}
	
	public int total_count(){
		return session.selectOne("member.total_count");
	}
	
	public int today(){
		return session.selectOne("member.today");
	}
	
	public int today_like(){
		return session.selectOne("admin.today_like");
	}
	
	public int today_content(){
		return session.selectOne("admin.today_content");
	}
	
	//�˶�
	public int Alarm(AlarmCommand dto){
		return session.insert("alarm.insert",dto);
	}
	
	public String receivedEmail(int connum){
		return session.selectOne("alarm.receivedemail",connum);
	}
	
	public List<AlarmCommand> AlarmAll(String email){
		return session.selectList("alarm.all",email);
	}
	
	public int alarm_count(String email){
		return session.selectOne("alarm.count",email);
	}
	
	public int alarm_delete(int connum) {
		return session.delete("alarm.alarmdelete", connum);
	}
	
	public String confirm(String comnick) {
		return session.selectOne("alarm.confirm", comnick);
	}
}