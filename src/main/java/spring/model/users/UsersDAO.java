package spring.model.users;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.sts.interfaces.DAOSTDInter;

@Repository
public class UsersDAO implements DAOSTDInter {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
	
	@Override
	public boolean create(Object dto) throws Exception {
		return mybatis.insert("users.create", (UsersDTO) dto) > 0
				? true : false; 
	}

	@Override
	public List list(Map map) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.selectList("users.list", map);
	}

	@Override
	public Object read(Object pk) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.selectOne("users.read", (Integer) pk);
	}

	@Override
	public boolean update(Object dto) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.update("users.update", (UsersDTO) dto) > 0
				? true : false;
	}

	@Override
	public boolean delete(Object pk) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.delete("users.delete", (Integer) pk) > 0
				? true : false;
	}

	@Override
	public int total(Map map) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.selectOne("users.total", map);
	}
	
	public boolean updateGrade(UsersDTO dto) {
		return mybatis.update("users.updateGrade", dto) > 0
				? true : false;
	}
	
	public boolean updateIntroduction(UsersDTO dto) {
		return mybatis.update("users.updateIntroDuction", dto) > 0
				? true : false;
	}
	
	public boolean updateFname(UsersDTO dto) {
		return mybatis.update("users.updateFname", dto) > 0
				? true : false;
	}
	
	public boolean updateBirthday(UsersDTO dto) {
		return mybatis.update("users.updateBirthday", dto) > 0
				? true : false;
	}
	
	public boolean updateGender(UsersDTO dto) {
		return mybatis.update("users.updateGender", dto) > 0
				? true : false;
	}
	
	public boolean loginCheck(Map map) {
		return (Integer) mybatis.selectOne("users.loginCheck", map) > 0
				? true : false;
	}
	
	public String getFname(String email) {
		return mybatis.selectOne("users.getFname", email);
	}
	
	public boolean updatePasswd(Map map) {
		return mybatis.update("users.updatePasswd", map) > 0
				? true : false;
	}
	
	public boolean passwdCheck(Map map) {
		return (Integer) mybatis.selectOne("users.passwdCheck", map) > 0
				? true : false;
	}
	
	public int duplicateEmail(String email) {
		return (Integer) mybatis.selectOne("users.duplicateEmail", email);
	}
	
	public String getPwFind(Map map) {
		return mybatis.selectOne("users.getPwFind", map);
	}
}
