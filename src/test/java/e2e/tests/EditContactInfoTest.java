package e2e.tests;

import e2e.TestBase;
import e2e.utils.DataProviders;
import org.testng.annotations.Test;



public class EditContactInfoTest extends TestBase {
    @Test(dataProvider = "changeLastNameAndDescription", dataProviderClass = DataProviders.class)
    public void editContactInfo(String lastName, String description) throws InterruptedException {
        String firstName = "14159d68-b169-4791-9d4a-19b22142a46e";

        app.getLogin().login();
        app.getEditeContact().changeLanguage();
        app.getEditeContact().goToContactPageAndFillFilterField(firstName);
        app.getEditeContact().checkCountRows(1);
        app.getEditeContact().openContact();
        app.getEditeContact().openEditForm();
        app.getEditeContact().editeLastNameAndDescription(lastName, description);
        app.getEditeContact().saveEditContact();
        app.getEditeContact().checkFieldsOnContactInfo(firstName, lastName, description);
    }

    @Test(dataProvider = "newContactWithCSVHomeWork", dataProviderClass = DataProviders.class)
    public void editContactInfoWithCSV(String lastName, String description) throws InterruptedException{
        String firstName = "14159d68-b169-4791-9d4a-19b22142a46e";

        app.getLogin().login();
        app.getEditeContact().changeLanguage();
        app.getEditeContact().goToContactPageAndFillFilterField(firstName);
        app.getEditeContact().checkCountRows(1);
        app.getEditeContact().openContact();
        app.getEditeContact().openEditForm();
        app.getEditeContact().editeLastNameAndDescription(lastName, description);
        app.getEditeContact().saveEditContact();
        app.getEditeContact().checkFieldsOnContactInfo(firstName, lastName, description);
    }

}
