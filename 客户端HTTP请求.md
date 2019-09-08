# -----------------客户端HTTP请求---------------
## :black_joker:读取HTTP头部的方法
+ Cookie[] getCookies() : 返回一个数组，包含客户端发送该请求的所有的 Cookie 对象。
这个方法我们在后面的cookies再做说明

+ Enumeration getParameterNames() ： 返回一个 String 对象的枚举，包含在该请求中包含的参数的名称。
主要用来查看提交参数，像提交的表单的内容
```Java
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        Enumeration ParameterNames = request.getParameterNames();    //获取该请求中包含的参数的名称

        while(ParameterNames.hasMoreElements())                    //是否有其他元素
        {
            String paramName = (String)ParameterNames.nextElement();     //获取name属性
            String paramValue = request.getParameter(paramName);        //获取value值
            out.print(paramName +":" + paramValue+"\n");
        } 
    }
```
当然这需要提交表单的信息以及get方法，所以在html页面添加提交的表单：
```Jsp
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>菜鸟教程(runoob.com)</title>
</head>
<body>

<form action="http://localhost:8080/Demo_war_exploded/servlet" method="GET">
    ID：<input type="text" name="id">
    <br />
    PassWord：<input type="text" name="pw" />
    <br>
    <input type="submit" value="提交" />
</form>

</body>
</html>
```
输入信息提交后，会在页面看到表单属性与参数。

+ Enumeration getHeaderNames() ： 返回一个枚举，包含在该请求中包含的所有的头名。
该方法主要是查看Http头部内容：
```java
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        Enumeration HeaderNames = request.getHeaderNames();    //获取头部内容

        while(HeaderNames.hasMoreElements()) {
            String paramName = (String)HeaderNames.nextElement();      //获取下一属性
            String paramValue = request.getHeader(paramName);          //返回属性信息值
            out.print(paramName +":" + paramValue+"<br>");
        }
    }
```
然后在页面上可以看到http头部内容：
```
host:localhost:8080
connection:keep-alive
user-agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.26 Safari/537.36 Core/1.63.6788.400 QQBrowser/10.3.2714.400
upgrade-insecure-requests:1
accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8
accept-encoding:gzip, deflate, br
accept-language:zh-CN,zh;q=0.9
cookie:JSESSIONID=066345369839E00DF3ECB347140228B0
HttpSession getSession() : 返回与该请求关联的当前 session 会话，或者如果请求没有 session 会话，则创建一个。
```
+ HttpSession getSession(boolean create) : 返回与该请求关联的当前 HttpSession，或者如果没有当前会话，且创建是真的，则返回一个新的 session 会话。

后面会介绍这个，这里不做解释。

其他方法参见下表:

方法|描述
---|:--:
String getAuthType()|返回用于保护 Servlet 的身份验证方案的名称，例如，"BASIC" 或 "SSL"，如果JSP没有受到保护则返回 null。
String getCharacterEncoding()|返回请求主体中使用的字符编码的名称。
String getContentType()|返回请求主体的 MIME 类型，如果不知道类型则返回 null。
String getContextPath()|返回指示请求上下文的请求 URI 部分。
String getHeader(String name)|以字符串形式返回指定的请求头的值。
String getMethod()|返回请求的 HTTP 方法的名称，例如，GET、POST 或 PUT。
String getParameter(String name)|以字符串形式返回请求参数的值，或者如果参数不存在则返回 null。
String getPathInfo()|当请求发出时，返回与客户端发送的 URL 相关的任何额外的路径信息。
String getProtocol()|返回请求协议的名称和版本。
String getQueryString()|返回包含在路径后的请求 URL 中的查询字符串。
String getRemoteAddr()|返回发送请求的客户端的互联网协议（IP）地址。
String getRemoteHost()|返回发送请求的客户端的完全限定名称。
String getRemoteUser()|如果用户已通过身份验证，则返回发出请求的登录用户，或者如果用户未通过身份验证，则返回 null。
String getRequestURI()|从协议名称直到 HTTP 请求的第一行的查询字符串中，返回该请求的 URL 的一部分。
String getRequestedSessionId()|返回由客户端指定的 session 会话 ID。
String getServletPath()|返回调用 JSP 的请求的 URL 的一部分。
String[] getParameterValues(String name)|返回一个字符串对象的数组，包含所有给定的请求参数的值，如果参数不存在则返回 null。
boolean isSecure()|返回一个布尔值，指示请求是否使用安全通道，如 HTTPS。
int getContentLength()|以字节为单位返回请求主体的长度，并提供输入流，或者如果长度未知则返回 -1。
int getIntHeader(String name)|返回指定的请求头的值为一个 int 值。
int getServerPort()|返回接收到这个请求的端口号。
int getParameterMap()|将参数封装成 Map 类型。
