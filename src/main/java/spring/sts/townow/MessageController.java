package spring.sts.townow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.model.board.BoardJoinedDTO;
import spring.model.message.MessageDAO;
import spring.model.message.MessageDTO;
import spring.utility.townow.Utility;

@Controller
public class MessageController {
	
	@Autowired
	private MessageDAO dao;
	
	@RequestMapping(value="/message/")
	public String moveToMessage() {
		return "/message/";
	}
	
	@ResponseBody
	@PostMapping(value="/message/create")
	public String create(MessageDTO dto) throws Exception {
		return dao.create(dto) ? "0" : "1";		
	}
	
	@RequestMapping(value="/message/functions")
	public String functions() {
		return "/message/functions";
	}
	
	@RequestMapping("/message/list")
	public String list(HttpServletRequest request) throws Exception {
		String url = "/message/list";
		String email = request.getParameter("email");
		String type = request.getParameter("type");
		int nowPage = 1;
		
		int recordPerPage = 10;
		
		if (request.getParameter("nowPage") != null && !request.getParameter("nowPage").equals("")){
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		
		int sno = ((nowPage - 1) * recordPerPage);
		Map map = new HashMap();
		map.put("sno", sno);
		map.put("recordPerPage", recordPerPage);
		map.put("type", type);
		map.put("email", email);
		
		System.out.println("total >>>>>>>>>> " + dao.total(map));
		// 1. model 사용
		List<BoardJoinedDTO> list = dao.list(map);
		int total = dao.total(map);
		int i = 0;
		String paging = Utility.messagePaging(total, recordPerPage, url, email, type, nowPage);

		// 2. request 저장

		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("type", type);
		
		// 3. view 페이지 선택

		return "/list";
	}
}
