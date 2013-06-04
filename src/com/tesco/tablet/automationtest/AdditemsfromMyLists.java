package com.tesco.tablet.automationtest;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.tesco.tablet.library.Constants;
import com.tesco.tablet.library.GetBackToDashboard;
import com.tesco.tablet.library.LaunchApp;
import com.tesco.tablet.library.OnBoardDismissal;
import android.util.Log;
import java.util.ArrayList;
import com.android.uiautomator.core.UiDevice;
import android.view.KeyEvent;


public class AdditemsfromMyLists extends UiAutomatorTestCase
{
    public void test_additemsfromMyListScreen() throws UiObjectNotFoundException
    {
        // Create a new object to Launch Tesco app from the list.
        LaunchApp tescoApp = new LaunchApp();

        // Get the app name from the Constants class and pass it to the LaunchApp class
        // http://stackoverflow.com/questions/3262670/how-to-use-the-variable-of-one-class-into-another-class-using-java
        Constants apptobeLaunched = new Constants();
        tescoApp.launchapp(apptobeLaunched.APPNAME,getUiDevice());

        try
        {
            Thread.sleep(4000);
            // Tap on the Search icon from the Dashboard

            // Tap on the dropdown from Dashboard
            UiObject dropdownbtn = new UiObject(new UiSelector().className("android.widget.Spinner").index(0));
            dropdownbtn.clickAndWaitForNewWindow();

            // Tap on the My Lists  dropdown option
            UiObject mylistsoption = new UiObject(new UiSelector().className("android.widget.TextView").text("My Lists"));
            mylistsoption.clickAndWaitForNewWindow();

            // Wait for a while before the My Lists is populated
            Thread.sleep(10000);


            // for fresh launch, tap on  OK button in the On-Boarding screen
            // Create a new object and then pass the parameter as 1 for 2 iterations of OK button in the Search screen for the public class
            //OnBoardDismissal

            OnBoardDismissal dimissonboardingscreen = new OnBoardDismissal();
            dimissonboardingscreen._dismissOnBoarding(2);



            // Add items from the Favourites list
            Constants favlisttxt = new Constants();
            if(_checklistnotempty(favlisttxt.NOFAVOURITES))
            {
                System.out.println("****Favourites: User has no favourites in his account. So, no items could be added****");

            }
            else
            {

                // Access the products from the Grid View of Search List
                UiCollection products = new UiCollection(new UiSelector().className("android.widget.GridView"));


                //----->>> Add logic to store the product quantity in a variable before hitting the ADD BUTTON<<<-------


                // Add item from the product list screen
                UiObject addbutton = products.getChild(new UiSelector().className("android.widget.Button").description("Increase Quantity"));
                if(addbutton.exists()&&addbutton.isEnabled())
                {
                    addbutton.clickAndWaitForNewWindow();
                    System.out.println("****PASS: Adding item from the product list screen successful****");

                    //----->>> Add logic to check if the product quantity got incremented<<<-------


                }

                // Add items from the product details screen
                UiObject productdetailscreen = products.getChild(new UiSelector().className("android.widget.ImageView").index(0));
                productdetailscreen.click();



                // tap on the Add button in  product details screen.
                UiObject productdetailsaddbutton = new UiObject(new UiSelector().className("android.widget.Button").description("Increase Quantity"));
                if(productdetailsaddbutton.exists()&&productdetailsaddbutton.isEnabled())
                {
                    productdetailsaddbutton.clickAndWaitForNewWindow();
                    System.out.println("****PASS: Adding item from the product details screen successful****");
                    //----->>> Add logic to check if the product quantity got incremented<<<-------

                }
            }

            // Navigate to My Usuals
            UiObject myusuals = new UiObject(new UiSelector().className("android.widget.TextView").text("My Usuals"));
            myusuals.clickAndWaitForNewWindow();


            // Navigate back to the Dashboard
            UiObject gotoHomeScreen = new UiObject(new UiSelector().className("android.widget.LinearLayout").description("Navigate up"));
            gotoHomeScreen.clickAndWaitForNewWindow();




        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }

    private boolean _checklistnotempty(String emptylisttxt)
    {
        UiObject checktxtdisplayed = new UiObject(new UiSelector().className("android.widget.TextView").text(emptylisttxt));

        if(checktxtdisplayed.exists())
        {
            System.out.println(checktxtdisplayed);
            return true;
        }
        return false;
    }

}
