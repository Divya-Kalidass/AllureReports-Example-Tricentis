package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.example.BrowserFunction.driver;

public class DemoWebShopTests {


    @BeforeClass
    public void setUpClass() {
        BrowserFunction.initiateBrowser("chrome");
        BrowserFunction.launchURL("https://demowebshop.tricentis.com/");
    }


    @Test(priority = 1)
    public void Validate_Homepage_Title() {
        try {
            String title = driver.getTitle();
            Assert.assertEquals(title, "Demo Web Shop");
        }catch (Exception e){
            System.out.println("Error occured "+ e);
        }
    }

    @Test(priority = 2)
    private void Test_Scroll_Functionality() {
        try{

            Actions actions = new Actions(driver);
            actions.scrollToElement(driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[2]/h3")));

        }catch (Exception e){
            System.out.println("Error occured "+ e);
        }

    }

    @Test(priority = 3)
    public void Test_Search_Functionality_Of_Tricentis() {
        try {
            WebElement searchBox = driver.findElement(By.id("small-searchterms"));
            searchBox.sendKeys("computer");
            searchBox.submit();
//            Assert.assertTrue(driver.getTitle().contains("Search"));
//        WebElement results = driver.findElement(By.className("product-item"));
//        Assert.assertTrue(results.isDisplayed());
        }catch (Exception e){
            System.out.println("Error occured "+ e);
        }
    }



    @Test(priority = 4)
    public void Test_Login_Button() {
        try{
        driver.findElement(By.linkText("Log in")).click();
        if(driver.getTitle().equals("Demo Web Shop. Login")){
            System.out.println("Login Test passed");
        }
        else {
            System.out.println("Login Test failed");
        }

        }catch (Exception e){
            System.out.println("Error occured "+ e);
        }
    }

    @Test(priority = 5)
    public void Test_Book_Link_From_Menu() {
        try {
            driver.findElement(By.linkText("Books")).click();
            String url = driver.getCurrentUrl();
            Assert.assertEquals(url, "https://demowebshop.tricentis.com/books");

        }catch (Exception e){
        System.out.println("Error occured "+ e);
    }
    }

    @Test(priority = 6)
    public void Test_Electronics_From_Side_Panel() {
        try{
            WebElement element= driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[1]/div[1]/div[2]/ul/li[3]/a"));
            element.click();
        } catch (Exception e){

        }
    }

    @Test(priority = 7)
    public void Test_Wishlist_Functionality() {
        try{
        driver.findElement(By.linkText("Books")).click();
        driver.findElement(By.linkText("Fiction")).click();
        driver.findElement(By.xpath("//input[@value='Add to wishlist']")).click();
        WebElement wishlistStatus = driver.findElement(By.className("wishlist-qty"));
        Assert.assertTrue(wishlistStatus.getText().contains("(1)"));
        }catch (Exception e){
            System.out.println("Error occured "+ e);
        }
    }

    @Test(priority = 8)
    public void Test_Contact_Us_Form() {
        try{
        driver.findElement(By.linkText("Contact us")).click();
        driver.findElement(By.id("FullName")).sendKeys("John Doe");
        driver.findElement(By.id("Email")).sendKeys("johndoe@example.com");
        driver.findElement(By.id("Enquiry")).sendKeys("This is a test enquiry.");
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        WebElement confirmation = driver.findElement(By.className("result"));
        Assert.assertEquals(confirmation.getText(), "Your enquiry has been successfully sent to the store owner.");
        }catch (Exception e){
            System.out.println("Error occured "+ e);
        }
    }

    @Test(priority = 9)
    public void Test_Logout() {
        try{
        driver.findElement(By.linkText("Log out")).click();
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        Assert.assertTrue(loginLink.isDisplayed());
        }catch (Exception e){
            System.out.println("Error occured "+ e);
        }
    }

    @Test(priority = 10)
    public void Test_Social_Media_Links() {
        try {
            WebElement facebook = driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/div[4]/ul/li[1]/a"));
            Assert.assertTrue(facebook.isDisplayed());
        }catch (Exception e){
            System.out.println("Error occured "+ e);
        }
    }


    @AfterClass
    public void tearDownClass() {
        driver.quit();
    }
}
