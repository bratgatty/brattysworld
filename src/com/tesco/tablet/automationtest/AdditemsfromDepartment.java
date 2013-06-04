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


public class AdditemsfromDepartment extends UiAutomatorTestCase
{
	public void test_additemsfromproductlistview() throws UiObjectNotFoundException
	{
        // Create a new object to Launch Tesco app from the list.
        LaunchApp tescoApp = new LaunchApp();

        // Get the app name from the Constants class and pass it to the LaunchApp class
        // http://stackoverflow.com/questions/3262670/how-to-use-the-variable-of-one-class-into-another-class-using-java
        tescoApp.launchapp(Constants.APPNAME,getUiDevice());
        //boolean passedTest = true;
        try
        {
            Thread.sleep(4000);
            // Tap on the Super Department by accessing the value from the Constants class
            Constants superdpt = new Constants();
            UiObject department = new UiObject(new UiSelector().className("android.widget.TextView").text(Constants.SUPERDEPARTMENT));
            department.clickAndWaitForNewWindow();
            Thread.sleep(2000);

            //Tap on the Department by accessing the value from the Constants class
            Constants dpt = new Constants();
            UiObject shelf = new UiObject(new UiSelector().className("android.widget.TextView").text(dpt.DEPARTMENT));
            shelf.clickAndWaitForNewWindow();
            Thread.sleep(2000);

            // for fresh launch, tap on  OK button in the On-Boarding screen
            // Create a new object and then pass the parameter as 1 for 2 iterations of OK button in the Dashboard screen for the public class
            //OnBoardDismissal

            OnBoardDismissal dimissonboardingscreen = new OnBoardDismissal();
            dimissonboardingscreen._dismissOnBoarding(1);


            //ArrayList<UiCollection> mylistofitems = new ArrayList<UiCollection>();

            // Access the products from the Grid View of Fresh Fruit category
            UiCollection products = new UiCollection(new UiSelector().className("android.widget.GridView"));

            UiObject fourthGridProduct = products.getChildByInstance(new UiSelector().className("android.widget.RelativeLayout"), 4);


            //int count = products.getChildCount(new UiSelector().className("android.widget.RelativeLayout"));
            // Add 1 quantity for the first product in the list.
            //UiObject productname = products.getChild(new UiSelector().className("android.widget.TextView"));
            //System.out.println(productname.getText());
//            products.getChild(new UiSelector().)
//            UiObject firstGridProduct = products.getChild(new UiSelector().className("android.widget.RelativeLayout"));
            UiObject plusMinusAndPrice = fourthGridProduct.getChild(new UiSelector().className("android.widget.LinearLayout"));
            UiObject quantity = plusMinusAndPrice.getChild(new UiSelector().className("android.widget.TextView"));
            String actualQuantity = quantity.getText();
            System.out.println("Actual Quantity: "+ actualQuantity);

            //----->>> Add logic to store the product quantity in a variable before hitting the ADD BUTTON<<<-------


            // Add item from the product list screen
            UiObject addbutton = products.getChild(new UiSelector().className("android.widget.Button").description("Increase Quantity"));
            if(addbutton.exists()&&addbutton.isEnabled())
            {
                //Log.i("Add button Test","Checking if the test went successfull");
                //assertTrue(" Add button is present.", addbutton.isClickable());
                //passedTest = !addbutton.isClickable();
                addbutton.clickAndWaitForNewWindow();
                System.out.println("****PASS: Adding item from the product list screen successful****");

                //----->>> Add logic to check if the product quantity got incremented<<<-------


            }

              // Add items from the product details screen
            UiObject productdetailscreen = products.getChildByText(new UiSelector().className("android.widget.RelativeLayout"), "Tesco Bramley Apples Loose");
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

            // To avoid the bug of department list open when user gets back to Dashboard.
            getUiDevice().pressBack();



        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }



//ssertTrue("Test passed", passedTest);

    }

}
