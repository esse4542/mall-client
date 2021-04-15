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
	
<%
	//클라이언트 리스트 받아오기
	Client clientOne = (Client)(request.getAttribute("clientOne"));
	System.out.println(clientOne + "<--clientOne");
%>
	<h2>비밀번호 변경</h2>
	<form action = "<%=request.getContextPath()%>/UpdateClientPwController" method = "post">

		<table border = "1">
			<tr>
				<td>ClientMail</td>
				<td><%=clientOne.getClientMail()%></td>
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