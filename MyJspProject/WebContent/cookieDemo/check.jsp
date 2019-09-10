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
			request.setCharacterEncoding("utf-8") ;
			String name = request.getParameter("uname");
			String pwd = request.getParameter("upwd");
			
			//将用户名 加入到Cookie种
			Cookie cookie = new Cookie("uname",name);
			
			cookie.setMaxAge(10) ;
			
			response.addCookie(cookie) ;
			
			response.sendRedirect("A.jsp") ;
		
		%>
</body>
</html>