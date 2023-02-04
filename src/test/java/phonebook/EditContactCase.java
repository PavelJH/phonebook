package phonebook;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditContactCase extends Login{
    By MessageBlock = By.xpath("//div[@class='toast-body']");
    By HomePage = By.xpath("//a[@class='navbar-brand']//*[name()='svg']");
    By SearchWindow = By.xpath("//input[@id='input-search-contact']");
    By GroupList = By.xpath("//div[@id='contacts-list']//div[@class='list-group']");
    By GroupListTap = By.xpath("//div[@id='contacts-list']//button[1]");
    By EditContact = By.id("btn-edit-contact");
    By DescriptionWindow = By.xpath("//textarea[@name='input-ec-description']");
    By SaveEditContact = By.xpath("//button[normalize-space()='Save']");
    private void ReloadPageSearchTap(String first_name) {
        driver.findElement(HomePage).click();
        fillField(first_name, SearchWindow);
        Number expectedCountRow = 1;
        Number actualCountRow = driver.findElements(GroupList).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
        driver.findElement(GroupListTap).click();
    }
    public void CheckCorrectName(String first_name, String last_name) {
        CheckItemText(By.id("contact-first-name"), first_name,"Actual first name is not equal expected name");
        CheckItemText(By.id("contact-last-name"), last_name,"Actual last name is not equal expected name");
    }
    private void DescriptionEditSave(String descriptionSecond) {
        driver.findElement(EditContact).click();
        driver.findElement(DescriptionWindow).clear();
        fillField(descriptionSecond,DescriptionWindow);
        driver.findElement(SaveEditContact).click();
    }

    @Test
    public void ChangeEditContactInfo() throws InterruptedException {
        String first_name = "Mario";
        String last_name = "Fernandez";
        String descriptionFirst = "I am a best in the World, at what I do";
       // String descriptionFirst = "I am a student. My Professor Mikhailov Leonid";
        String descriptionSecond = "I am a best in the World, at what I do";
        String expectedMessage = "Contact changed";

        ReloadPageSearchTap(first_name);
        Thread.sleep(1000);
        CheckCorrectName(first_name, last_name);
        CheckItemText(By.id("contact-description"), descriptionFirst,"Actual description is not equal expected name");
        DescriptionEditSave(descriptionSecond);
        CheckItemText(MessageBlock, expectedMessage, "error");
        ReloadPageSearchTap(first_name);
        Thread.sleep(1000);
        CheckCorrectName(first_name, last_name);
        CheckItemText(By.id("contact-description"), descriptionSecond,"Actual description is not equal expected name");
        }
}
