package utilities;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utility extends BaseTest {

    //----------Find Element Methods-------------
    public void clickOnElement(By by){
        driver.findElement(by).click();
    }
    public void sendTextToElement(By by, String text){
        driver.findElement(by).sendKeys(text);
    }
    public String getTextFromElement(By by){
        return driver.findElement(by).getText();
    }
    public String getAttributeValueFromElement(By by, String attribute){
        return driver.findElement(by).getAttribute(attribute);
    }
    public void clearTextFromElement(By by){
        driver.findElement(by).clear();
    }
    //---------------Select Methods---------------
    public void selectByVisibleTextFromDropDown(By by,String text){
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        select.selectByVisibleText(text);
    }
    public void selectByValueFromDropDown(By by,String value){
        Select select = new Select(driver.findElement(by));
        select.selectByValue(value);
    }
    public void selectByIndexFromDropDown(By by, int index) {
        Select select = new Select(driver.findElement(by));
        select.selectByIndex(index);
    }
    //---------------Action Methods------------------
    public void mouseHoverOnElement(By by){
        WebElement action = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(action).build().perform();
    }
    public void mouseHoverOnElementAndClick(By by){
        WebElement action = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(action).click().build().perform();
    }
    //----------------Verify Methods----------------
    public void verifyText(By by, String expectedText){
        String actualText = getTextFromElement(by);
        Assert.assertEquals("Error", expectedText,actualText);
        System.out.println("verified the text-ok");
    }
    //--------------Alert Methods-------------------
}
