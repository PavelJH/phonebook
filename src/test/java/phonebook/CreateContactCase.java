package phonebook;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContactCase extends Login {
//    @Test
//    void check_login(){ // запускаюся все beformethod
Faker faker = new Faker();
//    }
private void openAddNewContactDialog() {
    driver.findElement(By.cssSelector("[href='/contacts']")).click();
    //Check rope dialog(if one dialog - role)
    Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog']")));//проверяем, что есть все диалоговые окна, так как *
}
    private void fillAddNewContact(String firstName, String lastName, String description) {
        //Fill field "first name"
        fillField(firstName,By.xpath("//input[@id='form-name']")); //отталкиваемся от одного локатара - //*[@role='dialog'] - и в нем ишем другой - *[placeholder='First name']"
        //Fill field "last name"
        fillField(lastName,By.xpath("//input[@id='form-lastName']"));
        //Fill field "About"
        fillField(description,By.xpath("//input[@id='form-about']"));
    }
    private void saveNewContact() throws InterruptedException {
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        Thread.sleep(1000);
        Assert.assertFalse(isElementPresent(By.xpath("//*[@role='dialog'])")));;
    }
    private void checkFieldsOnContactInfoAfterCreatedContackt(String firstName, String lastName, String description) {
        //после создания проверка, создалось с правильными id
        CheckItemText(By.id("contact-first-name"), firstName,"Actual first name is not equal expected name");
        CheckItemText(By.id("contact-last-name"), lastName,"Actual last name is not equal expected name");
        CheckItemText(By.id("contact-description"), description,"Actual description is not equal expected name");
    }
    private void checkCountRows(Number expectedCountRow) {
        Number actualCountRow = driver.findElements(By.className("list-group")).size();// считаем сколько у нас строчек
        Assert.assertEquals(actualCountRow, expectedCountRow);// актуальный результат и ожидаемый
    }
    private void goToContactPageAndFillFilterField(String firstName) {
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();
        //driver.findElement(By.cssSelector("[href='/']")); - to same what a previous
        //Filter by creature name
        fillField(firstName, By.xpath("//input[@id='input-search-contact']"));
    }


    @Test
    public void createContact() throws InterruptedException {
        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.internet().uuid();
        //String firstName = "Pavel";
        //String lastName = "Kelbas";
        //String description = "I am a best in the World, at what I do";
        Number expectedCountRow = 1;
        //String firstAndLAstName = firstName + lastName;
        //click on the button"Add new contact"
        openAddNewContactDialog(); //три нижние строчки
        /* driver.findElement(By.cssSelector("[href='/contacts']")).click();
        //Check rope dialog(if one dialog - role)
        //Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog']")));//проверяем, что есть все диалоговые окна, так как *
        */
        fillAddNewContact(firstName, lastName, description);
        //Click on the button "Save"
        saveNewContact();
        checkFieldsOnContactInfoAfterCreatedContackt(firstName, lastName, description);
        goToContactPageAndFillFilterField(firstName);
        checkCountRows(expectedCountRow);
        //Expect result: Created contact show with correct data in the contact table

    }
    //@Test
    //TODO  //checkitemtext (checkFieldsOnContactInfoAfterCreatedContackt) - переходим после поиска на контаки и проверяем введеные данные
// вынисти локаторы в отдельно, как в register user



}