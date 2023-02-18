package e2e.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest {
    WebDriver driver;
    By emailField = By.cssSelector("[placeholder=\"Email\"]");





    //before


    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup(); // подгружения к Chrome
    }

    @BeforeMethod // вызываем драйвер
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080"); //что бы обрашался к url
        //driver.navigate().to("https://www.google.ru/"); = тоже самое что и предыдушая строка
        driver.manage().window().maximize();
        //driver.manage().window().getSize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //test
    @Test
    public void locators() {
        driver.findElement(By.name("email")).sendKeys("test@gmail.com");
        //driver.findElement(By.xpath("//input[@name='email']")).sendKeys("Jeffry@hmail.com"); // = xpath
        //driver.findElement(By.xpath("//input[@name='password']")).sendKeys("1234521"); // = xpath
//        driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys("test@gmail.com");//любой елемент
//        атрибута в [] скобках
        driver.findElement(By.name("password")).sendKeys("test@gmail.com");//любой елемент атрибута
        // в [] скобках
        driver.findElement(By.cssSelector(".btn.btn-info"));
//        driver.findElement(By.className("btn-info"));
//        driver.findElement(By.id("login-form"));
//        driver.findElement(By.linkText("/")); - с другой страницы


//        driver.findElement(By.cssSelector(".form-control.mb-2.rounded-pill.ng-pristine.ng-invalid.ng-touched"));
//        driver.findElement(By.cssSelector(".form-control.rounded-pill.ml-auto.ng-untouched.ng-pristine.ng-invalid"));
//        driver.findElement(By.cssSelector(".form-control.rounded-pill.ng-pristine.ng-invalid.ng-touched"));
//        driver.findElement(By.cssSelector(".btn.btn-info.my-1.btn-block"));
    }

        @Test
        public void registerNewUser() throws InterruptedException {
            String userData = "jeffry080";
            String userOtherData = "jeffry";
            driver.findElement(By.id("login-form")).isDisplayed(); //отображение(проверяет), есть ли эта форма (login)
            // Todo - Написать проверку есть ли кнопка phonebook.Login
            driver.findElement(By.cssSelector("[href=\"/user/registration\"]")).click();
            driver.findElement(By.id("registration-form")).isDisplayed();
            fillField(emailField, "jefr@gmail.com");
            fillField(By.cssSelector("[placeholder=\"Password\"]"), userOtherData);
            fillField(By.cssSelector("[placeholder=\"Confirm Password\"]"), userOtherData);
            //driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys(userData);
            //driver.findElement(By.cssSelector("[placeholder=\"Confirm Password\"]")).sendKeys(userData);
            driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
            //driver.findElement(By.cssSelector(".btn.btn-info.my-1.btn-block")).click();
        }

    private void fillField(By cssSelector, String data) {
        driver.findElement(cssSelector).click();
        driver.findElement(cssSelector).sendKeys(data);
    }

    /*driver.findElement(By.name("email"));
        driver.findElement(By.name("password"));
        driver.findElement(By.name("confirm password"));
        driver.findElement(By.cssSelector(".btn.btn-info.my-1.btn-block"));
        */



    //after
    @AfterMethod
    public void tearDown() throws InterruptedException { // Interrup - for Thread sleep
        if (driver !=null){
            Thread.sleep(1000);
            driver.quit(); // = закроет браузер
            //driver.close(); = закроет вкладку
        }
    }
}
