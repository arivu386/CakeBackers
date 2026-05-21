package dao;

import model.Cake;
import util.DatabaseUtil;

import java.sql.*;

public class CakeDAO 
{
    Connection connection =DatabaseUtil.getConnection();

    // Add Cake
    public int addCake(Cake cake) 
    {
        try {
            String query ="INSERT INTO Cakes(name,flavor,price,isActive) VALUES(?,?,?,?)";

            PreparedStatement ps =connection.prepareStatement(query);
            ps.setString(1,cake.getName());
            ps.setString(2,cake.getFlavor());
            ps.setDouble(3,cake.getPrice());
            ps.setBoolean(4,cake.isActive());
            return ps.executeUpdate();
        } 
        catch (Exception e) {
        	System.out.println("Error while adding cake");
            e.printStackTrace();
        }
        return 0;
    }

    // View Cake
    public boolean viewCake(int cakeId) 
    {
    	boolean found=false;
    	
        try {
            String query ="SELECT * FROM Cakes WHERE cakeId=?";

            PreparedStatement ps =connection.prepareStatement(query);
            ps.setInt(1, cakeId);
            ResultSet rs =ps.executeQuery();
             
            while(rs.next()) 
            {
            	found=true;
                System.out.println("\nCake ID : "+ rs.getInt("cakeId"));
                System.out.println("Cake Name : "+ rs.getString("name"));
                System.out.println("Flavor : "+ rs.getString("flavor"));
                System.out.println("Price : "+ rs.getDouble("price"));
            }
        } 
        catch (Exception e) 
        {
        	System.out.println("Error while viewing cake");
            e.printStackTrace();
        }
        return found;
    }

    // Modify Cake Price
    public int updateCakePrice(int cakeId,double price) 
    {
        try {
            String query ="UPDATE Cakes SET price=? WHERE cakeId=?";

            PreparedStatement ps =connection.prepareStatement(query);
            ps.setDouble(1,price);
            ps.setInt(2,cakeId);
            return ps.executeUpdate();
        } 
        catch (Exception e) {
        	System.out.println("Error while updating cake price");
            e.printStackTrace();
        }
        return 0;
    }

    // Deactivate Cake
    public int deactivateCake(int cakeId) 
    {
        try {

            String query ="UPDATE Cakes SET isActive=false WHERE cakeId=?";

            PreparedStatement ps =connection.prepareStatement(query);
            ps.setInt(1,cakeId);
            return ps.executeUpdate();    
        } 
        catch (Exception e) 
        {
        	System.out.println("Error while deactivating cake");
            e.printStackTrace();
        }
        return 0;
    }
}