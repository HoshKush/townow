package spring.utility.townow;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MemberLoginCheckFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Filter�? ?���? 중간?��?�� request객체�? 추출?��?��?��.
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		// ?��로운 ?��?��?�� ?��?��?���? ?���? request 객체?��?�� 기존?�� ?��?�� 반환
		HttpSession session = httpRequest.getSession(false);

		String id = request.getParameter("id");
		String sid = (String) session.getAttribute("id");

		// 로그?�� ?���? ?��?��?���? �??��
		boolean login = false;

		if (session != null) {// session 객체�? ?��?��?��?�� ?��?���? ?��?��
			// 로그?��?�� ?��?��면서 �?리자?���? ?��?��?��?��?��.
			if (sid != null && sid.equals("admin")) {
				login = true;// �?리자 ?��?��
			} else if (sid != null && (id == null || sid.equals(id))) {
				login = true;
			}
		}
		// ?��?��?��?���? 로그?��?�� ?��?��?���? ?���? ?��?���?�? ?��?��?��?��?��
		if (login) {
			chain.doFilter(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/member/login.do");
			dispatcher.forward(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
