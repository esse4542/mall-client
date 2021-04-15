<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* " %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- mainMenu -->
	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>

	<!-- cartList -->
	<%
		List<Map<String,Object>> cartList = (List<Map<String,Object>>)(request.getAttribute("cartList"));
	%>
	<h2>CartList</h2>
	
	<table border="1">
		<thead>
			<tr>
				<td>cartNo</td>
				<td>ebookNo</td>
				<td>ebookTitle</td>
				<td>cartDate</td>
				<td>삭제</td>
				<td>주문</td>
			</tr>
		</thead>
		<tbody>
				<tr>
			<%
				for(Map<String,Object> map : cartList) {
			%>
				<tr>
					<td><%=map.get("cartNo")%></td>
					<td><%=map.get("ebookNo")%></td>
					<td><%=map.get("ebookTitle")%></td>
					<td><%=map.get("cartDate")%></td>
					<!-- DeleteCartController - CartDao.deleteCart() - redirect:/CartListController -->
					<td><a href="">삭제</a></td>
					<!-- InsertOrdersController - insertOrders(),deleteCart():ISSUE 트랜처리 - reidirect:/OrdersListController -->
					<td><a href="">주문</a></td>
				</tr>
			<%			
				}
			%>

		</tbody>
	</table>	
</body>
</html>