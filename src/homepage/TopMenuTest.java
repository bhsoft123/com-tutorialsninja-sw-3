package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.List;

public class TopMenuTest extends Utility {

    String baseUrl =  "http://tutorialsninja.com/demo/index.php?";

    //1.1 create method with name "selectMenu" it has one parameter name "menu" of type string
    public void selectMenu(String menu) {
        List<WebElement> topMenuList  = driver.findElements(By.xpath("//nav[@id='menu']//ul/li[contains(@class, 'open')]/div/child::*"));
        for(WebElement element : topMenuList){
            System.out.println(element.getText());
        }
        }
    @Before
    public void setUp(){
        openBrowser(baseUrl);
    }
    @Test
    public void verifyUserShouldNavigateToDesktopsPageSuccessfully(){
        //1.1 Mouse hover on “Desktops” Tab and click
        mouseHoverOnElementAndClick(By.xpath("//a[normalize-space()='Desktops']"));
        //1.2 call selectMenu method and pass the menu = “Show All Desktops”
        selectMenu("Show All Desktops");
        //1.3 Verify the text ‘Desktops’
        verifyText(By.xpath("//a[normalize-space()='Desktops']"),"Desktops");
    }
    @Test
    public void erifyUserShouldNavigateToLaptopsAndNotebooksPageSuccessfully(){
        //2.1 Mouse hover on “Laptops & Notebooks” Tab and click
        mouseHoverOnElementAndClick(By.xpath("//a[contains(.,'Laptops & Notebooks')]"));
        //2.2 call selectMenu method and pass the menu = “Show All Laptops & Notebooks”
        selectMenu("Show All Laptops & Notebooks");
        //2.3 Verify the text ‘Laptops & Notebooks
        verifyText(By.xpath("//a[contains(.,'Laptops & Notebooks')]"),"Laptops & Notebooks");
    }
    @Test
    public void verifyUserShouldNavigateToComponentsPageSuccessfully(){
        //3.1 Mouse hover on “Components” Tab and click
        mouseHoverOnElementAndClick(By.xpath("//a[starts-with(text(),'Components')]"));
        //3.2 call selectMenu method and pass the menu = “Show All Components”
        selectMenu("Show All Components");
        //3.3 Verify the text ‘Components
        verifyText(By.xpath("//a[text()='Components']"),"Components");
    }
    @After
    public void tearDown(){
        closeBrowser();
    }
}






