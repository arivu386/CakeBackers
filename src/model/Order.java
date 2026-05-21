package model;

import java.sql.Date;

public class Order 
{
    private int orderId;
    private int customerId;
    private int cakeId;
    private double quantity;
    private double price;
    private Date orderDate;

    public Order() 
    {

    }

    public Order(int customerId,int cakeId,double quantity,double price,Date orderDate) 
    {
        this.customerId = customerId;
        this.cakeId = cakeId;
        this.quantity = quantity;
        this.price = price;
        this.orderDate = orderDate;
    }

    public int getOrderId() 
    {
        return orderId;
    }

    public void setOrderId(int orderId) 
    {
        this.orderId = orderId;
    }

    public int getCustomerId() 
    {
        return customerId;
    }

    public int getCakeId() 
    {
        return cakeId;
    }

    public double getQuantity() 
    {
        return quantity;
    }

    public double getPrice() 
    {
        return price;
    }

    public Date getOrderDate() 
    {
        return orderDate;
    }
}