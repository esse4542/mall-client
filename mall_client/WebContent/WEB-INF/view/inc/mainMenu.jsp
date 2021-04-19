<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mall.client.vo.*" %>
<!-- 상단 메인 메뉴 -->
<%
	if(session.getAttribute("loginClient") == null) {
%>
		<!-- 비로그인 일때 -->
		<div>
			<form action="<%=request.getContextPath()%>/LoginController" method="post">
				ID : <input type="text" name="clientMail" value="tdonlonhg@live.com">
				PW : <input type="password" name="clientPw" value="1234">
				<button type="submit">로그인</button>
			</form>
			<ul>
				<!-- InsertClientController - /view/client/insertClient.jsp -->
				<li><a href="<%=request.getContextPath()%>/InsertClientController">회원가입</a></li>
				<li><a href="<%=request.getContextPath()%>/EbookCalendarController">ebook 달력</a></li>
			</ul>
		</div>
<%		
	} else {
%>
		<!-- 로그인 되었을 때-->
		<div>
			<div>
				<%=((Client)(session.getAttribute("loginClient"))).getClientMail() %>님 반갑습니다.
			</div>
			<ul>
				<li><a href="<%=request.getContextPath()%>/IndexController">홈</a></li>
				<li><a href="<%=request.getContextPath()%>/LogoutController">로그아웃</a></li>
				<!-- ClientOneController - ClientDao.selectClientOne() - /view/client/clientOne.jsp -->
				<li><a href="<%=request.getContextPath()%>/ClientOneController">회원정보</a></li>
				<li><a href="<%=request.getContextPath()%>/CartListCotroller">장바구니</a></li>
				<!-- OrdersListController - OrdersDao.selectOrdersListByClient() - OrdersList.jsp -->
				<li><a href="<%=request.getContextPath()%>/OrdersListController">주문리스트</a></li>
				<li><a href="<%=request.getContextPath()%>/EbookCalendarController">ebook 달력</a></li>
			</ul>
		</div>
<%	
	}
%>
