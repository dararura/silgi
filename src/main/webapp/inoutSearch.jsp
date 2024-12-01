<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="silgi_product.ProductDAO"%>
<%@ page import="silgi_product.InoutDTO" %>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<meta charset="UTF-8">
<title>입출고내역조회</title>
</head>
<body>
<h1 class="title">입출고내역조회</h1>
<table class="product-table">
	<thead class="product-head-tr">
		<tr>
			<th>입출고번호</th>
			<th>제품코드</th>
			<th>제품명</th>
			<th>입출고구분</th>
			<th>수량</th>
			<th>거래처</th>
			<th>거래일자</th>
		</tr>
	</thead>
	<tbody>
		<%
		ProductDAO productDAO=new ProductDAO();
		List<InoutDTO> list=productDAO.getInout();;
		for(InoutDTO inoutDTO:list){
		%>
		<tr class="product-tbody-tr">
			<td><%=inoutDTO.getT_no() %></td>
			<td><%=inoutDTO.getP_code() %></td>
			<td><%=inoutDTO.getP_name()%></td>
			<td><%=inoutDTO.getT_type() %></td>
			<td><%=inoutDTO.getT_cnt() %></td>
			<td><%=inoutDTO.getC_name() %></td>
			<td><%=inoutDTO.getT_date() %></td>
		</tr>
		<%} %>
	</tbody>
</table>
</body>
</html>