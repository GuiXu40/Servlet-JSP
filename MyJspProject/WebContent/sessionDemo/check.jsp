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
			if(name.equals("zs") && pwd.equals("abc")){//假设 zs abc
				//只有登录成功，session中才会存在uname /upwd
				session.setAttribute("uname", name)		 ;
				session.setAttribute("upwd", pwd)		;
				System.out.println("sessionId"+session.getId());
				
				//Cookie cookie = new Cookie("uname" ,namxe);
				//response.addCookie(cookie) ;
				//服务端在第一次响应客户端时，会发送一个 JSESSIONID的cookie
				
				//session.setMaxInactiveInterval(10) ;
				
				request.getRequestDispatcher("welcome.jsp").forward(request, response) ;
				
			
			}else{
				//登录失败
				response.sendRedirect("login.jsp") ;
			}
		
		%>
</body>
</html>