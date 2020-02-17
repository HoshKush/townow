package spring.sts.townow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.model.board.BoardDAO;
import spring.model.board.BoardDTO;
import spring.model.board.BoardJoinedDTO;
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
	
	@ResponseBody
	@RequestMapping(value = "/updateLike")
	public String updateLike(int brd_id) throws Exception {
		if(dao.updateLike(brd_id)) {			
			BoardJoinedDTO dto = (BoardJoinedDTO) dao.read(brd_id);
			return String.valueOf(dto.getBrd_like());
		} else {
			return "예기치 못한 오류가 발생하였습니다. 관리자에게 문의하세요.";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateDislike")
	public String updateDislike(int brd_id) throws Exception {
		if(dao.updateDislike(brd_id)) {
			BoardJoinedDTO dto = (BoardJoinedDTO) dao.read(brd_id);
			return String.valueOf(dto.getBrd_dislike());
		} else {
			return "예기치 못한 오류가 발생하였습니다. 관리자에게 문의하세요.";
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

//	@RequestMapping(value = "/delete", method = RequestMethod.POST)
//	public String delete(int brd_id, String oldfile, HttpServletRequest request, Model model) throws Exception {
//		String bassPath = request.getRealPath("/storage");
//
//		if (dao.delete(brd_id)) {
//			Utility.deleteFile(bassPath, oldfile);
//			model.addAttribute("col", request.getParameter("col"));
//			model.addAttribute("word", request.getParameter("word"));
//			model.addAttribute("nowPage", request.getParameter("nowPage"));
//			model.addAttribute("loc_id", request.getParameter("loc_id"));
//			model.addAttribute("ca_id", request.getParameter("ca_id"));
//			return "redirect:/list";
//		} else {
//			return "/error";
//		}
//	}
	
	@ResponseBody
	@RequestMapping(value="/deleteArticle", method=RequestMethod.GET, produces="text/plain;charset=utf-8")
	public String delete(int brd_id, HttpServletRequest request) throws Exception {
		BoardJoinedDTO dto = (BoardJoinedDTO) dao.read(brd_id);
		String oldfile = dto.getFilename();
		String basePath = request.getRealPath("/storage");
		
		if (dao.hasGroup(brd_id)) {
			return "답글이 있어 삭제할 수 없습니다.";
		} else {
			if(dao.delete(brd_id)) {
				Utility.deleteFile(basePath, oldfile);
				return "s삭제되었습니다.";
			} else {
				return "삭제에 실패하였습니다. 관리자에게 문의해주세요.";
			}
		}
	}

	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(BoardDTO dto, HttpServletRequest request, Model model) {

		String bassPath = request.getRealPath("/storage");

		dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), bassPath));
		dto.setFilesize((int) dto.getFilenameMF().getSize());
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

		BoardJoinedDTO dto = (BoardJoinedDTO) dao.read(brd_id);

		model.addAttribute("dto", dto);

		return "/update";
	}

	@RequestMapping("/read")
	public String read(int brd_id, Model model, String email) throws Exception {
		dao.updateViewcount(brd_id);
		BoardJoinedDTO dto = (BoardJoinedDTO) dao.read(brd_id);
		String content = dto.getContent().replaceAll("\r\n", "<br>");
		model.addAttribute("dto", dto);
		model.addAttribute("content", content);
		model.addAttribute("isWriter", dao.isWriter(email));

		return "/read";
	}

	@PostMapping("/create")
	public String create(BoardDTO dto, HttpServletRequest request, Model model) throws Exception {
		String upDir = request.getRealPath("/storage");
		System.out.println(dto.getEmail());
		// 업로드 처리
		
		dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), upDir));
		dto.setFilesize((int) dto.getFilenameMF().getSize());

		boolean flag = dao.create(dto);
		if (flag) {
			System.out.println("성공");
			model.addAttribute("col", request.getParameter("col"));
			model.addAttribute("word", request.getParameter("word"));
			model.addAttribute("nowPage", request.getParameter("nowPage"));
			model.addAttribute("loc_id", request.getParameter("loc_id"));
			model.addAttribute("ca_id", request.getParameter("ca_id"));
			return "redirect:/list";
		} else {
			System.out.println("실패");
			return "/error";
		}

	}

	@GetMapping("/create")
	public String create() {
		System.out.println("create 호출");
		return "/create";
	}

	@RequestMapping("/list")
	public String list(HttpServletRequest request) throws Exception {
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		int loc_id = -1;
		int ca_id = -1;
		if (col.equals("total"))
			word = "";

		int nowPage = 1;

		int recordPerPage = 10;
		
		if (request.getParameter("nowPage") != null && !request.getParameter("nowPage").equals("")){
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		if (request.getParameter("ca_id") != null && !request.getParameter("ca_id").equals("")) {
			ca_id = Integer.parseInt(request.getParameter("ca_id"));
		}
		if (request.getParameter("loc_id") != null && !request.getParameter("loc_id").equals("")) {
			loc_id = Integer.parseInt(request.getParameter("loc_id"));
		}

		int sno = ((nowPage - 1) * recordPerPage);

//		int eno = nowPage * recordPerPage;
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("loc_id", loc_id);
		map.put("ca_id", ca_id);
		map.put("sno", sno);
		map.put("recordPerPage", recordPerPage);
//		map.put("eno", eno);
		// 1. model 사용

		List<BoardJoinedDTO> list = dao.list(map);
		int total = dao.total(map);
		int i = 0;
		String paging = Utility.paging3(total, nowPage, recordPerPage, ca_id, loc_id, col, word);

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
