package com.tradeling.mobile.buyerApp.Regression;

import com.tradeling.data.buyerApp.Constants;
import com.tradeling.mobile.driver.EnvironmentSetup;
import org.testng.annotations.Test;

public class Cart extends EnvironmentSetup {

    String SearchData = "airpods";

    /**
     * @URL https://tradeling.atlassian.net/browse/QTM-4556
     */
    @Test(description = "Verify that user can add product to cart successfully")
    public void QTM4556() throws InterruptedException {
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH, Constants.REGION_UAE);
        loginScreen.enterUserAndPass(environmentCredintiols.GetUserName(),environmentCredintiols.GetPassword());
        searchScreen.search(SearchData);
        searchScreen.SelectRandomProduct();
    }
}
