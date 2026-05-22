package service;

import dao.CakeDAO;
import model.Cake;

public class CakeService 
{
	CakeDAO dao = new CakeDAO();

	// -------------------------------------Add Cake---------------------------------------
	public void addCake(Cake cake) 
	{
		int rows = dao.addCake(cake);
		if (rows > 0) 
		{
			System.out.println("Cake Added Successfully");
		} else {
			System.out.println("Failed to Add Cake");
		}
	}

	// -------------------------------------View Cake----------------------------------------
	public void viewCake(int cakeId) 
	{
		boolean found = dao.viewCake(cakeId);
		if (!found) 
		{
			System.out.println("Cake Not Found");
		}
	}

	// -----------------------------------View all cake---------------------------------------
	public void viewAllCakes() 
	{
		boolean found = dao.viewAllCakes();
		if (!found) 
		{
			System.out.println("No Cakes Available");
		}
	}

	// ----------------------------------Modify cake name--------------------------------------
	public void updateCakeName(int cakeId, String name) 
	{
		int rows = dao.updateCakeName(cakeId, name);
		if (rows > 0) 
		{
			System.out.println("Cake Name Updated Successfully");
		} 
		else 
		{
			System.out.println("Cake Not Found");
		}
	}

	// ----------------------------------Modify cake flavor--------------------------------------
	public void updateCakeFlavor(int cakeId, String flavor) 
	{
		int rows = dao.updateCakeFlavor(cakeId, flavor);
		if (rows > 0) 
		{
			System.out.println("Cake Flavor Updated Successfully");
		} 
		else 
		{
			System.out.println("Cake Not Found");
		}
	}

	// -----------------------------------Modify Cake Price--------------------------------------
	public void updateCakePrice(int cakeId, double price) 
	{
		int rows = dao.updateCakePrice(cakeId, price);
		if (rows > 0) 
		{
			System.out.println("Cake Updated Successfully");
		} else 
		{
			System.out.println("Cake Not Found");
		}
	}

	// -------------------------------------Deactivate Cake----------------------------------------
	public void deactivateCake(int cakeId) 
	{
		int rows = dao.deactivateCake(cakeId);
		if (rows > 0) 
		{
			System.out.println("Cake Deactivated Successfully");
		} 
		else 
		{
			System.out.println("Cake Not Found");
		}
	}
}