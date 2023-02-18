package e2e.helpers;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CommonHelper  { // самый базовый helper// хранятся все основные(для всего общего) методы
    WebDriver driver;
    public WebDriverWait wait;
    public CommonHelper(WebDriver driver) {
        this.driver = driver;
    }
    public WebDriverWait setWait(){
        wait = new WebDriverWait(driver, 10);
        return wait;
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
    public void clickOnVisibleElement(By locator) {
        Assert.assertTrue(isElementPresent(locator));
        driver.findElement(locator).click();
    }
    public void openDialog(By locator) {
        clickOnVisibleElement(locator);
        setWait().until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//*[@role='dialog']")));
    }
    public void checkItemText(By locator, String expectedText, String err) {
        String actualText = driver.findElement(locator).getText();
        Assert.assertEquals(actualText, expectedText, err);
    }
    public String takeScreenshot() throws IOException {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("reference/screen" + System.currentTimeMillis() + ".png"); // System.currentTimeMillis() - время

        Files.copy(tmp,screenshot);
        return screenshot.getAbsolutePath();
    }
}
