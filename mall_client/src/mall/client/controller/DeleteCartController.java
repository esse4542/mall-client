package mall.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.vo.Cart;
import mall.client.vo.Client;

@WebServlet("/DeleteCartController")
public class DeleteCartController extends HttpServlet {
	CartDao cartDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 로그인 유효성 검사, redirect
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
				response.sendRedirect(request.getContextPath()+"/IndexController");
				return;
		}
		
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		
		// dao 호출
		this.cartDao = new CartDao();
		Cart cart = new Cart();
		
		// 삭제 메소드에 넣을 값(ebookNo, clientMail)
		cart.setEbookNo(ebookNo);
		cart.setClientMail(((Client)session.getAttribute("loginClient")).getClientMail());
		
		// 삭제 메소드에 값
		this.cartDao.deleteCart(cart);

		// CartListController
		response.sendRedirect(request.getContextPath()+"/CartListCotroller");
	}	
}