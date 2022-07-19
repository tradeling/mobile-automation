package com.tradeling.mobile.pageObject.mobileBuyerApp;

import com.tradeling.data.buyerApp.appSection;
import com.tradeling.mobile.driver.MobileActions;
import com.tradeling.reporting.Reporting;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchScreen {
    MobileActions actions;

    int searchSuggestion = 0;
    int searchCount = 0;
    List<String> suggestions;

    public SearchScreen(MobileActions actions){
        this.actions = actions;
        PageFactory.initElements(new AppiumFieldDecorator(actions.getDriver()),this);
    }

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='company-logo']/following-sibling::android.view.ViewGroup/android.widget.EditText")

    MobileElement searchBlock;

    @AndroidFindBy(xpath = "//android.widget.EditText")
    MobileElement  nestedSearchBlock;

    @AndroidFindBy(xpath = "//android.widget.Switch/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup//android.widget.TextView")
    List<MobileElement> searchResults;

    @AndroidFindBy(xpath = "//android.widget.Switch/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup")
    List<MobileElement> validSearchResult;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='search-top-sellers-section']/parent::android.view.ViewGroup/child::*[1]/android.view.ViewGroup//android.widget.TextView")
    List<MobileElement> searchSuggestions;

    @AndroidFindBy(id = "Categories_tab_bar")
    MobileElement categories;

    @AndroidFindBy(id = "Home_tab_bar")
    MobileElement home;

    @AndroidFindBy(id = "Deals_tab_bar")
    MobileElement deals;

    @AndroidFindBy(id = "MyCart_tab_bar")
    MobileElement cart;

    @AndroidFindBy(id = "MyAccount_tab_bar")
    MobileElement acccount;


    String dynamicSearchCount(String search){
        String Locator = "xpath-android://*[@class='android.widget.ScrollView']/preceding-sibling::android.view.ViewGroup//*[@text='"+search+"']/following-sibling::android.widget.TextView";
        return Locator;
    }

    public void search(String searchData)throws InterruptedException{
        actions.click(searchBlock);
        actions.click(nestedSearchBlock);
        actions.enterText(nestedSearchBlock,searchData);
        actions.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        Reporting.getLogger().logInfo("user attempt to search on "+searchData);
    }

    public void searchAllSellers(String searchData)throws InterruptedException{
        actions.click(searchBlock);
        actions.click(nestedSearchBlock);
        actions.enterText(nestedSearchBlock,searchData);
    }

    public void navigationBar(appSection section){
        switch (section){
            case Cart:
                actions.click(cart);
            case HOME:
                actions.click(home);
            case DEALS:
                actions.click(deals);
            case ACCOUNT:
                actions.click(acccount);
            case CATEGORIES:
                actions.click(categories);
        }
    }


    public void accessSearchSuggestion(){
        actions.click(searchBlock);
        searchSuggestion = searchSuggestions.size();
    }

    List<String> fetchSuggestions(){
        for (MobileElement suggestion :
                searchSuggestions) {
            suggestions.add(suggestion.getText());
        }
        return suggestions;
    }

    public Boolean verifySearchSuggestion(String Suggestion){
        fetchSuggestions();
        if (suggestions.contains(Suggestion)){
            return true;
        }else {
            return false;
        }
    }

    public int getSearchCount(){
        return this.searchCount;
    }

    public Boolean verifySearchCountNotNull(String search)throws InterruptedException{
        Pattern onlyDigits = Pattern.compile("\\d+");
        Matcher text = onlyDigits.matcher(actions.Element(dynamicSearchCount(search)).getText());
        int results = Integer.valueOf(text.group());
        this.searchCount = results;
        if (results !=0)
            return true;
        else
            return false;
    }

    public boolean verifySearchCountDisplayed(String search)throws InterruptedException{
        MobileElement testElm= actions.getLocator(dynamicSearchCount(search));
        if (testElm.isDisplayed())
            return true;
        else
            return false;
    }

    public int getSearchSuggestion(){
        return searchSuggestion;
    }

    public boolean verifySearchResults(String searchData){
        actions.waitForElementToDisplay(searchBlock);
        Boolean flag = false;
        int found=1;
        for (MobileElement searchResult:searchResults
             ) {
            System.out.println(searchResult.getText());
            if (searchResult.getText().contains(searchData)){
                found++;
            }
        }
        if(validSearchResult.size()==found){
            flag=true;
        }else{
            flag=false;
        }
        return flag;
    }
}

