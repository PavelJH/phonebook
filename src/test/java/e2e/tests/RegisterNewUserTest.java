package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.helpers.CommonHelper;
import e2e.helpers.RegisterHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class RegisterNewUserTest extends TestBase {

    Faker faker = new Faker();

    //Positive
    @Test
    public void registerNewUserWithVavalidData() throws IOException, AWTException, InterruptedException {
        //Arrange - Данные
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";
        //Act - Действие
        /*driver.findElement(loginForm).isDisplayed();
        driver.findElement(userRegistrationLink).click();
        driver.findElement(registrationForm).isDisplayed();
        */
        //app.getRegister().deleteFiles("records");
        app.getRegister().startRecording();
        app.getRegister().goToRegistrationPage(); // все что выше три строчки
        app.getRegister().fillRegistrationForm(userData, password);
        app.getRegister().clickSingUpButton();
        //Assert
        app.getRegister().checkErrorMessage(app.getRegister().errorMessageBlock, expectedErrorMessage); // от вынесеного 4-го метода
        Thread.sleep(5000);
        app.getRegister().stopRecording();

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
        app.getRegister().goToRegistrationPage();//делаем extract метод //refactor - extract metod
        app.getRegister().fillRegistrationForm(userData, password);
        Assert.assertFalse(app.getRegister().isElementPresent(app.getRegister().errorMessageBlock)); // find text error message block
        //Assert
        app.getRegister().checkErrorMessage(app.getRegister().errorEmailMessageBlock, expectedEmailErrorMessage); // от вынесеного 4-го метода
        app.getRegister().checkErrorMessage(app.getRegister().errorPasswordMMaxLengthBlock, expectedPasswordErrorMessage); // от вынесеного 4-го метода// ВМЕСТО НИЖНИХ СТРОЧЕК//TODO

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
        app.getRegister().goToRegistrationPage();
        app.getRegister().fillRegistrationForm(userData, password);
        /*driver.findElement(loginButton).click();
        driver.findElement(loginButton).isEnabled(); // кликабельна ли кнопка
        */
        app.getRegister().clickSingUpButton(); // две верхние строчки
        //Assert
        app.getRegister().checkErrorMessage(app.getRegister().errorMessageBlock, expectedErrorMessage); // от вынесеного 4-го метода
    }
}