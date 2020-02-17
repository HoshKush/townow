package spring.model.board;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.sts.interfaces.DAOSTDInter;

@Repository
public class BoardDAO implements DAOSTDInter {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void setMybatis(SqlSessionTemplate mybatis) {
		this.mybatis = mybatis;
	}
	
	@Override
	public boolean create(Object dto) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.insert("board.create", (BoardDTO) dto) > 0
				? true : false;
	}

	@Override
	public List list(Map map) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.selectList("board.list", map);
	}

	@Override
	public Object read(Object pk) throws Exception {
		// TODO Auto-generated method stub
		return (BoardJoinedDTO) mybatis.selectOne("board.read", (Integer) pk);
	}

	@Override
	public boolean update(Object dto) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.update("board.update", (BoardDTO) dto) > 0
				? true : false;
	}

	@Override
	public boolean delete(Object pk) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.delete("board.delete", (Integer) pk) > 0
				? true : false;
	}

	@Override
	public int total(Map map) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.selectOne("board.total", map);
	}
	
	public boolean isWriter(String email) {
		return (Integer) mybatis.selectOne("board.isWriter", email) > 0
				? true : false;
	}
	
	public boolean updateViewcount(int brd_id) {
		return mybatis.update("board.updateViewcount", brd_id) > 0 
				? true : false;
	}
	
	public boolean hasGroup (int brd_id) {
		return (Integer) mybatis.selectOne("board.hasGroup", brd_id) > 0
				? true : false;
	}
	
	public boolean updateAnsno (Map map) {
		return mybatis.update("board.updateAnsno", map) > 0
				? true : false;
	}
	
	public boolean updateLike(int brd_id) {
		return mybatis.update("board.updateLike", brd_id) > 0
				? true : false;
	}
	
	public boolean updateDislike(int brd_id) {
		return mybatis.update("board.updateDislike", brd_id) > 0
				? true : false;
	}
	
	public boolean createReply(BoardDTO dto) {
		return mybatis.insert("board.createReply", dto) > 0
				? true : false;
	}
	
	public BoardDTO readToReply(int brd_id) {
		return mybatis.selectOne("board.readReply", brd_id);
	}
}
