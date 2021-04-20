<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
	<h2>책 정보</h2>
	<table border="1">
		<tr>
			<td>categoryName</td>
			<td>${ebook.categoryName}</td>
		</tr>
	    <tr>
	         <td>ebookISBN</td>
	         <td>${ebook.ebookISBN}</td>
	    </tr>
	    <tr>
	         <td>ebookTitle</td>
	         <td>${ebook.ebookTitle}</td>
	    </tr>
	    <tr>
            <td>ebookImg</td>
            <td><img src="${pageContext.request.contextPath}/img/무야호.png"></td>
        </tr>
		<tr>
			<td>ebookAuthor</td>
			<td>${ebook.ebookAuthor}</td>
		</tr>
		<tr>
			<td>ebookCompany</td>
			<td>${ebook.ebookCompany}</td>
		</tr>
		<tr>
			<td>ebookDate</td>
			<td>${ebook.ebookDate.substring(0,11)}</td>
		</tr>
		<tr>
			<td>ebookPageCount</td>
			<td>${ebook.ebookPageCount}</td>
		</tr>
		<tr>
			<td>ebookState</td>
			<td>${ebook.ebookState}</td>
		</tr>
		<tr>
			<td>ebookPrice</td>
			<td>${ebook.ebookPrice}</td>
		</tr>
		<tr>
			<td>ebookSummary</td>
			<td>${ebook.ebookSummary}</td>
		</tr>
	      
   </table>

	<!-- InsertCartController?ebookNo - CartDao.insertCart() - redirect:CartListController -->
	<a href="${pageContext.request.contextPath}/InsertCartController?ebookNo=${ebook.ebookNo}">
		<c:if test="${loginClient == null || ebook.ebookState == '품절' || ebook.ebookState == '절판' || ebook.ebookState == '구편절판'}">
			<button type="button" disabled="disabled">장바구니 추가</button>
		</c:if>
		<c:if test="${loginClient != null && ebook.ebookState == '판매중'}">
			<button type="button">장바구니 추가</button>
		</c:if>
	</a>
	<a href="${pageContext.request.contextPath}/IndexController?rowPerPage=${rowPerPage}&currentPage=${currentPage}">뒤로 가기</a>
</body>
</html> 