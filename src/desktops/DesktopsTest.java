package desktops;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.List;

public class DesktopsTest extends Utility {

    String baseUrl =  "http://tutorialsninja.com/demo/index.php?";

@Before
    public void setUp(){
        openBrowser(baseUrl);
}
@Test
    public void verifyProductArrangedInAlphaBaticalOrder(){
        //1.1 Mouse hover on Desktops Tab.and click
        mouseHoverOnElementAndClick(By.xpath("//a[normalize-space()='Desktops']"));
        //1.2 Click on “Show All Desktops”
        mouseHoverOnElementAndClick(By.xpath("//a[contains(text(),'Show AllDesktops')]"));
        //1.3 Select Sort By position "Name: Z to A"
        // Get all the products name and stored into array list
        //List<WebElement> orginalProdList = driver.findElements(By.xpath("//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']"));
        List<WebElement> orginalProdList = driver.findElements(By.xpath("//h4/a"));
        ArrayList<String> orinalProd =new ArrayList<>();
        for(WebElement e : orginalProdList) {
            orinalProd.add(e.getText());
        }
        System.out.println(orinalProd);
        //select and display Sort By menu
        mouseHoverOnElementAndClick(By.id("input-sort"));
        selectByVisibleTextFromDropDown(By.id("input-sort"),"Name (Z - A)");
        List<WebElement> sortByList = driver.findElements(By.xpath("//div[@class='product-layout product-grid col-lg-4 col-md-4 col-sm-6 col-xs-12']"));
        ArrayList<String> originallist = new ArrayList<>();
        for( WebElement list : sortByList){
            System.out.println(list.getText());
            originallist.add(list.getText());
        }
        System.out.println(originallist);

        //1.4 Verify the Product will arrange in Descending order
        // After filter Z -A Get all the products name and stored into array list
        List<WebElement> sortedproducts = driver.findElements(By.xpath("//h4/a"));
        ArrayList<String> sortedprod =new ArrayList<>();
        for (WebElement e : sortedproducts){
            sortedprod.add(e.getText());}
        System.out.println(sortedprod);
        Assert.assertEquals("Not in order", orinalProd,sortedprod);
    }
@Test
    public void verifyProductAddedToShoppingCartSuccessFully(){
        //2.1 Mouse hover on Currency Dropdown and click
        mouseHoverOnElementAndClick(By.xpath("//span[normalize-space()='Currency']"));
        //2.2 Mouse hover on £Pound Sterling and click
        mouseHoverOnElementAndClick(By.xpath("//button[normalize-space()='£Pound Sterling']"));
        //2.3 Mouse hover on Desktops Tab
        mouseHoverOnElement(By.xpath("//a[normalize-space()='Desktops']"));
        //2.4 Click on “Show All Desktops”
        mouseHoverOnElementAndClick(By.xpath("//a[normalize-space()='Show AllDesktops']"));
        //2.5 Select Sort By position "Name: A to Z"
        selectByVisibleTextFromDropDown(By.id("input-sort"),"Name (A - Z)");
        //2.6 Select product “HP LP3065”
        mouseHoverOnElementAndClick(By.xpath("//a[normalize-space()='HP LP3065']"));
        //2.7 Verify the Text "HP LP3065"
        verifyText(By.xpath("//h1[normalize-space()='HP LP3065']"),"HP LP3065");
        //2.8 Select Delivery Date "2023-11-27"
        mouseHoverOnElementAndClick(By.xpath("//i[@class='fa fa-calendar']"));
        String year = "2023";
        String month = "November";
        String date = "27";
        clickOnElement(By.xpath("//div[@class = 'input-group date']//button"));
        while(true){
            String monthAndYear = driver.findElement(By.xpath("//div[@class ='datepicker']/div[1]//th[@class='picker-switch']")).getText();
            String[]arr = monthAndYear.split(" ");
            String mon = arr[0];
            String yer = arr[1];
            if(mon.equalsIgnoreCase(month) && yer.equalsIgnoreCase(year)){
                break;
            } else {
                clickOnElement(By.xpath("//div[@class='datepicker-days']//th[@class='next']"));
            }
        }
        List<WebElement> allDates = driver.findElements(By.xpath("//div[@class = 'datepicker']/div[1]//tbody/tr/td[@class = 'day']"));
        for (WebElement e : allDates){
            if(e.getText().equalsIgnoreCase(date)){
                e.click();
                break;
            }
        }
        //2.9.Enter Qty "1” using Select class.
        sendTextToElement(By.name("quantity"),"1");
        //2.10 Click on “Add to Cart” button
        clickOnElement(By.xpath("//button[@id='button-cart']"));
        //2.11 Verify the Message “Success: You have added HP LP3065 to your shopping cart!
        //verifyText(By.cssSelector("body:nth-child(2) div.container:nth-child(4) > div.alert.alert-success.alert-dismissible),"\Success: You have added HP LP3065 to your shopping cart!");
        Assert.assertTrue("Product not added to cart",
            getTextFromElement(By.cssSelector("body:nth-child(2) div.container:nth-child(4) > div.alert.alert-success.alert-dismissible"))
                    .contains("Success: You have added HP LP3065 to your shopping cart!"));

        /* another way
        Assert.assertTrue("Product not added to cart",
        getTextFromElement(By.cssSelector("body:nth-child(2) div.container:nth-child(4) > div.alert.alert-success.alert-dismissible"))
               .contains("Success: You have added HP LP3065 to your shopping cart!"));*/
        //2.12 Click on link “shopping cart” display into success message
        clickOnElement(By.xpath("//a[contains(text(),'shopping cart')]"));
        //2.13 Verify the text "Shopping Cart"
        verifyText(By.xpath("//div[@id='content']//h1"),"Shopping Cart");
        //2.14 Verify the Product name "HP LP3065"
        verifyText(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[2]/a"),"HP LP3065");
        //2.15 Verify the Delivery Date "2023-11-27"
        verifyText(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[2]/small[1]"),"2023-11-27");
        //2.16 Verify the Model "Product21"
        verifyText(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[3]"), "Product21");
        //2.17 Verify the Todat "£74.73"
        verifyText(By.xpath("//div[@class = 'table-responsive']/table/tbody/tr/td[6]"),"£74.73");
}
@After
    public void tearDown(){
    closeBrowser();
    }
}
