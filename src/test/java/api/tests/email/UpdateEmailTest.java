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

public class UpdateEmailTest extends ApiBase {
    Faker faker = new Faker();
    int contactId;
    int emailId;
    int wrongEmailId;
    int wrongContactId;
    Response response;
    Response responseForEmail;
    ContactDto contactDto;
    AddEmailDto addEmailDto;
    String email = faker.internet().emailAddress();
    String newEmail = faker.internet().emailAddress();
    EmailDto emailDto;




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
    public void updateEmail(){
        emailDto = new EmailDto();
        emailDto.setId(emailId);
        emailDto.setEmail(newEmail);
        emailDto.setContactId(contactId);

        doPutRequest(EndPoint.UPDATE_EMAIL, 200, emailDto);
    }
    @Test
    public void updateEmailWithWrongEmailId(){
        wrongEmailId = getWrongId();
        emailDto = new EmailDto();
        emailDto.setId(wrongEmailId);
        emailDto.setEmail(newEmail);
        emailDto.setContactId(contactId);

        Response responseForUpdate = doPutRequest(EndPoint.UPDATE_EMAIL, 500, emailDto);

        Assert.assertEquals(responseForUpdate.jsonPath().getString("message"), ERROR_MESSAGE_FOR_EMAIL);
    }
}
