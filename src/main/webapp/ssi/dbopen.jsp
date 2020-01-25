<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>

<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%
	request.setCharacterEncoding("utf-8");
	String url = "jdbc:mysql://hosh.cbome6leip4k.ap-northeast-2.rds.amazonaws.com:3306/mydb?useSSL=false&amp;serverTimezone=UTC";
	String user = "admin";
	String password = "12345678";
	String driver = "com.mysql.jdbc.Driver";
	Class.forName(driver);
	Connection con = DriverManager.getConnection(url, user, password);
	StringBuffer sql = new StringBuffer();
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
%>

