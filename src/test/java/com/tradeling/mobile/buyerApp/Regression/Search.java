package com.tradeling.mobile.buyerApp.Regression;

import com.tradeling.data.buyerApp.Constants;
import com.tradeling.data.buyerApp.appSection;
import com.tradeling.mobile.driver.EnvironmentSetup;
import com.tradeling.utilities.Utilities;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Search extends EnvironmentSetup {
    String searchData = "IPhone";
    String altSearch ="airpods";
    String searchDataPen = "pen";
    SoftAssert softAssert = new SoftAssert();
    String username = "new-buyer-stage@tradeling.com";
    String password = "new-buyer-stage@tradeling.com";


    /**
     * @URL https://tradeling.atlassian.net/browse/QTM-4554
     * @throws InterruptedException
     */
    @Test(description = "Verify that search for a specific brand is returning the correct results")
    public void QTM4554()throws InterruptedException{
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH,Constants.REGION_UAE);
        loginScreen.enterUserAndPass(username,password);
        searchScreen.search(searchData);
        Assert.assertTrue(searchScreen.verifySearchResults(searchData));
    }

    /**
     * @URL https://tradeling.atlassian.net/browse/QTM-4553
     * @throws InterruptedException
     */
    @Test(description = "Verify that search results are related to the search input and not cashed")
    public void QTM4553()throws InterruptedException{
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH,Constants.REGION_UAE);
        loginScreen.enterUserAndPass(username,password);
        searchScreen.search(searchData);
        Boolean validSearch = searchScreen.verifySearchResults(searchData);
        searchScreen.search(altSearch);
        Boolean isSearchUpdated = searchScreen.verifySearchResults(searchData);
        softAssert.assertTrue(validSearch,"search results not matching search quary");
        softAssert.assertFalse(isSearchUpdated,"Search results not updated according search quary");
    }

    /**
     * @URL https://tradeling.atlassian.net/browse/QTM-4552
     * @throws InterruptedException
     */
    @Test(description = "Verify that user search inputs through enter action or auto suggest are listed in the search history")
    public void QTM4552()throws InterruptedException{
        String altSearchData = searchData+ Utilities.getRandomDate();
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH,Constants.REGION_UAE);
        loginScreen.enterUserAndPass(username,password);
        searchScreen.search(searchData);
        searchScreen.search(altSearchData);
        searchScreen.accessSearchSuggestion();
        softAssert.assertTrue(searchScreen.verifySearchResults(searchData));
        softAssert.assertTrue(searchScreen.verifySearchResults(altSearchData));
    }

    /**
     * @URL https://tradeling.atlassian.net/browse/QTM-4551
     * @throws InterruptedException
     */
    @Test(description = "Verify that user can search using keywords successfully")
    public void QTM4551()throws InterruptedException{
        //test scenario has no specific acceptence criteria
    }

    /**
     * @URL https://tradeling.atlassian.net/browse/QTM-5094
     * @throws InterruptedException
     */
    @Test(description = "Verify that the user can get the correct results while searching for an item/product")
    public void QTM5094() throws InterruptedException{
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH,Constants.REGION_UAE);
        loginScreen.enterUserAndPass(username,password);
        searchScreen.search(searchData);
        softAssert.assertTrue(searchScreen.verifySearchResults(searchData),"search data is not displayed");
        softAssert.assertTrue(searchScreen.verifySearchCountDisplayed(searchData),"search count is not displayed");
        softAssert.assertTrue(searchScreen.verifySearchCountNotNull(searchData),"search count equal to "+searchScreen.getSearchCount());
    }

    /**
     * @URL https://tradeling.atlassian.net/browse/QTM-5095
     * @throws InterruptedException
     */
    @Test(description = "Verify that the user can search for specific seller")
    public void QTM5095()throws InterruptedException{
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH,Constants.REGION_UAE);
        loginScreen.enterUserAndPass(username,password);
        //test scenario needs unique ids to be completed
    }

    /**
     * @URL https://tradeling.atlassian.net/browse/QTM-5243
     * @throws InterruptedException
     */
    @Test(description = "Verify that the user can get the recent search in history")
    public void QTM5243()throws InterruptedException {
        launchScreen.selectLanguageAndRegion(Constants.LANG_ENGLISH,Constants.REGION_UAE);
        loginScreen.loginAsGuest();
//        searchScreen.search(searchDataPen);
        searchScreen.navigationBar(appSection.ACCOUNT);
        searchScreen.navigationBar(appSection.Cart);
        searchScreen.navigationBar(appSection.DEALS);
    }
}
