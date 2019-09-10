<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
			<%
				//服务端
				Cookie cookie1 = new Cookie("name","zs");
				Cookie cookie2 = new Cookie("pwd","abc");
				
				response.addCookie( cookie1 );
				response.addCookie( cookie2 );
				
				//页面跳转到客户端（转发、重定向）
				response.sendRedirect("result.jsp") ;
				
				
			
			%>
</body>
</html>