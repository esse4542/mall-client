package mall.client.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.ClientDao;
import mall.client.vo.Client;


@WebServlet("/InsertClientController")
public class InsertClientController extends HttpServlet {
	private ClientDao clientDao;

	// form
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") != null) {
			response.sendRedirect("/IndexController");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/client/insertClient.jsp").forward(request, response);
	}

	// action : C -> M -> redirect(indexController)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.clientDao = new ClientDao();

		// 1. 이메일 중복 검사
		String returnClientMail = this.clientDao.selectClientMail(request.getParameter("clientMail"));
		if(returnClientMail != null) { // 메일이 존재한다면
			System.out.println("사용중인 이메일입니다. 다른 이메일을 입력해주세요.");
			response.sendRedirect(request.getContextPath()+"/InsertClientController");
			return;
		}
		// 2. 회원가입, 파라미터 값
		request.setCharacterEncoding("UTF-8");
		String clientMail = request.getParameter("clientMail");
		String clientPw = request.getParameter("clientPw");
		// 디버깅
		System.out.println("clientMail : " + clientMail);
		System.out.println("clientPw : " + clientPw);

		// 2-1. 전처리
		Client client = new Client(); // 객체 생성 및 초기화
		client.setClientMail(clientMail);
		client.setClientPw(clientPw);

		// 2-2. Dao에서 insert함수 호출
		clientDao.insertClient(client);

		// 3. redirect
		response.sendRedirect(request.getContextPath()+"/IndexController");
	}

}