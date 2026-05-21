package dao;

import model.Customer;
import util.DatabaseUtil;

import java.sql.*;

public class CustomerDAO 
{
    Connection connection =DatabaseUtil.getConnection();

    // Add Customer
    public int addCustomer(Customer customer) 
    {
        try {
            String query ="INSERT INTO Customers(name,contact,address,email,isActive) VALUES(?,?,?,?,?)";

            PreparedStatement ps =connection.prepareStatement(query);
            ps.setString(1,customer.getName());
            ps.setString(2,customer.getContact());
            ps.setString(3,customer.getAddress());
            ps.setString(4,customer.getEmail());
            ps.setBoolean(5,customer.isActive());
            return ps.executeUpdate();
        } 
        catch (Exception e) 
        {
        	System.out.println("Error while adding customer");
            e.printStackTrace();
        }
        return 0;
    }

    // View Customer
    public boolean viewCustomer(int customerId) 
    {
    	boolean found = false;
    	
        try {
            String query ="SELECT * FROM Customers WHERE customerId=?";
            
            PreparedStatement ps =connection.prepareStatement(query);
            ps.setInt(1,customerId);
            ResultSet rs =ps.executeQuery();
            
            while(rs.next()) 
            {
            	found=true;
                System.out.println("\nCustomer ID : "+ rs.getInt("customerId"));
                System.out.println("Name : "+ rs.getString("name"));
                System.out.println("Contact : "+ rs.getString("contact"));
                System.out.println("Address : "+ rs.getString("address"));
                System.out.println("Email : "+ rs.getString("email"));
            }
            
        } 
        catch (Exception e) 
        {
        	System.out.println("Error while viewing customer");
            e.printStackTrace();
        }
        return found;
    }

    // Modify Email
    public int updateCustomerEmail(int customerId,String email) 
    {
        try {
            String query ="UPDATE Customers SET email=? WHERE customerId=?";

            PreparedStatement ps =connection.prepareStatement(query);
            ps.setString(1,email);
            ps.setInt(2,customerId);
            return ps.executeUpdate(); 
        } 
        catch (Exception e) 
        {
        	System.out.println("Error while updating customer email");
            e.printStackTrace();
        }
        return 0;
    }

    // Deactivate Customer
    public int deactivateCustomer(int customerId) 
    {
        try {
            String query ="UPDATE Customers SET isActive=false WHERE customerId=?";
            
            PreparedStatement ps =connection.prepareStatement(query);
            ps.setInt(1,customerId);
            return ps.executeUpdate();
        } 
        catch (Exception e) 
        {
        	System.out.println("Error while deactivating customer");
            e.printStackTrace();
        }
        return 0;
    }
}