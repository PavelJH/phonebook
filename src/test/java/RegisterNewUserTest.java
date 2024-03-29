import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RegisterNewUserTest extends TestBase {
    By loginForm = By.id("login-form");
    By userRegistrationLink = By.cssSelector("[href=\"/user/registration\"]");
    By registrationForm = By.id("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPasswordField = By.cssSelector("[ng-reflect-name=\"confirm_password\"]");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    By errorMessageBlock = By.id("error-message");
    By errorEmailMessageBlock = By.id("email-error-invalid");
    By errorPasswordMMaxLengthBlock = By.id("password-error-maxlength"); // locator password (div, красная строчка)
    Faker faker = new Faker();

    private void goToRegistrationPage() { // переход
        Assert.assertTrue(isElementPresent(loginForm));
        driver.findElement(userRegistrationLink).click();
        Assert.assertTrue(isElementPresent(registrationForm));// разобрать
    }
    private void fillRegistrationForm(String userData, String password) { // заполнения
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
        // Если вдруг добавиться новое поле к примеру confirmEmail, мы его может тут добавить а не писать большыми кусками кода в каждом тесте
    }
    private void clickSingUpButton() { // Кликабельность
        driver.findElement(loginButton).click();
        driver.findElement(loginButton).isEnabled();
    }
    private void checkErrorMessage(By locator, String expectedErrorMessage) {
        String actualErrorMessage = driver.findElement(locator).getText(); // find text error message
        String err = "Actual error message is not equal expected";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, err);
    }


    //Positive
    @Test
    public void registerNewUserWithVavalidData() {
        //Arrange - Данные
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";
        //Act - Действие
        /*driver.findElement(loginForm).isDisplayed();
        driver.findElement(userRegistrationLink).click();
        driver.findElement(registrationForm).isDisplayed();
        */
        goToRegistrationPage(); // все что выше три строчки
        fillRegistrationForm(userData, password);
        clickSingUpButton();
        //Assert
        checkErrorMessage(errorMessageBlock, expectedErrorMessage); // от вынесеного 4-го метода


        /* Incorrect

        //Negative Test -1
        By actualUserRegistrationLink = By.cssSelector("[/user/forgotpassword]");// incorrect locator
        By expectedUserRegistrationLink = By.cssSelector("[href=/user/registration]");// correct locator
        Assert.assertNotEquals(actualUserRegistrationLink, expectedUserRegistrationLink);
        //Negative Test 02
        Assert.assertFalse(isElementPresent(By.xpath("registration-form"))); //wrong selector for registration form
        Assert.assertFalse(isElementPresent(By.cssSelector("type=\"password\""))); //write wrong address
        Assert.assertFalse(isElementPresent(By.cssSelector("[\"Confirm Password\"]")));
        Assert.assertFalse(isElementPresent(By.xpath("//*[@type=\"button\"]")));
        */
    }



    //Negative
    @Test
    public void registerNewUserWithInvalidData() {
        //Arrange - Данные
        String userData = faker.internet().password(); // email
        String password = faker.internet().emailAddress(); // password
        String expectedEmailErrorMessage = "Email must be a valid email address.";
        String expectedPasswordErrorMessage = "Password must be no longer than 20 characters.";

        //Act - Действие
        //driver.findElement(loginForm).isDisplayed();
        //driver.findElement(registrationForm).isDisplayed();
        goToRegistrationPage();//делаем extract метод //refactor - extract metod
        fillRegistrationForm(userData, password);
        Assert.assertFalse(isElementPresent(errorMessageBlock)); // find text error message block
        //Assert
        checkErrorMessage(errorEmailMessageBlock, expectedEmailErrorMessage); // от вынесеного 4-го метода
        checkErrorMessage(errorPasswordMMaxLengthBlock, expectedPasswordErrorMessage); // от вынесеного 4-го метода// ВМЕСТО НИЖНИХ СТРОЧЕК//TODO

        //Assert.assertFalse(isElementClickable(loginButton));//проверем доступна ли клик ответ false
        /*
        String actualEmailErrorMessage = driver.findElement(errorEmailMessageBlock).getText();
        String actualPasswordErrorMessage = driver.findElement(errorPasswordMMaxLengthBlock).getText(); // свкрху берем ее

        //Assert
        //Это 4-й метод
        String err = "Actual error message is not equal expected";
        Assert.assertEquals(actualEmailErrorMessage, expectedEmailErrorMessage, err);// notEquals
        Assert.assertEquals(actualPasswordErrorMessage, expectedPasswordErrorMessage, err);// notEquals
        */
    }




    //Negative
    @Test
    public void registerExistingUser() {
        //Arrange - Данные
        String userData = "test@gmail.com";
        String password = "test@gmail.com";
        String expectedErrorMessage = "Error! User already exists Login now?";
        //Act - Действие
        goToRegistrationPage();
        fillRegistrationForm(userData, password);
        /*driver.findElement(loginButton).click();
        driver.findElement(loginButton).isEnabled(); // кликабельна ли кнопка
        */
        clickSingUpButton(); // две верхние строчки
        //Assert
        checkErrorMessage(errorMessageBlock, expectedErrorMessage); // от вынесеного 4-го метода
    }
}