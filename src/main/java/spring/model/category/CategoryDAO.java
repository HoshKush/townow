package spring.model.category;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.sts.interfaces.DAOSTDInter;

@Repository
public class CategoryDAO implements DAOSTDInter {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
	@Override
	public boolean create(Object dto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List list(Map map) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int total(Map map) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean createCategory1(CategoryDTO dto) {
		return mybatis.insert("category.createCategory1", dto) > 0
				? true : false;
	}
	
	public boolean createCategory2(CategoryDTO dto) {
		return mybatis.insert("category.createCategory2", dto) > 0
				? true : false;
	}
	
	public boolean deleteCategory1(String ca1_name) {
		return mybatis.delete("category.deleteCategory1", ca1_name) > 0
				? true : false;
	}
	
	public boolean deleteCategory2(String ca2_name) {
		return mybatis.delete("category.deleteCategory2", ca2_name) > 0
				? true : false;
	}
}
