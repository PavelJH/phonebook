package api.tests.phone;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.model.email.EmailDto;
import api.model.phone.PhoneDTO;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetPhoneTest extends ApiBase {
    Faker faker = new Faker();

    ContactDto contactDto;
    Response response;
    PhoneDTO phoneDTO;
    int id;
    Response responseForPhone;
    int phoneId;
    @BeforeMethod()
    public void precondition(){
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");

        phoneDTO = new PhoneDTO();
        phoneDTO.setPhoneNumber(faker.phoneNumber().phoneNumber());
        phoneDTO.setCountryCode("+" + faker.phoneNumber().subscriberNumber(2));
        phoneDTO.setContactId(id);
        doPostRequest(EndPoint.ADD_NEW_PHONE, 201, phoneDTO);

        responseForPhone = doGetRequestWithParam(EndPoint.GET_LIST_OF_PHONES_BY_CONTACT_ID, 200, id);
        phoneId = responseForPhone.jsonPath().getInt("[0].id");
    }
    @AfterMethod()
    public void afterTest(){
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);
    }

    @Test
    public void getListOfPhonesByContactId() {
        Assert.assertEquals(responseForPhone.jsonPath().getInt("[0].id"), phoneId);
        Assert.assertEquals(responseForPhone.jsonPath().getString("[0].phoneNumber"), phoneDTO.getPhoneNumber());
        Assert.assertEquals(responseForPhone.jsonPath().getString("[0].countryCode"), phoneDTO.getCountryCode());
        Assert.assertEquals(responseForPhone.jsonPath().getInt("[0].contactId"), id);
    }
}
