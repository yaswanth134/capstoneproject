package checkout;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class signExcelTest {
	WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Set the path to your ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Administrator\\Downloads\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://magento.softwaretestingboard.com/customer/account/create/");
    }

    @Test(dataProvider = "registrationData")
    public void registerUser(String firstName, String lastName, String email, String password, String confirmPassword) {
        WebElement firstNameField = driver.findElement(By.id("firstname"));
        WebElement lastNameField = driver.findElement(By.id("lastname"));
        WebElement emailField = driver.findElement(By.id("email_address"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement confirmPasswordField = driver.findElement(By.id("password-confirmation"));
        WebElement submitButton = driver.findElement(By.xpath("//button[@title='Create an Account']"));

        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();

        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        confirmPasswordField.sendKeys(confirmPassword);

        submitButton.click();

        // Add assertions here to verify the result (e.g., check for success message or error message)
    }

    @DataProvider(name = "registrationData")
    public Object[][] readExcelData() throws IOException {
        String excelFilePath = "C:\\Users\\Administrator\\Downloads\\Data3.xlsx";
        FileInputStream excelFile = new FileInputStream(excelFilePath);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheet("Sheet1");

        int rowCount = sheet.getPhysicalNumberOfRows();
        int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][columnCount];

        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < columnCount; j++) {
                Cell cell = row.getCell(j);
                data[i - 1][j] = cell.toString();
            }
        }

        workbook.close();
        excelFile.close();

        return data;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
