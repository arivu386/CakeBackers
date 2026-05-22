package dao;

import model.Feedback;
import util.DatabaseUtil;

import java.sql.*;

public class FeedbackDAO 
{
	Connection connection = DatabaseUtil.getConnection();

	// -------------------------------------------Add Feedback---------------------------------------
	public int addFeedback(Feedback feedback) 
	{
		try 
		{
			// Check Customer Exists
			String checkQuery = "SELECT * FROM Customers WHERE customerId=?";

			PreparedStatement checkPs = connection.prepareStatement(checkQuery);
			checkPs.setInt(1, feedback.getCustomerId());
			ResultSet rs = checkPs.executeQuery();

			if (!rs.next()) 
			{
				System.out.println("Customer ID does not exist");
				return 0;
			}

			// Rating Validation
			if (feedback.getRating() < 1 || feedback.getRating() > 5) 
			{
				System.out.println("Rating must be between 1 and 5");
				return 0;
			}

			String query = "INSERT INTO Feedback(customerId,feedbackMessage,rating) VALUES(?,?,?)";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, feedback.getCustomerId());
			ps.setString(2, feedback.getFeedbackMessage());
			ps.setInt(3, feedback.getRating());
			return ps.executeUpdate();

		} 
		catch (Exception e) 
		{
			System.out.println("Error while adding feedback");
			e.printStackTrace();
		}
		return 0;
	}

	// -----------------------------------------------View Feedback----------------------------------------------
	public boolean viewFeedback(int feedbackId) 
	{
		boolean found = false;
		try 
		{
			String query = "SELECT f.feedbackId, " + "cu.name AS customerName, " + "c.name AS cakeName, "
					+ "f.feedbackMessage, " + "f.rating " + "FROM Feedback f " + "JOIN Customers cu "
					+ "ON f.customerId = cu.customerId " + "JOIN Orders o " + "ON cu.customerId = o.customerId "
					+ "JOIN Cakes c " + "ON o.cakeId = c.cakeId " + "WHERE f.feedbackId=?";

			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, feedbackId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) 
			{
				found = true;
				System.out.println("\n======= FEEDBACK =======");
				System.out.println("\nFeedback ID : " + rs.getInt("feedbackId"));
				System.out.println("Customer Name : " + rs.getString("customerName"));
				System.out.println("Cake Name : " + rs.getString("cakeName"));
				System.out.println("Feedback : " + rs.getString("feedbackMessage"));
				System.out.println("Rating : " + rs.getInt("rating"));
				System.out.println("============================");
			}

		} 
		catch (Exception e) 
		{
			System.out.println("Error while viewing feedback");
			e.printStackTrace();
		}
		return found;
	}

	// -------------------------------------------View All Feedbacks------------------------------------------
	public boolean viewAllFeedbacks() 
	{
		boolean found = false;
		try 
		{
			String query = "SELECT f.feedbackId, " + "cu.name AS customerName, " + "c.name AS cakeName, "
					+ "f.feedbackMessage, " + "f.rating " + "FROM Feedback f " + "JOIN Customers cu "
					+ "ON f.customerId = cu.customerId " + "JOIN Orders o " + "ON cu.customerId = o.customerId "
					+ "JOIN Cakes c " + "ON o.cakeId = c.cakeId";

			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			System.out.println("\n===== FEEDBACK LIST =====");

			while (rs.next()) 
			{
				found = true;
				System.out.println("\nFeedback ID : " + rs.getInt("feedbackId"));
				System.out.println("Customer Name : " + rs.getString("customerName"));
				System.out.println("Cake Name : " + rs.getString("cakeName"));
				System.out.println("Feedback : " + rs.getString("feedbackMessage"));
				System.out.println("Rating : " + rs.getInt("rating"));
				System.out.println("============================");
			}

		} 
		catch (Exception e) 
		{
			System.out.println("Error while viewing all feedbacks");
			e.printStackTrace();
		}
		return found;
	}

	// ----------------------------------------Sort Feedback By Rating-------------------------------------
	public boolean sortFeedbackByRating() 
	{
		boolean found = false;
		try 
		{
			String query = "SELECT c.name AS cakeName, " + "AVG(f.rating) AS averageRating, "
					+ "COUNT(f.rating) AS totalRatings " + "FROM Feedback f " + "JOIN Customers cu "
					+ "ON f.customerId = cu.customerId " + "JOIN Orders o " + "ON cu.customerId = o.customerId "
					+ "JOIN Cakes c " + "ON o.cakeId = c.cakeId " + "GROUP BY c.name " + "ORDER BY averageRating DESC";

			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			System.out.println("\n===== CAKE RATING REPORT =====");

			while (rs.next()) 
			{
				found = true;
				System.out.println("\nCake Name : " + rs.getString("cakeName"));
				System.out.println("Average Rating : " + rs.getDouble("averageRating"));
				System.out.println("Total Ratings : " + rs.getInt("totalRatings"));
				System.out.println("============================");
			}

		} 
		catch (Exception e) 
		{
			System.out.println("Error while sorting feedback");
			e.printStackTrace();
		}
		return found;
	}
}
