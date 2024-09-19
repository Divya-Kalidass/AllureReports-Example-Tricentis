package demo.web.shop;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestRunner {

    @BeforeTest
    public void setup(){
        FunctionUtility.reportGeneration();
        FunctionUtility.configureReport();
        BrowserInitiation.launchBrowser("chrome");
        BrowserInitiation.launchUrl("https://demowebshop.tricentis.com/");

    }
    @Test(priority = 1)
    public void User_Register_With_Valid_Credentials(){
        Registration.register();
        BrowserInitiation.quitMethod();
    }
    @Test(dataProviderClass = FunctionUtility.class, dataProvider = "logindata",priority = 2)
    public void Login_With_Valid_Credentials(String emailid, String password){
        Login.loginMethod(emailid,password);
    }

    @Test(priority = 3)
    public void Select_Music_Album_From_Digital_Downloads(){
        DigitalDownloads.digitalDownloads();
        DigitalDownloads.addToCard();

    }
    @Test(priority = 4)
    public void Enter_Valid_Checkout_Details(){
        CheckoutDetails.billingAddress();
        CheckoutDetails.paymentMethod();
        CheckoutDetails.paymentInformation();
        CheckoutDetails.productDetails();
        CheckoutDetails.confirmOrder();
//        System.out.println("Billing address entered successfully");
    }

    @AfterTest
    public void teardown(){
        FunctionUtility.flushMethod();
        BrowserInitiation.quitMethod();
    }

}
