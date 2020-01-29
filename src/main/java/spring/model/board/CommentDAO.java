package spring.model.board;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.sts.interfaces.DAOSTDInter;

@Repository
public class CommentDAO implements DAOSTDInter {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
	
	@Override
	public boolean create(Object dto) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.insert("comment.create", (CommentDTO) dto) > 0
				? true : false;
	}

	@Override
	public List list(Map map) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.selectList("comment.list", map);
	}

	@Override
	public boolean update(Object dto) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.update("board.update", (CommentDTO) dto) > 0
				? true : false;
	}

	@Override
	public boolean delete(Object pk) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.delete("comment.delete", (int) pk) > 0
				? true : false;
	}

	@Override
	public int total(Map map) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.selectOne("comment.total", map);
	}

	@Override
	public Object read(Object pk) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean deleteAll(int brd_id) {
		return mybatis.delete("comment.deleteAll", brd_id) > 0
				? true : false;
	}
	
	public boolean hasGroup(int cmt_grp) {
		return (int) mybatis.selectOne("comment.hasGroup", cmt_grp) > 0
				? true : false;
	}
	
	public String nicknameOf(int cmt_id) {
		return mybatis.selectOne("comment.nicknameOf", cmt_id);
	}
	
	
}
