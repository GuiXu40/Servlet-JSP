# -----------------JSP问价操作---------------
## 目录
<a href="#p1">:dart:JSP文件操作</a><br>
<a href="#p2">:dart: 文件读写</a><br>

<p id="p1"></p>

## :black_joker:JSP文件操作
<a href="#title">:flower_playing_cards:回到目录</a>
JSP网页在使用文件前，必须建立File类的实体对象，同时还要指定对象所要操作的文件实体路径。可以使用File对象的构造函数来构建文件：

如获取text目录下的log.txt文件：
```JSP
<body>
        <%
            String url = application.getRealPath("text/log.txt");
            File file = new File(url);
            out.println(url);
            out.println("文件是否存在："+file.exists());
        %>
</body>
```
对于这个文件url不同的是，在jsp中读写文件需要使用真实物理绝对地址，所以使用了getRealPath()这个方法，与jsp和网页重定向不一样的是，这个需要知道文件的物理地址，所以在对文件操作时，需要使用这个方法，还可以想下面其他方法：
```JSP
1.jsp中取得路径：
以工程名为TEST为例：
(1)得到包含工程名的当前页面全路径：request.getRequestURI()
结果：/TEST/test.jsp
(2)得到工程名：request.getContextPath()
结果：/TEST
(3)得到当前页面所在目录下全名称：request.getServletPath()
结果：如果页面在jsp目录下 /TEST/jsp/test.jsp
(4)得到页面所在服务器的全路径：application.getRealPath("页面.jsp")
结果：D:\resin\webapps\TEST\test.jsp
(5)得到页面所在服务器的绝对路径：absPath=new java.io.File(application.getRealPath(request.getRequestURI())).getParent();
结果：D:\resin\webapps\TEST

2.在类中取得路径：
(1)类的绝对路径：Class.class.getClass().getResource("/").getPath()
结果：/D:/TEST/WebRoot/WEB-INF/classes/pack/
(2)得到工程的路径：System.getProperty("user.dir")
结果：D:\TEST

3.在Servlet中取得路径：
(1)得到工程目录：request.getSession().getServletContext().getRealPath("") 参数可具体到包名。
结果：E:\Tomcat\webapps\TEST
(2)得到IE地址栏地址：request.getRequestURL()
结果：http://localhost:8080/TEST/test
(3)得到相对地址：request.getRequestURI()
结果：/TEST/test
```
File类的其他实例方法还可以参见java的文件操作方法。与java文件类是一样的可以使用。
<p id="p2"></p>

## :black_joker:文件读写
<a href="#title">:flower_playing_cards:回到目录</a>
读写方法主要是FileWriter及FileReader。其中FileWriter()负责将数据写入文件，FileReader()则用于读文件。语法如下：
```JSP
        <%
            String url = application.getRealPath("text/log.txt");
            FileWriter fileWriter = new FileWriter(url);   //写
            FileReader fileReader = new FileReader(url);   //读

            fileWriter.write("GuiXu\n");            //覆盖式写法\n换行
            fileWriter.write("xxxxxxxxx");           
            fileWriter.close();           //用完必须关闭这个流
            int i = fileReader.read();
            while (i!=-1){
                out.println((char)i);
                i = fileReader.read();
            }
            fileReader.close();  //关闭读取流
        %>
```
这里需要注意的是，上面的是会覆盖原有文件的写法，且写完一定要关闭写入流，不然会造成进程等待，读取不到文件。如果想改为在文件末尾写入可以在声明时：
```JSP
FileWriter fileWriter = new FileWriter(url,true);
```
第二个参数决定了是否是在文件末尾添加内容。

值得注意的是在IDEA中引入外部文件需要在xml中添加文件类型：(这是servlet2.5的性质,在servlet3.0中不需要这种方式)
```JSP
    <servlet-mapping>
        <servlet-name>default</servlet-name>
        <url-pattern>*.txt</url-pattern>
    </servlet-mapping>
```
上面只是添加了一种.txt的类型文件，添加其他类型同理：
```JSP
    <servlet-mapping>
        <servlet-name>default</servlet-name>
        <url-pattern>*.文件扩展名</url-pattern>
    </servlet-mapping>
 ```
