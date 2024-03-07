package controller;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BoardDao;
import dao.MemberDao;
import dto.ArticlePage;
import dto.Board;
import dto.Member;

/**
 * Servlet implementation class DispatcherServlet
 */
//서버에 도착한 요청의 url이 .do로 끝날 경우 이 서블릿이 호출된다
// "/*.do"로 쓰지 않도록 주의. 이 코드는 /로 시작하고 .do로 끝나는 URL에 대한 것이며 웹 애플리케이션의 루트 디렉토리에 있는 요청만 해당된다
// 반면에 "*.do"는 웹 어플리케이션의 모든 경로가 해당된다
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DispatcherServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get 형식으로 오는 요청을 process로 넘김
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Post 형식으로 오는 요청을 process로 넘김
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 클라이언트가 어떤 요청을 했는지 알기위해 url의 일부를 추출
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));

		// 요청에 따른 조건문 작성
		if (path.equals("/index.do")) {
			String loginStatus = null;
			// 세션에 입력된 회원 정보에 따라 loginStatus을 정의하고 일반 사용자, 관리자 구분
			Member member = (Member) request.getSession().getAttribute("login");
			if (member != null && member.getId() != null) {
				loginStatus = "true";
				if (member.getId().equals("admin")) {
					loginStatus = "admin";
				}
			} else {
				loginStatus = "false";
			}
			// 해당 로그인 정보를 index.jsp에 포워딩
			request.setAttribute("loginStatus", loginStatus);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/boardList.do")) {

			Member member = (Member) request.getSession().getAttribute("login");
			// 로그인 검사
			if (member == null) {
				response.sendRedirect("memberLoginForm.do");
			}

			// search에서 넘겨받은 검색정보 확인
			if (request.getAttribute("sTotal") != null && request.getAttribute("BoardList") != null) {

				// 검색정보를 바탕으로 ArticlePage 객체 생성 << search에서 생성하는게 더 좋은지?
				int sTotal = (int) request.getAttribute("sTotal");
				ArrayList<Board> sList = (ArrayList<Board>) request.getAttribute("BoardList");

				// 검색 결과는 1페이지부터 시작함 
				ArticlePage sArticlePage = new ArticlePage(sTotal, 1, 5, sList, null);

				// View 페이지로 포워딩하여 페이지 출력
				request.setAttribute("ArticleB", sArticlePage);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/boardview/boardList.jsp");
				dispatcher.forward(request, response);

			} else {
				// 포워딩된 값이 없으면 일반 게시글 페이징
				String pageNoval = request.getParameter("pageNo");
				int pageNo = 1;
				if (pageNoval != null) {
					pageNo = Integer.parseInt(pageNoval);
				}
				BoardDao bdao = BoardDao.getInstance();
				int total = bdao.selectCount();
				
				ArrayList<Board> list = bdao.selectPage((((pageNo - 1) * 5) + 1), pageNo * 5);
				ArticlePage articlePage = new ArticlePage(total, pageNo, 5, list, null);
				
				request.setAttribute("ArticleB", articlePage);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/boardview/boardList.jsp");
				dispatcher.forward(request, response);
			}
		} else if (path.equals("/boardSearch.do")) {
			//포워딩 된 검색 옵션과 키워드를 변수에 대입
			BoardDao bdao = BoardDao.getInstance();
			String option = request.getParameter("selectedOption");
			String keyword = request.getParameter("Keyword");
			
			//검색 옵션이 선택되었다면 옵션과 키워드를 바탕으로 메소드 활용 후 포어딩
			if (!option.equals("none")) {

				int sTotal = bdao.countForSearch(option, keyword);
				ArrayList<Board> list = bdao.search(option, keyword);

				request.setAttribute("BoardList", list);
				request.setAttribute("sTotal", sTotal);
				RequestDispatcher dispatcher = request.getRequestDispatcher("boardList.do");
				dispatcher.forward(request, response);
			} else {
				response.sendRedirect("boardList.do");
			}
		} else if (path.equals("/boardView.do")) {
			// 선택된 게시글 번호(num)와 일치하는 데이터를 DB에서 불러와 view 페이지에 포워딩
			HttpSession session = request.getSession(false);
			int num = Integer.parseInt(request.getParameter("num"));
			BoardDao bdao = BoardDao.getInstance();
			//num과 세션을 활용해 각 게시글마다 조회수 반영 여부를 체크
			String vPS = (String) session.getAttribute("hitsPerSession" + num);
			Board board = null;
			
			//이번 접속동안 해당 게시글을 조회한 적이 없는 경우 
			if (vPS == null) {
				//해당 게시글의 조회수를 올린 후 세션값을 1로 설정
				board = bdao.selectOne(num, true);
				session.setAttribute("hitsPerSession" + num, "1");
			} else {
				//hitsPerSession이 null이 아니라면 조회수 반영 X
				board = bdao.selectOne(num, false);
			}
			
			// dto의 값을 불러와 공백과 줄바꿈 처리를 한 후 setter를 통해 적용시킴
			String title = board.getTitle().replace(" ", "&nbsp;");
			board.setTitle(title);
			String content = board.getContent().replace(" ", "&nbsp;").replace("\n", "<br>");
			board.setContent(content);

			// 포워딩
			request.setAttribute("board", board);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/boardview/boardView.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/boardWrite.do")) {
			// 글 번호 값 얻기, 주어지지 않았으면 0으로 설정

			HttpSession session = request.getSession(false);
			BoardDao bdao = BoardDao.getInstance();
			Member member = (Member) session.getAttribute("login");
			
			//글 번호를 통해 수정 / 삽입 여부 확인
			String tmp = request.getParameter("num");
			int num = (tmp != null && tmp.trim().length() > 0) ? Integer.parseInt(tmp) : 0;
			String action = "boardInsert.do";
			Board board = null;
			
			//글 번호가 주어졌으면, 수정
			if (num > 0) {
				board = bdao.selectOne(num, false);
				if (member.getMemberno() == board.getMemberno() || member.getId().equals("admin")) {
					//Write에 있는 Form의 action값을 트리거로 하여 
					action = "boardUpdate.do?num=" + num;
				} else {
					action = "boardList.do?";
				}
			}
			request.setAttribute("board", board);
			request.setAttribute("action", action);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/boardview/boardWrite.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/boardInsert.do")) {
			HttpSession session = request.getSession(true);
			Member member = (Member) session.getAttribute("login");

			int memberno = member.getMemberno();
			String memberName = member.getName();
			String title = request.getParameter("title");
			String content = request.getParameter("content");

			BoardDao bdao = BoardDao.getInstance();
			Board board = new Board(title, content, memberno, memberName);

			bdao.insert(board);
			response.sendRedirect("boardList.do");

		} else if (path.equals("/boardUpdate.do")) {
			int num = Integer.parseInt(request.getParameter("num"));
			String content = request.getParameter("content");
			String title = request.getParameter("title");

			BoardDao bdao = BoardDao.getInstance();
			Board board = new Board(num, title, content);

			bdao.update(board);

			response.sendRedirect("boardList.do");

		} else if (path.equals("/boardDelete.do")) {
			HttpSession session = request.getSession(false);
			Member member = (Member) session.getAttribute("login");
			int num = Integer.parseInt(request.getParameter("num"));
			BoardDao bdao = BoardDao.getInstance();
			Board board = bdao.selectOne(num, false);

			if (member.getId().equals("admin") || member.getMemberno() == board.getMemberno()) {
				bdao.delete(num);
			}

			response.sendRedirect("boardList.do");

		} else if (path.equals("/memberLogin.do")) {
			// 요청은 사용자가 URL을 입력하거나, 링크를 클릭하거나, 폼을 제출하는 등의 동작을 통해 생성됨
			// 사용자의 요청(request)에 포함되어있는 id라는 파라미터를 get하여 id에 저장,
			String id = request.getParameter("id");
			String email = request.getParameter("email");
			String check = request.getParameter("radio");

			Member member = MemberDao.getInstance().selectForLogin(id, email);
			if (member != null && member.getId() != null) {// 일치하는 회원이 있는 경우
				HttpSession session = request.getSession(true);
				session.setAttribute("login", member);

				if (member.getId().equals("admin")) { // 회원의 ID가 admin인 경우 관리자 페이지로 이동
					Cookie cookie = new Cookie("AutoLogin", "");
					Cookie cookie1 = new Cookie("AutoId", "");
					cookie.setMaxAge(0);
					cookie.setPath("/"); // 쿠키의 경로를 설정하여 해당 경로의 모든 페이지에서 쿠키가 제거되도록 함
					response.addCookie(cookie);
					response.addCookie(cookie1);

					RequestDispatcher dispatcher = request.getRequestDispatcher("index.do");
					dispatcher.forward(request, response);

				} else if (check != null && check.equals("login")) { // 자동 로그인을 위한 쿠키 생성

					String loginInfo = String.format("%s/%s", member.getId(), member.getEmail());
					Cookie cookie = new Cookie("AutoLogin", loginInfo);
					cookie.setMaxAge(60 * 60 * 24 * 15);
					cookie.setPath("/");
					response.addCookie(cookie);
					response.sendRedirect("index.do");

				} else if (check != null && check.equals("id")) { // ID 저장을 위한 쿠키 생성

					Cookie cookie = new Cookie("AutoId", id);
					cookie.setMaxAge(60 * 60 * 24 * 15);
					cookie.setPath("/");
					response.addCookie(cookie);
					response.sendRedirect("index.do");
				} else {
					response.sendRedirect("index.do");
				}
			} else {
				response.sendRedirect("memberLoginForm.do");
			}

		} else if (path.equals("/memberList.do")) {
			MemberDao mdao = MemberDao.getInstance();

			String pageNoval = request.getParameter("pageNo");
			int pageNo = 1;
			if (pageNoval != null) {
				pageNo = Integer.parseInt(pageNoval);
			}
			int total = mdao.selectCount() - 1;

			ArrayList<Member> list = mdao.selectPage((((pageNo - 1) * 5) + 1), pageNo * 5);
			ArticlePage articlePage = new ArticlePage(total, pageNo, 5, null, list);
			System.out.println(articlePage);
			request.setAttribute("ArticleM", articlePage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/memberview/memberList.jsp");
			dispatcher.forward(request, response);

			// memberLogout
		} else if (path.equals("/memberLogout.do")) {
			HttpSession session = request.getSession(false);
			Cookie cookie = new Cookie("AutoLogin", "");
			cookie.setMaxAge(0);
			cookie.setPath("/"); // 쿠키의 경로를 설정하여 해당 경로의 모든 페이지에서 쿠키가 제거되도록 함
			response.addCookie(cookie);

			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect("index.do");

		} else if (path.equals("/memberLoginForm.do")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/memberview/memberLoginForm.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/memberDelete.do")) {
			int memberno = Integer.parseInt(request.getParameter("memberno"));

			System.out.println(memberno);
			MemberDao mdao = MemberDao.getInstance();
			int abc = mdao.delete(memberno);
			System.out.println(abc);
			response.sendRedirect("memberList.do");

		} else if (path.equals("/memberUpdate.do")) {

			int memberno = Integer.parseInt(request.getParameter("memberno"));
			String id = request.getParameter("id");
			String email = request.getParameter("email");
			String name = request.getParameter("name");

			MemberDao mdao = MemberDao.getInstance();
			Member member = new Member(memberno, id, email, name);
			mdao.update(member);

			response.sendRedirect("memberList.do");

		} else if (path.equals("/memberUpdateForm.do")) {
			int memberno = Integer.parseInt(request.getParameter("memberno"));

			MemberDao mdao = MemberDao.getInstance();
			Member member = mdao.selectMember(memberno);
			request.setAttribute("member", member);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/memberview/memberUpdateForm.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/memberInsert.do")) {

			String id = request.getParameter("id");
			String email = request.getParameter("email");
			String name = request.getParameter("name");

			MemberDao mdao = MemberDao.getInstance();
			Member member = new Member(0, id, email, name);
			mdao.insert(member);
			response.sendRedirect("memberList.do");

		} else if (path.equals("/memberSignUp.do")) {

			String id = request.getParameter("id");
			String email = request.getParameter("email");
			String name = request.getParameter("name");

			MemberDao mdao = MemberDao.getInstance();
			Member member = new Member(0, id, email, name);
			mdao.insert(member);
			response.sendRedirect("memberLoginForm.do");

		} else if (path.equals("/memberSignUpForm.do")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/memberview/memberSignUpForm.jsp");
			dispatcher.forward(request, response);

		} else if (path.equals("/memberInsertForm.do")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/memberview/memberInsertForm.jsp");
			dispatcher.forward(request, response);
		}
			
	}
}
