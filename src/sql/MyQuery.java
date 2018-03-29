package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyQuery {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		try {
		       Class.forName("com.mysql.jdbc.Driver");
		       Connection conn = DriverManager.getConnection(
		               "jdbc:mysql://192.168.0.19:3306/picklog_db","picklog","p!cklog");
		       Statement st = conn.createStatement();
		       ResultSet rs =
		       st.executeQuery("select * from log_tbl");

		       while(rs.next()) {
		           int id = rs.getInt(1);
		           int location = rs.getInt(2);
		           String tid = rs.getString(3);
		           String time = rs.getString(4);
		           System.out.println
		                ("ID："+id+" location:"+location+" tid:"+tid+" time:"+time);
		           }
		           rs.close();
		           st.close();
		           conn.close();
		           System.exit(0);
		   } catch (ClassNotFoundException e) {
		       System.out.println("ドライバを読み込めませんでした "+ e);
		   } catch (SQLException e) {
		       System.out.println("データベース接続エラー"+ e);
		   }


	}

}
