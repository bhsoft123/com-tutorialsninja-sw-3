package myaccounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class MyAccountsTest extends Utility {

    String baseUrl =  "http://tutorialsninja.com/demo/index.php?";
    public void selectMyAccountOptions(String option){
        List<WebElement> topmenuNames = driver.findElements(By.cssSelector("#top-links a"));
        for (WebElement names : topmenuNames) {
            //System.out.println(names.getText());
            if (names.getText().equalsIgnoreCase(option)) {
                names.click();
                break;
            }
    }}

@Before
    public void setUp(){
        openBrowser(baseUrl);
    }

@Test
public void verifyUserShouldNavigateToRegisterPageSuccessfully(){
    //1.1 Click on My Account Link.
    clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
    //1.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
    selectMyAccountOptions("Register");
    //1.3 Verify the text “Register Account”
    Assert.assertEquals("Register Account", "//h1[normalize-space()='Register Account']");
}
@Test
public void verifyUserShouldNavigateToLoginPageSuccessfully() {
    //2.1 Click on My Account Link.
    clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
    //2.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
    selectMyAccountOptions("Login");
    //2.3 Verify the text “Returning Customer”
    Assert.assertEquals("Returning Customer", getTextFromElement(By.xpath("//h2[normalize-space()='Returning Customer']")));
}
@Test
public void verifyThatUserRegisterAccountSuccessfully() {

    // 3.1 Click on My Account Link.
    clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
    // 3.2 Call the method “selectMyAccountOptions” method and pass the parameter “Register”
    selectMyAccountOptions("Register");
    // 3.3 Enter First Name
    sendTextToElement(By.xpath("//input[@id='input-firstname']"), "Orange");
    // 3.4 Enter Last Name
    sendTextToElement(By.xpath("//input[@id='input-lastname']"), "Purple");
    // 3.5 Enter Email
    sendTextToElement(By.xpath("//input[@id='input-email']"), "orangepurple@gmail.com");
    // 3.6 Enter Telephone
    sendTextToElement(By.xpath("//input[@id='input-telephone']"), "07777777777");
    // 3.7 Enter Password
    sendTextToElement(By.xpath("//input[@id='input-password']"), "Orange@purple");
    // 3.8 Enter Password Confirm
    sendTextToElement(By.xpath("//input[@id='input-confirm']"), "Orange@purple");
    // 3.9 Select Subscribe Yes radio button
    clickOnElement(By.xpath("//label[normalize-space()='Yes']//input[@name='newsletter']"));
    // 3.10 Click on Privacy Policy check box
    clickOnElement(By.xpath("//input[@name='agree']"));
    // 3.11 Click on Continue button
    clickOnElement(By.xpath("//input[@value='Continue']"));
    // 3.12 Verify the message “Your Account Has Been Created!”
    Assert.assertEquals("Your Account Has Been Created!", getTextFromElement(By.xpath("//h1[normalize-space()='Your Account Has Been Created!']")));
    // 3.13 Click on Continue button
    clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
    // 3.14 Click on My Account Link.
    clickOnElement(By.xpath("//a[@class='list-group-item'][normalize-space()='My Account']"));
    // 3.15 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
    selectMyAccountOptions("Logout");
    // 3.16 Verify the text “Account Logout”
    Assert.assertEquals("Account Logout", getTextFromElement(By.xpath("//h1[normalize-space()='Account Logout']")));
    // 3.17 Click on Continue button
    clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
}

    public void verifyThatUserShouldLoginAndLogoutSuccessfully() {
        //4.1 Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        //4.2 Call the method “selectMyAccountOptions” method and pass the parameter “Login”
        selectMyAccountOptions("Login");
        //4.3 Enter Email address
        sendTextToElement(By.xpath("//input[@id='input-email']"), "prim1234@gmail.com");
        //4.4 Enter Password
        sendTextToElement(By.xpath("//input[@id='input-password']']"), "Snow123");
        //4.5 Click on Login button
        clickOnElement(By.xpath("//input[@value='Login']"));
        //4.6 Verify text “My Account”
        Assert.assertEquals("My Account", getTextFromElement(By.xpath("//h2[normalize-space()='My Account']")));
        //4.7 Click on My Account Link.
        clickOnElement(By.xpath("//span[normalize-space()='My Account']"));
        //4.8 Call the method “selectMyAccountOptions” method and pass the parameter “Logout”
        selectMyAccountOptions("Logout");
        //4.9 Verify the text “Account Logout”
        Assert.assertEquals("Account Logout", getTextFromElement(By.xpath("//h1[normalize-space()='Account Logout']")));
        //4.10 Click on Continue button
        clickOnElement(By.xpath("//a[@class='btn btn-primary']"));
    }
    @After
    public void tearDown() {
        closeBrowser();
    }
}