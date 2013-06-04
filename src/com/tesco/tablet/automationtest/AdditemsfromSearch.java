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


public class AdditemsfromSearch extends UiAutomatorTestCase
{
    public void test_additemsfromSearchResultsList() throws UiObjectNotFoundException
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

            UiObject searchbtn = new UiObject(new UiSelector().className("android.widget.ImageView").description("Search"));
            searchbtn.clickAndWaitForNewWindow();

            Constants searchtext = new Constants();

            _enterText("Search query", searchtext.SEARCHITEM);
            Thread.sleep(2000);

            // Tap on the Search button using the keyboard
            getUiDevice().pressKeyCode(KeyEvent.KEYCODE_ENTER);

            // Wait till the results get loaded

            Thread.sleep(3000);

            // for fresh launch, tap on  OK button in the On-Boarding screen
            // Create a new object and then pass the parameter as 1 for 2 iterations of OK button in the Search screen for the public class
            //OnBoardDismissal

            OnBoardDismissal dimissonboardingscreen = new OnBoardDismissal();
            dimissonboardingscreen._dismissOnBoarding(2);




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

            // Navigate back to the Dashboard
            UiObject gotoHomeScreen = new UiObject(new UiSelector().className("android.widget.LinearLayout").description("Navigate up"));
            gotoHomeScreen.clickAndWaitForNewWindow();




        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
    }

    private void _enterText(String field, String text) throws UiObjectNotFoundException
    {
        UiObject enterdata = new UiObject(new UiSelector().className("android.widget.EditText").description(field));
        enterdata.setText(text);
    }

}
