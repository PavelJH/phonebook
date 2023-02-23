package e2e.helpers;

import com.google.common.io.Files;
import e2e.utils.Recorder;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.VideoFormatKeys.*;
import static org.monte.media.VideoFormatKeys.QualityKey;

public class CommonHelper  { // самый базовый helper// хранятся все основные(для всего общего) методы
    WebDriver driver;
    public WebDriverWait wait;
    public ScreenRecorder screenRecorder;
    public CommonHelper(WebDriver driver) {
        this.driver = driver;
    }
    public WebDriverWait setWait(){
        wait = new WebDriverWait(driver, 10);
        return wait;
    }
    // два метода ниже для рекординг
    public void startRecording() throws IOException, AWTException {
        File file = new File("records");// так как лежит в корне проекта
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0,0,width,height);
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

        screenRecorder = new Recorder(gc, captureSize, new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_MJPG, CompressorNameKey, ENCODING_AVI_MJPG, DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60), new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)), null, file, "MyVideo");
        screenRecorder.start();
    }
    public void stopRecording() throws IOException {
        screenRecorder.stop();
    }
    public String deleteFiles(String folder) {
        File directory = new File(folder);
        File[] files = directory.listFiles();
        for (File f : files) {
            f.delete();
        }
        return "deleted all files" + folder;
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
