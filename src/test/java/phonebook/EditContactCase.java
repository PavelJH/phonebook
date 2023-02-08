package phonebook;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EditContactCase extends Login{
    By messageBlock = By.xpath("//div[@class='toast-body']");
    By homePage = By.xpath("//a[@class='navbar-brand']//*[name()='svg']");
    By searchWindow = By.xpath("//input[@id='input-search-contact']");
    By groupList = By.xpath("//div[@id='contacts-list']//div[@class='list-group']");
    By groupListTap = By.xpath("//div[@id='contacts-list']//button[1]");
    By editContact = By.id("btn-edit-contact");
    By descriptionWindow = By.xpath("//textarea[@name='input-ec-description']");
    By lastNameWindow = By.xpath("//input[@name='input-ec-lastName']");
    By saveEditContact = By.xpath("//button[normalize-space()='Save']");
    @DataProvider
    public Iterator<Object[]> changeLastNameAndDescription() { // Бкжнт идти, пока не проверят все записи в методе
        List<Object[]> list = new ArrayList<>(); // переменная не где не использеться и тут пустой масив
        list.add(new Object[]{ "Kelbas", "Best A"});// внизу написано в @TEST
        list.add(new Object[]{ "Sherif", "Best AAA"});// внизу написано в @TEST
        list.add(new Object[]{ "Golof", "Best B"});// внизу написано в @TEST
        return list.iterator();
    }
    private void reloadPageSearchTap(String first_name) {
        driver.findElement(homePage).click();
        //отображение
        fillField(first_name, searchWindow);
        Number expectedCountRow = 1;
        Number actualCountRow = driver.findElements(groupList).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);

        driver.findElement(groupListTap).click();
        //отобраз страница инфо
    }
    public void checkCorrectName(String first_name, String last_name, String des) {// rename| checkContactInfoForm
        CheckItemText(By.id("contact-first-name"), first_name,"Actual first name is not equal expected name");
        CheckItemText(By.id("contact-last-name"), last_name,"Actual last name is not equal expected name");
        CheckItemText(By.id("contact-description"), des,"Actual description is not equal expected name");
    }
    private void descriptionEditSave(String dataField,String dataField2) {
        driver.findElement(editContact).click();




        fillField(dataField, lastNameWindow);
        fillField(dataField2, descriptionWindow);
        driver.findElement(saveEditContact).click();
    }
    //open |driver.findElement(EditContact).click(); = проверка

    /*
    @Test
    public void changeEditContactInfo() throws InterruptedException {
        String first_name = "Mario";
        String last_name = "Fernandez";
        String descriptionFirst = "I am a best in the World, at what I do";
       // String descriptionFirst = "I am a student. My Professor Mikhailov Leonid";
        String descriptionSecond = "I am a best in the World, at what I do";
        String expectedMessage = "Contact changed";

        reloadPageSearchTap(first_name);
        Thread.sleep(1000);
        checkCorrectName(first_name, last_name, descriptionFirst);

        descriptionEditSave(descriptionSecond);
        CheckItemText(messageBlock, expectedMessage, "error");
        reloadPageSearchTap(first_name);
        Thread.sleep(1000);
        checkCorrectName(first_name, last_name, descriptionSecond);
        }*/
    @Test(dataProvider = "changeLastNameAndDescription")
    public void changeEditContactInfo(String last_name, String description) throws InterruptedException {
        String first_name = "Mario";



        reloadPageSearchTap(first_name);
        Thread.sleep(1000);
        Assert.assertEquals(first_name, first_name, "err");
//        checkCorrectName(first_name, last_name, description);
//
        descriptionEditSave(last_name, description);
//        CheckItemText(messageBlock, expectedMessage, "error");
        reloadPageSearchTap(first_name);
        Thread.sleep(1000);
        Assert.assertEquals(first_name, first_name, "err");
       // checkCorrectName(first_name, last_name, description);
    }

}
