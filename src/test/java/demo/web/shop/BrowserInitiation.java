package demo.web.shop;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static demo.web.shop.FunctionUtility.extentTest;


public class BrowserInitiation {
    static WebDriver driver;
    public static void launchBrowser(String browser_name){
        try {
            if(browser_name.equals("chrome")){

                driver = new ChromeDriver();
            } else if (browser_name.equals("edge")) {
                driver = new EdgeDriver();
            }
            else {
                System.setProperty("webdriver.gecko.driver","./drivers/geckodriver.exe");
                driver =new FirefoxDriver();
            }
            extentTest.log(Status.PASS,"Browser launched successfully");
        }catch (Exception exception){
            extentTest.log(Status.FAIL, "Error occurred while launching browser: "+exception);
        }

    }

    public static void launchUrl(String url){
        driver.manage().window().maximize();
        try {
            driver.get(url);
            extentTest.log(Status.PASS, "URL launched successfully");
        }
        catch (Exception exception){
            extentTest.log(Status.FAIL, "Error occurred while launching URL: "+exception+". The given URL is: "+url);
        }


    }
    public static void quitMethod(){
        driver.quit();
    }


}
