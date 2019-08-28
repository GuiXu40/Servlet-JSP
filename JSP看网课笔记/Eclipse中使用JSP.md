## 1.使用Eclipse开发Web项目(JSP项目)  tomcat

## 2. 在Eclipse中创建的Web项目
浏览器可以直接访问 WebContent中的文件，<br>
例如http://localhost:8888/MyJspProject/index1.jsp<br>
其中的index1.jsp就在WebContent目录中；<br>
但是WEB-INF中的文件  无法通过客户端（浏览器）直接访问，只能通过请求转发来访问<br>

**注意**：并不是 任何的内部跳转都能访问WEB-INF；原因是 跳转有2种方式：请求转发 、重定向

## 3.配置tomcat运行时环境
jsp<->Servlet
+ a.将tomcat/lib中的servlet-api.jar加入项目的构建路径
+ b.右键项目->Build Path -> Add library ->Server Runtime

## 4.部署tomcat
在servers面板 新建一个 tomcat实例 ，  再在该实例中 部署项目（右键-add）
之后运行

**注意**：一般建议 将eclipse中的tomcat与 本地tomcat的配置信息保持一致： 将eclipse中的tomcat设置为托管模式：【第一次】创建tomcat实例之后， 双击，选择Server Location的第二项




## 5.统一字符集编码
a.编码分类：
设置jsp文件的编码（jsp文件中的pageEncoding属性）：  jsp -> java        
设置浏览器读取jsp文件的编码（jsp文件中content属性）
一般将上述设置成 一致的编码，推荐使用UTF-8<br>
文本编码：
+	i.将整个eclipse中的文件 统一设置 （推荐）
+	ii.设置 某一个项目
+	iii.设置单独文件
	

## 6.JSP的页面元素
HTML  java代码（脚本Scriptlet）、指令、注释
+ a.脚本Scriptlet
```   
		<%
				局部变量、java语句
		%>
	i
		<%!
				全局变量、定义方法
		%>

		<%=输出表达式 %>
```

一般而言，修改web.xml、配置文件、java  需要重启tomcat服务
但是如果修改 Jsp\html\css\js ，不需要重启



**注意**:out.println()不能回车； 要想回车：“<br/>”，即out.print() <%= %> 可以直接解析html代码


+ b.指令
page指令
```
<%@ page  ....%>
```
page指定的属性：

属性|描述
---|:--:
language|jsp页面使用的脚本语言
import|导入类
pageEncoding|jsp文件自身编码  jsp ->java
contentType|浏览器解析jsp的编码

```JSP
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="java.util.Date" %>
```

+ c.注释
```
	html注释<!-- -->  ,可以被客户 通过浏览器查看源码 所观察到
	java注释//     /*...*/
	jsp注释<%-- --%>
```

## 7.JSP九大内置对象（自带的，不需要new 也能使用的对象）
+ out：输出对象，向客户端输出内容
+ request：请求对象；存储“客户端向服务端发送的请求信息”
+ request对象的常见方法：
+ String getParameter(String name) :根据请求的字段名key （input标签的name属性值） ，返回字段值value （input标签的value属性值）
+ String[] getParameterValues(String name): 根据请求的字段名key ，返回多个字段值value  （checkbox）
+ void setCharacterEncoding("编码格式utf-8") ：设置post方式的请求编码  （tomcat7以前默认iso-8859-1，tomcat8以后改为了utf-8）
+ getRequestDispatcher("b.jsp").forward(request,response) ;  ：请求转发 的方式跳转页面   A - > B
+ ServletContext getServerContext():获取项目的ServletContext对象

示例：<br>
注册
register.jsp
```JSP
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="show.jsp">
		账号:<input type="text" name="uname"><br>
		密码:<input type="password" name="upwd"><br>
		
		爱好:
			<input type="checkbox" name="uhobby">篮球<br>
			<input type="checkbox" name="uhobby">足球<br>
			<input type="checkbox" name="uhobby">乒乓<br>
		<input type="submit">注册
	</form>
</body>
</html>
```
show.jsp
```JSP
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//设置编码
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("uname");
		
		String pwd= request.getParameter("upwd");
		String[] hobby=request.getParameterValues("uhobby");
	%>
	注册信息为:<br>
		用户名:<%= name%><br>
		
		密码:<%=pwd %><br>
		爱好:
			<%
				if(!(hobby==null)){
					for(String hob:hobby){
						out.println(hob);
					}
				}
			%>
</body>
</html>
```
http://localhost:8888/MyJspProject/show.jsp?uname=aa&upwd=123&uage=22&uhobbies=%E7%AF%AE%E7%90%83<br>
		连接/文件？参数名1=参数值1 & 参数名2=参数值2 & 参数名1=参数值1 
  
get提交方式:  method="get" 和 地址栏 、超链接(<a href="xx">)请求方式 默认都属于get提交方式

**get与post请求方式的区别**：<br>
+ a.   get方式 在地址栏显示 请求信息  (但是地址栏能够容纳的 信息有限，4-5KB；如果请求数据存在大文件，图片等  会出现地址栏无法容纳全部的数据而出错) ；post不会显示
+ b.   文件上传操作，必须是post
推荐使用post

## 8.统一请求的编码 request
get方式请求 如果出现乱码，解决：


+ a.统一每一个变量的 编码 （不推荐）<br>
	new String(  旧编码，新编码);
	name = new String(name.getBytes("iso-8859-1"),"utf-8");

+ b. 修改server.xml ，一次性的 更改tomcat默认get提交方式的编码 （utf-8）
建议 使用tomcat时， 首先在server.xml中 统一get方式的编码.. URIEncoding="UTF-8"


tomcat7 (iso-8859-1)

tomcat8（utf-8）


post
request.setCharacterEncoding("utf-8") ;















