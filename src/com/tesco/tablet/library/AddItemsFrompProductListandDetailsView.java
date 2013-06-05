package com.tesco.tablet.library;

import com.android.uiautomator.core.*;

/**
 * Created by apple on 04/06/13.
 */
public class AddItemsFrompProductListandDetailsView
{
    public void _additemsfromProductListView() throws UiObjectNotFoundException
    {
        // Access the products from the Grid View of product list
        UiCollection productlist = new UiCollection(new UiSelector().className("android.widget.GridView"));

        // Get the details of the first product in the list view
        UiObject firstGridProduct = productlist.getChild(new UiSelector().className("android.widget.RelativeLayout"));

        UiObject productName = firstGridProduct.getChild(new UiSelector().className("android.widget.TextView").index(1));
        // If
        String producttext = productName.getText();
        if(!"Offer".equals(producttext))
        {
            System.out.println("Product to be added to the basket is--> " + producttext);

        }
        else
        {
            UiObject productNameNew = firstGridProduct.getChild(new UiSelector().className("android.widget.TextView").index(2));
            String producttextnew = productNameNew.getText();
            System.out.println("Product to be added to the basket is--> " + producttextnew);
        }

        // Store the quantity details before adding the product.
        UiObject plusMinusAndPrice = firstGridProduct.getChild(new UiSelector().className("android.widget.LinearLayout"));
        UiObject quantity = plusMinusAndPrice.getChild(new UiSelector().className("android.widget.TextView"));
        String actualQuantitybeforeAdding = quantity.getText();
        System.out.println("Product List View: Actual Quantity Before Adding: "+ actualQuantitybeforeAdding);

        _validateadditemfromListView(productlist,quantity,actualQuantitybeforeAdding);
        _validateadditemfromDetailsScreen(productName,plusMinusAndPrice);


    }

    private void _validateadditemfromListView(UiObject productlist,UiObject quantity,String actualQuantitybeforeAdding) throws UiObjectNotFoundException
    {
        // Add item from the product list screen
        UiObject addbutton = productlist.getChild(new UiSelector().className("android.widget.Button").description("Increase Quantity"));
        if(addbutton.exists()&&addbutton.isEnabled())
        {
            addbutton.clickAndWaitForNewWindow();
            String actualQuantityafterAdding = quantity.getText();
            if(actualQuantitybeforeAdding!=actualQuantityafterAdding)
            {
                System.out.println("Product List View: Actual Quantity after Adding: "+ actualQuantityafterAdding);
                System.out.println("****PASS: Adding item from the product list view successful****");
            }
            else
            {
                System.out.println("****FAIL: Adding items from the product list view failed**** ");
            }
        }
        else
        {
            System.out.println("Skip: Need to implement better logic to skip the first product and try to hit the add button for the next product");
        }
    }

    private void _validateadditemfromDetailsScreen(UiObject productName,UiObject plusMinusAndPrice) throws UiObjectNotFoundException
    {
        // Add items from the product details screen
        productName.clickAndWaitForNewWindow();
        // tap on the Add button in  product details screen.
        UiObject productdetailsaddbutton = new UiObject(new UiSelector().className("android.widget.Button").description("Increase Quantity"));
        if(productdetailsaddbutton.exists()&&productdetailsaddbutton.isEnabled())
        {
            UiObject quantitydetails = plusMinusAndPrice.getChild(new UiSelector().className("android.widget.TextView"));
            String actualQuantitybeforeAdding2 = quantitydetails.getText();
            productdetailsaddbutton.clickAndWaitForNewWindow();
            UiObject quantity = plusMinusAndPrice.getChild(new UiSelector().className("android.widget.TextView"));
            String actualQuantityafterAdding2 = quantity.getText();

            System.out.println("ProductDetails: Actual Quantity Before Adding: "+ actualQuantitybeforeAdding2);

            if(actualQuantitybeforeAdding2!=actualQuantityafterAdding2)
            {
                System.out.println("ProductDetails: Actual Quantity After Adding: "+ actualQuantityafterAdding2);
                System.out.println("****PASS: Adding item from the product details view successful****");
            }
            else
            {
                System.out.println("****FAIL: Adding items from the product details view failed**** ");
            }

        }
    }
}
