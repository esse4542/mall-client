<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mall.client.vo.*" %>
<!-- 상단 메인 메뉴 -->
<%
	if(session.getAttribute("loginClient") == null) {
%>
		<!-- 로그아웃 -->
		<div>
			<form action="<%=request.getContextPath()%>/LoginCotroller" method="post">
				ID : <input type="text" name="clientMail">
				PW : <input type="password" name="clientPw">
				<button type="submit">로그인</button>
			</form>
		</div>
<%		
	} else {
%>
		<!-- 로그인-->
		<div>
			<div>
				<%=((Client)(session.getAttribute("loginClient"))).getClientMail() %>
			</div>
			<ul>
				<li><a href="<%=request.getContextPath()%>/LogoutCotroller">로그아웃</a></li>
			</ul>
		</div>
<%	
	}
%>
