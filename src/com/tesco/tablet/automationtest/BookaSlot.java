package com.tesco.tablet.automationtest;

//Import the uiautomator libraries
import com.android.uiautomator.core.*;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.tesco.tablet.library.OnBoardDismissal;
import com.tesco.tablet.library.LaunchApp;
import com.tesco.tablet.library.Constants;
import com.tesco.tablet.library.GetBackToDashboard;


public class BookaSlot extends UiAutomatorTestCase
{
    public void test_BookHomeDeliverySlot() throws UiObjectNotFoundException
    {
        /*Create a new object to Launch Tesco app from the list. Any app can be launched by changing the parameters
        LaunchApp tescoApp = new LaunchApp();
        // Get the app name from the Constants class and pass it to the LaunchApp class
        // http://stackoverflow.com/questions/3262670/how-to-use-the-variable-of-one-class-into-another-class-using-java
        tescoApp.launchapp(Constants.APPNAME,getUiDevice());*/

        //Check if the slot is already booked.
        if(_checkslotalreadyBooked())
        {
            System.out.println("-->Slot already booked<--.So, executing the next test case");
        }
        else
        {
            if(_navigatetoBookaslotScreen())
            {
                System.out.println("****Pass: App navigated to Choose Delivery Method Screen as expected****");
                if(_navigatetoHomeDeliveryScreen())
                {
                    System.out.println("****Pass: App navigated to Home Delivery Screen as expected****");

                    if(_chooseslotandBook())
                    {
                        System.out.println("****Pass: App booked Home Delivery Screen as expected****");

                        // Navigate back to the Dashboard
                    /*GetBackToDashboard gotoHomeScreen = new GetBackToDashboard();
    				gotoHomeScreen._returntoHomeScreen();*/

                        // Check whether user navigated back to Home screen after booking slot
                        UiObject welcometxtvalidation = new UiObject(new UiSelector().className("android.widget.TextView").textContains("Welcome"));
                        if(welcometxtvalidation.exists())
                        {
                            System.out.println("****Pass: App navigated to Home screen as expected****");
                        }
                        else
                        {
                            System.out.println("****Fail: App failed to get back to the Dashboard****");
                        }

                    }
                }

            }

        }
 
    }
    
    private boolean _navigatetoBookaslotScreen() throws UiObjectNotFoundException
    {
    	try
    	{
    		Thread.sleep(5000);
    		// Tap on the dropdown from Dashboard
        	UiObject dropdownbtn = new UiObject(new UiSelector().className("android.widget.Spinner").index(0));
        	dropdownbtn.clickAndWaitForNewWindow();
        	
        	// Tap on the Book a Slot dropdown option
        	UiObject bookaslotoption = new UiObject(new UiSelector().className("android.widget.TextView").text("Book a Slot"));
        	if(bookaslotoption.exists())
        	{
        		bookaslotoption.clickAndWaitForNewWindow();
        		// Wait for the maximum server timeout limit of 30 seconds
        		Thread.sleep(20000);
        		UiObject homedeliveryicon = new UiObject(new UiSelector().className("android.widget.ImageView").description("Home Delivery"));
        		if(homedeliveryicon.exists())
        		{
        			return true;
        		}
        		else
        		{
        			System.out.println("****Fail:App failed to navigate to Book a Slot screen. TestCase Fail****");
            		return false;
        		}
        				
        	}
        	else
        	{
        		System.out.println("****Fail:Book a Slot option not present in the dropdown. TestCase Fail****");
        		return false;
        	}
    		
    	}
    	catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    	return false;
    	
    	
 
    }
    
    private boolean _navigatetoHomeDeliveryScreen() throws UiObjectNotFoundException
    {
    	try
    	{
    		// Wait for the maximum server timeout limit of 30 seconds
    		Thread.sleep(3000);
    		// tap on the Home Delivery icon
        	UiObject homedeliveryicon = new UiObject(new UiSelector().className("android.widget.ImageView").description("Home Delivery"));
        	if(homedeliveryicon.isEnabled())
        	{
        		homedeliveryicon.clickAndWaitForNewWindow();
        		Thread.sleep(10000);

                // Dismiss the on-baording OK button if it exists
                UiObject okbtn = new UiObject(new UiSelector().className("android.widget.Button").text("OK"));
                if(okbtn.exists()&&okbtn.isEnabled())
                {
                    okbtn.clickAndWaitForNewWindow();
                }
        		return true;
        		
        	}
        	else
        	{
        		System.out.println("****Fail: Looks like a server delayed occured due to which app failed to enable the Slot method options");
        		return false;
        	}
    	}
    	catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    	return false;
    }
    
    private boolean _chooseslotandBook() throws UiObjectNotFoundException
    {
    	try
    	{
    		Thread.sleep(10000);
    		
    		// Choose slot from the Week1---->> Need some work here to be done
    		UiObject homedeliveryslot = new UiObject(new UiSelector().className("android.widget.CheckedTextView").textContains("4.00"));

        	if(homedeliveryslot.isEnabled())
        	{
        		homedeliveryslot.click();
        		// Check if Book a slot button gets enabled on selecting a slot
        		UiObject bookaslotbtn = new UiObject(new UiSelector().className("android.widget.Button").text("Book slot"));
        		if(bookaslotbtn.isEnabled())
        		{
        			bookaslotbtn.click();
        			Thread.sleep(5000);
        			return true;
        			
        		}
        		else
        		{
        			System.out.println("****Fail: App failed to reserve the chosen slot");
            		return false;	
        		}
        		
        	}
        	else
        	{
        		System.out.println("****Fail: No delivery slots are displayed due to which no slots could be chosen for reservation");
        		return false;
        	}
    	}
    	catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    	return false;
    }

    private boolean _checkslotalreadyBooked() throws UiObjectNotFoundException
    {
        UiCollection dashboardgrid = new UiCollection(new UiSelector().className("android.widget.RelativeLayout"));
        UiObject dashboardcards = dashboardgrid.getChild(new UiSelector().className("android.widget.LinearLayout"));
        UiObject slotcard = dashboardcards.getChild(new UiSelector().className("android.widget.RelativeLayout").index(2));
        UiObject slotinfo = slotcard.getChild(new UiSelector().className("android.widget.LinearLayout"));
        UiObject slotdata = slotinfo.getChild(new UiSelector().className("android.widget.TextView").index(1));
        String slotstatus = slotdata.getText();
        System.out.println(slotstatus);
        if("Book a slot".equals(slotstatus))
        {
            System.out.println("--> Slot is not yet booked.<---");
            return false;

        }
        return true;


    }

    
}
