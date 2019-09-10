import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.Scanner;

public class JDBCCallableStatement {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
	private static final String USERNAME = "scott";
	private static final String PWD = "tiger";

	public static void invokeProcedure() {
		Connection connection = null;
		CallableStatement cstmt = null;
		try {
			// a.导入驱动，加载具体的驱动类
			Class.forName("oracle.jdbc.OracleDriver");// 加载具体的驱动类
			// b.与数据库建立连接
			connection = DriverManager.getConnection(URL, USERNAME, PWD);
			
			
			// c.发送sql，执行(增删改、查)  num1+num2 ->num3
			 cstmt = 	connection.prepareCall(   "{ call addTwoNum(?,?,?) }" ) ;
			 cstmt.setInt(1, 30);
			 cstmt.setInt(2, 40);
			
			 cstmt.registerOutParameter(3, Types.INTEGER);
			 cstmt.execute() ;//num1+num2 ,execute()之前处理 输入参数以及输出参数类型，之后接受输出参数值
			 //设置输出参数的类型
			
			 int result = cstmt.getInt(3) ;//获取计算结果 
			System.out.println(result);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				 if(cstmt!=null) cstmt.close();// 对象.方法
				 if(connection!=null)connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void invokeFunction() {
		Connection connection = null;
		CallableStatement cstmt = null;
		try {
			// a.导入驱动，加载具体的驱动类
			Class.forName("oracle.jdbc.OracleDriver");// 加载具体的驱动类
			// b.与数据库建立连接
			connection = DriverManager.getConnection(URL, USERNAME, PWD);
			
			// c.发送sql，执行(增删改、查)  num1+num2 ->num3
			 cstmt = 	connection.prepareCall(   "{? =  call addTwoNumfunction	(?,?) }" ) ;
		
			 cstmt.setInt(2, 30);
			 cstmt.setInt(3,40);
			
			 cstmt.registerOutParameter(1, Types.INTEGER);
			 cstmt.execute() ;//num1+num2 ,execute()之前处理 输入参数以及输出参数类型，之后接受输出参数值
			 //设置输出参数的类型
			
			 int result = cstmt.getInt(1) ;//获取计算结果 
			System.out.println(result);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				 if(cstmt!=null) cstmt.close();// 对象.方法
				 if(connection!=null)connection.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
//		update() ;
//		invokeProcedure();
		invokeFunction() ;
	}
}
