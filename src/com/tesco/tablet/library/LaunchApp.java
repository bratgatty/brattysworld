package com.tesco.tablet.library;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.tesco.tablet.library.Constants;

/**
 * Created by apple on 30/05/13.
 */
public class LaunchApp extends UiAutomatorTestCase
{
    public void launchapp(String appname, UiDevice uiDevice) throws UiObjectNotFoundException
    {
        uiDevice.pressHome();
        // tap on APPS button
        UiObject allappbtn = new UiObject(new UiSelector().description("Apps"));
        if(allappbtn.isClickable()&&allappbtn.isEnabled())
        {
            allappbtn.clickAndWaitForNewWindow();
        }
        else
        {
            System.out.print("****Fail:AllApps Button not present in the Home Screen****");
        }

        // Click on the All Apps tab
        UiObject appsTab = new UiObject(new UiSelector().textContains("Apps"));
        if(appsTab.isClickable()&&appsTab.isEnabled())
        {
            appsTab.click();
        }
        else
        {
            System.out.print("****Fail:AllApps Tab not present in the Home Screen****");
        }

        // Create a scrollable object and then swipe through the object to search for Tesco app
        UiScrollable appViews = new UiScrollable(new UiSelector().scrollable(true));
        //Set swiping to Horizontal as default is vertical
        appViews.setAsHorizontalList();

        // In the scrollable object search for Tesco and tap on it
        UiObject tescoApp = appViews.getChildByText(new UiSelector().className(android.widget.TextView.class.getName()),appname);
        if(tescoApp.isClickable())
        {
            tescoApp.clickAndWaitForNewWindow();

        }
        else
        {
            System.out.println("****Fail:Tesco app not present****");

        }
    }
}

