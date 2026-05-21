package service;

import dao.CustomerDAO;
import model.Customer;
import util.ApplicationUtil;

public class CustomerService 
{
    CustomerDAO dao =new CustomerDAO();
    ApplicationUtil au=new ApplicationUtil();

    // Add Customer
    public void addCustomer(Customer customer) 
    {
        String mobile =customer.getContact();
        String email =customer.getEmail();

        // Mobile Validation
        if(!au.isValidMobile(mobile)) 
        {
            System.out.println("Invalid Mobile Number");

            System.out.println("Mobile number must:");
            System.out.println("1. Start with 7/8/9");
            System.out.println("2. Contain exactly 10 digits");
            return;
        }

        // Email Validation
        if(!au.isValidEmail(email)) 
        {
            System.out.println("Invalid Email");
            System.out.println("Email must contain @gmail.com");
            return;
        }

        int rows=dao.addCustomer(customer);
        if(rows > 0) 
        {
            System.out.println("Customer Added Successfully");
        }
        else 
        {
            System.out.println("Failed to Add Customer");
        }
    }

    // View Customer
    public void viewCustomer(int customerId) 
    {
        boolean found=dao.viewCustomer(customerId);
        if(!found) 
        {
            System.out.println("Customer Not Found");
        }
    }

    // Update Email
    public void updateCustomerEmail(int customerId,String email) 
    {
        if(!au.isValidEmail(email)) 
        {
            System.out.println("Invalid Email");
            return;
        }

        int rows=dao.updateCustomerEmail(customerId,email);
        if(rows > 0) 
        {
            System.out.println("Customer Email Updated Successfully");
        }
        else 
        {
            System.out.println("Customer Not Found");
        }
    }

    // Deactivate Customer
    public void deactivateCustomer(int customerId) 
    {
        int rows=dao.deactivateCustomer(customerId);
        if(rows > 0) 
        {
            System.out.println("Customer Deactivated Successfully");
        }
        else 
        {
            System.out.println("Customer Not Found");
        }

    }

}