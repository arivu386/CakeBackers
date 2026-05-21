package model;

public class Cake 
{
    private int cakeId;
    private String name;
    private String flavor;
    private double price;
    private boolean isActive;
    
    public Cake() 
    {

    }

    public Cake(String name,String flavor,double price,boolean isActive)
    {
        this.name = name;
        this.flavor = flavor;
        this.price = price;
        this.isActive = isActive;
    }

    public int getCakeId() 
    {
        return cakeId;
    }

    public void setCakeId(int cakeId) 
    {
        this.cakeId = cakeId;
    }

    public String getName() 
    {
        return name;
    }

    public String getFlavor() 
    {
        return flavor;
    }

    public double getPrice() 
    {
        return price;
    }

    public boolean isActive() 
    {
        return isActive;
    }
}