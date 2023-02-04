package api.tests.contact;

import api.enums.EndPoint;
import api.model.ContactDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class AddNewConctactTest extends ApiBase {
    //до теста выносим все переменные
    Faker faker = new Faker();
    ContactDto contactDto;
    Response response;

    int id;

    @AfterMethod
    public void afterTest(){
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);// id кого удаляем, 18 и 34 строчка
    }

    @Test
    public void createContactTest(){
/* //все что внизу записано стандартом в ApiBase
        contactDto = new ContactDto(); // получился пустым
        contactDto.setFirstName(faker.name().firstName());
        contactDto.setLastName(faker.name().lastName());
        contactDto.setDescription(faker.lorem().sentence(5));// сгенерирует строку с пяти слов
*/
        contactDto = createContact(); // все что сверху
    response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto); // contactDto тело нашего запроса = эта строчка записывает весь ответ
        id = response.jsonPath().getInt("id");// из ответа получим id
        Assert.assertEquals(response.jsonPath().getString("firstName"), contactDto.getFirstName());
    }
}
