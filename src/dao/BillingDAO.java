package dao;

import model.Billing;
import util.DatabaseUtil;

import java.sql.*;

public class BillingDAO 
{
	Connection connection = DatabaseUtil.getConnection();

	// --------------------------------Generate Bill-----------------------------------------
	public int generateBill(Billing billing) 
	{
		try 
		{
			// Check Order Exists
			String checkQuery = "SELECT * FROM Orders WHERE orderId=?";

			PreparedStatement checkPs = connection.prepareStatement(checkQuery);
			checkPs.setInt(1, billing.getOrderId());
			ResultSet rs = checkPs.executeQuery();

			if (!rs.next()) 
			{
				System.out.println("Order ID does not exist");
				return 0;
			}

			String query = "INSERT INTO Billing(orderId,totalAmount,billDate) VALUES(?,?,?)";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, billing.getOrderId());
			ps.setDouble(2, billing.getTotalAmount());
			ps.setDate(3, billing.getBillDate());
			return ps.executeUpdate();

		} 
		catch (Exception e) 
		{
			System.out.println("Error while generating bill");
			e.printStackTrace();
		}

		return 0;
	}

	// --------------------------------View Bill--------------------------------------------
	public boolean viewBill(int billId) 
	{
		boolean found = false;
		try 
		{
			String query = "SELECT b.billId, " + "cu.name AS customerName, " + "c.name AS cakeName, " + "o.quantity, "
					+ "c.price, " + "b.totalAmount, " + "b.billDate " + "FROM Billing b " + "JOIN Orders o "
					+ "ON b.orderId = o.orderId " + "JOIN Customers cu " + "ON o.customerId = cu.customerId "
					+ "JOIN Cakes c " + "ON o.cakeId = c.cakeId " + "WHERE b.billId=?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, billId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) 
			{
				found = true;
				System.out.println("\n========== BILL ==========");
				System.out.println("Bill ID : " + rs.getInt("billId"));
				System.out.println("Customer Name : " + rs.getString("customerName"));
				System.out.println("Cake Name : " + rs.getString("cakeName"));
				System.out.println("Quantity : " + rs.getDouble("quantity"));
				System.out.println("Price : " + rs.getDouble("price"));
				System.out.println("Total Amount : " + rs.getDouble("totalAmount"));
				System.out.println("Bill Date : " + rs.getDate("billDate"));
				System.out.println("==========================");
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Error while viewing bill");
			e.printStackTrace();
		}
		return found;
	}

	// -----------------------------------------View All Bills----------------------------------------------
	public boolean viewAllBills() 
	{
		boolean found = false;
		try {
			String query = "SELECT b.billId, " + "cu.name AS customerName, " + "c.name AS cakeName, " + "o.quantity, "
					+ "c.price, " + "b.totalAmount, " + "b.billDate " + "FROM Billing b " + "JOIN Orders o "
					+ "ON b.orderId = o.orderId " + "JOIN Customers cu " + "ON o.customerId = cu.customerId "
					+ "JOIN Cakes c " + "ON o.cakeId = c.cakeId";

			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			System.out.println("\n========== ALL BILLS ==========");

			while (rs.next()) 
			{
				found = true;
				System.out.println("\nBill ID : " + rs.getInt("billId"));
				System.out.println("Customer Name : " + rs.getString("customerName"));
				System.out.println("Cake Name : " + rs.getString("cakeName"));
				System.out.println("Quantity : " + rs.getDouble("quantity"));
				System.out.println("Price : " + rs.getDouble("price"));
				System.out.println("Total Amount : " + rs.getDouble("totalAmount"));
				System.out.println("Bill Date : " + rs.getDate("billDate"));
				System.out.println("===============================");
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Error while viewing all bills");
			e.printStackTrace();
		}
		return found;
	}
}