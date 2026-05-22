package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtil 
{
    static Connection connection;

    public static Connection getConnection() 
    {
        try 
        {
        	if(connection == null || connection.isClosed())
        	{
	            String url ="jdbc:mysql://localhost:3306/project";
	            String username = "root";
	            String password = "arivu";
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            connection =DriverManager.getConnection(url,username,password);
           
                System.out.println("Database Connected Successfully");
            }
        } 
        catch (Exception e) 
        {
        	System.out.println("Error while connecting database");
            e.printStackTrace();
        }

        return connection;
    }
}