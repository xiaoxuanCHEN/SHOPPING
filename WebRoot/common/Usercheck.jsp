<%@page import="xm.chen.modal.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	User uCheck = (User) session.getAttribute("user");
	if (uCheck == null)
		response.sendRedirect("login.jsp");
%>