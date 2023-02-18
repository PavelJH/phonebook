package api.tests.email;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.model.email.AddEmailDto;
import api.model.email.EmailDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetEmailByIdTest extends ApiBase {
    Faker faker = new Faker();
    int contactId;
    int emailId;
    int wrongId;
    Response response;
    Response responseForEmail;
    ContactDto contactDto;
    AddEmailDto addEmailDto;
    String email = faker.internet().emailAddress();



    @BeforeMethod()
    public void precondition(){ // тут создаем новый контакт
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto); // contactDto тело нашего запроса = эта строчка записывает весь ответ
        contactId = response.jsonPath().getInt("id");// из ответа получим id

        // создаем email
        addEmailDto = new AddEmailDto();
        addEmailDto.setEmail(email); // создаем email
        addEmailDto.setContactId(contactId);


        doPostRequest(EndPoint.ADD_NEW_EMAIL, 201, addEmailDto);
        responseForEmail = doGetRequestWithParam(EndPoint.GET_LIST_OF_EMAILS_BY_CONTACT_ID, 200, contactId);
        // записываем в перемунную emailId
        emailId = responseForEmail.jsonPath().getInt("[0].id");
    }

    @AfterMethod()// этот метод удаляет контакт
    public void afterTest(){
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, contactId);// id кого удаляем, 18 и 34 строчка
    }

    @Test
    public void getEmailByEmailIdTest(){
        response = doGetRequestWithParam(EndPoint.GET_EMAIL_BY_EMAIL_ID, 200, emailId);
        EmailDto emailDto = response.as(EmailDto.class);

        Assert.assertEquals(emailDto.getId(), emailId);
        Assert.assertEquals(emailDto.getEmail(), email);
        Assert.assertEquals(emailDto.getContactId(), contactId);
    }
    @Test
    public void getListOfEmailByContactIdTest(){
        //Assert.assertTrue(emailId);
        Assert.assertEquals(responseForEmail.jsonPath().getString("[0].email"), email);
        Assert.assertEquals(responseForEmail.jsonPath().getInt("[0].contactId"), contactId);
    }

}
