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
        
        CakeService cakeService =new CakeService();
        CustomerService customerService =new CustomerService();
        OrderService orderService =new OrderService();
        BillingService billingService =new BillingService();
        FeedbackService feedbackService =new FeedbackService();

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
                // Cake Management
                case 1:

                    while (true) 
                    {
                        System.out.println("\n===== Cake Management =====");
                        System.out.println("1. Add Cake");
                        System.out.println("2. View Cake");
                        System.out.println("3. Modify Cake");
                        System.out.println("4. Deactivate Cake");
                        System.out.println("5. Back");
                        System.out.print("Enter Choice : ");
                        int cakeChoice =Integer.parseInt(sc.nextLine());

                        switch (cakeChoice) 
                        {
                            // Add Cake
                            case 1:
                                System.out.println("\nEnter Cake Details");
                                System.out.println("Format : CakeName:Flavor:Price");
                                String cakeInput =sc.nextLine();
                                String[] cakeData =ApplicationUtil.splitDetails(cakeInput);

                                Cake cake =new Cake(cakeData[0],cakeData[1],Double.parseDouble(cakeData[2]),true);
                                cakeService.addCake(cake);
                                break;

                            // View Cake
                            case 2:
                                System.out.println("\nEnter Cake ID");
                                String cakeIdInput =sc.nextLine();
                                int cakeId =Integer.parseInt(cakeIdInput);
                                cakeService.viewCake(cakeId);
                                break;

                            // Modify Cake
                            case 3:
                                System.out.println("\nEnter Details");
                                System.out.println("Format : CakeId:NewPrice");
                                String modifyCakeInput =sc.nextLine();
                                String[] modifyCakeData =ApplicationUtil.splitDetails(modifyCakeInput);

                                cakeService.updateCakePrice(Integer.parseInt(modifyCakeData[0]),Double.parseDouble(modifyCakeData[1]));
                                break;

                            // Deactivate Cake
                            case 4:
                                System.out.println("\nEnter Cake ID");
                                String deactivateInput =sc.nextLine();
                                int deactivateCakeId = Integer.parseInt(deactivateInput);
                                cakeService.deactivateCake(deactivateCakeId);
                                break;

                            // Back
                            case 5:
                                break;

                            default:
                                System.out.println("Invalid Choice");
                        }

                        if (cakeChoice == 5) 
                        {
                            break;
                        }
                    }

                    break;

                // Customer Management
                case 2:
                	
                    while (true) 
                    {
                        System.out.println("\n===== Customer Management =====");
                        System.out.println("1. Add Customer");
                        System.out.println("2. View Customer");
                        System.out.println("3. Modify Customer");
                        System.out.println("4. Deactivate Customer");
                        System.out.println("5. Back");
                        System.out.print("Enter Choice : ");

                        int customerChoice =Integer.parseInt( sc.nextLine());

                        switch (customerChoice) 
                        {
                            // Add Customer
                            case 1:
                                System.out.println("\nEnter Customer Details");
                                System.out.println("Format : Name:Contact:Address:Email");
                                String customerInput =sc.nextLine();
                                String[] customerData =ApplicationUtil.splitDetails(customerInput);
                                Customer customer =new Customer(customerData[0],customerData[1],customerData[2],customerData[3],true);

                                customerService.addCustomer(customer);
                                break;

                            // View Customer
                            case 2:
                                System.out.println("\nEnter Customer ID");
                                int customerId =Integer.parseInt(sc.nextLine());
                                customerService.viewCustomer(customerId);
                                break;

                            // Modify Customer
                            case 3:
                                System.out.println("\nEnter Details");
                                System.out.println("Format : CustomerId:NewEmail");
                                String modifyCustomerInput =sc.nextLine();
                                String[] modifyCustomerData =ApplicationUtil.splitDetails(modifyCustomerInput);
                                customerService.updateCustomerEmail(Integer.parseInt(modifyCustomerData[0]),modifyCustomerData[1]);
                                break;

                            // Deactivate Customer
                            case 4:
                                System.out.println("\nEnter Customer ID");
                                int deactivateCustomerId =Integer.parseInt(sc.nextLine());
                                
                                customerService.deactivateCustomer(deactivateCustomerId);
                                break;

                            // Back
                            case 5:
                                break;

                            default:
                            	System.out.println("Invalid Choice");
                        }

                        if (customerChoice == 5) 
                        {
                            break;
                        }
                    }

                    break;

                // Order Management
                case 3:
                    while (true) 
                    {
                        System.out.println("\n===== Order Management =====");
                        System.out.println("1. Place Order");
                        System.out.println("2. View Order");
                        System.out.println("3. Cancel Order");
                        System.out.println("4. Back");
                        System.out.print("Enter Choice : ");
                        int orderChoice =Integer.parseInt(sc.nextLine());

                        switch (orderChoice) 
                        {
                            // Place Order
                            case 1:
                                System.out.println("\nEnter Order Details");
                                System.out.println("Format : CustomerId:CakeId:Quantity:Price");
                                String orderInput =sc.nextLine();
                                String[] orderData =ApplicationUtil.splitDetails(orderInput);

                                Order order =new Order(Integer.parseInt(orderData[0]),Integer.parseInt(orderData[1]),
                                		Double.parseDouble(orderData[2]),Double.parseDouble(orderData[3]),
                                		new Date(System.currentTimeMillis()));

                                orderService.placeOrder(order);
                                break;

                            // View Order
                            case 2:
                                System.out.println("\nEnter Order ID");
                                int orderId =Integer.parseInt(sc.nextLine());

                                orderService.viewOrder(orderId);
                                break;

                            // Cancel Order
                            case 3:
                                System.out.println("\nEnter Order ID");
                                int cancelId =Integer.parseInt(sc.nextLine());

                                try {
                                    orderService.cancelOrder(cancelId);

                                } 
                                catch (OrderNotCancelledException e) {
                                    System.out.println(e.getMessage());
                                }

                                break;

                            // Back
                            case 4:
                                break;

                            default:
                                System.out.println("Invalid Choice");
                        }

                        if (orderChoice == 4) 
                        {
                            break;
                        }
                    }

                    break;

                //Billing Management
                case 4:
                    while (true) 
                    {
                        System.out.println("\n===== Billing Management =====");
                        System.out.println("1. Generate Bill");
                        System.out.println("2. View Bill");
                        System.out.println("3. Back");
                        System.out.print("Enter Choice : ");

                        int billingChoice =Integer.parseInt(sc.nextLine());

                        switch (billingChoice) 
                        {
                            // Generate Bill
                            case 1:
                                System.out.println("\nEnter Bill Details");
                                System.out.println("Format : OrderId:TotalAmount");
                                String billInput =sc.nextLine();

                                String[] billData =ApplicationUtil.splitDetails(billInput);

                                Billing billing =new Billing(Integer.parseInt(billData[0]),Double.parseDouble(billData[1]),
                                				 new java.sql.Date(System.currentTimeMillis()));

                                billingService.generateBill(billing);

                                break;

                            // View Bill
                            case 2:
                                System.out.println("\nEnter Bill ID");
                                int billId =Integer.parseInt(sc.nextLine());
                                billingService.viewBill(billId);

                                break;

                            // Back
                            case 3:
                                break;

                            default:
                                System.out.println("Invalid Choice");
                        }

                        if (billingChoice == 3) 
                        {
                            break;
                        }
                    }

                    break;
                    
                // Feedback Management
                case 5:
                    while (true) 
                    {
                        System.out.println("\n===== Feedback Management =====");
                        System.out.println("1. Add Feedback");
                        System.out.println("2. View Feedback");
                        System.out.println("3. Back");
                        System.out.print("Enter Choice : ");

                        int feedbackChoice =Integer.parseInt(sc.nextLine());

                        switch (feedbackChoice) 
                        {
                            // Add Feedback
                            case 1:
                                System.out.println("\nEnter Feedback Details");

                                System.out.println("Format : CustomerId:Message:Rating");

                                String feedbackInput =sc.nextLine();

                                String[] feedbackData =ApplicationUtil.splitDetails(feedbackInput);

                                Feedback feedback =new Feedback(Integer.parseInt(feedbackData[0]),feedbackData[1],
                                				   Integer.parseInt(feedbackData[2]));

                                feedbackService.addFeedback(feedback);

                                break;

                            // View Feedback
                            case 2:
                                System.out.println("\nEnter Feedback ID");
                                int feedbackId =Integer.parseInt(sc.nextLine());
                                feedbackService.viewFeedback(feedbackId);

                                break;

                            // Back
                            case 3:
                                break;

                            default:
                                System.out.println("Invalid Choice");
                        }

                        if (feedbackChoice == 3) 
                        {
                            break;
                        }
                    }

                    break;
                    
                // Exit
                case 6:
                    System.out.println("\nThank You");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}