<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="silgi_product.*"%>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>

<%
String t_no = request.getParameter("t_no");
String p_code = request.getParameter("p_code");
String t_type = request.getParameter("t_type");
int p_cnt = Integer.parseInt(request.getParameter("p_cnt"));
String t_dateStr = request.getParameter("t_date");
String c_code = request.getParameter("c_code");

java.sql.Date t_date =null;
try {
    if (t_dateStr != null && t_dateStr.matches("\\d{8}")) { // 8자리 숫자인지 확인
        String formattedDate = t_dateStr.substring(0, 4) + "-" + 
                               t_dateStr.substring(4, 6) + "-" + 
                               t_dateStr.substring(6, 8); // YYYY-MM-DD 형식으로 변환
        t_date = java.sql.Date.valueOf(formattedDate); // java.sql.Date로 변환
    }
} catch (IllegalArgumentException e) {
    e.printStackTrace();
    return;
}

// DAO 객체 생성 및 데이터 저장
ProductDAO productDAO = new ProductDAO();
boolean result = productDAO.inout(t_no, p_code, t_type, p_cnt, t_date, c_code);

if (result) {
%>
<script>
	alert("입출고등록이 정상적으로 등록되었습니다!");
	location.href="index.jsp";
</script>
<%
}
%>