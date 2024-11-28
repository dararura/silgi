<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="silgi_product.CompanyDTO, silgi_product.InoutDTO, silgi_product.ProductDAO, java.sql.*"%>

<%
String t_no = request.getParameter("t_no");
String p_code = request.getParameter("p_code");
String t_type = request.getParameter("t_type");
String p_cnt = request.getParameter("p_cnt");
String t_date = request.getParameter("t_date");
String c_name = request.getParameter("c_name");

// DAO 객체 생성 및 데이터 저장
ProductDAO productDAO = new ProductDAO();
boolean result = productDAO.inout(t_no, p_code, t_type, p_cnt, t_date, c_code);

if (result) {
    out.println("입출고 등록이 완료되었습니다.");
} else {
    out.println("입출고 등록에 실패했습니다.");
}
%>