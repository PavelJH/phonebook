package api.tests.contact;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class AddNewConctactTest extends ApiBase {
    Faker faker = new Faker();
    //до теста выносим все переменные
    ContactDto contactDto;
    Response response;

    int id;

    @AfterMethod(onlyForGroups = {"positive"})
    public void afterTest(){
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);// id кого удаляем, 18 и 34 строчка
    }

    @Test(groups = ("positive"))
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
    @Test
    public void createContactWithoutFirstName(){
        contactDto = new ContactDto();
        contactDto.setLastName(faker.name().lastName());
        contactDto.setDescription(faker.lorem().sentence(4));
        doPostRequest(EndPoint.ADD_NEW_CONTACT, 400, contactDto);
    }

}
