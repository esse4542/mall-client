<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="mall.client.vo.*" %>
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
		Client clientOne = (Client)(request.getAttribute("clientOne"));
	%>
	<h1>회원 정보</h1>
	<table border="1">
		<thead>
			<tr>
				<td>clientNo</td>
				<td>clientMail</td>
				<td>clientDate</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><%=clientOne.getClientNo() %></td>
				<td><%=clientOne.getClientMail() %></td>
				<td><%=clientOne.getClientDate() %></td>
			</tr>
		</tbody>
	</table>
	<!-- UpdateClientPWController.doGet() - updateClientPw.jsp - redirect:/IndexController -->
	<!-- UpdateClientPWController.doPost() - ClientDao.updateClientPW() - session.invalidate() - redirect:/IndexController -->
	<a href="<%=request.getContextPath()%>/UpdateClientPwController" class="btn">비밀번호 수정</a>
	<!-- DeleteClientController - ClientDao.deleteCarByClient(mall),ClientDao.deleteClient() - session.invalidate() - redirect:/IndexController -->
	<a href="<%=request.getContextPath()%>/DeleteClientController" class="btn">회원탈퇴</a>
</body>
</html> 