#### 1.JDBC:Java DataBase Connectivity  可以为多种关系型数据库DBMS 提供统一的访问方式，用Java来操作数据库
#### 2.JDBC API 主要功能：
三件事，具体是通过以下类/接口实现：
+ DriverManager ： 管理jdbc驱动
+ Connection： 连接（通过DriverManager产生）
+ Statement（PreparedStatement） ：增删改查  （通过Connection产生 ）
+ CallableStatement  ： 调用数据库中的 存储过程/存储函数  （通过Connection产生 ）
+ Result ：返回的结果集  （上面的Statement等产生 ）

Connection产生操作数据库的对象：
+ Connection产生Statement对象：createStatement()
+ Connection产生PreparedStatement对象：prepareStatement()
+ Connection产生CallableStatement对象：prepareCall();

Statement操作数据库：
+ 增删改：executeUpdate()
+ 查询：executeQuery();

ResultSet：保存结果集 select * from xxx<br>
next():光标下移，判断是否有下一条数据；true/false<br>
previous():  true/false<br>
getXxx(字段名|位置):获取具体的字段值 <br>



**PreparedStatement操作数据库**：
public interface PreparedStatement extends Statement 

+ 增删改：executeUpdate()
+ 查询：executeQuery();
+ --此外
赋值操作 setXxx();


**PreparedStatement与Statement在使用时的区别**：
1.Statement:
sql--
executeUpdate(sql)

2.PreparedStatement:
sql(可能存在占位符?)
在创建PreparedStatement 对象时，将sql预编译 prepareStatement(sql)
executeUpdate()
setXxx()替换占位符？

**推荐使用PreparedStatement：原因如下**：
+ 1.编码更加简便（避免了字符串的拼接）
```
String name = "zs" ;
int age = 23 ;

stmt:
String sql =" insert into student(stuno,stuname) values('"+name+"',  "+age+" )    " ;
stmt.executeUpdate(sql);

pstmt:
String sql =" insert into student(stuno,stuname) values(?,?) " ;
pstmt = connection.prepareStatement(sql);//预编译SQL
pstmt.setString(1,name);
pstmt.setInt(2,age);
```
+ 2.提高性能(因为 有预编译操作，预编译只需要执行一次)
需要重复增加100条数 
```
stmt:
String sql =" insert into student(stuno,stuname) values('"+name+"',  "+age+" )    " ;
for(100)
stmt.executeUpdate(sql);

pstmt:
String sql =" insert into student(stuno,stuname) values(?,?) " ;
pstmt = connection.prepareStatement(sql);//预编译SQL
pstmt.setString(1,name);
pstmt.setInt(2,age);
for( 100){
pstmt.executeUpdate();
}
```
+ 3.安全（可以有效防止sql注入）
sql注入： 将客户输入的内容  和 开发人员的SQL语句 混为一体
```
stmt:存在被sql注入的风险  
(例如输入  用户名：任意值 ' or 1=1 --
	   密码：任意值)
分析：
select count(*) from login where uname='任意值 ' or 1=1 --' and upwd ='任意值'  ;
select count(*) from login where uname='任意值 ' or 1=1 ;
select count(*) from login ;

select count(*) from login where uname='"+name+"' and upwd ='"+pwd+"' 
```
pstmt:有效防止sql注入


推荐使用pstmt
#### 3.jdbc访问数据库的具体步骤：
+ a.导入驱动，加载具体的驱动类
+ b.与数据库建立连接
+ c.发送sql，执行
+ d.处理结果集 （查询）

#### 4.数据库驱动
数据库类型|驱动jar	|具体驱动类|连接字符串
---|:--:|:--:|:--:
Oracle|ojdbc-x.jar|oracle.jdbc.OracleDriver|jdbc:oracle:thin:@localhost:1521:ORCL
MySQL|mysql-connector-java-x.jar|com.mysql.jdbc.Driver|jdbc:mysql://localhost:3306/数据库实例名
SqlServer|sqljdbc-x.jar|com.microsoft.sqlserver.jdbc.SQLServerDriver|jdbc:microsoft:sqlserver:localhost:1433;databasename=数据库实例名

使用jdbc操作数据库时，如果对数据库进行了更换，只需要替换：驱动、具体驱动类、连接字符串、用户名、密码
