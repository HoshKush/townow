package spring.model.local;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.sts.interfaces.DAOSTDInter;

@Repository
public class LocalDAO implements DAOSTDInter {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
	@Override
	public boolean create(Object dto) throws Exception {		
		return mybatis.insert("local.create", (LocalDTO) dto) > 0
				? true : false;
	}

	@Override
	public List list(Map map) throws Exception {
		return mybatis.selectList("local.list", map);
	}

	@Override
	public Object read(Object pk) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Object dto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object pk) throws Exception {
		return mybatis.delete("local.delete", (int) pk) > 0
				? true : false;
	}

	@Override
	public int total(Map map) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
