<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="silgi_product.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<title>출고매출이익</title>
</head>
<body>
<h1 class="title">(제품별)출고매출이익통계</h1>
<table class="product-table">
	<thead class="product-head-tr">
		<tr>
			<th>제품코드</th>
			<th>제품명</th>
			<th>출고수량</th>
			<th>출고매출수익</th>
		</tr>
	</thead>
	<tbody>
		<%
		ProductDAO productDAO=new ProductDAO();
		List<SalesDTO> list=productDAO.getSales();
		for(SalesDTO salesDTO:list){
		%>
		<tr class="product-tbody-tr">
			<td><%=salesDTO.getP_code() %></td>
			<td><%=salesDTO.getP_name() %></td>
			<td><%=salesDTO.getTotalSales() %></td>
			<td class="product-cost">\<%=String.format("%,d",salesDTO.getSalesprice())%></td>
		</tr>
		<%} %>
	</tbody>
</table>
</body>
</html>