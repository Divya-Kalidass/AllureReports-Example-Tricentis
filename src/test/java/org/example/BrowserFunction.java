package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserFunction {
    static WebDriver driver;

    public static void initiateBrowser(String drivername){

        if (drivername.equals("chrome")){
            driver = new ChromeDriver();
            // driver.get("https://sampleapp.tricentis.com/101/app.php#");
            // System.out.println("browser initiation");
        }
        else {
            driver = new EdgeDriver();
            //driver.get("https://sampleapp.tricentis.com/101/app.php#");
            //System.out.println("browser initiation");
        }
    }

    public static void launchURL(String url){
        driver.get(url);
        //System.out.println("launch chrome");
    }
}
