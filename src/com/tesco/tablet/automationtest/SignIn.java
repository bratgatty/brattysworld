package com.tesco.tablet.automationtest;

//Import the uiautomator libraries
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.tesco.tablet.library.OnBoardDismissal;
import com.tesco.tablet.library.LaunchApp;
import com.tesco.tablet.library.Constants;
import com.android.uiautomator.core.UiDevice;

public class SignIn extends UiAutomatorTestCase
{

    public void test_SignintotheApp() throws UiObjectNotFoundException
    {

        // Create a new object to Launch Tesco app from the list.
        LaunchApp tescoApp = new LaunchApp();

        // Get the app name from the Constants class and pass it to the LaunchApp class
        Constants apptobeLaunched = new Constants();

        tescoApp.launchapp(apptobeLaunched.APPNAME,getUiDevice());

    	// for fresh launch, tap on  OK button in the On-Boarding screen
        // Create a new object and then pass the parameter as 1 for 2 iterations of OK button in the Dashboard screen for the public class
        //OnBoardDismissal


        OnBoardDismissal dimissonboardingscreen = new OnBoardDismissal();
        dimissonboardingscreen._dismissOnBoarding(1);
		
		if(_navigatetoSignIn())
		{
			//
			System.out.println("****User went to Sign In screen as expected.****");
			if(_enterdetailsandsignin())
			{
				System.out.println("****Pass: User signed into the app succesfully");	
			}
			else
			{
				System.out.println("****Fail: Some error occured and user could not sign into the app****");
			}
		}
		else
		{
			System.out.println("****Sign In Test Completd successfully****");
		}
		
    }

    private boolean _navigatetoSignIn() throws UiObjectNotFoundException
    {
    	try
    	{
    		Thread.sleep(1000);
    		if(_checkusersigned())
        	{
        		UiObject signinbtn = new UiObject(new UiSelector().text("Sign In"));
        		signinbtn.clickAndWaitForNewWindow();
        		return true;
        	}
        	else
        	{
        		System.out.println("****User is already in the Home Screen****");
        		return false;
        	}
    		
    	}
    	catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    	return false;
	
    }
    
    private boolean _checkusersigned() throws UiObjectNotFoundException
    {
    	try
    	{
    		Thread.sleep(1000);
    		UiObject signinbtn = new UiObject(new UiSelector().text("Sign In"));
        	if(signinbtn.exists())
        	{
        		System.out.println("****User has not signed in****");
        		return true;
        	}
        	else
        	{
        		System.out.println("****User Has already signed into the app***");
        		return false;
        	}
    	}
    	catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
		return false;	
    	
    }
    
    private boolean _enterdetailsandsignin() throws UiObjectNotFoundException
    {
    	try
    	{
    		Thread.sleep(1000);
    		UiObject emailaddressfield = new UiObject(new UiSelector().className("android.widget.EditText").description("Email"));

    		
        	if(emailaddressfield.exists())
        	{
                // Get the username and password from the Constants class
                Constants username = new Constants();
                Constants password = new Constants();

        		_enterText("Email", username.EMAIL);
                _enterText("Password", password.PASSWORD);
                _tapSigninbtn();
                return true;
        		
        	}
        	else
        	{
        		return false;
        	}
    	}
    	catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
		return false;
    	
    }
    
    private void _enterText(String field, String text) throws UiObjectNotFoundException 
    {
        UiObject enterdata = new UiObject(new UiSelector().className("android.widget.EditText").description(field));
        enterdata.setText(text);
    }
    
    private boolean _tapSigninbtn() throws UiObjectNotFoundException 
    {
        try
        {
            getUiDevice().pressBack();
            UiObject signinbtn = new UiObject(new UiSelector().className("android.widget.Button").description("Sign in"));
            signinbtn.clickAndWaitForNewWindow();
            Thread.sleep(5000);


            // Dismiss the Terms&Conditions dialog if shown
            UiObject termscondtionokbtn = new UiObject(new UiSelector().className("android.widget.Button").text("OK"));
            if(termscondtionokbtn.exists()&&termscondtionokbtn.isEnabled())
            {
                termscondtionokbtn.clickAndWaitForNewWindow();
                Thread.sleep(5000);

            }

            // check if user has logged into the app
            UiObject homebtn = new UiObject(new UiSelector().className("android.widget.TextView").text("Home"));
            if(homebtn.exists())
            {
                return true;
            }
            else
            {
                return false;
            }

        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        return false;



    }
    

    
    
}