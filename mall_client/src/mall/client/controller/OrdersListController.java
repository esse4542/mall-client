package mall.client.controller;

import java.io.IOException;

import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import mall.client.model.OrdersDao;
import mall.client.vo.Client;


@WebServlet("/OrdersListController")
public class OrdersListController extends HttpServlet {
	private OrdersDao ordersDao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 유효성 검사, redirect
		HttpSession session = request.getSession();
		if(session.getAttribute("loginClient") == null) {
				response.sendRedirect(request.getContextPath()+"/IndexController");
				return;
		}
		
		// request 호출, request 분석
		int currentPage = 1; // 현페 페이지
		if(request.getParameter("currentPage") != null) { // 현재페이지를 받아옴
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		int rowPerPage = 10; // 페이지당 행의 개수
		if(request.getParameter("rowPerPage") != null) { // rowPerPage가 존재한다면 받아옴
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}

		int beginRow = (currentPage-1) * rowPerPage; // 시작 행

		String searchTitle = ""; // 검색할 제목
		if(request.getParameter("searchTitle") != null) {
			searchTitle = request.getParameter("searchTitle");
		}

		System.out.println("currentPage : " + currentPage);
		System.out.println("rowPerPage : " + rowPerPage);
		System.out.println("searchWord : " + searchTitle);
		
		//의존 객체 생성
		this.ordersDao = new OrdersDao();
		
		// dao 매소드 호출
		Client client = (Client)session.getAttribute("loginClient");
		List<Map<String, Object>> ordersList = this.ordersDao.selectOrdersListByClient(client.getClientNo(), beginRow, rowPerPage, searchTitle);
		int totalRow = ordersDao.totalCount(searchTitle);
		System.out.println("totalRow : " + totalRow);
		
		request.setAttribute("ordersList", ordersList);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("searchTitle", searchTitle);
		request.getRequestDispatcher("/WEB-INF/view/orders/ordersList.jsp").forward(request, response);
		
	}
}
