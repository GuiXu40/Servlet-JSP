# -----------------JSP语法--------------
## 目录
<a href="#p1">:dart:脚本代码</a><br>
<a href="#p2">:dart:JSP声明</a><br>
<a href="#p3">:dart:JSP表达式</a><br>
<a href="#p4">:dart:JSP 动作元素</a><br>

<p id="p1"></p>

## :black_joker:脚本代码
<a href="#title">:flower_playing_cards:回到目录</a>
脚本程序可以包含任意量的Java语句、变量、方法或表达式，只要它们在脚本语言中是有效的。

脚本程序的语法格式：
```Java
<% 代码片段 %>
```
或者，您也可以编写与其等价的XML语句，就像下面这样：
```Java
<jsp:scriptlet>
   代码片段
</jsp:scriptlet>
```
任何文本、HTML标签、JSP元素必须写在脚本程序的外面。<br>
实例:
```Java
<html>
<head><title>Hello World</title></head>
<body>
Hello World!<br/>
<%
out.println("Your IP address is " + request.getRemoteAddr());
%>
</body>
</html>
```
**中文乱码问题**
如果我们要在页面正常显示中文，我们需要在 JSP 文件头部添加以下代码：
```Java
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
```
<p id="p2"></p>

## :black_joker:JSP声明
<a href="#title">:flower_playing_cards:回到目录</a>
一个声明语句可以声明一个或多个变量、方法，供后面的Java代码使用。在JSP文件中，您必须先声明这些变量和方法然后才能使用它们。

JSP声明的语法格式：
```Java
<%! declaration; [ declaration; ]+ ... %>
```
或者，您也可以编写与其等价的XML语句，就像下面这样：
```Java
<jsp:declaration>
   代码片段
</jsp:declaration>
```
<p id="p3"></p>

## :black_joker:JSP表达式
<a href="#title">:flower_playing_cards:回到目录</a>
一个JSP表达式中包含的脚本语言表达式，先被转化成String，然后插入到表达式出现的地方。

由于表达式的值会被转化成String，所以您可以在一个文本行中使用表达式而不用去管它是否是HTML标签。

表达式元素中可以包含任何符合Java语言规范的表达式，但是不能使用分号来结束表达式。

JSP表达式的语法格式：
```Java
<%= 表达式 %>
```
同样，您也可以编写与之等价的XML语句：
```Java 
<jsp:expression>
   表达式
</jsp:expression>
```
实例
```JSP
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>菜鸟教程(runoob.com)</title>
</head>
<body>
<p>
   今天的日期是: <%= (new java.util.Date()).toLocaleString()%>
</p>
</body> 
</html>
```
<p id="p4"></p>

## :black_joker:指令
<a href="#title">:flower_playing_cards:回到目录</a>
JSP指令用来设置与整个JSP页面相关的属性。

JSP指令语法格式：
```Java
<%@ directive attribute="value" %>
```
3中指令标签

指令|描述
---|:--:
<%@ page ... %>|定义页面的依赖属性，比如脚本语言、error页面、缓存需求等等
<%@ include ... %>|包含其他文件
<%@ taglib ... %>|引入标签库的定义，可以是自定义标签

#### :memo:page指令
Page指令为容器提供当前页面的使用说明。一个JSP页面可以包含多个page指令。

Page指令的语法格式：
```Java
<%@ page attribute="value" %>
```
等价的XML格式：
```Java 
<jsp:directive.page attribute="value" />
```
下表列出与Page指令相关的属性：

属性名|作用
---|:--:
buffer|	指定out对象使用缓冲区的大小
autoFlush|	控制out对象的 缓存区
contentType | 指定当前JSP页面的MIME类型和字符编码
errorPag|指定当JSP页面发生异常时需要转向的错误处理页面
isErrorPage|指定当前页面是否可以作为另一个JSP页面的错误处理页面
extends|指定servlet从哪一个类继承
import|导入要使用的Java类
info|定义JSP页面的描述信息
isThreadSafe|指定对JSP页面的访问是否为线程安全
language|定义JSP页面所用的脚本语言，默认是Java
session|指定JSP页面是否使用session
isELIgnored|指定是否执行EL表达式
isScriptingEnabled|确定脚本元素能否被使用

**Include指令**<br>
JSP可以通过include指令来包含其他文件。被包含的文件可以是JSP文件、HTML文件或文本文件。包含的文件就好像是该JSP文件的一部分，会被同时编译执行。

Include指令的语法格式如下：
```Java
<%@ include file="文件相对 url 地址" %>
```
**Taglib指令**
JSP API允许用户自定义标签，一个自定义标签库就是自定义标签的集合。

Taglib指令引入一个自定义标签集合的定义，包括库路径、自定义标签。

Taglib指令的语法：
```Java
<%@ taglib uri="uri" prefix="prefixOfTag" %>
```
uri属性确定标签库的位置，prefix属性指定标签库的前缀。

等价的XML语法：
```jsp
<jsp:directive.taglib uri="uri" prefix="prefixOfTag" />
```
<p id="p5"></p>

## :black_joker:JSP 动作元素
<a href="#title">:flower_playing_cards:回到目录</a>
与JSP指令元素不同的是，JSP动作元素在请求处理阶段起作用。JSP动作元素是用XML语法写成的。

利用JSP动作可以动态地插入文件、重用JavaBean组件、把用户重定向到另外的页面、为Java插件生成HTML代码。

动作元素只有一种语法，它符合XML标准：
```JSP
<jsp:action_name attribute="value" />
```
动作元素基本上都是预定义的函数，JSP规范定义了一系列的标准动作，它用JSP作为前缀，可用的标准动作元素如下：

语法|描述
---|:--:
jsp:include|在页面被请求的时候引入一个文件。
jsp:useBean|寻找或者实例化一个JavaBean。
jsp:setProperty|设置JavaBean的属性。
jsp:getProperty|输出某个JavaBean的属性。
jsp:forward|把请求转到一个新的页面。
jsp:plugin|根据浏览器类型为Java插件生成OBJECT或EMBED标记。
jsp:element|定义动态XML元素
jsp:attribute|设置动态定义的XML元素属性。
jsp:body|设置动态定义的XML元素内容。
jsp:text|在JSP页面和文档中使用写入文本的模板

--------------
所有的动作要素都有两个属性：id属性和scope属性。

+ **id属性**：id属性是动作元素的唯一标识，可以在JSP页面中引用。动作元素创建的id值可以通过PageContext来调用。
+ **scope属性**：该属性用于识别动作元素的生命周期。 id属性和scope属性有直接关系，scope属性定义了相关联id对象的寿命。 scope属性有四个可能的值： (a) page, (b)request, (c)session, 和 (d) application。
-------------------
#### :memo:<jsp:include>动作元素
<jsp:include>动作元素用来包含静态和动态的文件。该动作把指定文件插入正在生成的页面。语法格式如下：
```jsp
<jsp:include page="相对 URL 地址" flush="true" />
```
　前面已经介绍过include指令，它是在JSP文件被转换成Servlet的时候引入文件，而这里的jsp:include动作不同，插入文件的时间是在页面被请求的时候。

以下是include动作相关的属性列表。

属性|描述
---|:--:
page|包含在页面中的相对URL地址。
flush|布尔属性，定义在包含资源前是否刷新缓存区。

**实例**<br>
以下我们定义了两个文件 date.jsp 和 main.jsp，代码如下所示：

date.jsp文件代码：
```JSP
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<p>
   今天的日期是: <%= (new java.util.Date()).toLocaleString()%>
</p>
```
main.jsp文件代码：
```JSP
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>菜鸟教程(runoob.com)</title>
</head>
<body>

<h2>include 动作实例</h2>
<jsp:include page="date.jsp" flush="true" />

</body>
</html>
```
现在将以上两个文件放在服务器的根目录下，访问main.jsp文件。显示结果如下：
```JSP
include 动作实例

今天的日期是: 2016-6-25 14:08:17
```
