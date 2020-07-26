package lti.shopproject.dbcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Database Connection

public class DbConnection {
    final String driver="oracle.jdbc.OracleDriver";
    final String username="hr";
    final String password="hr";
    final String url="jdbc:oracle:thin:@localhost:1521:sumit";
    
    private static Connection conm;
//method to create connection...
    
        public Connection getConnection(){
        try {
            //Step1- load driver..
            Class.forName(driver);
            //Step2-Connection to DB
            conm=DriverManager.getConnection(url,username,password);
            System.out.println("Connection :"+conm);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return conm;
    }
}
