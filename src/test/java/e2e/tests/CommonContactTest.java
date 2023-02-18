package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.helpers.EditeContactHelpers;
import org.testng.annotations.Test;

public class CommonContactTest extends TestBase {
    Faker faker = new Faker();
    @Test
    public void userCanCreateEditRemoveContact() throws InterruptedException {
        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.lorem().paragraph(1);
        Number expectedCountRow = 1;

        app.getLogin().login();
        app.getCreateContact().changeLanguage();
        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddNewContact(firstName, lastName, description);
        app.getCreateContact().saveNewContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);
        app.getCreateContact().goToContactPageAndFillFilterField(firstName);
        app.getCreateContact().checkCountRows(expectedCountRow);

        String newFirstName = faker.internet().uuid();
        String newLastName = faker.internet().uuid();
        String newDescription = faker.lorem().paragraph(1);

        app.getEditeContact().goToContactPageAndFillFilterField(firstName);
        app.getEditeContact().checkCountRows(expectedCountRow);
        app.getEditeContact().openContact();
        app.getEditeContact().openEditForm();
        app.getEditeContact().editeContactInfoForm(newFirstName, newLastName, newDescription);
        app.getEditeContact().saveEditContact();
        app.getEditeContact().checkFieldsOnContactInfo(newFirstName, newLastName, newDescription);

        EditeContactHelpers getRemoveContact = app.getEditeContact();
        getRemoveContact.goToContactPageAndFillFilterField(newFirstName);
        getRemoveContact.openRemoveContactDialog();
        getRemoveContact.removeContact();

        getRemoveContact.checkCountRows(0);


    }
}
