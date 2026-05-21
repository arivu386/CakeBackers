package model;

public class Feedback 
{
    private int feedbackId;
    private int customerId;
    private String feedbackMessage;
    private int rating;

    public Feedback() 
    {

    }

    public Feedback(int customerId,String feedbackMessage,int rating) 
    {
        this.customerId = customerId;
        this.feedbackMessage = feedbackMessage;
        this.rating = rating;
    }

    public int getFeedbackId() 
    {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) 
    {
        this.feedbackId = feedbackId;
    }

    public int getCustomerId() 
    {
        return customerId;
    }

    public String getFeedbackMessage() 
    {
        return feedbackMessage;
    }

    public int getRating() 
    {
        return rating;
    }
}