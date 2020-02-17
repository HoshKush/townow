package spring.model.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {
	
	@Autowired private BoardDAO dao;
	@Autowired private CommentDAO cdao;
	
	@Transactional
	public void delete(int brd_id) throws Exception {
		cdao.delete(brd_id);
		dao.delete(brd_id);		
	}
	
	@Transactional
	public boolean reply(BoardDTO dto) {
		
		boolean flag = false;
//		BoardDTO rdto = dao.readToReply(dto.getBrd_id());
		System.out.println(dto.getBrd_id());
		
		Map map = new HashMap();
		map.put("grp", dto.getGrp());
		map.put("ansno", dto.getAnsno());
		
		dao.updateAnsno(map);	
		return dao.createReply(dto);
		
	}
}
