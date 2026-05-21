package service;

import dao.OrderDAO;
import exception.OrderNotCancelledException;
import model.Order;

public class OrderService 
{
    OrderDAO dao =new OrderDAO();

    // Place Order
    public void placeOrder(Order order) 
    {
        int rows=dao.placeOrder(order);
        if(rows > 0) 
        {
            System.out.println("Order Placed Successfully");
        }
        else 
        {
            System.out.println("Failed to Place Order");
        }
    }

    // View Order
    public void viewOrder(int orderId) 
    {
        boolean found=dao.viewOrder(orderId);
        if(!found) 
        {
            System.out.println("Order Not Found");
        }
    }

    // Cancel Order
    public void cancelOrder(int orderId)throws OrderNotCancelledException 
    {
        int rows=dao.cancelOrder(orderId);
        if(rows > 0) 
        {
            System.out.println("Order Cancelled Successfully");
        }
        else 
        {
            System.out.println("Failed to Cancel Order");
        }
    }
}