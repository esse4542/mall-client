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

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private ClientDao clientDao;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 유효성 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") != null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		// 
		request.setCharacterEncoding("utf-8"); // 한글이 안 들어와도 해야함
		String clientMail = request.getParameter("clientMail");
		String clientPw = request.getParameter("clientPw");
		Client client = new Client();
		client.setClientMail(clientMail);
		client.setClientPw(clientPw);
		
		System.out.println(client.getClientMail());
		System.out.println(client.getClientPw());
		
		this.clientDao = new ClientDao();
		
		Client returnClient = this.clientDao.login(client);
		
		System.out.println(returnClient.getClientMail());
		System.out.println(returnClient.getClientNo());
		
		if(returnClient != null) {
			session.setAttribute("loginClient", returnClient);
		}
		
		response.sendRedirect(request.getContextPath()+"/IndexController");
	}
}
