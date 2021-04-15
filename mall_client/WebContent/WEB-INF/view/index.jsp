<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="mall.client.vo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>
</head>
<body>

	<jsp:include page="/WEB-INF/view/inc/mainMenu.jsp"></jsp:include>
	
	<h1>index</h1>
	<%
		List<Ebook> ebookList = (List<Ebook>)(request.getAttribute("ebookList"));
	%>
	<table border="1">
		<tr>
			<%
				int i = 0;
				for(Ebook ebook : ebookList){
					i+=1;
			%>
					<td>
						<div><img src="<%=request.getContextPath()%>/img/depart.jpg"></div>
						<!-- EbookOneController - EbookDao.selectEbookOne() - ebookOne.jsp -->
						<div>
							<a href="<%=request.getContextPath()%>/EbookOneController?ebookNo=<%=ebook.getEbookNo()%>">
								<%=ebook.getEbookTitle()%>
							</a>
						</div>
						
						<div>￦<%=ebook.getEbookPrice()%></div>
					</td>
			<%		
					if(i%5==0){
			%>
						</tr><tr>
			<%			
					}
				}
			%>
		</tr>
	</table>
</body>
</html>