package exception;

public class OrderNotCancelledException extends Exception 
{
    public OrderNotCancelledException(String message) 
    {
        super(message);
    }
}