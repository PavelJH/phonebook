import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver driver;
    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public void fillField(String userData, By locator) {
        driver.findElement(locator).click();
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
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        if (driver != null) {
            driver.quit();
        }
    }

    }
