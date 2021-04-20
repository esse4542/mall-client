<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<td>${clientOne.clientNo}</td>
				<td>${clientOne.clientMail}</td>
				<td>${clientOne.clientDate}</td>
			</tr>
		</tbody>
	</table>
	<!-- UpdateClientPWController.doGet() - updateClientPw.jsp - redirect:/IndexController -->
	<!-- UpdateClientPWController.doPost() - ClientDao.updateClientPW() - session.invalidate() - redirect:/IndexController -->
	<a href="${pageContext.request.contextPath}/UpdateClientPwController" class="btn">비밀번호 수정</a>
	<!-- DeleteClientController - ClientDao.deleteCarByClient(mall),ClientDao.deleteClient() - session.invalidate() - redirect:/IndexController -->
	<a href="${pageContext.request.contextPath}/DeleteClientController" class="btn">회원탈퇴</a>
</body>
</html> 