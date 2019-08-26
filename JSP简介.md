# ------------------JSP简介---------------
<p id="title"></p>

## 目录
<a href="#p1">:dart:JSP结构</a><br>
<a href="#p2">:dart:JSP生命周期</a><br>

<p id="p1"></p>

## :black_joker:JSP结构
<a href="#title">:flower_playing_cards:回到目录</a>
网络服务器需要一个 JSP 引擎，也就是一个容器来处理 JSP 页面。**容器负责截获对 JSP 页面的请求**<br>
JSP 容器与 Web 服务器协同合作，为JSP的正常运行提供必要的运行环境和其他服务，并且能够正确识别专属于 JSP 网页的特殊元素。

下图显示了 JSP 容器和 JSP 文件在 Web 应用中所处的位置。
<img src="img"/>
#### :memo:JSP处理
以下步骤表明了 Web 服务器是如何使用JSP来创建网页的：

+ 就像其他普通的网页一样，您的浏览器发送一个 HTTP 请求给服务器。
+ Web 服务器识别出这是一个对 JSP 网页的请求，并且将该请求传递给 JSP 引擎。通过使用 URL或者 .jsp 文件来完成。
+ JSP 引擎从磁盘中载入 JSP 文件，然后将它们转化为 Servlet。这种转化只是简单地将所有模板文本改用 println() 语句，并且将所有的 JSP 元素转化成 Java 代码。
+ JSP 引擎将 Servlet 编译成可执行类，并且将原始请求传递给 Servlet 引擎。
+ Web 服务器的某组件将会调用 Servlet 引擎，然后载入并执行 Servlet 类。在执行过程中，Servlet 产生 HTML 格式的输出并将其内嵌于 HTTP response 中上交给 Web 服务器。
+ Web 服务器以静态 HTML 网页的形式将 HTTP response 返回到您的浏览器中。
+ 最终，Web 浏览器处理 HTTP response 中动态产生的HTML网页，就好像在处理静态网页一

以上提及到的步骤可以用下图来表示：<br>
<img src="img/">
<p id="p2"></p>

## :black_joker:JSP生命周期
<a href="#title">:flower_playing_cards:回到目录</a>
类似于servlet生命周期，区别在于**JSP生命周期还包括将JSP文件编译成servlet**,以下是JSP生命周期中所走过的几个阶段：
+ 编译阶段：
   + servlet容器编译servlet源文件，生成servlet类
+ 初始化阶段：
   + 加载与JSP对应的servlet类，创建其实例，并调用它的初始化方法
+ 执行阶段：
   + 调用与JSP对应的servlet实例的服务方法
+ 销毁阶段：
   +调用与JSP对应的servlet实例的销毁方法，然后销毁servlet实例
#### :memo:JSP编译
当浏览器请求JSP页面时，JSP引擎会首先去检查是否需要编译这个文件。如果这个文件没有被编译过，或者在上次编译后被更改过，则编译这个JSP文件。

编译的过程包括三个步骤：

+ 解析JSP文件。
+ 将JSP文件转为servlet。
+ 编译servlet
#### :memo:JSP初始化
容器载入JSP文件后，它会在为请求提供任何服务前调用jspInit()方法。如果您需要执行自定义的JSP初始化任务，复写jspInit()方法就行了，就像下面这样：
```Java
public void jspInit(){
  // 初始化代码
}
```
一般来讲程序只初始化一次，servlet也是如此。通常情况下您可以在jspInit()方法中初始化数据库连接、打开文件和创建查询表。
#### :memo:JSP执行
这一阶段描述了JSP生命周期中一切与请求相关的交互行为，直到被销毁。

当JSP网页完成初始化后，JSP引擎将会调用_jspService()方法。

 jspService()方法需要一个HttpServletRequest对象和一个HttpServletResponse对象作为它的参数，就像下面这样：
```Java
void _jspService(HttpServletRequest request,
                 HttpServletResponse response)
{
   // 服务端处理代码
}
```
jspService()方法在每个request中被调用一次并且负责产生与之相对应的response，并且它还负责产生所有7个HTTP方法的回应，比如GET、POST、DELETE等等。
#### :memo:清理
JSP生命周期的销毁阶段描述了当一个JSP网页从容器中被移除时所发生的一切。

jspDestroy()方法在JSP中等价于servlet中的销毁方法。当您需要执行任何清理工作时复写jspDestroy()方法，比如释放数据库连接或者关闭文件夹等等。

jspDestroy()方法的格式如下：
```java
public void jspDestroy()
{
   // 清理代码
}
```
#### :memo:实例
```Java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>life.jsp</title>
</head>
<body>

<%! 
  private int initVar=0;
  private int serviceVar=0;
  private int destroyVar=0;
%>
  
<%!
  public void jspInit(){
    initVar++;
    System.out.println("jspInit(): JSP被初始化了"+initVar+"次");
  }
  public void jspDestroy(){
    destroyVar++;
    System.out.println("jspDestroy(): JSP被销毁了"+destroyVar+"次");
  }
%>

<%
  serviceVar++;
  System.out.println("_jspService(): JSP共响应了"+serviceVar+"次请求");

  String content1="初始化次数 : "+initVar;
  String content2="响应客户请求次数 : "+serviceVar;
  String content3="销毁次数 : "+destroyVar;
%>
<h1>菜鸟教程 JSP 测试实例</h1>
<p><%=content1 %></p>
<p><%=content2 %></p>
<p><%=content3 %></p>

</body>
</html>
```
