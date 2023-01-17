package api.repositories.mysql;

import java.sql.Connection;
import java.sql.DriverManager;


public class MysqlConection {
    
    public Connection conn;
    private String driver = "com.mysql.cj.jdbc.Driver";

    private String url = "jdbc:mysql://localhost:3306/java_test_srvlets?" +
            "useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    public MysqlConection(){
        
        if(conn == null){
            this.getConnection();
        }
    }
    private void getConnection() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, "root", "");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }
}
