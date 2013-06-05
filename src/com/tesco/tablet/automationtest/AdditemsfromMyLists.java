package com.tesco.tablet.automationtest;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.tesco.tablet.library.*;
import android.util.Log;
import java.util.ArrayList;
import com.android.uiautomator.core.UiDevice;
import android.view.KeyEvent;


public class AdditemsfromMyLists extends UiAutomatorTestCase
{
    public void test_additemsfromMyListScreen() throws UiObjectNotFoundException
    {
        /* Create a new object to Launch Tesco app from the list.
        LaunchApp tescoApp = new LaunchApp();

        // Get the app name from the Constants class and pass it to the LaunchApp class
        // http://stackoverflow.com/questions/3262670/how-to-use-the-variable-of-one-class-into-another-class-using-java
        tescoApp.launchapp(Constants.APPNAME,getUiDevice());*/

        try
        {
            Thread.sleep(4000);
            // Tap on the Search icon from the Dashboard

            // Tap on the dropdown from Dashboard
            UiObject dropdownbtn = new UiObject(new UiSelector().className("android.widget.Spinner").index(0));
            dropdownbtn.clickAndWaitForNewWindow();

            // Tap on the My Lists  dropdown option
            UiObject mylistsoption = new UiObject(new UiSelector().className("android.widget.TextView").text("Favourites"));
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
                // Create a new object to test the add item from Favourites list
                AddItemsFrompProductListandDetailsView validateaddingitemsfromFavourites = new AddItemsFrompProductListandDetailsView();
                validateaddingitemsfromFavourites._additemsfromProductListView();
                // Press back to get back to My Favorites screen
                getUiDevice().pressBack();

            }
            // Navigate to My Usuals
            UiObject myusuals = new UiObject(new UiSelector().className("android.widget.TextView").text("My Usuals"));
            myusuals.clickAndWaitForNewWindow();

            // Add items from the My Usuals list
            Constants usualslisttxt = new Constants();
            if(_checklistnotempty(usualslisttxt.NOUSUALS))
            {
                System.out.println("****My Usuals: User has no usuals in his account. So, no items could be added.****");

            }
            else
            {
                // Create a new object to test the add item from Favourites list
                AddItemsFrompProductListandDetailsView validateaddingitemsfrommyusuals = new AddItemsFrompProductListandDetailsView();
                validateaddingitemsfrommyusuals._additemsfromProductListView();

                // Press back to get back to My Favorites screen
                getUiDevice().pressBack();

            }

            // Navigate to Last Order
            UiObject lastorder = new UiObject(new UiSelector().className("android.widget.TextView").text("Last Order"));
            lastorder.clickAndWaitForNewWindow();

            // Add items from the My Usuals list
            Constants lastordertxt = new Constants();
            if(_checklistnotempty(usualslisttxt.LASTORDER))
            {
                System.out.println("**** Last Order: User has no products in his last order list. So, no items could be added****");
                getUiDevice().pressBack();

            }
            else
            {
                // Create a new object to test the add item from Favourites list
                AddItemsFrompProductListandDetailsView validateaddingitemsfromlastorder = new AddItemsFrompProductListandDetailsView();
                validateaddingitemsfromlastorder._additemsfromProductListView();

            }
                // Navigate back to the Dashboard
            getUiDevice().pressBack();
            getUiDevice().pressBack();





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
