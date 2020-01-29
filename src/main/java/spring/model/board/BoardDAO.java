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
		return (BoardDTO) mybatis.selectOne("board.read", (int) pk);
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
		return mybatis.delete("board.delete", (int) pk) > 0
				? true : false;
	}

	@Override
	public int total(Map map) throws Exception {
		// TODO Auto-generated method stub
		return mybatis.selectOne("board.total", map);
	}
	
	public boolean isWriterk(String email) {
		return (int) mybatis.selectOne("board.isWriter", email) > 0
				? true : false;
	}
	
	public boolean viewcount(int brd_id) {
		return mybatis.update("board.viewcount", brd_id) > 0 
				? true : false;
	}
	
	public boolean hasGroup (int grpno) {
		return (int) mybatis.selectOne("board.hasGroup", grpno) > 0
				? true : false;
	}
	
	public boolean updateAnsnum (Map map) {
		return mybatis.update("board.updateAnsnum", map) > 0
				? true : false;
	}
	
	public boolean likeUp(int brd_id) {
		return mybatis.update("board.likeUp", brd_id) > 0
				? true : false;
	}
	
	public boolean dislikeUp(int brd_id) {
		return mybatis.update("board.dislikeUp", brd_id) > 0
				? true : false;
	}
	
	public boolean insertReply(BoardDTO dto) {
		return mybatis.insert("board.insertReply", dto) > 0
				? true : false;
	}
	
	public BoardDTO readReply(int brd_id) {
		return mybatis.selectOne("readReply", brd_id);
	}
}
