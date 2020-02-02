package spring.sts.townow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.board.BoardDAO;
import spring.model.board.BoardDTO;
import spring.model.board.BoardService;
import spring.model.board.CommentDAO;
import spring.utility.townow.Utility;

@Controller
public class BoardController {

	@Autowired
	private BoardDAO dao;

	@Autowired
	private CommentDAO cdao;

	@Autowired
	private BoardService mgr;
	
	@RequestMapping(value = "/board/updateLike")
	public String updateLike(int brd_id, Model model, String nowPage, String col, String word,
			int loc_id, int ca_id) {
		if(dao.updateLike(brd_id)) {
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("brd_id", brd_id);
			model.addAttribute("loc_id", loc_id);
			model.addAttribute("ca_id", ca_id);
			
			return "redirect:/board/read";
		} else {
			return "/error";
		}
	}
	
	@RequestMapping(value = "/board/updateDislike")
	public String updateDislike(int brd_id, Model model, String nowPage, String col, String word,
			int loc_id, int ca_id) {
		if(dao.updateDislike(brd_id)) {
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("brd_id", brd_id);
			model.addAttribute("loc_id", loc_id);
			model.addAttribute("ca_id", ca_id);
			
			return "redirect:/board/read";
		} else {
			return "/error";
		}
	}
	
	@RequestMapping(value = "/board/deleteComment")
	public String deleteComment(int cmt_id, Model model, String nowPage, String col, String word,
			int brd_id, int loc_id, int ca_id) throws Exception {
		if (cdao.delete(cmt_id)) {
			model.addAttribute("nowPage", nowPage);
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("brd_id", brd_id);
			model.addAttribute("loc_id", loc_id);
			model.addAttribute("ca_id", ca_id);

			return "redirect:/board/read";
		} else {
			return "/error/error";
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(int brd_id, String oldfile, HttpServletRequest request, Model model) throws Exception {

		String bassPath = request.getRealPath("/storage");

		if (dao.delete(brd_id)) {
			Utility.deleteFile(bassPath, oldfile);
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			model.addAttribute("loc_id", request.getParameter("loc_id"));
			model.addAttribute("ca_id", request.getParameter("ca_id"));
			return "redirect:/list";
		} else {
			return "/error";
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(int brd_id) {
		boolean flag = dao.hasGroup(brd_id);

		if (flag)
			return "/hasGroupError";
		else
			return "/delete";
	}

	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(BoardDTO dto, HttpServletRequest request, Model model) {

		String bassPath = request.getRealPath("/storage");

		dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), bassPath));
		dto.setFilesize((int) dto.getFilenameMF().getSize());
		System.out.println("2" + mgr);
		boolean flag = mgr.reply(dto);

		if (flag) {
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			model.addAttribute("loc_id", request.getParameter("loc_id"));
			model.addAttribute("ca_id", request.getParameter("ca_id"));
			model.addAttribute("brd_id", request.getParameter("brd_id"));
			return "redirect:/list";
		} else {
			return "/error";
		}

	}

	@RequestMapping(value = "/reply", method = RequestMethod.GET)
	public String reply(int brd_id, Model model) {

		BoardDTO dto = dao.readToReply(brd_id);
		model.addAttribute("dto", dto);

		return "/reply";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardDTO dto, HttpServletRequest request, String oldfile, Model model) throws Exception {

		String bassPath = request.getRealPath("/storage");

		dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), bassPath));
		dto.setFilesize((int) dto.getFilenameMF().getSize());
		model.addAttribute("dto", dto);

		Map map = new HashMap();
		map.put("brd_id", dto.getBrd_id());

		boolean flag = false;

		flag = dao.update(dto);
		String str = null;
		if (flag) {
			if (dto.getFilesize() > 0) {
				Utility.deleteFile(bassPath, oldfile);
			}
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			model.addAttribute("loc_id", request.getParameter("loc_id"));
			model.addAttribute("ca_id", request.getParameter("ca_id"));
			model.addAttribute("brd_id", request.getParameter("brd_id"));
			str = "redirect:/list";
		} else {
			if (dto.getFilesize() > 0) {
				Utility.deleteFile(bassPath, dto.getFilename());
			}
			str = "/error";
		}

		return str;
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(int brd_id, Model model) throws Exception {

		BoardDTO dto = (BoardDTO) dao.read(brd_id);

		model.addAttribute("dto", dto);

		return "/update";
	}

	@RequestMapping("/read")
	public String read(int brd_id, Model model, String email) throws Exception {
		dao.updateViewcount(brd_id);
		BoardDTO dto = (BoardDTO) dao.read(brd_id);
		String content = dto.getContent().replaceAll("\r\n", "<br>");
		model.addAttribute("dto", dto);
		model.addAttribute("content", content);
		model.addAttribute("isWriter", dao.isWriter(email));

		return "/read";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(BoardDTO dto, HttpServletRequest request, Model model) throws Exception {
		String upDir = request.getRealPath("/storage");

		// 업로드 처리

		dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), upDir));
		dto.setFilesize((int) dto.getFilenameMF().getSize());

		boolean flag = dao.create(dto);
		if (flag) {
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			model.addAttribute("loc_id", request.getParameter("loc_id"));
			model.addAttribute("ca_id", request.getParameter("ca_id"));
			model.addAttribute("brd_id", request.getParameter("brd_id"));
			return "redirect:/list";
		} else {
			return "/error";
		}

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create() {

		return "/create";
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request) throws Exception {
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		int loc_id = Integer.parseInt(request.getParameter("loc_id"));
		int ca_id = Integer.parseInt(request.getParameter("ca_id"));
		int brd_id = Integer.parseInt(request.getParameter("brd_id"));
		if (col.equals("total"))
			word = "";

		String url = "list";

		int nowPage = 1;

		int recordPerPage = 10;

		if (request.getParameter("nowPage") != null) {

			nowPage = Integer.parseInt(request.getParameter("nowPage"));

		}

		int sno = ((nowPage - 1) * recordPerPage) + 1;

		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("loc_id", loc_id);
		map.put("ca_id", ca_id);
		map.put("brd_id", brd_id);
		map.put("sno", sno);
		map.put("eno", eno);

		// 1. model 사용

		List<BoardDTO> list = dao.list(map);

		int total = dao.total(map);

		String paging = Utility.paging(total, recordPerPage, url, brd_id, ca_id, loc_id, nowPage, col, word);

		// 2. request 저장

		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("loc_id", loc_id);
		request.setAttribute("ca_id", ca_id);

		// 3. view 페이지 선택

		return "/list";
	}
}
