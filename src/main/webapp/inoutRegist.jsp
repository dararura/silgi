<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="silgi_product.CompanyDTO, silgi_product.ProductDAO, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<script src="js/js.js"></script>
<meta charset="UTF-8">
<title>입출고등록</title>
</head>
<body>
<h1>입출고등록</h1>
<form method="post" id="inputForm" action="registAction.jsp" onsubmit="return checkForm()">
<table>
	<tr>
		<td>입출고번호</td>
		<%
		ProductDAO productDAO=new ProductDAO();
		int num=productDAO.getNext();
		%>
		<td><input type="text" name="t_no" id="t_no" value="<%=num %>" readonly="readonly"> 예)20200006</td>
	</tr>
	<tr>
		<td>제품코드</td>
		<td><input type="text" name="p_code" id="p_code"></td>
	</tr>
	<tr>
		<td>입출고구분</td>
		<td>
			<input type="radio" name="t_type" value="I" checked="checked">입고
			<input type="radio" name="t_type" value="O">출고
		</td>
	</tr>
	<tr>
		<td>수량</td>
		<td><input type="text" name="p_cnt" id="p_cnt">
	</tr>
	<tr>
		<td>거래일자</td>
		<td><input type="text" name="t_date" id="t_date"></td>
	</tr>
	<tr>
		<td>거래처</td>
		<td>
		<%
			List<CompanyDTO> list=productDAO.getCompany();
		%>
			<select name="c_code" id="c_code">
				<option value="">거래처명</option>
				<%for(CompanyDTO name:list){%>
				<option value="<%=name.getC_code()%>"><%=name.getC_name()%></option>
				<%}%>
			</select>
		</td>
	</tr>
	<tr>
		<td>
			<input type="submit" value="입출고등록"><button type="button" onclick="reForm()">다시쓰기</button>
		</td>
	</tr>
</table>
</form>
</body>
</html>