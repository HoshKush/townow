package spring.model.message;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.model.users.UsersDTO;
import spring.sts.interfaces.DAOSTDInter;

@Repository
public class MessageDAO implements DAOSTDInter {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
	
	@Override
	public boolean create(Object dto) throws Exception {
		return mybatis.insert("message.create", (MessageDTO) dto) > 0
				? true : false; 
	}

	@Override
	public List list(Map map) throws Exception {
		return mybatis.selectList("message.list", map);
	}

	@Override
	public Object read(Object pk) throws Exception {
		return mybatis.selectOne("message.read", (int) pk);
	}

	@Override
	public boolean update(Object dto) throws Exception {
		return mybatis.update("message.update", (MessageDTO) dto) > 0
				? true : false;
	}

	@Override
	public boolean delete(Object pk) throws Exception {
		return mybatis.delete("message.delete", (int) pk) > 0
				? true : false;
	}

	@Override
	public int total(Map map) throws Exception {
		return mybatis.selectOne("message.total", map);
	}
	
	public boolean checkRead(int msg_id) {
		return mybatis.update("message.checkRead", msg_id) > 0
				? true : false;
	}
	
	public boolean senderDelete(int msg_id) {
		return mybatis.update("message.senderDelete", msg_id) > 0
				? true : false;
	}
	
	public boolean recipientDelete(int msg_id) {
		return mybatis.update("message.recipientDelete", msg_id) > 0
				? true : false;
	}
}
