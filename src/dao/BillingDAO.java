package dao;

import model.Billing;
import util.DatabaseUtil;

import java.sql.*;

public class BillingDAO 
{
    Connection connection = DatabaseUtil.getConnection();

    // Generate Bill
    public int generateBill(Billing billing) 
    {
        try {
            String query ="INSERT INTO Billing(orderId,totalAmount,billDate) VALUES(?,?,?)";

            PreparedStatement ps =connection.prepareStatement(query);
            ps.setInt(1,billing.getOrderId());
            ps.setDouble(2,billing.getTotalAmount());
            ps.setDate(3,billing.getBillDate());
            return ps.executeUpdate();
        }
        catch (Exception e) {
        	System.out.println("Error while genarating bill");
            e.printStackTrace();
        }
        return 0;
    }

    // View Bill
    public boolean viewBill(int billId) 
    {
    	boolean found = false;
    	
        try {
            String query ="SELECT * FROM Billing WHERE billId=?";

            PreparedStatement ps =connection.prepareStatement(query);
            ps.setInt(1,billId);
            ResultSet rs =ps.executeQuery();
            
            while(rs.next()) 
            {
            	found=true;
                System.out.println("\nBill ID : "+ rs.getInt("billId"));
                System.out.println("Order ID : "+ rs.getInt("orderId"));
                System.out.println("Total Amount : "+ rs.getDouble("totalAmount"));
                System.out.println("Bill Date : "+ rs.getDate("billDate"));
            }

        } 
        catch (Exception e) 
        {
        	System.out.println("Error occurred while viewing the bill");
            e.printStackTrace();
        }
        return found;
    }
}