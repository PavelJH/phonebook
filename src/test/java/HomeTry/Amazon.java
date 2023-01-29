package HomeTry;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Amazon {

    WebDriver driver;

    @BeforeClass
    public static void setUp() { WebDriverManager.chromedriver().setup();}
    @BeforeMethod
    public void setupTest(){
    driver = new ChromeDriver();
    driver.get("https://amazon.de");
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @Test
    public void locators(){
        driver.findElement(By.xpath("(//a[@id='nav-link-accountList'])[1]")).click();

    }
}

