package demo.web.shop;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static demo.web.shop.FunctionUtility.extentTest;

public class OrderConfirmation {

    static WebElement confirmaiton_msg;
    static WebDriverWait wait=FunctionUtility.waitMehtod();
    public static void orderConfirmation(){

        try {
            confirmaiton_msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[4]/div[1]/div[4]/div/div/div[2]/div/div[1]/strong")));
            if(confirmaiton_msg.getText().equals("Your order has been successfully processed!")){
                extentTest.log(Status.PASS,"Order confirmation message validated successfully- message matched");
                System.out.println("Order confirmation message validated successfully - message matched");
            }
            else {
                extentTest.log(Status.FAIL,"Order confirmation message validated successfully - message mismatched");
            }
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot validate order confirmation message, error occurred"+ e);
        }

    }
}
