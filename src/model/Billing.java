package model;

import java.sql.Date;

public class Billing 
{
    private int billId;
    private int orderId;
    private double totalAmount;
    private Date billDate;

    public Billing() 
    {

    }

    public Billing(int orderId,double totalAmount,Date billDate) 
    {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.billDate = billDate;
    }

    public int getBillId() 
    {
        return billId;
    }

    public void setBillId(int billId) 
    {
        this.billId = billId;
    }

    public int getOrderId() 
    {
        return orderId;
    }

    public double getTotalAmount() 
    {
        return totalAmount;
    }

    public Date getBillDate() 
    {
        return billDate;
    }
}