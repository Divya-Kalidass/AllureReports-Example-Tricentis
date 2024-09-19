package demo.web.shop;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static demo.web.shop.BrowserInitiation.driver;
import static demo.web.shop.FunctionUtility.extentTest;

public class Registration {
    static  WebDriverWait wait;
    static String gen;
    static String fn;
    static String ln;
    static String password;
    static String emailid;


    static WebElement regpagebtn;
    static WebElement gender;
    static WebElement firstname;
    static WebElement lastname;
    static WebElement email;
    static WebElement newpassword;
    static WebElement confirmpassword;
    static  WebElement registerbutton;
    static ArrayList<String> data = new ArrayList<>();
    public static void register() {

        wait = FunctionUtility.waitMehtod();

        try {
            regpagebtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html/body/div[4]/div[1]/div[1]/div[2]/div[1]/ul/li[1]/a")));
            Actions action = new Actions(driver);
            action.moveToElement(regpagebtn);
            regpagebtn.click();
            extentTest.log(Status.PASS, "Register Button clicked successfully");
        } catch (Exception exception) {
            extentTest.log(Status.FAIL, "Error occurred while clicking register button: " + exception);
        }

        try {
            if (driver.getCurrentUrl().equals("https://demowebshop.tricentis.com/register")) {
                extentTest.log(Status.PASS, "URL validated successfully successfully "+driver.getCurrentUrl());
            }

        } catch (Exception e){

            extentTest.log(Status.FAIL,"URL mismatched "+driver.getCurrentUrl());

        }
        try {
            gender = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gender-female")));
            gender.click();
            gen = "Female";
            extentTest.log(Status.PASS, "Gender radio button clicked successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL, "Error Occurred while clicking gender radio button: "+e);
        }

        try {
            fn = "Divya";
            firstname= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("FirstName")));
            firstname.sendKeys(fn);
            extentTest.log(Status.PASS,"Value in firstname entered successfully");
        }
        catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot enter value in firstname, error occurred: "+e);
        }

        try {
            ln = "Kalidass";
            lastname= wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("LastName")));
            lastname.sendKeys(ln);
            extentTest.log(Status.PASS,"Value in lastname entered successfully");
        }
        catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot enter value in lastname, error occurred: "+e);
        }

        try {
            emailid = "divya@bahwancybertek.com";
            email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
            email.sendKeys(emailid);
            extentTest.log(Status.PASS,"Value in email entered successfully");
        }
        catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot enter value in email, error occurred: "+e);
        }

        try {
            password = "divya.k@0612";
            newpassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password")));
            newpassword.sendKeys(password);
            extentTest.log(Status.PASS,"Value in password entered successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot enter value in password, error occurred: "+e);
        }

        try {
            confirmpassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ConfirmPassword")));
            confirmpassword.sendKeys(password);
            extentTest.log(Status.PASS,"Value in confirm password entered successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot enter value in confirm password, error occurred: "+e);
        }

        try {
            registerbutton = wait.until(ExpectedConditions.elementToBeClickable(By.id("register-button")));
            registerbutton.click();
            extentTest.log(Status.PASS,"Register button clicked successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot click register button, error occurred: "+e);
        }

        try{
            data.add(gen);
            data.add(fn);
            data.add(ln);
            data.add(emailid);
            data.add(password);
            FunctionUtility.reg_data_insertion(data);

        }catch (Exception e){
            System.out.println("user details cannot enter in excel sheet, error occurred,"+e);
        }
    }
}
