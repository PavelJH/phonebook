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
        String firstName = "12";
        String lastName = "12";
        String description = "12";
        Number expectedCountRow = 1;
        //String firstAndLAstName = firstName + lastName;
        //click on the button"Add new contact"
        driver.findElement(By.cssSelector("[href='/contacts']")).click();
        //Check rope dialog(if one dialog - role)
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog'])")));//проверяем, что есть все диалоговые окна, так как *
        //Fill field "first name"
        fillField(firstName,By.xpath("//*[@role='dialog']//*[placeholder='First name']"));//отталкиваемся от одного локатара - //*[@role='dialog'] - и в нем ишем другой - *[placeholder='First name']"
        //Fill field "last name"
        fillField(lastName,By.xpath("//*[@role='dialog']//*[placeholder='Last name']"));
        //Fill field "About"
        fillField(description,By.xpath("//*[@role='dialog']//*[placeholder='About']"));
        //Click on the button "Save"
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        Assert.assertFalse(isElementPresent(By.xpath("//*[@role='dialog'])")));;
        //Filter by creature name
        fillField(firstName, By.xpath("//*[placeholder='Search...']"));

        Number actualCountRow = driver.findElements(By.className("list-group")).size();// считаем сколько у нас строчек
        Assert.assertEquals(actualCountRow, expectedCountRow);// актуальный результат и ожидаемый
        //Expect result: Created contact show with correct data in the contact table

    }
}