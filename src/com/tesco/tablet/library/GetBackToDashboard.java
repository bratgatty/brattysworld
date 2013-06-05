package com.tesco.tablet.library;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

/* Currently this class is not used as the implementation has changed in Release 2, In Release 1, after booking slot, user needed to go back to Choose Delivery method screen
and the from there, user needed to tap Home option from dropdown. In Release 2, user directly goes to Dashboard after booking slot.*/

public class GetBackToDashboard {
    public boolean _returntoHomeScreen() throws UiObjectNotFoundException
    {
        try
        {
            Thread.sleep(1000);

            // Tap on the top nav bar button
            UiObject navbackbtn = new UiObject(new UiSelector().className("android.widget.TextView").text("Book a home delivery slot"));
            navbackbtn.clickAndWaitForNewWindow();

            // Tap on the dropdown from Dashboard
            UiObject dropdownbtn = new UiObject(new UiSelector().className("android.widget.Spinner").index(0));
            dropdownbtn.clickAndWaitForNewWindow();

            // Tap on the Book a Slot dropdown option
            UiObject homeoption = new UiObject(new UiSelector().className("android.widget.TextView").text("Home"));
            if(homeoption.exists())
            {
                homeoption.clickAndWaitForNewWindow();
                UiObject welcometxtvalidation = new UiObject(new UiSelector().className("android.widget.TextView").textContains("Welcome"));
                if(welcometxtvalidation.exists())
                {
                    System.out.println("****Pass: App navigated to Home screen as expected****");
                    return true;
                }

            }
            else
            {
                System.out.println("Fail:App cannot navigate to Home screen as the Home option is not present");
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
