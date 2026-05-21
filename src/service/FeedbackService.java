package service;

import dao.FeedbackDAO;
import model.Feedback;

public class FeedbackService 
{
    FeedbackDAO dao =new FeedbackDAO();

    // Add Feedback
    public void addFeedback(Feedback feedback) 
    {
        int rows=dao.addFeedback(feedback);
        if(rows > 0) 
        {
            System.out.println("Feedback Added Successfully");
        }
        else 
        {
            System.out.println("Failed to Add Feedback");
        }
    }

    // View Feedback
    public void viewFeedback(int feedbackId) 
    {
        boolean found=dao.viewFeedback(feedbackId);
        if(!found) 
        {
            System.out.println("Feedback Not Found");
        }
    }
}
