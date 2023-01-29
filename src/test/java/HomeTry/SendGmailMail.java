package HomeTry;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SendGmailMail {
    WebDriver driver;

    @BeforeClass
    public static void setUp(){
        WebDriverManager.firefoxdriver().setup();
    }
    @BeforeMethod
    public void setupTest(){
        driver = new FirefoxDriver();
        driver.get("https://accounts.google.com/v3/signin/identifier?dsh=S-771559603%3A1674311845583462&continue=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&emr=1&followup=https%3A%2F%2Fmail.google.com%2Fmail%2Fu%2F0%2F&osid=1&passive=1209600&service=mail&flowName=GlifWebSignIn&flowEntry=ServiceLogin&ifkv=AWnogHeCMzj4jGGOKCbudPqjFfGJJbv-eP9qC3Q4u_Md8uA3sfTkb1j2Dhc_3bXIHhpHn2eCK7oA6A");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void locators(){
        String userEmailData = "artmike00800@gmail.com";
        String userPasswordData = "artmike00800@gmail.com";
        String userWriteText = "Hello World My Friends" +
                "I will mett you todat at 8:00 pm" +
                "See you soon";
        driver.findElement(By.xpath("(//input[@id='identifierId'])[1]")).sendKeys(userEmailData);
        driver.findElement(By.xpath("(//button[@class='VfPpkd-LgbsSe VfPpkd-LgbsSe-OWXEXe-k8QpJ VfPpkd-LgbsSe-OWXEXe-dgl2Hf nCP5yc AjY5Oe DuMIQc LQeN7 qIypjc TrZEUc lw1w4b'])[1]")).click();
        driver.findElement(By.xpath("(//input[@name='Passwd']")).sendKeys(userPasswordData);
        driver.findElement(By.xpath("(//span[contains(text(),'Далее')]")).click();
    }

}
