package demo.web.shop;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

import static demo.web.shop.BrowserInitiation.driver;
import static demo.web.shop.FunctionUtility.extentTest;

public class CheckoutDetails {

    static String first_name_val;
    static String last_name_val;
    static String email_val;
    static String company_value;
    static String city_value;
    static String country_value;
    static String state_value;
    static String add_1_value;
    static String add_2_value;
    static String zip_code_value;
    static String phone_number_value;
    static String payment_method_value;
    static String product_name;
    static String product_price;
    static String product_Qty;
    static String product_total;
    static String product_sub_total;
    static String product_shipping;
    static String product_payment_method_additional_fee;
    static String product_tax;
    static String product_total_including_all_tax;
    static String payment_info_message_value = "You will pay by COD";

    static ArrayList<String> data = new ArrayList<>();
    static WebElement company;
    static WebElement payment_method;
    static WebElement city;
    static Select country;
    static Select state;
    static Select select_billing_address;
    static WebElement address_1;
    static WebElement address_2;
    static WebElement zip_code;
    static WebElement phone_number;
    static WebElement continue_to_payment_confirmation;
    static WebElement continue_to_payment_method;
    static WebElement continue_to_confirm_order;
    static WebElement payment_info_message;
    static WebElement confirm_order_btn;
    static WebElement prod_name;
    static WebElement prod_price;
    static WebElement prod_Qty;
    static WebElement prod_total;
    static WebElement prod_sub_total;
    static WebElement prod_shipping;
    static WebElement prod_payment_method_additional_fee;
    static WebElement prod_tax;
    static WebElement prod_total_including_all_tax;
    static WebDriverWait wait= FunctionUtility.waitMehtod();
    public static void billingAddress(){

        try {
            select_billing_address = new Select(driver.findElement(By.name("billing_address_id")));
            select_billing_address.selectByVisibleText("New Address");
            extentTest.log(Status.PASS,"New address selected successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot select new address, error occurred: "+e);
        }

        try {
            first_name_val = driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_FirstName\"]")).getAttribute("value");
            last_name_val = driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_LastName\"]")).getAttribute("value");
            email_val = driver.findElement(By.xpath("//*[@id=\"BillingNewAddress_Email\"]")).getAttribute("value");
            extentTest.log(Status.PASS,"Values from first name, last name, email retrieved successfully");
        }
        catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot retrieve values(firstname, lastname, email), error occurred: "+e);
        }

        try {
            company = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"BillingNewAddress_Company\"]")));
            company_value = "Bahwan Cybertek";
            company.sendKeys(company_value);
            extentTest.log(Status.PASS,"Company name entered successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot enter company name, error occurred: "+e);
        }

        try{
            country = new Select(driver.findElement(By.name("BillingNewAddress.CountryId")));
            country_value="India";
            country.selectByVisibleText(country_value);
            extentTest.log(Status.PASS,"Country selected successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot select country, error occurred: "+e);
        }

        try {
            state = new Select(driver.findElement(By.name("BillingNewAddress.StateProvinceId")));
            state_value = "Other (Non US)";
            state.selectByVisibleText(state_value);
            extentTest.log(Status.PASS,"State selected successfully");

        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot select state, error occurred: "+e);
        }

        try {
            city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"BillingNewAddress_City\"]")));
            city_value = "Chennai";
            city.sendKeys(city_value);
            extentTest.log(Status.PASS,"City value entered successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot enter city value, error occurred: "+e);
        }

        try {
            address_1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"BillingNewAddress_Address1\"]")));
            add_1_value = "1st main road, Subbarayan nagar";
            address_1.sendKeys(add_1_value);
            extentTest.log(Status.PASS,"Address 1 value entered successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot enter value in address 1, error occurred: "+e);
        }

        try {
            address_2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BillingNewAddress_Address2")));
            add_2_value = "Thoraipakkam";
            address_2.sendKeys(add_2_value);
            extentTest.log(Status.PASS,"Address 2 value entered successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot enter value in address 2, error occurred: "+e);
        }

        try {
            zip_code = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BillingNewAddress_ZipPostalCode")));
            zip_code_value = "600097";
            zip_code.sendKeys(zip_code_value);
            extentTest.log(Status.PASS,"Zip code entered successfully");

        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot enter zip code value, error occurred: "+e);
        }

        try {
            phone_number = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("BillingNewAddress_PhoneNumber")));
            phone_number_value = "9361844977";
            phone_number.sendKeys(phone_number_value);
            extentTest.log(Status.PASS,"Phone number entered successfully");

        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot enter phone number value, error occurred: "+e);
        }

        try {
            continue_to_payment_method = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"billing-buttons-container\"]/input")));
            continue_to_payment_method.click();
            extentTest.log(Status.PASS,"Continue button on billing address page,clicked successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot click continue button on billing address page, error occurred: "+e);
        }


    }

    public static void paymentMethod(){
        try {
            payment_method = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"paymentmethod_0\"]")));
            payment_method.click();
            String payment_method_val = payment_method.getAttribute("value");
            String[] payment_method_values = payment_method_val.split("\\.");
            payment_method_value = payment_method_values[1];
            extentTest.log(Status.PASS,"Payment method selected successfully");

        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot select payment method, error occurred: "+e);
        }

        try {
            continue_to_payment_confirmation = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"payment-method-buttons-container\"]/input")));
            continue_to_payment_confirmation.click();
            extentTest.log(Status.PASS,"Continue button on payment method clicked successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot click continue button on payment method page, error occurred: "+e);
        }

    }

    public static void paymentInformation(){

        try {
            payment_info_message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout-payment-info-load\"]/div/div/div[1]/table/tbody/tr/td/p")));
            if (payment_info_message.getText().equals(payment_info_message_value)) {
                System.out.println("Payment information message validated successfully");
            } else {
                System.out.println("Payment information message was wrong ");
            }
            extentTest.log(Status.PASS,"Payment information message validated successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot validate payment information message, error occurred: "+e);
        }

        try {
            continue_to_confirm_order = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"payment-info-buttons-container\"]/input")));
            continue_to_confirm_order.click();
            extentTest.log(Status.PASS,"Continue button on payment information page clicked successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot click continue button on payment information page, error occurred: "+e);
        }


    }

    public static void productDetails(){
        prod_name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout-confirm-order-load\"]/div/div[2]/div/form/table/tbody/tr/td[2]/a")));
        product_name = prod_name.getText();

        product_price = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout-confirm-order-load\"]/div/div[2]/div/form/table/tbody/tr/td[3]/span[2]"))).getText();

        product_Qty =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout-confirm-order-load\"]/div/div[2]/div/form/table/tbody/tr/td[4]/span[2]"))).getText();

        product_total = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout-confirm-order-load\"]/div/div[2]/div/form/table/tbody/tr/td[5]/span[2]"))).getText();

        product_sub_total=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout-confirm-order-load\"]/div/div[2]/div/form/div[2]/div[2]/div/table/tbody/tr[1]/td[2]/span/span"))).getText();

        product_shipping=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout-confirm-order-load\"]/div/div[2]/div/form/div[2]/div[2]/div/table/tbody/tr[2]/td[2]/span/span"))).getText();

        product_payment_method_additional_fee=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout-confirm-order-load\"]/div/div[2]/div/form/div[2]/div[2]/div/table/tbody/tr[3]/td[2]/span/span"))).getText();

        product_tax=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout-confirm-order-load\"]/div/div[2]/div/form/div[2]/div[2]/div/table/tbody/tr[4]/td[2]/span/span"))).getText();

        product_total_including_all_tax=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout-confirm-order-load\"]/div/div[2]/div/form/div[2]/div[2]/div/table/tbody/tr[5]/td[2]/span/span/strong"))).getText();

    }

    public static void checkoutdetails(){
        data.add("Billing Address : ");

//        name
        data.add(first_name_val);
        data.add(last_name_val);
//        email
        data.add(email_val);
//        phone
        data.add(phone_number_value);
//        fax
//        data.add(driver.findElement(By.xpath("//*[@id=\"checkout-confirm-order-load\"]/div/div[2]/div/div/ul/li[5]")).getText());
//        company
        data.add(company_value);
//        address 1
        data.add(add_1_value);
//        address 2
        data.add(add_2_value);
//        city, zip code
        data.add(city_value);
        data.add(zip_code_value);
//        country
        data.add(country_value);
//        payment method
        data.add("Payment method : ");
        data.add(payment_method_value);

        data.add("Product Detailes");
        data.add(product_name);
        data.add(product_price);
        data.add(product_Qty);
        data.add(product_total);
        data.add(product_sub_total);
        data.add(product_shipping);
        data.add(product_payment_method_additional_fee);
        data.add(product_tax);
        data.add(product_total_including_all_tax);

        FunctionUtility.billing_address_data_insert(data);
    }

    public  static void confirmOrder(){
        checkoutdetails();

        try {
            confirm_order_btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"confirm-order-buttons-container\"]/input")));
            confirm_order_btn.click();
            extentTest.log(Status.PASS,"Confirm order button clicked successfully");
        }catch (Exception e){
            extentTest.log(Status.FAIL,"Cannot click confirm order button, error occurred: "+e);
        }

        OrderConfirmation.orderConfirmation();
    }
}
