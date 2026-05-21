package dao;

import exception.OrderNotCancelledException;
import model.Order;
import util.DatabaseUtil;

import java.sql.*;
import java.time.LocalDate;

public class OrderDAO 
{
    Connection connection =DatabaseUtil.getConnection();

    // Place Order
    public int placeOrder(Order order) 
    {
        try {

            String query ="INSERT INTO Orders(customerId,cakeId,quantity,price,orderDate) VALUES(?,?,?,?,?)";

            PreparedStatement ps =connection.prepareStatement(query);
            ps.setInt(1,order.getCustomerId());
            ps.setInt(2,order.getCakeId());
            ps.setDouble(3,order.getQuantity());
            ps.setDouble(4,order.getPrice());
            ps.setDate(5,order.getOrderDate());
            return ps.executeUpdate();
        } 
        catch (Exception e) 
        {
        	System.out.println("Error while placing order");
            e.printStackTrace();
        }
        return 0;
    }

    // View Order
    public boolean viewOrder(int orderId) 
    {
    	boolean found=false;
    	
        try {
            String query ="SELECT * FROM Orders WHERE orderId=?";

            PreparedStatement ps =connection.prepareStatement(query);
            ps.setInt(1,orderId);
            ResultSet rs =ps.executeQuery();
            
            while(rs.next()) 
            {
            	found=true;
                System.out.println( "\nOrder ID : "+ rs.getInt("orderId"));
                System.out.println("Customer ID : "+ rs.getInt("customerId"));
                System.out.println("Cake ID : "+ rs.getInt("cakeId"));
                System.out.println("Quantity : "+ rs.getDouble("quantity"));
                System.out.println("Price : "+ rs.getDouble("price"));
                System.out.println("Order Date : "+ rs.getDate("orderDate"));
            }
            
        } 
        catch (Exception e) 
        {
        	System.out.println("Error while viewing order");
            e.printStackTrace();
        }
        return found;
    }

    // Cancel Order
    public int cancelOrder(int orderId)throws OrderNotCancelledException 
    {
        try {
            String query ="SELECT orderDate FROM Orders WHERE orderId=?";

            PreparedStatement ps =connection.prepareStatement(query);
            ps.setInt(1,orderId);
            ResultSet rs =ps.executeQuery();

            if(rs.next()) 
            {
                Date orderDate =rs.getDate("orderDate");

                if(orderDate.toLocalDate().equals(LocalDate.now())) 
                {
                    String deleteQuery ="DELETE FROM Orders WHERE orderId=?";

                    PreparedStatement deletePs =connection.prepareStatement(deleteQuery);
                    deletePs.setInt(1,orderId);
                    return deletePs.executeUpdate();   
                } 
                else 
                {
                    throw new OrderNotCancelledException("Order Cannot Be Cancelled After One Day");
                }
            }

        } 
        catch (SQLException e) 
        {
        	System.out.println("Error while cancelling order");
            e.printStackTrace();
        }
        return 0;
    }
}