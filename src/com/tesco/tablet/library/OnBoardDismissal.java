package com.tesco.tablet.library;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

/**
 * Created by apple on 30/05/13.
 */
public class OnBoardDismissal
{
    public boolean _dismissOnBoarding(int N) throws UiObjectNotFoundException
    {
        try
        {
            Thread.sleep(5000);
            UiObject okbtn = new UiObject(new UiSelector().className("android.widget.Button").text("OK"));
            int i = 0;
            if(okbtn.exists()&&okbtn.isEnabled())
            {
                for(i=0;i<=N;i++)
                {
                    okbtn.clickAndWaitForNewWindow();
                    Thread.sleep(2000);
                }
                return true;
            }
            else
            {
                System.out.println("No Onboarding Screen to show to the user.");
                return true;
            }

        }
        catch (InterruptedException ie)
        {
            ie.printStackTrace();
        }
        return false;
    }

}
