package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import exception.OrderNotCancelledException;
import util.DatabaseUtil;
import model.Order;

public class OrderDAO 
{
	Connection connection = DatabaseUtil.getConnection();

	// --------------------------------------------Place Order-------------------------------------------------
	public int placeOrder(Order order) 
	{
		try 
		{
			// Check Customer Exists & Active
			String customerQuery = "SELECT isActive FROM Customers WHERE customerId=?";

			PreparedStatement customerPs = connection.prepareStatement(customerQuery);
			customerPs.setInt(1, order.getCustomerId());
			ResultSet customerRs = customerPs.executeQuery();

			if (!customerRs.next()) 
			{
				System.out.println("Customer Not Found");
				return 0;
			}

			if (!customerRs.getBoolean("isActive")) 
			{
				System.out.println("Customer Is Inactive");
				return 0;
			}

			// Check Cake Exists & Active
			String cakeQuery = "SELECT price,isActive,name FROM Cakes WHERE cakeId=?";

			PreparedStatement cakePs = connection.prepareStatement(cakeQuery);
			cakePs.setInt(1, order.getCakeId());
			ResultSet cakeRs = cakePs.executeQuery();

			if (!cakeRs.next()) 
			{
				System.out.println("Cake Not Found");
				return 0;
			}

			if (!cakeRs.getBoolean("isActive")) 
			{
				System.out.println("Cake Is Not Available");
				return 0;
			}

			// Auto Calculate Total Price
			double cakePrice = cakeRs.getDouble("price");
			double totalPrice = cakePrice * order.getQuantity();

			// Insert Order
			String query = "INSERT INTO Orders" + "(customerId,cakeId,quantity,price,orderDate) " + "VALUES(?,?,?,?,?)";

			PreparedStatement ps = connection.prepareStatement(query);

			ps.setInt(1, order.getCustomerId());
			ps.setInt(2, order.getCakeId());
			ps.setDouble(3, order.getQuantity());
			ps.setDouble(4, totalPrice);
			ps.setDate(5, order.getOrderDate());

			return ps.executeUpdate();
		} 
		catch (Exception e) 
		{
			System.out.println("Error while placing order");
			e.printStackTrace();
		}
		return 0;
	}


	// ------------------------------------------------View Single Order------------------------------------------------
	public boolean viewOrder(int orderId) 
	{
		boolean found = false;
		try 
		{
			String query = "SELECT o.orderId, " + "cu.name AS customerName, " + "c.name AS cakeName, " + "o.quantity, "
					+ "o.price, " + "o.orderDate " + "FROM Orders o " + "JOIN Customers cu "
					+ "ON o.customerId = cu.customerId " + "JOIN Cakes c " + "ON o.cakeId = c.cakeId "
					+ "WHERE o.orderId=?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) 
			{
				found = true;
				System.out.println("\n========== ORDER ==========");
				System.out.println("Order ID : " + rs.getInt("orderId"));
				System.out.println("Customer Name : " + rs.getString("customerName"));
				System.out.println("Cake Name : " + rs.getString("cakeName"));
				System.out.println("Quantity : " + rs.getDouble("quantity"));
				System.out.println("Total Price : " + rs.getDouble("price"));
				System.out.println("Order Date : " + rs.getDate("orderDate"));
				System.out.println("===========================");
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Error while viewing order");
			e.printStackTrace();
		}
		return found;
	}

	// --------------------------------------------View All Orders-----------------------------------------------
	public boolean viewAllOrders() 
	{
		boolean found = false;
		try 
		{
			String query = "SELECT o.orderId, " + "cu.name AS customerName, " + "c.name AS cakeName, " + "o.quantity, "
					+ "o.price, " + "o.orderDate " + "FROM Orders o " + "JOIN Customers cu "
					+ "ON o.customerId = cu.customerId " + "JOIN Cakes c " + "ON o.cakeId = c.cakeId";

			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			System.out.println("\n========== ALL ORDERS ==========");

			while (rs.next()) 
			{
				found = true;
				System.out.println("\nOrder ID : " + rs.getInt("orderId"));
				System.out.println("Customer Name : " + rs.getString("customerName"));
				System.out.println("Cake Name : " + rs.getString("cakeName"));
				System.out.println("Quantity : " + rs.getDouble("quantity"));
				System.out.println("Total Price : " + rs.getDouble("price"));
				System.out.println("Order Date : " + rs.getDate("orderDate"));
				System.out.println("===============================");
			}
		} 
		catch (Exception e) 
		{
			System.out.println("Error while viewing all orders");
			e.printStackTrace();
		}
		return found;
	}

	// ----------------------------------------------Update Quantity----------------------------------------------
	public int updateOrderQuantity(int orderId, double quantity) 
	{
		try 
		{
			// Get Cake Price
			String getPriceQuery = "SELECT c.price " + "FROM Orders o " + "JOIN Cakes c " + "ON o.cakeId = c.cakeId "
									+ "WHERE o.orderId=?";

			PreparedStatement getPs = connection.prepareStatement(getPriceQuery);
			getPs.setInt(1, orderId);
			ResultSet rs = getPs.executeQuery();

			if (rs.next()) 
			{
				double cakePrice = rs.getDouble("price");
				double totalPrice = cakePrice * quantity;

				String updateQuery = "UPDATE Orders " + "SET quantity=?, price=? " + "WHERE orderId=?";

				PreparedStatement ps = connection.prepareStatement(updateQuery);
				ps.setDouble(1, quantity);
				ps.setDouble(2, totalPrice);
				ps.setInt(3, orderId);
				return ps.executeUpdate();
			}

		} 
		catch (Exception e) 
		{
			System.out.println("Error while updating order");
			e.printStackTrace();
		}
		return 0;
	}


	// ------------------------------------------------Cancel Order------------------------------------------------
	public void cancelOrder(int orderId) throws OrderNotCancelledException 
	{
		try 
		{
			String query = "SELECT orderDate " + "FROM Orders " + "WHERE orderId=?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, orderId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) 
			{
				Date orderDate = rs.getDate("orderDate");

				if (orderDate.toLocalDate().equals(LocalDate.now())) 
				{
					// Delete Billing First
					String deleteBilling = "DELETE FROM Billing " + "WHERE orderId=?";

					PreparedStatement billingPs = connection.prepareStatement(deleteBilling);
					billingPs.setInt(1, orderId);
					billingPs.executeUpdate();

					// Delete Order
					String deleteOrder = "DELETE FROM Orders " + "WHERE orderId=?";
					PreparedStatement deletePs = connection.prepareStatement(deleteOrder);
					deletePs.setInt(1, orderId);
					int rows = deletePs.executeUpdate();

					if (rows > 0) 
					{
						System.out.println("Order Cancelled Successfully");
					}
				} 
				else 
				{
					throw new OrderNotCancelledException("Order Cannot Be Cancelled After One Day");
				}
			} 
			else 
			{
				System.out.println("Order Not Found");
			}
		} 
		catch (OrderNotCancelledException e) 
		{
			throw e;
		} 
		catch (Exception e) 
		{
			System.out.println("Error while cancelling order");
			e.printStackTrace();
		}
	}
}