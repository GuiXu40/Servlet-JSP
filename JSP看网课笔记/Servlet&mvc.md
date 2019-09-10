####  JavaBean
刚才我们将 jsp中 登录操作的代码  转移到了LoginDao.java；其中LoginDao类 就称之为JavaBean。<br>
JavaBean的作用：a.减轻的jsp复杂度  b.提高代码复用（以后任何地方的 登录操作，都可以通过调用LoginDao实现）

JavaBean（就是一个Java类）的定义：满足一下2点 ，就可以称为JavaBean
+ a.public 修饰的类  ,public 无参构造
+ b.所有属性(如果有) 都是private，并且提供set/get   (如果boolean 则get 可以替换成is)

使用层面，Java分为2大类：
+ a.封装业务逻辑的JavaBean (LoginDao.java封装了登录逻辑)			逻辑
	可以将jsp中的JDBC代码，封装到Login.java类中 （Login.java）

+ b.封装数据的JavaBean   （实体类，Student.java  Person.java  ）		数据 
	对应于数据库中的一张表
	Login login = new Login(uname,upwd) ;//即用Login对象 封装了2个数据（用户名 和密码）

**封装数据的JavaBean 对应于数据库中的一张表   (Login(name,pwd))**<br>
**封装业务逻辑的JavaBean 用于操作 一个封装数据的JavaBean**

可以发现，JavaBean可以简化 代码(jsp->jsp+java)、提供代码复用(LoginDao.java)
```java
public  void sleep(String name,String place, int time)
{

}

public  void sleep(Person per)
{
	per.getName()
	per.getPlace()
	...
}
```


#### MVC设计模式：
+ M：Model	，模型  ：一个功能。用JavaBean实现。
+ V:View，视图： 用于展示、以及与用户交互。使用html  js  css jsp jquery等前端技术实现
+ C:Controller，控制器 ：接受请求，将请求跳转到模型进行处理；模型处理完毕后，再将处理的结果
			返回给 请求处 。 可以用jsp实现，  但是一般建议使用 Servlet实现控制器。

Jsp->Java(Servlet)->JSP


#### Servlet：
Java类必须符合一定的 规范：
+ a.必须继承  javax.servlet.http.HttpServlet
+ b.重写其中的 doGet()或doPost()方法
   + doGet()： 接受 并处 所有get提交方式的请求
   + doPost()：接受 并处 所有post提交方式的请求

Servlet要想使用，必须配置
+ Serlvet2.5：web.xml
+ Servle3.0： @WebServlet


**Serlvet2.5：web.xml:**

项目的根目录：WebContent 、src

<a href="WelcomeServlet">所在的jsp是在 WebContent目录中，因此 发出的请求WelcomeServlet  是去请求项目的根目录。

Servlet流程：
请求 -><url-pattern> -> 根据<servlet-mapping>中的<servlet-name> 去匹配  <servlet> 中的<servlet-name>，然后寻找到<servlet-class>，求中将请求交由该<servlet-class>执行。



2个/:
jsp:/  localhost:8888
web.xml: /   http://localhost:8888/项目名/
