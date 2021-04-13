package mall.client.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mall.client.model.CartDao;
import mall.client.vo.Client;

@WebServlet("/CartListCotroller")
public class CartListCotroller extends HttpServlet {
	private CartDao cartDao;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 되어있는 사람만 볼 수 있음
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
			response.sendRedirect(request.getContextPath()+"/IndexController");
			return;
		}
		
		//인코딩
		request.setCharacterEncoding("utf-8");
		
		//로그인 정보 가져오기.
		String clientMail = ((Client)(session.getAttribute("loginClient"))).getClientMail();
		
		// Daoȣ��
		this.cartDao = new CartDao();
		List<Map<String,Object>> cartList = this.cartDao.selectCartList(clientMail);

		// View forward , īƮ����Ʈ�� ���ư���
		request.setAttribute("cartList", cartList);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/cart/cartList.jsp");
		rd.forward(request, response);
	}
}
