package mall.client.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.CategoryDao;
import mall.client.model.EbookDao;
import mall.client.model.OrdersDao;
import mall.client.model.StatsDao;
import mall.client.vo.Ebook;
import mall.client.vo.Stats;

// C -> M -> V
@WebServlet("/IndexController")
public class IndexController extends HttpServlet {
	private EbookDao ebookDao;
	private CategoryDao categoryDao;
	private OrdersDao ordersDao;
	private StatsDao statsDao;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request
		
		System.out.println("/IndexController 시작...");
		
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

		String categoryName = ""; // 카테고리
		if(request.getParameter("categoryName") != null) {
			categoryName = request.getParameter("categoryName");
		}

		System.out.println("currentPage : " + currentPage);
		System.out.println("rowPerPage : " + rowPerPage);
		System.out.println("searchTitle : " + searchTitle);
		System.out.println("categoryName : " + categoryName);
		
		// model 
		this.ebookDao = new EbookDao();
		this.categoryDao = new CategoryDao();
		this.ordersDao = new OrdersDao();
		List<Ebook> ebookList = this.ebookDao.selectEbookListByPage(beginRow, rowPerPage, searchTitle, categoryName);
		List<String> categoryNameList = this.categoryDao.categoryNameList();
		List<Map<String, Object>> bestOrdersList = this.ordersDao.selectBestOrdersList();
		int totalRow = ebookDao.totalCount(searchTitle, categoryName);
		System.out.println("totalRow : " + totalRow);
		
		
		//접속자 관련 
		this.statsDao= new StatsDao(); 
		long total = this.statsDao.selectStatsTotal(); // 전체 접속자
		Stats stats = this.statsDao.selectStatsByToday(); // today 접속자
		long statsCount =0;
		if(stats != null) {
			statsCount = stats.getStatsCount();	
		}
		
		//
		request.setAttribute("total", total);
		request.setAttribute("statsCount", statsCount);
		request.setAttribute("bestOrdersList", bestOrdersList);
		request.setAttribute("ebookList", ebookList);
		request.setAttribute("categoryNameList", categoryNameList);
		request.setAttribute("rowPerPage", rowPerPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("searchTitle", searchTitle);
		request.setAttribute("categoryName", categoryName);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/index.jsp");
		rd.forward(request, response);
	}
}
