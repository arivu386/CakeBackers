package dao;

import model.Feedback;
import util.DatabaseUtil;

import java.sql.*;

public class FeedbackDAO 
{
    Connection connection =DatabaseUtil.getConnection();

    // Add Feedback
    public int addFeedback(Feedback feedback) 
    {
        try {
            String query ="INSERT INTO Feedback(customerId,feedbackMessage,rating) VALUES(?,?,?)";

            PreparedStatement ps =connection.prepareStatement(query);
            ps.setInt(1,feedback.getCustomerId());
            ps.setString(2,feedback.getFeedbackMessage());
            ps.setInt(3,feedback.getRating());
            return ps.executeUpdate();
        } 
        catch (Exception e) 
        {
        	System.out.println("Error while adding feedback");
            e.printStackTrace();
        }
        return 0;
    }

    // View Feedback
    public boolean viewFeedback(int feedbackId) 
    {
    	boolean found=false;
    	
        try {
            String query ="SELECT * FROM Feedback WHERE feedbackId=?";

            PreparedStatement ps =connection.prepareStatement(query);
            ps.setInt(1,feedbackId);
            ResultSet rs =ps.executeQuery();
            
            while(rs.next()) 
            {
            	found=true;
                System.out.println("\nFeedback ID : "+ rs.getInt("feedbackId"));
                System.out.println("Customer ID : "+ rs.getInt("customerId"));
                System.out.println("Feedback : "+ rs.getString("feedbackMessage"));
                System.out.println("Rating : "+ rs.getInt("rating"));
            }
            
        } 
        catch (Exception e) 
        {
        	System.out.println("Error while viewing feedback");
            e.printStackTrace();
        }
        return found;
    }
}
