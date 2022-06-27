package Connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static String driver =  "org.postgresql.Driver";
    public static String url = "jdbc:postgresql://localhost:5432/brief8";
    public static String user = "postgres";
    public static String password = "1234";

    public static Connection cn;


    public static Connection getConnection() {

        try{
            //Class.forName(driver);
            return DriverManager.getConnection(url, user, password );
            //ClassNotFoundException
        }catch( SQLException e){
            e.printStackTrace();
            System.out.println("Error Opening DB!");

        }
        return null;
    }

}
