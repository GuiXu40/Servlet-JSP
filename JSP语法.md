# -----------------JSP语法--------------
## 目录
<a href="#p1">:dart:脚本代码</a><br>
<a href="#p2">:dart:JSP声明</a><br>
<a href="#p3">:dart:JSP表达式</a><br>
<a href="#p4">:dart:</a><br>
<a href="#p5">:dart:</a><br>
<a href="#p6">:dart:</a><br>

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

<p id="p5"></p>

## :black_joker:JSP 动作元素
<a href="#title">:flower_playing_cards:回到目录</a>
#### :memo:
<p id="p6"></p>

## :black_joker:
<a href="#title">:flower_playing_cards:回到目录</a>
#### :memo:
