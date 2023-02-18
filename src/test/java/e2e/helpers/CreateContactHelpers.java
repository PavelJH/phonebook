package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CreateContactHelpers extends ContactHelpers{
    public CreateContactHelpers(WebDriver driver) {
        super(driver);
    }
    public void openAddNewContactDialog() {
        openDialog(By.cssSelector("[href='/contacts']"));

    }
    public void fillAddNewContact(String firstName, String lastName, String description) {
        fillField(firstName,By.xpath("//input[@id='form-name']"));
        fillField(lastName,By.xpath("//input[@id='form-lastName']"));
        fillField(description,By.xpath("//input[@id='form-about']"));
    }
    public void saveNewContact() throws InterruptedException {
        clickOnVisibleElement(By.xpath("//button[@class='btn btn-primary']"));
        setWait().until(ExpectedConditions.invisibilityOfElementLocated
                (By.xpath("//*[@role='dialog']")));

    }

}
