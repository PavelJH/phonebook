package api.tests.phone;

import api.enums.EndPoint;
import api.model.address.AddressDto;
import api.model.contact.ContactDto;
import api.model.phone.PhoneDTO;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewPhoneTest extends ApiBase{
    Faker faker = new Faker();

    ContactDto contactDto;
    Response response;
    PhoneDTO phoneDTO;

    int id;
    @BeforeMethod()
    public void precondition(){
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");
    }
    @AfterMethod()
    public void afterTest(){
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);// id кого удаляем, 18 и 34 строчка
    }

    @Test
    public void addNewPhoneNumber(){
        phoneDTO = new PhoneDTO();
        phoneDTO.setPhoneNumber(faker.phoneNumber().phoneNumber());
        phoneDTO.setCountryCode("+" + faker.phoneNumber().subscriberNumber(2));
        phoneDTO.setContactId(id);
        doPostRequest(EndPoint.ADD_NEW_PHONE, 201, phoneDTO);
    }
}
