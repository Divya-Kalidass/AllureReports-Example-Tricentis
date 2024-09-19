package demo.web.shop;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static demo.web.shop.BrowserInitiation.driver;
import static demo.web.shop.FunctionUtility.extentTest;

public class Login {
    static WebDriverWait wait = FunctionUtility.waitMehtod();
    static WebElement email;
    static WebElement password;
    static WebElement login_button;
    static WebElement login_link_btn;
    public static void loginMethod(String emailvalue, String passwordvalue){

        BrowserInitiation.launchBrowser("chrome");
        BrowserInitiation.launchUrl("https://demowebshop.tricentis.com/");

        try {
            login_link_btn = driver.findElement(By.xpath("html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a"));
            login_link_btn.click();                                                                     ///html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a
            extentTest.log(Status.PASS,"Login button clicked successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot click login button, error occurred: "+e);
        }


        try {
            wait = FunctionUtility.waitMehtod();
            email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
            email.sendKeys(emailvalue);
            extentTest.log(Status.PASS,"Value entered in email successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot enter value in email, error occurred: "+e);
        }

        try {
            password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password")));
            password.sendKeys(passwordvalue);
            extentTest.log(Status.PASS,"Value entered in password successfully");

        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot enter value in password, error occurred: "+e);
        }

        try {
            login_button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//html/body/div[4]/div[1]/div[4]/div[2]/div/div[2]/div[1]/div[2]/div[2]/form/div[5]/input")));
            login_button.click();
            extentTest.log(Status.PASS, "Logged in successfully");

        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot login, error occurred: "+e);
        }

//        if(driver.getCurrentUrl().equals("https://demowebshop.tricentis.com/")){
//            System.out.println("login successful");
//        }
//        else {
//            System.out.println("cannot login");
//        }
    }
}
