package phonebook;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


public class TestBase {
    WebDriver driver;
    public static Logger logger() {
        return LoggerFactory.getLogger(TestBase.class);
    }

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        logger().info("Setup chrome driver");
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger().info("Star test");
    }
    public void fillField(String userData, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();//desFill
        driver.findElement(locator).sendKeys(userData);

    }
    public boolean isElementPresent(By by) {//проверка, сушечтвует ли элемент
        try {
            driver.findElement(by);// try to found over element
            return true;// if we found element - true
        } catch (NoSuchElementException exception) {// catch - ловить
            exception.printStackTrace();//это означает, что в терминал, если это условие выполняется,
            // точнее предыдушее не выполняется, будет вывдиься это сообщение//
            return false;// вывлжится - false
        }//Трай пробует(и у него не получилось, то катч отлавливает, что у него не оплучилось), катч отлавливвает
    }
    public boolean isElementClickable(By by) {//проверка, кликабельный элемент
        try {
            driver.findElement(by).click();
            return true;
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            return false;
        }
    }
    public void CheckItemText(By locator, String expectedText, String err) {// перенесли с register
        String actualText = driver.findElement(locator).getText(); // find text error message
        // тут вырезали и перенесли в checkErrorMessage
        Assert.assertEquals(actualText, expectedText, err);
    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        if (driver != null) {
            driver.quit();
        }
        logger().info("Stop test");
    }

    }
