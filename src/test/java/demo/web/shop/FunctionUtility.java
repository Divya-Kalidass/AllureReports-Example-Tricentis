package demo.web.shop;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;

import static demo.web.shop.BrowserInitiation.driver;

public class FunctionUtility {

    static ExtentSparkReporter extentSparkReporter;
    static ExtentReports extentReports;
    static ExtentTest extentTest;
    static String reportName;
    static String timeStamp;

    static WebDriverWait wait;

    static Workbook workbook;
    static Sheet sheet;
    static Row row;
    static int i;
    static Row dataRow;
    static Row getDataRow;
    public static WebDriverWait waitMehtod(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait;
    }

    public static void reportGeneration(){
        // Create a timestamp to ensure each report has a unique name
        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportName = "Demo_web_app_report_" + timeStamp + ".html";
        extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"./reports/"+reportName);
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }

    public static void configureReport(){

        extentSparkReporter.config().setDocumentTitle("Tricentis Report");
        extentSparkReporter.config().setReportName("Report 1");
        extentSparkReporter.config().setTheme(Theme.STANDARD);
        extentSparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extentTest = extentReports.createTest("Test Case 1","Testing demo Web App by Tricentis");
    }
    public static void flushMethod(){
        extentReports.flush();
    }

    public static void reg_data_insertion(ArrayList<String> all_data){
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("Demo web app data");
        //sheet = workbook.createSheet();

        row = sheet.createRow(0);
        row.createCell(0).setCellValue("Gender");
        row.createCell(1).setCellValue("First name");
        row.createCell(2).setCellValue("Last name");
        row.createCell(3).setCellValue("Email");
        row.createCell(4).setCellValue("Password");

        dataRow = sheet.createRow(sheet.getLastRowNum()+1);

        for (int index=0;index<all_data.size();index++){
            dataRow.createCell(index).setCellValue(all_data.get(index));
//                        dataRow.createCell(1).setCellValue(data[1]);
        }

        try (FileOutputStream fileOut = new FileOutputStream("./tricentis-web-app.XLSX")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @DataProvider(name="logindata")
    public Object[][] testDataExample(){
        ExtractData config = new ExtractData("./tricentis-web-app.XLSX");
        Object[][] login_credentials = new Object[1][2];

            login_credentials[0][0] = config.getData(0, 1, 3);
            login_credentials[0][1] = config.getData(0, 1, 4);

        return login_credentials;
    }

    public static void billing_address_data_insert(ArrayList<String> all_data){

        FileInputStream fileIn = null;
        FileOutputStream fileOut = null;
        Workbook workbook = null;
        try {
            // Open the existing Excel file
            fileIn = new FileInputStream("./tricentis-web-app.XLSX");
            workbook = new XSSFWorkbook(fileIn);
            Sheet sheet = workbook.getSheetAt(0);


            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                headerRow = sheet.createRow(0);
            }
            headerRow.createCell(5).setCellValue("Checkout Details");


            i = sheet.getLastRowNum();

            for (int index = 0; index < all_data.size(); index++, i++) {
                getDataRow = sheet.getRow(i);
                if (getDataRow == null) {
                    getDataRow = sheet.createRow(i);
                }
                getDataRow.createCell(5).setCellValue(all_data.get(index));
//                getDataRow = sheet.createRow(i+1);
            }

            // Write changes back to the Excel file
            fileOut = new FileOutputStream("./tricentis-web-app.XLSX");
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (fileIn != null) fileIn.close();
                if (workbook != null) workbook.close();
                if (fileOut != null) fileOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
