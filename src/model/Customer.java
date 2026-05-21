package model;

public class Customer 
{
    private int customerId;
    private String name;
    private String contact;
    private String address;
    private String email;
    private boolean isActive;

    public Customer() 
    {

    }

    public Customer(String name,String contact,String address,String email,boolean isActive) 
    {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.email = email;
        this.isActive = isActive;
    }

    public int getCustomerId() 
    {
        return customerId;
    }

    public void setCustomerId(int customerId) 
    {
        this.customerId = customerId;
    }

    public String getName() 
    {
        return name;
    }

    public String getContact() 
    {
        return contact;
    }

    public String getAddress() 
    {
        return address;
    }

    public String getEmail() 
    {
        return email;
    }

    public boolean isActive() 
    {
        return isActive;
    }
}