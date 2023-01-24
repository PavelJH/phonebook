import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest_True {
    WebDriver driver;
    By emailField = By.cssSelector("[placeholder=\"Email\"]");

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();}

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void locators() {
        driver.findElement(By.name("email")).sendKeys("test@gmail.com");
        driver.findElement(By.name("password")).sendKeys("test@gmail.com");
        driver.findElement(By.cssSelector(".btn.btn-info"));
    }
        @Test
        public void registerNewUser() throws InterruptedException {
            String userData = "jeffry080";
            String userOtherData = "jeffry";
            driver.findElement(By.id("login-form")).isDisplayed();
            driver.findElement(By.cssSelector("[href=\"/user/registration\"]")).click();
            driver.findElement(By.id("registration-form")).isDisplayed();
            fillField(emailField, "jefr@gmail.com");
            fillField(By.cssSelector("[placeholder=\"Password\"]"), userOtherData);
            fillField(By.cssSelector("[placeholder=\"Confirm Password\"]"), userOtherData);
            driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
        }

    private void fillField(By cssSelector, String data) {
        driver.findElement(cssSelector).click();
        driver.findElement(cssSelector).sendKeys(data);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        if (driver !=null){
            Thread.sleep(1000);
            driver.quit();
        }
    }
}
