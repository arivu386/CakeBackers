package util;

public class ApplicationUtil 
{
    // Split Method
    public static String[] splitDetails(String input) 
    {
        return input.split(":");
    }

    // Mobile Validation Method
    public boolean isValidMobile(String mobile) 
    {
        return mobile.matches("[789][0-9]{9}");
    }

    // Email Validation Method
    public boolean isValidEmail(String email) 
    {
        return email.matches("^[A-Za-z0-9+_.-]+@gmail\\.com$");
    }
}