package ConnectionPackage;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_Class {


    public java.sql.Connection EstablishConnection () throws SQLException, ClassNotFoundException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaprogrammingproject", "root", "Password@123");
        return con;
    }
}
