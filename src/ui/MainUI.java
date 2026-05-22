package ui;

import exception.OrderNotCancelledException;
import model.Billing;
import model.Cake;
import model.Customer;
import model.Feedback;
import model.Order;
import service.BillingService;
import service.CakeService;
import service.CustomerService;
import service.FeedbackService;
import service.OrderService;
import util.ApplicationUtil;

import java.sql.Date;
import java.util.Scanner;

public class MainUI 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);

		CakeService cakeService = new CakeService();
		CustomerService customerService = new CustomerService();
		OrderService orderService = new OrderService();
		BillingService billingService = new BillingService();
		FeedbackService feedbackService = new FeedbackService();

		while (true) 
		{
			System.out.println("\n================================");
			System.out.println(" TastyBakers Management System ");
			System.out.println("================================");
			System.out.println("1. Cake Management");
			System.out.println("2. Customer Management");
			System.out.println("3. Order Management");
			System.out.println("4. Billing Management");
			System.out.println("5. Feedback Management");
			System.out.println("6. Exit");
			System.out.println("================================");

			System.out.print("Enter Choice : ");
			int choice = Integer.parseInt(sc.nextLine());

			switch (choice) 
			{
			// -----------------------------------------Cake Management------------------------------------------
			case 1:
				
				while (true) 
				{
					System.out.println("\n===== Cake Management =====");
					System.out.println("1. Add Cake");
					System.out.println("2. View Cake");
					System.out.println("3. View All Cakes");
					System.out.println("4. Modify Cake");
					System.out.println("5. Deactivate Cake");
					System.out.println("6. Back");
					System.out.print("Enter Choice : ");
					int cakeChoice = Integer.parseInt(sc.nextLine());

					switch (cakeChoice) 
					{
					// Add Cake
					case 1:
						System.out.println("\nEnter Cake Details 🍰 CakeName : Flavor : Price ");
						String cakeInput = sc.nextLine();
						String[] cakeData = ApplicationUtil.splitDetails(cakeInput);
						Cake cake = new Cake(cakeData[0], cakeData[1], Double.parseDouble(cakeData[2]), true);
						cakeService.addCake(cake);
						break;

					// View Cake
					case 2:
						System.out.println("\nEnter Cake ID : ");
						String cakeIdInput = sc.nextLine();
						int cakeId = Integer.parseInt(cakeIdInput);
						cakeService.viewCake(cakeId);
						break;

					// View all cake
					case 3:
						cakeService.viewAllCakes();
						break;

					// Modify Cake
					case 4:
						System.out.println("Enter Cake ID : ");
						int cakeeId = sc.nextInt();
						sc.nextLine();

						System.out.println("\nEditable Fields");
						System.out.println("1. Name");
						System.out.println("2. Flavor");
						System.out.println("3. Price");

						System.out.println("Choose Field : ");
						int modifyChoice = sc.nextInt();
						sc.nextLine();

						switch (modifyChoice) {
						case 1:
							System.out.println("Enter New Cake Name : ");
							String newName = sc.nextLine();
							cakeService.updateCakeName(cakeeId, newName);
							break;

						case 2:
							System.out.println("Enter New Flavor : ");
							String newFlavor = sc.nextLine();
							cakeService.updateCakeFlavor(cakeeId, newFlavor);
							break;

						case 3:
							System.out.println("Enter New Price : ");
							double newPrice = sc.nextDouble();
							cakeService.updateCakePrice(cakeeId, newPrice);
							break;

						default:
							System.out.println("Invalid Choice");
						}
						break;

					// Deactivate Cake
					case 5:
						System.out.println("\nEnter Cake ID : ");
						String deactivateInput = sc.nextLine();
						int deactivateCakeId = Integer.parseInt(deactivateInput);
						cakeService.deactivateCake(deactivateCakeId);
						break;

					// Back
					case 6:
						break;

					default:
						System.out.println("Invalid Choice");
					}

					if (cakeChoice == 6) 
					{
						break;
					}
				}

				break;

			// ----------------------------------------Customer Management---------------------------------------------
			case 2:

				while (true) 
				{
					System.out.println("\n===== Customer Management =====");
					System.out.println("1. Add Customer");
					System.out.println("2. View Customer");
					System.out.println("3. View All Customers");
					System.out.println("4. Modify Customer");
					System.out.println("5. Deactivate Customer");
					System.out.println("6. Back");
					System.out.print("Enter Choice : ");

					int customerChoice = Integer.parseInt(sc.nextLine());

					switch (customerChoice) 
					{
					// Add Customer
					case 1:
						System.out.println("\nEnter Customer Details 🍰 Name : Contact : Address : Email ");
						String customerInput = sc.nextLine();
						String[] customerData = ApplicationUtil.splitDetails(customerInput);
						Customer customer = new Customer(customerData[0], customerData[1], customerData[2],customerData[3], true);
						customerService.addCustomer(customer);
						break;

					// View Customer
					case 2:
						System.out.println("\nEnter Customer ID : ");
						int customerId = Integer.parseInt(sc.nextLine());
						customerService.viewCustomer(customerId);
						break;

					// View all customer
					case 3:
						customerService.viewAllCustomers();
						break;

					// Modify customer
					case 4:

						System.out.println("Enter Customer ID : ");
						int customerrId = sc.nextInt();
						sc.nextLine();
						System.out.println("\nEditable Fields");
						System.out.println("1. Name");
						System.out.println("2. Contact");
						System.out.println("3. Address");
						System.out.println("4. Email");

						System.out.println("Choose Field : ");
						int modifyCustomer = sc.nextInt();
						sc.nextLine();

						switch (modifyCustomer) 
						{

						case 1:
							System.out.println("Enter New Name : ");
							String name = sc.nextLine();
							customerService.updateCustomerName(customerrId, name);
							break;

						case 2:
							System.out.println("Enter New Contact : ");
							String contact = sc.nextLine();
							customerService.updateCustomerContact(customerrId, contact);
							break;

						case 3:
							System.out.println("Enter New Address : ");
							String address = sc.nextLine();
							customerService.updateCustomerAddress(customerrId, address);
							break;

						case 4:
							System.out.println("Enter New Email : ");
							String email = sc.nextLine();
							customerService.updateCustomerEmail(customerrId, email);
							break;

						default:
							System.out.println("Invalid Choice");
						}

						break;

					// Deactivate Customer
					case 5:
						System.out.println("\nEnter Customer ID : ");
						int deactivateCustomerId = Integer.parseInt(sc.nextLine());

						customerService.deactivateCustomer(deactivateCustomerId);
						break;

					// Back
					case 6:
						break;

					default:
						System.out.println("Invalid Choice");
					}

					if (customerChoice == 6) 
					{
						break;
					}
				}

				break;

			// -----------------------------------------------Order Management---------------------------------------------
			case 3:
				while (true) 
				{
					System.out.println("\n===== Order Management =====");
					System.out.println("1. Place Order");
					System.out.println("2. View Order");
					System.out.println("3. View All Orders");
					System.out.println("4. Update Order Quantity");
					System.out.println("5. Cancel Order");
					System.out.println("6. Back");
					System.out.print("Enter Choice : ");
					int orderChoice = Integer.parseInt(sc.nextLine());

					switch (orderChoice) 
					{
					// Place Order
					case 1:
						System.out.println("\nEnter Order Details 🍰 CustomerId : CakeId : Quantity : Price ");
						String orderInput = sc.nextLine();
						String[] orderData = ApplicationUtil.splitDetails(orderInput);

						Order order = new Order(Integer.parseInt(orderData[0]), Integer.parseInt(orderData[1]),
								Double.parseDouble(orderData[2]), Double.parseDouble(orderData[3]),
								new Date(System.currentTimeMillis()));

						orderService.placeOrder(order);
						break;

					// View Order
					case 2:
						System.out.println("\nEnter Order ID : ");
						int orderId = Integer.parseInt(sc.nextLine());

						orderService.viewOrder(orderId);
						break;

					// view all order
					case 3:
						orderService.viewAllOrders();
						break;
						
					//update quantity
					case 4:
						System.out.println("Enter Details 🍰 orderId : newQuantity ");
						String updateInput = sc.nextLine();
						String[] updateData = ApplicationUtil.splitDetails(updateInput);
						int updateOrderId = Integer.parseInt(updateData[0]);
						double newQuantity = Double.parseDouble(updateData[1]);
						orderService.updateOrderQuantity(updateOrderId, newQuantity);
						break;
						
					// Cancel Order
					case 5:
						System.out.println("\nEnter Order ID : ");
						int cancelId = Integer.parseInt(sc.nextLine());
						try 
						{
							orderService.cancelOrder(cancelId);
						} 
						catch (OrderNotCancelledException e) 
						{
							System.out.println(e.getMessage());
						}
						break;

					// Back
					case 6:
						break;

					default:
						System.out.println("Invalid Choice");
					}

					if (orderChoice == 6) 
					{
						break;
					}
				}

				break;

			// ----------------------------------------------Billing Management----------------------------------------------
			case 4:
				while (true) 
				{
					System.out.println("\n===== Billing Management =====");
					System.out.println("1. Generate Bill");
					System.out.println("2. View Bill");
					System.out.println("3. View All Bill");
					System.out.println("4. Back");
					System.out.print("Enter Choice : ");
					int billingChoice = Integer.parseInt(sc.nextLine());

					switch (billingChoice) 
					{
					// Generate Bill
					case 1:
						System.out.println("\nEnter Bill Details 🍰 OrderId:TotalAmount");
						String billInput = sc.nextLine();
						String[] billData = ApplicationUtil.splitDetails(billInput);
						Billing billing = new Billing(Integer.parseInt(billData[0]), Double.parseDouble(billData[1]),
								new java.sql.Date(System.currentTimeMillis()));
						billingService.generateBill(billing);
						break;

					// View Bill
					case 2:
						System.out.println("\nEnter Bill ID : ");
						int billId = Integer.parseInt(sc.nextLine());
						billingService.viewBill(billId);
						break;

					// view all bills
					case 3:
						billingService.viewAllBills();
						break;

					// Back
					case 4:
						break;

					default:
						System.out.println("Invalid Choice");
					}

					if (billingChoice == 4) 
					{
						break;
					}
				}

				break;

			// ---------------------------------------------Feedback Management-------------------------------------------
			case 5:
				while (true) 
				{
					System.out.println("\n===== Feedback Management =====");
					System.out.println("1. Add Feedback");
					System.out.println("2. View Feedback");
					System.out.println("3. View All Feedback");
					System.out.println("4. Sort By Ratings");
					System.out.println("5. Back");
					System.out.print("Enter Choice : ");

					int feedbackChoice = Integer.parseInt(sc.nextLine());

					switch (feedbackChoice) 
					{
					// Add Feedback
					case 1:
						System.out.println("\nEnter Feedback Details 🍰 CustomerId : Message : Rating(10) ");
						String feedbackInput = sc.nextLine();
						String[] feedbackData = ApplicationUtil.splitDetails(feedbackInput);
						Feedback feedback = new Feedback(Integer.parseInt(feedbackData[0]), feedbackData[1],
								Integer.parseInt(feedbackData[2]));
						feedbackService.addFeedback(feedback);
						break;

					// View Feedback
					case 2:
						System.out.println("\nEnter Feedback ID : ");
						int feedbackId = Integer.parseInt(sc.nextLine());
						feedbackService.viewFeedback(feedbackId);
						break;

					// view all feedbacks
					case 3:
						feedbackService.viewAllFeedbacks();
						break;

					// View sorted feedback
					case 4:
						feedbackService.sortFeedbackByRating();
						break;

					// Back
					case 5:
						break;

					default:
						System.out.println("Invalid Choice");
					}

					if (feedbackChoice == 5) 
					{
						break;
					}
				}

				break;

			// -----------------------------------------------Exit---------------------------------------------------
			case 6:
				System.out.println("\nThank You");
				return;
				
			default:
				System.out.println("Invalid Choice");
			}
		}
	}
}