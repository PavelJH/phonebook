import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContactCase extends Login{
//    @Test
//    void check_login(){ // запускаюся все beformethod
//    }
    @Test
    public void createContact(){
        String firstName = "Pavel";
        String lastName = "Kelbas";
        String description = "I am a best in the World, at what I do";
        Number expectedCountRow = 1;
        //String firstAndLAstName = firstName + lastName;
        //click on the button"Add new contact"
        driver.findElement(By.cssSelector("[href='/contacts']")).click();
        //Check rope dialog(if one dialog - role)
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog']")));//проверяем, что есть все диалоговые окна, так как *
        //Fill field "first name"
        fillField(firstName,By.xpath("//input[@id='form-name']")); //отталкиваемся от одного локатара - //*[@role='dialog'] - и в нем ишем другой - *[placeholder='First name']"
        //Fill field "last name"
        fillField(lastName,By.xpath("//input[@id='form-lastName']"));
        //Fill field "About"
        fillField(description,By.xpath("//input[@id='form-about']"));
        //Click on the button "Save"
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        Assert.assertFalse(isElementPresent(By.xpath("//*[@role='dialog'])")));;
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();
        //Filter by creature name
        fillField(firstName, By.xpath("//input[@id='input-search-contact']"));

        Number actualCountRow = driver.findElements(By.className("list-group")).size();// считаем сколько у нас строчек
        Assert.assertEquals(actualCountRow, expectedCountRow);// актуальный результат и ожидаемый
        //Expect result: Created contact show with correct data in the contact table

    }
}