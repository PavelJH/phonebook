package api.tests.contact;

import api.model.ContactDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewConctactTest extends ApiBase {
    //до теста выносим все переменные
    Faker faker = new Faker();
    ContactDto contactDto;
    Response response;

    @Test
    public void createContactTest(){
        //все что внизу записано стандартом в ApiBase
        contactDto = new ContactDto(); // получился пустым
        contactDto.setFirstName(faker.name().firstName());
        contactDto.setLastName(faker.name().lastName());
        contactDto.setDescription(faker.lorem().sentence(5));// сгенерирует строку с пяти слов

    response = doPostRequest("/api/contact", 201, contactDto); // contactDto тело нашего запроса = эта строчка записывает ответ

        Assert.assertEquals(response.jsonPath().getString("firstName"), contactDto.getFirstName());
    }
}
