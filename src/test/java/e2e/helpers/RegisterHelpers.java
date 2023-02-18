package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class RegisterHelpers extends CommonHelper{
    By loginForm = By.id("login-form");
    By userRegistrationLink = By.cssSelector("[href=\"/user/registration\"]");
    By registrationForm = By.id("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPasswordField = By.cssSelector("[ng-reflect-name=\"confirm_password\"]");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    public By errorMessageBlock = By.id("error-message");
    public By errorEmailMessageBlock = By.id("email-error-invalid");
    public By errorPasswordMMaxLengthBlock = By.id("password-error-maxlength"); // locator password (div, красная строчка)

    public RegisterHelpers(WebDriver driver) {
        super(driver);
    }


    public void goToRegistrationPage() { // переход
        Assert.assertTrue(isElementPresent(loginForm));
        driver.findElement(userRegistrationLink).click();
        Assert.assertTrue(isElementPresent(registrationForm));// разобрать
    }
    public void fillRegistrationForm(String userData, String password) { // заполнения
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
        // Если вдруг добавиться новое поле к примеру confirmEmail, мы его может тут добавить а не писать большыми кусками кода в каждом тесте
    }
    public void clickSingUpButton() { // Кликабельность
        driver.findElement(loginButton).click();
        driver.findElement(loginButton).isEnabled();
    }
    public void checkErrorMessage(By locator, String expectedErrorMessage) {
        String err = "Actual error message is not equal expected";// можно менять один ерор для всех трех тестов
        checkItemText(locator, expectedErrorMessage, err);
    }
}
