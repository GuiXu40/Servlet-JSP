# ------------------服务端HTTP响应---------------
## 目录
<a href="#p1">:dart:设置HTTP响应报头的方法</a><br>
<a href="#p2">:dart:HTTP Header 响应实例</a><br>

<p id="p1"></p>

## :black_joker:设置HTTP响应报头的方法
<a href="#title">:flower_playing_cards:回到目录</a>
正如前面的章节中讨论的那样，当一个 Web 服务器响应一个 HTTP 请求时，响应通常包括一个状态行、一些响应报头、一个空行和文档。一个典型的响应如下所示：
```
HTTP/1.1 200 OK
Content-Type: text/html
Header2: ...
...
HeaderN: ...
  (Blank Line)
<!doctype ...>
<html>
<head>...</head>
<body>
...
</body>
</html>
```
方法|描述
String encodeRedirectURL(String url)|为 sendRedirect 方法中使用的指定的 URL 进行编码，或者如果编码不是必需的，则返回 URL 未改变。
String encodeURL(String url)|对包含 session 会话 ID 的指定 URL 进行编码，或者如果编码不是必需的，则返回 URL 未改变。
boolean containsHeader(String name)|返回一个布尔值，指示是否已经设置已命名的响应报头。
boolean isCommitted()|返回一个布尔值，指示响应是否已经提交。
void addCookie(Cookie cookie)|把指定的 cookie 添加到响应。
void addDateHeader(String name, long date)|添加一个带有给定的名称和日期值的响应报头。
void addHeader(String name, String value)|添加一个带有给定的名称和值的响应报头。
void addIntHeader(String name, int value)|添加一个带有给定的名称和整数值的响应报头。
void flushBuffer()|强制任何在缓冲区中的内容被写入到客户端。
void reset()|清除缓冲区中存在的任何数据，包括状态码和头。
void resetBuffer()|清除响应中基础缓冲区的内容，不清除状态码和头。
void sendError(int sc)|使用指定的状态码发送错误响应到客户端，并清除缓冲区。
void sendError(int sc, String msg)|使用指定的状态发送错误响应到客户端。
void sendRedirect(String location)|使用指定的重定向位置 URL 发送临时重定向响应到客户端。
void setBufferSize(int size)|为响应主体设置首选的缓冲区大小。
void setCharacterEncoding(String charset)|设置被发送到客户端的响应的字符编码（MIME 字符集）例如，UTF-8。
void setContentLength(int len)|设置在 HTTP Servlet响应中的内容主体的长度，该方法设置 HTTP Content-Length 头。
void setContentType(String type)|如果响应还未被提交，设置被发送到客户端的响应的内容类型。
void setDateHeader(String name, long date)|设置一个带有给定的名称和日期值的响应报头。
void setHeader(String name, String value)|设置一个带有给定的名称和值的响应报头。
void setIntHeader(String name, int value)|设置一个带有给定的名称和整数值的响应报头。
void setLocale(Locale loc)|如果响应还未被提交，设置响应的区域。
void setStatus(int sc)|为该响应设置状态码

<p id="p2"></p>

## :black_joker:HTTP Header 响应实例
<a href="#title">:flower_playing_cards:回到目录</a>
常用的可以有：void setIntHeader(String name, int value)，该方法可以自动刷新页面：
```java
public class Servlet extends HttpServlet {


    public void init(HttpServletRequest request, HttpServletResponse response) throws ServletException
    {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        // 设置刷新自动加载时间为 3 秒
        response.setIntHeader("Refresh", 3);      //头部名称要为Refresh才能刷新
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out  = response.getWriter();
        //设计随机数
        Random r = new Random();
        
        out.print(r.nextInt(500));

    }
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            doGet(request,response);
    }
    public void destroy()
    {
    
    }
}
```
运行servelt，你可以每隔3秒看到一个随机数。用于自动更新页面。其他方法可以执行尝试，所有的方法都要基于response实例下。
