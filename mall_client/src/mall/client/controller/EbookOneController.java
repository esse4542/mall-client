package mall.client.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mall.client.model.EbookDao;
import mall.client.vo.Ebook;

@WebServlet("/EbookOneController")
public class EbookOneController extends HttpServlet {
	private EbookDao ebookDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request수집
		int ebookNo = Integer.parseInt(request.getParameter("ebookNo"));
		
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) { // 현재페이지를 받아온다.
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		int rowPerPage = 15;
		if(request.getParameter("rowPerPage") != null) { // rowPerPage가 존재한다면 받아온다.
			rowPerPage = Integer.parseInt(request.getParameter("rowPerPage"));
		}

		System.out.println("currentPage : " + currentPage);
		System.out.println("rowPerPage : " + rowPerPage);
		
		// dao
		this.ebookDao = new EbookDao();
		Ebook ebook = this.ebookDao.selectEbookOne(ebookNo);
		// 
		request.setAttribute("ebook", ebook);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("rowPerPage", rowPerPage);
		request.getRequestDispatcher("/WEB-INF/view/ebook/ebookOne.jsp").forward(request, response);
	}

}
