# ------------------Session跟踪---------------
## 目录
<a href="#p1">:dart:Cookies</a><br>
<a href="#p2">:dart:隐藏的表单字段</a><br>
<a href="#p3">:dart:URL 重写</a><br>
<a href="#p4">:dart:URL HttpSession 对象</a><br>
<a href="#p5">:dart:Session 跟踪实例</a><br>
<a href="#p6">:dart:删除 Session 会话数据</a><br>

<p id="p1"></p>

## :black_joker:Cookies
<a href="#title">:flower_playing_cards:回到目录</a><br>
HTTP 是一种"无状态"协议，这意味着每次客户端检索网页时，客户端打开一个单独的连接到 Web 服务器，服务器会自动不保留之前客户端请求的任何记录。 但是仍然有以下三种方式来维持 Web 客户端和 Web 服务器之间的 session 会话：

一个 Web 服务器可以分配一个唯一的 session 会话 ID 作为每个 Web 客户端的 cookie，对于客户端的后续请求可以使用接收到的 cookie 来识别。 这可能不是一个有效的方法，因为很多浏览器不支持 cookie，所以我们建议不要使用这种方式来维持 session 会话。
<p id="p2"></p>

## :black_joker:隐藏的表单字段
<a href="#title">:flower_playing_cards:回到目录</a><br>
一个 Web 服务器可以发送一个隐藏的 HTML 表单字段，以及一个唯一的 session 会话 ID，如下所示：
```jsp
<input type="hidden" name="sessionid" value="12345">
```
该条目意味着，当表单被提交时，指定的名称和值会被自动包含在 GET 或 POST 数据中。每次当 Web 浏览器发送回请求时，session_id 值可以用于保持不同的 Web 浏览器的跟踪。

这可能是一种保持 session 会话跟踪的有效方式，但是点击常规的超文本链接（）不会导致表单提交，因此隐藏的表单字段也不支持常规的 session 会话跟踪。
<p id="p3"></p>

## :black_joker:URL 重写
<a href="#title">:flower_playing_cards:回到目录</a><br>
您可以在每个 URL 末尾追加一些额外的数据来标识 session 会话，服务器会把该 session 会话标识符与已存储的有关 session 会话的数据相关联。

例如，"http://w3cschool.cc/file.htm;sessionid=12345，session "会话标识符被附加为 sessionid=12345，标识符可被 Web 服务器访问以识别客户端。

URL 重写是一种更好的维持 session 会话的方式，它在浏览器不支持 cookie 时能够很好地工作，但是它的缺点是会动态生成每个 URL 来为页面分配一个 session 会话 ID，即使是在很简单的静态 HTML 页面中也会如此。
<p id="p4"></p>

## :black_joker:URL HttpSession 对象
<a href="#title">:flower_playing_cards:回到目录</a><br>
除了上述的三种方式，Servlet 还提供了 HttpSession 接口，该接口提供了一种跨多个页面请求或访问网站时识别用户以及存储有关用户信息的方式。

Servlet 容器使用这个接口来创建一个 HTTP 客户端和 HTTP 服务器之间的 session 会话。会话持续一个指定的时间段，跨多个连接或页面请求。

您会通过调用 HttpServletRequest 的公共方法 getSession() 来获取 HttpSession 对象，如下所示：
```java
HttpSession session = request.getSession();
```
你需要在向客户端发送任何文档内容之前调用 request.getSession()。下面总结了 HttpSession 对象中可用的几个重要的方法：

方法|描述
---|:--:
public Object getAttribute(String name)|该方法返回在该 session 会话中具有指定名称的对象，如果没有指定名称的对象，则返回 null。
public Enumeration getAttributeNames()|该方法返回 String 对象的枚举，String 对象包含所有绑定到该 session 会话的对象的名称。
public long getCreationTime()|该方法返回该 session 会话被创建的时间，自格林尼治标准时间 1970 年 1 月 1 日午夜算起，以毫秒为单位。
public String getId()|该方法返回一个包含分配给该 session 会话的唯一标识符的字符串。
public long getLastAccessedTime()|该方法返回客户端最后一次发送与该 session 会话相关的请求的时间自格林尼治标准时间 1970 年 1 月 1 日午夜算起，以毫秒为单位。
public int getMaxInactiveInterval()|该方法返回 Servlet 容器在客户端访问时保持 session 会话打开的最大时间间隔，以秒为单位。
public void invalidate()|该方法指示该 session 会话无效，并解除绑定到它上面的任何对象。
public boolean isNew()|如果客户端还不知道该 session 会话，或者如果客户选择不参入该 session 会话，则该方法返回 true。
public void removeAttribute(String name)|该方法将从该 session 会话移除指定名称的对象。
public void setAttribute(String name, Object value)|该方法使用指定的名称绑定一个对象到该 session 会话。
public void setMaxInactiveInterval(int interval)|该方法在 Servlet 容器指示该 session 会话无效之前，指定客户端请求之间的时间，以秒为单位。
<p id="p5"></p>

## :black_joker:Session 跟踪实例
<a href="#title">:flower_playing_cards:回到目录</a><br>
利用上述方法可以完成Session的跟踪，如下，我们创建一个购物的会话，用于查看用户的购物会话。

Buy类：
```java
public class Buy {
    private String BuyName;
    public  int BuyNumber;
    public  int Spend;

    public Buy(String name,int n,int s){
        BuyName = name;
        BuyNumber = n;
        Spend = s;
    }
    public String GetName(){
        return BuyName;
    }
}
```
Servelt：
```java
public class Servlet extends HttpServlet {


    public void init(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out  = response.getWriter();

        HttpSession session = request.getSession(true);          //获取当前Session会话
        session.setAttribute("First",new Buy("铅笔",5,3));       //将对象绑定到会话中。第一个参数为会话名称
        session.setAttribute("Second",new Buy("本子",4,5));

        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //设置时间格式

        out.println("Session名称："+session.getId()+"<br>");
        Buy buy1 = (Buy) session.getAttribute("First");         //获取First会话的对象
        Buy buy2 = (Buy) session.getAttribute("Second");
        out.println("购买时间:"+df.format(session.getCreationTime())+"<br>");
        out.println("物品："+buy1.GetName() + " 数量: "+buy1.BuyNumber+"  花费:"+buy1.Spend+"<br>");
        out.println("物品："+buy2.GetName() + " 数量: "+buy2.BuyNumber+"  花费:"+buy2.Spend+"<br>");
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

可以看出，会话绑定的对象即是我们想要的操作的东西，可以根据Session中的方法来完成对这些对象的操作。
<p id="p6"></p>

## :black_joker:删除 Session 会话数据
<a href="#title">:flower_playing_cards:回到目录</a><br>
当您完成了一个用户的 session 会话数据，您有以下几种选择：

+ 移除一个特定的属性：您可以调用 public void removeAttribute(String name) 方法来删除与特定的键相关联的值。
+ 删除整个 session 会话：您可以调用 public void invalidate() 方法来丢弃整个 session 会话。
+ 设置 session 会话过期时间：您可以调用 public void setMaxInactiveInterval(int interval) 方法来单独设置 session 会话超时。
+ 注销用户：如果使用的是支持 servlet 2.4 的服务器，您可以调用 logout 来注销 Web 服务器的客户端，并把属于所有用户的所有 session 会话设置为无效。
+ web.xml 配置：如果您使用的是 Tomcat，除了上述方法，您还可以在 web.xml 文件中配置 session 会话超时，如下所示：
```
  <session-config>
    <session-timeout>15</session-timeout>
  </session-config>
```
上面实例中的超时时间是以分钟为单位，将覆盖 Tomcat 中默认的 30 分钟超时时间。

在一个 Servlet 中的 getMaxInactiveInterval() 方法会返回 session 会话的超时时间，以秒为单位。所以，如果在 web.xml 中配置 session 会话超时时间为 15 分钟，那么 getMaxInactiveInterval() 会返回 900。
