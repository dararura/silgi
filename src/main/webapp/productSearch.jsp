<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="silgi_product.ProductDAO, silgi_product.ProductDTO, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<title>productSerch</title>
</head>
<body>
<h1 class="title">제품조회</h1>
<table class="product-table">
	<thead class="product-head-tr">
		<tr>
			<th>제품코드</th>
			<th>제품명</th>
			<th>사이즈</th>
			<th>매입단가</th>
			<th>출고단가</th>
		</tr>
	</thead>
	<tbody>
	<%
	ProductDAO productDAO=new ProductDAO();
	List<ProductDTO> list=productDAO.productList();
	for(ProductDTO productDTO : list){
	%>
	<tr class="product-tbody-tr">
		<td><%=productDTO.getP_code()%></td>
		<td><%=productDTO.getP_name()%></td>
		<td><%=productDTO.getP_size()%> mm</td>
		<td class="product-cost">\<%=String.format("%,d",productDTO.getP_incost())%></td>
		<td class="product-cost">\<%=String.format("%,d",productDTO.getP_outcost())%></td>
	</tr>
	
	<%} %>
	</tbody>
</table>
</body>
</html>