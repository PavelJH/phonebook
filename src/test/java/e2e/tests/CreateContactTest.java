package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.utils.DataProviders;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class CreateContactTest extends TestBase {





    @Test(dataProvider = "newContact",dataProviderClass = DataProviders.class) // иницыализация с класа DataProviders
    public void createContactDataProvider(String firstName, String lastName, String description ) throws InterruptedException {


        Number expectedCountRow = 1;

        app.getLogin().login();
        app.getCreateContact().changeLanguage();
        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddNewContact(firstName, lastName, description);
        app.getCreateContact().saveNewContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);
        app.getCreateContact().goToContactPageAndFillFilterField(firstName);
        app.getCreateContact().checkCountRows(expectedCountRow);
        app.getCreateContact();
    }
    @Test(dataProvider = "newContactWithCSV",dataProviderClass = DataProviders.class)// иницыализация с класа DataProviders
    public void createContactDataProviderWithFileCSV(String firstName, String lastName, String description ) throws InterruptedException {

        Number expectedCountRow = 1;
        app.getLogin().login();
        app.getCreateContact().changeLanguage();
        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddNewContact(firstName, lastName, description);
        app.getCreateContact().saveNewContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);
        app.getCreateContact().goToContactPageAndFillFilterField(firstName);
        app.getCreateContact().checkCountRows(expectedCountRow);


    }
@Test
    public void negativeTestGaps() throws InterruptedException {
        String firstName = " ";
        String lastName = " ";
        String description = " ";
        String errorMessage = "Contact save fail";
    app.getLogin().login();
    app.getCreateContact().changeLanguage();
    app.getCreateContact().openAddNewContactDialog();
    app.getCreateContact().fillAddNewContact(firstName, lastName, description);
    app.getCreateContact().saveNewContact();
    app.driver.findElement(By.xpath("(//div[@class='toast-body'])[1]"));
    app.getCreateContact().checkItemText(By.xpath("(//div[@class='toast-body'])[1]"), errorMessage, "good");
}
@Test
    public void negativeTestWithoutValidData() throws InterruptedException{
        String errorMessage = "To add a contact, you must specify a name";
    app.getLogin().login();
    app.getCreateContact().changeLanguage();
    app.getCreateContact().openAddNewContactDialog();
    app.driver.findElement(By.xpath("//input[@placeholder='Last name']")).click();
    app.getCreateContact().checkItemText(By.xpath("//ngb-alert[@role='alert']"), errorMessage, "good");
}
}