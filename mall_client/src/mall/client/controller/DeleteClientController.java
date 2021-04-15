package mall.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.model.ClientDao;
import mall.client.vo.Client;

@WebServlet("/DeleteClientController")
public class DeleteClientController extends HttpServlet {
	ClientDao clientDao;
	CartDao cartDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 유효성 검사, redirect
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
				response.sendRedirect(request.getContextPath()+"/IndexController");
				return;
		}
		
		// 수집
		String clientMail = ((Client)(session.getAttribute("loginClient"))).getClientMail();
		System.out.println("clientMail : "+clientMail); // 디버깅

		// model (Dao)
		this.cartDao = new CartDao();
		cartDao.deleteCartByClient(clientMail);
		this.clientDao = new ClientDao();
		clientDao.deleteCartByClient(clientMail);

		// IndexController로 돌아가기
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/IndexController");
	}
}