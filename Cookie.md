# ------------------Cookie---------------
Cookie 是存储在客户端计算机上的文本文件，并保留了各种跟踪信息。Java Servlet 显然支持 HTTP Cookie。

识别返回用户包括三个步骤：

📣 服务器脚本向浏览器发送一组 Cookie。例如：姓名、年龄或识别号码等。

📣 浏览器将这些信息存储在本地计算机上，以备将来使用。

📣 当下一次浏览器向 Web 服务器发送任何请求时，浏览器会把这些 Cookie 信息发送到服务器，服务器将使用这些信息来识别用户。

所以需要了解如何设置或重置 Cookie，如何访问它们，以及如何将它们删除。

Servlet Cookie 处理需要对中文进行编码与解码，方法如下：
```java
String   str   =   java.net.URLEncoder.encode("中文"，"UTF-8");            //编码
String   str   =   java.net.URLDecoder.decode("编码后的字符串","UTF-8");   // 解码
```
🔎Cookie简介
Cookie 通常设置在 HTTP 头信息中（虽然 JavaScript 也可以直接在浏览器上设置一个 Cookie），并保存在客户机上，多用于身份验证。在浏览器中的开发者工具中含有cookies记录信息，可以查看,如下：



这其中包括了Cookies与Session。这都是用户身份的认证。

🔎Servlet Cookie 方法

方法|描述
---|:--:
public void setDomain(String pattern)|该方法设置 cookie 适用的域，例如 runoob.com。
public String getDomain()|该方法获取 cookie 适用的域，例如 runoob.com。
public void setMaxAge(int expiry)|该方法设置 cookie 过期的时间（以秒为单位）。如果不这样设置，cookie 只会在当前 session 会话中持续有效.
public int getMaxAge()|该方法返回 cookie 的最大生存周期（以秒为单位），默认情况下，-1 表示 cookie 将持续下去，直到浏览器关闭。
public String getName()|该方法返回 cookie 的名称。名称在创建后不能改变。
public void setValue(String newValue)|该方法设置与 cookie 关联的值。
public String getValue()|该方法获取与 cookie 关联的值。
public void setPath(String uri)|该方法设置 cookie 适用的路径。如果您不指定路径，与当前页面相同目录下的（包括子目录下的）所有 URL 都会返回 cookie。
public String getPath()|该方法获取 cookie 适用的路径。
public void setSecure(boolean flag)|该方法设置布尔值，表示 cookie 是否应该只在加密的（即 SSL）连接上发送
public void setComment(String purpose)|设置cookie的注释。该注释在浏览器向用户呈现 cookie 时非常有用。
public String getComment()|获取 cookie 的注释，如果 cookie 没有注释则返回 null。

🔎通过 Servlet 设置 Cookie<br>
通过 Servlet 设置 Cookie 包括三个步骤：

+ (1) 创建一个 Cookie 对象： 可以调用带有 cookie 名称和 cookie 值的 Cookie 构造函数，cookie 名称和 cookie 值都是字符串。
```java
Cookie cookie = new Cookie("key","value");
```
无论是名字还是值，都不应该包含空格或以下任何字符：
```java
[ ] ( ) = , " / ? @ : ;
```
+ (2) 设置最大生存周期： 可以使用 setMaxAge 方法来指定 cookie 能够保持有效的时间（以秒为单位）。下面将设置一个最长有效期为 24 小时的 cookie。
```java
cookie.setMaxAge(60*60*24); 
```
**(3) 发送 Cookie 到 HTTP 响应头: ** 可以使用 response.addCookie 来添加 HTTP 响应头中的 Cookie，如下所示：
```java
response.addCookie(cookie);
```
我们看下如下例子：
```java
public class Servlet extends HttpServlet {


    public void init(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        Cookie cookie1 = new Cookie("UserName","Lumnca");
        Cookie cookie2 = new Cookie("Identity","Administration");

        cookie1.setMaxAge(60);
        cookie2.setMaxAge(60);


        response.addCookie(cookie1);
        response.addCookie(cookie2);

        PrintWriter out  = response.getWriter();

        out.print("Hello!");

    }
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            doGet(request,response);
    }
    public void destroy()
    {
        // 什么也不做
    }
}
```
运行打开浏览器开发工具，在Cookies信息可以看到我们写的Cookies信息。由于我们只设置了1分钟，当一分钟后此Cookie将不存在。



当然也可以用servlet获取Cookies信息，使用前面所说的Http头部方法：
```java
public class Servlet extends HttpServlet {
    public void init(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out  = response.getWriter();
        Cookie cookie1 = new Cookie("UserName","Lumnca");
        Cookie cookie2 = new Cookie("Identity","Administration");
        cookie1.setMaxAge(60);
        cookie2.setMaxAge(60);
        response.addCookie(cookie1);
        response.addCookie(cookie2);

        Cookie[] cookies = request.getCookies();

        response.setIntHeader("Refresh",3);

        for(int i=0;i<cookies.length;i++){
            out.print("  Cookie名称:  "+cookies[i].getName()+"   Cookie值："+cookies[i].getValue()+"<br>");
        }
    }
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            doGet(request,response);
    }
    public void destroy()
    {
        // 什么也不做
    }
}
```
运行3秒后，你将看到所有的Cookies的信息。

还可以通过 Servlet 删除 Cookie，删除 Cookie 是非常简单的。如果您想删除一个 cookie，那么您只需要按照以下三个步骤进行：

读取一个现有的 cookie，并把它存储在 Cookie 对象中。
使用 setMaxAge() 方法设置 cookie 的年龄为零，来删除现有的 cookie。
把这个 cookie 添加到响应头。
如下，依次删除，当数量不足时又添加：
```java
public class Servlet extends HttpServlet {


    public void init(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setIntHeader("Refresh",3);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out  = response.getWriter();

        Cookie[] cookies = request.getCookies();

        if(cookies.length<2){
            Cookie cookie1 = new Cookie("UserName","Lumnca");   //创建一个cookie
            Cookie cookie2 = new Cookie("Identity","Administration");
            cookie1.setMaxAge(60);
            cookie2.setMaxAge(60);
            response.addCookie(cookie1);  //添加cookie
            response.addCookie(cookie2);
        }


        for(int i=0;i<cookies.length;i++){
            out.print("  Cookie名称:  "+cookies[i].getName()+"   Cookie值："+cookies[i].getValue()+"<br>");
        }

        Cookie del = cookies[cookies.length-1];
        if(del!=null&&cookies.length!=1){
            del.setMaxAge(0);
            response.addCookie(del);
        }
    }
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            doGet(request,response);
    }
    public void destroy()
    {
        // 什么也不做
    }
}
```
以上就是Cookies的基本操作，但是Cookies的真正作用并不是这些，则需要和用户验证联系到一起。后面会介绍这种验证方式。
