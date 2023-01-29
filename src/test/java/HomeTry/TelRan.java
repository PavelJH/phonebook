package HomeTry;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TelRan {
    WebDriver driver;



    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        }
    @BeforeMethod
    public void setupTest(){
        driver = new ChromeDriver();
        driver.get("https://skilldesk.starta.university/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void locators(){
        String userEmailData = "Kelbaspavel@gmail.com";
        String userPasswordData = "VGlBbEQy";
        String userWriteText = "Отличное повествования лекции, все отлично и коректно обьяснено." +
                "Превосходно обьяснение";
        driver.findElement(By.cssSelector("[placeholder=\"mail@example.com\"]")).sendKeys(userEmailData);
        driver.findElement(By.cssSelector("[placeholder=\"John Smith\"]")).sendKeys(userPasswordData);
        driver.findElement(By.xpath("(//a[normalize-space()='Log in'])[1]")).click();
//        Implicit Wait можно использовать для:
//
//        ожидания полной загрузки страницы — pageLoadTimeout();
//        ожидания появления элемента на странице — implicitlyWait();
//        ожидания выполнения асинхронного запроса — setScriptTimeout();
        //TODO - how write code^ where pause will be 10 sec?
        driver.findElement(By.xpath("(//a[normalize-space()='Lessons'])[1]")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("(//td[contains(text(),'Give feedback')])[7]")).click();
        driver.findElement(By.xpath("(//body[1]/div[1]/div[9]/div[1]/div[2]/div[2]/div[4]/form[1]/div[2]/div[1]/div[2]/div[1])")).click();
//        driver.findElement(By.xpath("((//textarea[@placeholder='Замечания и предложения, относительно данного урока'])[1]")).sendKeys(userWriteText);



    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        if (driver !=null){
            Thread.sleep(1000);
            driver.quit();
        }
    }


    }


