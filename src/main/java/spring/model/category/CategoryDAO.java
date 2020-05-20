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
		return mybatis.insert("category.create", (CategoryDTO) dto) > 0
				? true : false;
	}

	@Override
	public List list(Map map) throws Exception {
		return mybatis.selectList("category.list", map);
	}
	
	public List getCa1_names() {
		return mybatis.selectList("category.getCa1_names");
	}
	
	public List categoryList(String ca1_name) {
		return mybatis.selectList("category.categoryList", ca1_name);
	}

	@Override
	public Object read(Object pk) throws Exception {
		return mybatis.selectOne("category.read", (Integer)pk);
	}

	@Override
	public boolean update(Object dto) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object pk) throws Exception {
		return mybatis.delete("category.delete", (Integer) pk) > 0
				? true : false;
	}
	
	public int hasCategory2(String ca1_name) {
		return mybatis.selectOne("category.hasCa2", ca1_name);
	}

	@Override
	public int total(Map map) throws Exception {
		return mybatis.selectOne("category.total", map);
	}
	
	public int hasArticle(int ca_id) {
		return mybatis.selectOne("category.hasArticle", ca_id);
	}
}
