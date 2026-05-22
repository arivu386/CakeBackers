package service;

import dao.OrderDAO;
import exception.OrderNotCancelledException;
import model.Order;

public class OrderService 
{
	OrderDAO dao = new OrderDAO();

	// -------------------------------------Place Order-------------------------------------
	public void placeOrder(Order order) 
	{
		int rows = dao.placeOrder(order);
		if (rows > 0) 
		{
			System.out.println("Order Placed Successfully");
		} 
		else 
		{
			System.out.println("Failed to Place Order");
		}
	}

	// --------------------------------------View Order----------------------------------------
	public void viewOrder(int orderId) 
	{
		boolean found = dao.viewOrder(orderId);
		if (!found) 
		{
			System.out.println("Order Not Found");
		}
	}

	// ------------------------------------view all order---------------------------------------
	public void viewAllOrders() 
	{
		boolean found = dao.viewAllOrders();
		if (!found) 
		{
			System.out.println("No Orders Available");
		}
	}

	// ---------------------------------Update Order Quantity-----------------------------------
	public void updateOrderQuantity(int orderId, double quantity) 
	{
		int rows=dao.updateOrderQuantity(orderId, quantity);
		if (rows > 0) 
		{
			System.out.println("Order Updated Successfully");
		} 
		else 
		{
			System.out.println("Order Can't Be Updated");
		}
	}

	// -------------------------------------Cancel Order-----------------------------------------
	public void cancelOrder(int orderId) throws OrderNotCancelledException 
	{
		dao.cancelOrder(orderId);
	}
}