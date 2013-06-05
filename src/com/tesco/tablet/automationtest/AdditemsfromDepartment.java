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
import com.tesco.tablet.library.AddItemsFrompProductListandDetailsView;



public class AdditemsfromDepartment extends UiAutomatorTestCase
{
	public void test_additemsfromproductlistview() throws UiObjectNotFoundException
	{
        /* Create a new object to Launch Tesco app from the list.
        //LaunchApp tescoApp = new LaunchApp();
        // Get the app name from the Constants class and pass it to the LaunchApp class
        // http://stackoverflow.com/questions/3262670/how-to-use-the-variable-of-one-class-into-another-class-using-java
        //tescoApp.launchapp(Constants.APPNAME,getUiDevice());*/

        try
        {
            Thread.sleep(4000);
            // Tap on the Super Department by accessing the value from the Constants class
            UiObject department = new UiObject(new UiSelector().className("android.widget.TextView").text(Constants.SUPERDEPARTMENT));
            department.clickAndWaitForNewWindow();
            Thread.sleep(1000);

            //Tap on the Department by accessing the value from the Constants class
            UiObject shelf = new UiObject(new UiSelector().className("android.widget.TextView").text(Constants.DEPARTMENT));
            shelf.clickAndWaitForNewWindow();
            Thread.sleep(1000);

            // for fresh launch, tap on  OK button in the On-Boarding screen
            // Create a new object and then pass the parameter as 1 for 2 iterations of OK button in the Dashboard screen for the public class
            //OnBoardDismissal

            OnBoardDismissal dimissonboardingscreen = new OnBoardDismissal();
            dimissonboardingscreen._dismissOnBoarding(1);

            // Create a new object to test the add item from Department use case
            AddItemsFrompProductListandDetailsView validateaddingitemsfromDepartment = new AddItemsFrompProductListandDetailsView();
            validateaddingitemsfromDepartment._additemsfromProductListView();


            // Navigate back to the Dashboard
            UiObject gotoHomeScreen = new UiObject(new UiSelector().className("android.widget.LinearLayout").description("Navigate up"));
            gotoHomeScreen.clickAndWaitForNewWindow();

            // To avoid the bug of department list open when user gets back to Dashboard. This will be fixed in next bulilds
            getUiDevice().pressBack();

        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }

    }

}
