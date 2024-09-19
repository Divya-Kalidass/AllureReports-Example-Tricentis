package demo.web.shop;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static demo.web.shop.BrowserInitiation.driver;
import static demo.web.shop.FunctionUtility.extentTest;

public class DigitalDownloads {

    static WebElement digital_download_element;
    static WebElement add_to_cart_1;
    static String notification_1_value;
    static WebElement add_to_cart_2;
    static WebElement notification_1;
    static WebElement shopping_cart_link;
    static WebElement terms_and_conditions;
    static WebElement checkout_btn;
    static Actions actions = new Actions(driver);
    static WebDriverWait wait= FunctionUtility.waitMehtod();;
    public static void digitalDownloads(){
        try {
            digital_download_element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[1]/div[2]/ul[1]/li[5]/a")));
            actions.moveToElement(digital_download_element).perform();
            actions.clickAndHold(digital_download_element).perform();
            digital_download_element.click();
            extentTest.log(Status.PASS, "Digital download button from menu clicked successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot click digital from menu, error occurred: "+e);
        }
    }

    public static void addToCard(){

        try {
            add_to_cart_1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/div[2]/div[2]/div[3]/div[2]/div/div[2]/div[3]/div[2]/input")));
            add_to_cart_1.click();
            extentTest.log(Status.PASS,"Add to cart btn clicked successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot click add to cart button, error occurred: "+e);
        }

        try {
            notification_1 = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"bar-notification\"]/p")));
            notification_1_value = notification_1.getText();
            if(notification_1_value.equals("The product has been added to your shopping cart")){
                System.out.println("Notification validated");
                extentTest.log(Status.PASS,"Notification validated successfully - notification matched");
            }
            else {
                extentTest.log(Status.FAIL,"Notification validated successfully - notification mismatched");
            }

        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot validate notification, error occurred: "+e);
        }

        try {
            shopping_cart_link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"bar-notification\"]/p/a")));
            shopping_cart_link.click();
            extentTest.log(Status.PASS,"Moved to cart page successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot move to cart, error occurred: "+e);
        }

        try {
            terms_and_conditions = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"termsofservice\"]")));
            terms_and_conditions.click();
            extentTest.log(Status.PASS,"Terms and conditions checked successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot check terms and conditions, error occurred: "+e);
        }

        try {
            checkout_btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"checkout\"]")));
            actions.moveToElement(checkout_btn).perform();
            checkout_btn.click();
            extentTest.log(Status.PASS,"Check out button on cart clicked successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot click check out button on cart page, error occurred: "+e);
        }


    }
}
