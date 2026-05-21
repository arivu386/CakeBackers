package service;

import dao.BillingDAO;
import model.Billing;

public class BillingService 
{
    BillingDAO dao =new BillingDAO();

    // Generate Bill
    public void generateBill(Billing billing) 
    {
        int rows=dao.generateBill(billing);
        if(rows > 0) 
        {
            System.out.println("Bill Generated Successfully");
        }
        else 
        {
            System.out.println("Failed to Generate Bill");
        }
    }

    // View Bill
    public void viewBill(int billId) 
    {
        boolean found=dao.viewBill(billId);
        if(!found) 
        {
            System.out.println("Bill Not Found");
        }
    }
}