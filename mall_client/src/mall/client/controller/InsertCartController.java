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


@WebServlet("/InsertCartController")
public class InsertCartController extends HttpServlet {
	private CartDao cartDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect("/IndexController");
			return;
		}
		
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		
		this.cartDao = new CartDao();
		Cart cart = new Cart();
		cart.setEbookNo(ebookNo);
		cart.setClientMail(((Client)session.getAttribute("loginClient")).getClientMail());
		
		// 카트안에 동일한 ebook이 존재하는지 확인하는 코드추가
		if(this.cartDao.selectCilentMail(cart)) {
			this.cartDao.insertCart(cart);
		} else {
			System.out.println("장바구니 안에 똑같은 상품이 있습니다.");	
		}
		
		response.sendRedirect(request.getContextPath()+"/CartListCotroller");
	}
}
