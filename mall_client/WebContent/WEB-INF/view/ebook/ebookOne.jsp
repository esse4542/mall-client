<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mall.client.vo.*" %>
<%@ page import="mall.client.controller.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- mainMenu -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<!-- clientOne -->
	<%
		// 형변환
	    Ebook ebook = (Ebook)request.getAttribute("ebook");
	%>
	
	<h2>책 정보</h2>
	<table border="1">
	      <tr>
	         <td>CategoryName</td>
	         <td><%=ebook.getCategoryName() %></td>
	      </tr>
	      <tr>
	         <td>ebookISBN</td>
	         <td><%=ebook.getEbookISBN() %></td>
	      </tr>
	      <tr>
	         <td>ebookTitle</td>
	         <td><%=ebook.getEbookTitle() %></td>
	      </tr>
	      <tr>
	      	<td>ebookState</td>
	      	<td><%=ebook.getEbookState() %></td>
	      </tr>
   </table>

	<!-- InsertCartController?ebookNo - CartDao.insertCart() - redirect:CartListController -->
	<a href="<%=request.getContextPath()%>/InsertCartController?ebookNo=<%=ebook.getEbookNo()%>">
		<%
			if(session.getAttribute("loginClient") == null 
				|| !ebook.getEbookState().equals("판매중")) {
		%>
				<button type="button" disabled="disabled">장바구니추가</button>
		<% 		
			} else {
		%>
				<button type="button">장바구니추가</button>
		<%		
			}
		%>
	</a>
	<a href="<%=request.getContextPath()%>/IndexController?ebookNo=<%=ebook.getEbookNo()%>" class="btn">뒤로 가기</a>
</body>
</html> 