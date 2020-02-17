package spring.sts.townow;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import spring.model.users.UsersDTO;
import spring.model.users.UsersDAO;
import spring.utility.townow.Utility;

// redirect로 갈땐 model로, 
//@Controller
public class UsersController {

	@Autowired
	UsersDAO dao = new UsersDAO();

	@RequestMapping("/admin/list")
	public String list(HttpServletRequest request) throws Exception {
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		if (col.equals("total"))
			word = "";

		// 페이징관련
		int nowPage = 1;
		int recordPerPage = 5;
		if (request.getParameter("nowPage") != null)
			nowPage = Integer.parseInt(request.getParameter("nowPage"));

		// DB에 가져올 순번
		int sno = ((nowPage - 1) * recordPerPage) + 1;
		int eno = nowPage * recordPerPage;

		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
//		int total = dao.total(map);
		
		List<UsersDTO> list = dao.list(map);

//		String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);

		request.setAttribute("list", list);
//		request.setAttribute("paging", paging);
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		
		return "/admin/list";
	}

	@RequestMapping(value = "/users/delete", method = RequestMethod.POST)
	public String delete(String id, HttpSession session, HttpServletRequest request, Model model) throws Exception {

		String upDir = request.getRealPath("/users/storage");

		String sid = (String) session.getAttribute("id");
		String grade = (String) session.getAttribute("grade");
		String oldfile = dao.getFname(id);
		boolean flag = dao.delete(id);

		if (flag) {
			if (oldfile != null && !oldfile.equals("member.jpg")) {
				Utility.deleteFile(upDir, oldfile);
			}
			if (sid != null && !grade.equals("A")) {
				session.invalidate();
				return "redirect:/";
			}else {
				model.addAttribute("col", request.getParameter("col"));
				model.addAttribute("word", request.getParameter("word"));
				model.addAttribute("nowPage", request.getParameter("nowPage"));
				return "redircet:/admin/list";
			}
		} else {
			return "/error/error";
		}
	}

	@RequestMapping(value = "/users/delete", method = RequestMethod.GET)
	public String delete(String id, HttpSession session, Model model) {

		if (id == null)
			id = (String) session.getAttribute("id");

		model.addAttribute("id", id);

		return "/users/delete";
	}

//	@RequestMapping(value = "/users/updateFile", method = RequestMethod.POST)
//	public String updateFile(String id, String oldfile, MultipartFile fnameMF, HttpServletRequest request,
//			Model model) {
//
//		String upDir = request.getRealPath("/users/storage");
//
//		String fname = null;
//		if (fnameMF.getSize() > 0) {
//			if (oldfile != null && !oldfile.equals("member.jpg"))
//				Utility.deleteFile(upDir, oldfile);
//			fname = Utility.saveFileSpring(fnameMF, upDir);
//		}
//
//		Map map = new HashMap();
//		map.put("id", id);
//		map.put("fname", fname);
//
//		boolean flag = dao.updateFile(map);
//
//		if (flag) {
//			model.addAttribute("id", id);
//			return "redirect:/member/read";
//		} else {
//			return "/error/error";
//		}
//	}

	@RequestMapping(value = "/users/updateFile", method = RequestMethod.GET)
	public String updateFile(String id, String oldfile, Model model) {

		model.addAttribute("id", id);
		model.addAttribute("oldfile", oldfile);

		return "/users/updateFile";
	}

	@RequestMapping("/users/emailProc")
	public String emailProc(String email) {
		return "/users/email_form";
	}

	@RequestMapping(value = "/users/update", method = RequestMethod.POST)
	public String update(UsersDTO dto, HttpSession session, Model model, HttpServletRequest request) throws Exception {
		
		if (dao.update(dto)) {
			if(session.getAttribute("id")!=null && session.getAttribute("grade").equals("A")) {
				model.addAttribute("col", request.getParameter("col"));
				model.addAttribute("word", request.getParameter("word"));
				model.addAttribute("nowPage", request.getParameter("nowPage"));

				return "redirect:/users/list";
			}else {
				return "redirect:/";
			}
		} else {
			return "/error/error";
		}
	}

	@RequestMapping(value = "/users/update", method = RequestMethod.GET)
	public String update(String id, Model model) throws Exception {
		UsersDTO dto = (UsersDTO) dao.read(id);

		model.addAttribute("dto", dto);

		return "/users/update";
	}

//	@ResponseBody
//	@RequestMapping(value = "/users/idfind", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
//	public String idfind(@RequestParam Map<String, String> map) {
//
//		String id = dao.getIdFind(map);
//		String str = null;
//		if (id != null) {
//			str = "찾으시는 id는 " + id + " 입니다.";
//		} else {
//			str = "잘못된 정보를 입력하였습니다.";
//		}
//
//		return str;
//	}

	@ResponseBody
	@RequestMapping(value = "/users/pwfind", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public String pwfind(@RequestParam Map<String, String> map) {

		String pw = dao.getPwFind(map);
		String str = null;
		if (pw != null) {
			str = "찾으시는 패스워드는 " + pw + " 입니다.";
		} else {
			str = "잘못된 정보를 입력하였습니다.";
		}

		return str;
	}

	@RequestMapping("/users/idpwfind")
	public String idpwfind() {

		return "/users/idpwfind";
	}

	@RequestMapping(value = "/users/updatePasswd", method = RequestMethod.POST)
	public String updatePasswd(@RequestParam Map<String, String> map, String id, String currPasswd, String newPasswd,
			HttpServletRequest request, Model model) {

		map.put("passwd", currPasswd);
		map.put("id", id);
		map.put("newPasswd", newPasswd);

		boolean mflag = dao.passwdCheck(map);
//		boolean mflag2 = dao.passwdCheck2(map);// 기존 비밀번호와 같은지 비교, true이면 변경불가 처리
		boolean flag = dao.updatePasswd(map);

		model.addAttribute("mflag", mflag);
//		model.addAttribute("mflag2", mflag2);
		model.addAttribute("flag", flag);

		return "/users/updatePasswdProc";
	}

	@RequestMapping(value = "/users/updatePasswd", method = RequestMethod.GET)
	public String updatePasswd(String id, HttpServletRequest request) throws Exception {
		UsersDTO dto = (UsersDTO) dao.read(id);

		request.setAttribute("dto", dto);

		return "/users/updatePasswd";
	}

	@RequestMapping("/users/read")
	public String read(String email, HttpSession session, Model model) throws Exception {

		if (email == null) {
			email = (String) session.getAttribute("email");
		}
		UsersDTO dto = (UsersDTO) dao.read(email);

		model.addAttribute("dto", dto);

		return "/users/read";
	}

	@RequestMapping("/users/logout")
	public String logout(HttpSession session) {
		session.invalidate();

		return "redirect:/";
	}

//	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
//	public String login(@RequestParam Map<String, String> map, String c_id, HttpSession session, Model model,
//			HttpServletResponse response, HttpServletRequest request) {
//
//		String id = (String) map.get("id");
//		boolean flag = dao.loginCheck(map);
//		String url = "/error/passwdError";		
//		String grade = null;
//
//		if (flag) {
//			grade = dao.getGrade(id);
//			session.setAttribute("id", id);
//			session.setAttribute("grade", grade);
//		 // ----------------------------------------------
//			// Cookie 저장, Checkbox는 선택하지 않으면 null 임
//			// ----------------------------------------------
//		Cookie cookie = null;
//
//		if (c_id != null) { // 처음에는 값이 없음으로 null 체크로 처리
//			cookie = new Cookie("c_id", "Y"); // 아이디 저장 여부 쿠키
//			cookie.setMaxAge(120); // 2 분 유지
//			response.addCookie(cookie); // 쿠키 기록
//
//			cookie = new Cookie("c_id_val", id); // 아이디 값 저장 쿠키
//			cookie.setMaxAge(120); // 2 분 유지
//			response.addCookie(cookie); // 쿠키 기록
//
//		} else {
//			cookie = new Cookie("c_id", ""); // 쿠키 삭제
//			cookie.setMaxAge(0);
//			response.addCookie(cookie);
//
//			cookie = new Cookie("c_id_val", ""); // 쿠키 삭제
//			cookie.setMaxAge(0);
//			response.addCookie(cookie);
//		}
//		url = "redirect:/";
//		//---------------------------------------------		
//		/** 댓글쓰기 페이지로 돌아가기위한 데이터들 **/
//		String rflag = request.getParameter("flag");
//		String bbsno = request.getParameter("bbsno");
//		String nPage = request.getParameter("nPage");
//		String col = request.getParameter("col");
//		String word = request.getParameter("word");
//		String nowPage = request.getParameter("nowPage");
//		/** 댓글쓰기 데이터 끝 **/
//		
//		if(rflag!=null && !rflag.equals("")) {
//			url = "redirect:"+rflag;
//			model.addAttribute("bbsno", bbsno);
//			model.addAttribute("nPage", nPage);
//			model.addAttribute("col", col);
//			model.addAttribute("word", word);
//			model.addAttribute("nowPage", nowPage);
//		}
//		}
//		return url;
//	}

	@RequestMapping(value = "/users/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {

		String c_id = ""; // ID 저장 여부를 저장하는 변수, Y
		String c_id_val = ""; // ID 값

		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];

				if (cookie.getName().equals("c_id")) {
					c_id = cookie.getValue(); // Y
				} else if (cookie.getName().equals("c_id_val")) {
					c_id_val = cookie.getValue(); // user1...
				}
			}
		}

		/*----쿠키설정 내용 끝----------------------------*/

		request.setAttribute("c_id", c_id);
		request.setAttribute("c_id_val", c_id_val);

		return "/users/login";
	}

	@ResponseBody
	@RequestMapping(value = "/users/emailCheck", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
	public int emailCheck(String email) {
		return dao.duplicateEmail(email);
	}

//	@ResponseBody
//	@RequestMapping(value = "/users/IDCheck", method = RequestMethod.GET, produces = "text/plain;charset=utf-8")
//	public String idCheck(String id) {
//		String str = null;
//		if (dao.duplicateId(id)) {
//			str = "중복된 ID 입니다.";
//		} else {
//			str = "사용 가능한 ID 입니다.";
//		}
//		return str;
//	}

	@RequestMapping("/users/createProc")
	public String create(UsersDTO dto, HttpServletRequest request, Model model) throws Exception {

		String str = null;
		String url = "/users/pcreate";
		if (dao.duplicateEmail(dto.getEmail()) > 0) {
			str = "중복된 이메일 주소입니다. 다른 이메일 주소를 입력해주세요.";
			model.addAttribute("str", str);
		} else {
			String upDir = request.getRealPath("/users/storage");

			int size = (int) dto.getFnameMF().getSize();
			String fname = null;
			if (size > 0) {
				fname = Utility.saveFileSpring(dto.getFnameMF(), upDir);
			} else {
				fname = "member.jpg";
			}

			dto.setFname(fname);

			boolean flag = dao.create(dto);

			model.addAttribute("flag", flag);

			url = "/users/createProc";
		}
		return url;
	}

	@RequestMapping("/users/create")
	public String create() {

		return "/users/create";
	}

	@RequestMapping("/users/agree")
	public String agree() {

		return "/users/agree";
	}
}
