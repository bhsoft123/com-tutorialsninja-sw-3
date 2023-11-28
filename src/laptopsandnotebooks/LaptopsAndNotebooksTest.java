package laptopsandnotebooks;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LaptopsAndNotebooksTest extends Utility {

    String baseUrl =  "http://tutorialsninja.com/demo/index.php?";

@Before
public void setUp(){
    openBrowser(baseUrl);
}
@Test
public void verifyProductsPriceDisplayHighToLowSuccessfully(){
    //1.1 Mouse hover on Laptops & Notebooks Tab.and click
    mouseHoverOnElementAndClick(By.linkText("Laptops & Notebooks"));
    //1.2 Click on “Show All Laptops & Notebooks”
    clickOnElement(By.linkText("Show AllLaptops & Notebooks"));
    //1.3 Select Sort By "Price (High > Low)"
    mouseHoverOnElementAndClick(By.id("input-sort"));
    mouseHoverOnElementAndClick(By.xpath(""));
    //1.4 Verify the Product price will arrange in High to Low order
    //Get all products price and store into array list
    List<WebElement> products = driver.findElements(By.xpath("//p[@class ='price']"));
    ArrayList<Double> originalProductsPrice = new ArrayList<>();
    for(WebElement e : products) {
        System.out.println(e.getText());
        String[] arr = e.getText().split("Ex Tax");
        originalProductsPrice.add(Double.valueOf(arr[0].substring(1).replaceAll(",", ",")));
    }
    System.out.println(originalProductsPrice);
    // Sort By Reverse order
    Collections.sort(originalProductsPrice, Collections.reverseOrder());
    System.out.println(originalProductsPrice);
    //Select sort by Price(High>Low)
    selectByVisibleTextFromDropDown(By.id("input-sort"),"Price(Low > High)");
    //After filter Price (High>Low)Get all the products name and stored into array list
    products = driver.findElements(By.xpath("//p[@class ='price']"));
    ArrayList<Double> afterSortByPrice = new ArrayList<>();
    for(WebElement e : products){
        String [] arr = e.getText().split("Ex Tax:");
        afterSortByPrice.add(Double.valueOf(arr[0].substring(1).replaceAll(",",",")));
    }
    System.out.println(afterSortByPrice);
    Assert.assertEquals("Product not sorted by price High to Low",originalProductsPrice, afterSortByPrice);
    }
@Test
    public void verifyThatUserPlaceOrderSuccessfully(){
    //get the list of currency menu
    clickOnElement(By.xpath("//span[contains(text(),'Currency')]"));
    List<WebElement> currencyList = driver.findElements(By.xpath("//ul[@class = 'dropdown-menu']/li"));
    for(WebElement e : currencyList){
        if(e.getText().equalsIgnoreCase("£ Pound Sterling")){
            e.click();
            break;
        }
    }
    //2.1 Mouse hover on Laptops & Notebooks Tab and click
    mouseHoverOnElementAndClick(By.linkText("Laptops & Notebooks"));
    //2.2 Click on “Show All Laptops & Notebooks”
    mouseHoverOnElementAndClick(By.linkText("//a[normalize-space()='Show AllLaptops & Notebooks']"));
    //2.3 Select Sort By "Price (High > Low)"
    selectByVisibleTextFromDropDown(By.id("input-sort"), "Price (High > Low)");
    //2.4 Select Product “MacBook”
    clickOnElement(By.linkText("MacBook"));
    //2.5 Verify the text “MacBook”
    Assert.assertEquals("Error", "MacBook",getTextFromElement(By.xpath("//h1[contains(text(),'MacBook')]")));
    //2.6 Click on ‘Add To Cart’ button
    clickOnElement(By.xpath("//button[@id='button-cart']"));
    //2.7 Verify the message “Success: You have added MacBook to your shopping cart!”
    String actualText = getTextFromElement(By.cssSelector("body:nth-child(2) div.container:nth-child(4) > div.alert.alert-success.alert-dismissible"));
    Assert.assertEquals("Error",actualText,"Success: You have added MacBook to your shopping cart!");
    //2.8 Click on link “shopping cart” display into success message
    clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
    //2.9 Verify the text "Shopping Cart"
    Assert.assertEquals("Error",getTextFromElement(By.xpath("//div[@id='content']//h1")).contains("Shopping Cart"));
    //2.10 Verify the Product name "MacBook"
    Assert.assertEquals("Product name not matched", "MacBook", getTextFromElement(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[2]/a")));
    //2.11 Change Quantity "2"
    sendTextToElement(By.xpath("//input[contains(@name, 'quantity')]"), "2");
    //2.12 Click on “Update” Tab
    clickOnElement(By.xpath("//button[contains(@data-original-title, 'Update')]"));
    //2.13 Verify the message “Success: You have modified your shopping cart!”
    Assert.assertEquals("Error", getTextFromElement(By.xpath("//div[@id='checkout-cart']/div[1]")).contains("Success: You have modified your shopping cart!"));
    //2.14 Verify the Total £737.45
    Assert.assertEquals("Total not matched", "£737.45", getTextFromElement(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[6]")));
    //2.15 Click on “Checkout” button
    clickOnElement(By.xpath("//a[contains(text(),'Checkout')]"));
    //2.16 Verify the text “Checkout”
    verifyText(By.xpath("//h1[contains(text(),'Checkout')]"),"Checkout");
    //2.17 Verify the Text “New Customer”
    verifyText(By.xpath("//h2[contains(text(),'New Customer')]"),"New Customer");
    //2.18 Click on “Guest Checkout” radio button
    clickOnElement(By.xpath("//body/div[@id='checkout-checkout']/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/label[1]/input[1]"));
    //2.19 Click on “Continue” tab
    //2.20 Fill the mandatory fields
    //2.21 Click on “Continue” Button
    clickOnElement(By.xpath("//input[@id='button-account']"));
    sendTextToElement(By.xpath("//input[@id='input-payment-firstname']"),"Orange");
    sendTextToElement(By.xpath("//input[@id='input-payment-lastname']"),"Purple");
    sendTextToElement(By.xpath("//input[@id='input-payment-email']"),"orangepurple@yahoo.co.in");
    sendTextToElement(By.xpath("//input[@id='input-payment-telephone']"),"02222222222");
    sendTextToElement(By.xpath("//input[@id='input-payment-address-1']"),"15");
    sendTextToElement(By.xpath("//input[@id='input-payment-city']"),"london");
    sendTextToElement(By.xpath("//input[@id='input-payment-postcode']"),"ab1 2cd");
    clickOnElement(By.xpath("//option[contains(text(),'Pakistan')]"));
    clickOnElement(By.xpath("//option[contains(text(),'Punjab')]"));
    clickOnElement(By.xpath("//input[@id='button-guest']"));
    //2.22 Add Comments About your order into text area
    sendTextToElement(By.xpath("//body/div[@id='checkout-checkout']/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/p[2]/textarea[1]"),"I need delivery after 5:00pm");
    //2.23 Check the Terms & Conditions check box
    //Thread.sleep(1000);
    clickOnElement(By.xpath("//body/div[@id='checkout-checkout']/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/input[1]"));
    //2.24 Click on “Continue” button
    clickOnElement(By.xpath("//body/div[@id='checkout-checkout']/div[1]/div[1]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/input[1]"));
    // Success: You have modified your shopping cart!
    clickOnElement(By.xpath("//input[@id='button-payment-method']"));
    //2.25 Verify the message “Warning: Payment method required!”
    verifyText(By.xpath("//div[@class='alert alert-success alert-dismissible']/child::i"),"Warning: Payment method required!");
}
@After
    public void tearDown(){
        closeBrowser();
}
}



