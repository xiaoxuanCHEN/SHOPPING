<%@page import="xm.chen.modal.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	User admins = (User) session.getAttribute("user");
	if (admins == null) {
		response.sendRedirect("../user/login.jsp");
		return;
	}
%>