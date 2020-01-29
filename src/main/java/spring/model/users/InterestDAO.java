package spring.model.users;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.sts.interfaces.DAOSTDInter;

@Repository
public class InterestDAO implements DAOSTDInter {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
	
	@Override
	public boolean create(Object dto) throws Exception {
		return mybatis.insert("interest.create", (InterestDTO) dto) > 0
				? true : false; 
	}

	@Override
	public List list(Map map) throws Exception {
		return mybatis.selectList("interes.list", map);
	}

	@Override
	public Object read(Object pk) throws Exception {
		return null;
	}

	@Override
	public boolean update(Object dto) throws Exception {
		return false;
	}

	@Override
	public boolean delete(Object pk) throws Exception {
		return mybatis.delete("interest.delete", (int) pk) > 0
				? true : false; 
	}

	@Override
	public int total(Map map) throws Exception {
		return mybatis.selectOne("interest.total", map);
	}
	
	public boolean updatePriority(InterestDTO dto) {
		return mybatis.update("interest.updatePriority", dto) > 0
				? true : false;
	}

}
