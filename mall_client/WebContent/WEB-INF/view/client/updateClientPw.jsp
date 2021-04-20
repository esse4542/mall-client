<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "mall.client.vo.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- mainMenu -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<h2>비밀번호 변경</h2>
	<form action = "${pageContext.request.contextPath}/UpdateClientPwController" method = "post">

		<table border = "1">
			<tr>
				<td>ClientMail</td>
				<td>${c.clientMail}</td>
			</tr>
			<tr>
				<td>New Password</td>
				<td><input type="password" name="newClientPw"></td>
			</tr>
		</table>
		<button type = "submit">수정</button>
	</form>
</body>
</html>