package api.tests.address;

import api.enums.EndPoint;
import api.model.address.AddressDto;
import api.model.contact.ContactDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewAddressTest extends ApiBase {
    Faker faker = new Faker();
    ContactDto contactDto;
    AddressDto addressDto;
    Response response;
    int id;
    int wrongId;


    @BeforeMethod(onlyForGroups = {"positive"})
    public void precondition(){
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");
    }

    @AfterMethod(onlyForGroups = {"positive"})
    public void afterTest() {
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);
    }

    @Test(groups = {"positive"})
    public void addNewAddressTest() {
        addressDto = new AddressDto();
        addressDto.setCity(faker.address().city());
        addressDto.setCountry(faker.address().country());
        addressDto.setStreet(faker.address().streetAddress());
        addressDto.setZip(faker.address().zipCode());
        addressDto.setContactId(id);

        doPostRequest(EndPoint.ADD_NEW_ADDRESS, 201, addressDto);
    }

    @Test
    public void addNewAddressWithWrongId() {
        wrongId = getWrongId();
        addressDto = new AddressDto();
        addressDto.setCity(faker.address().city());
        addressDto.setCountry(faker.address().country());
        addressDto.setStreet(faker.address().streetAddress());
        addressDto.setZip(faker.address().zipCode());
        addressDto.setContactId(wrongId);
        response = doPostRequest(EndPoint.ADD_NEW_ADDRESS, 500, addressDto);

        Assert.assertEquals(response.jsonPath().getString("message"), ERROR_MESSAGE_FOR_CONTACT);
    }

    @Test
    public void addNewAddressWithoutContactId() {
        addressDto = new AddressDto();
        addressDto.setCity(faker.address().city());
        addressDto.setCountry(faker.address().country());
        addressDto.setStreet(faker.address().streetAddress());
        addressDto.setZip(faker.address().zipCode());

        response = doPostRequest(EndPoint.ADD_NEW_ADDRESS, 500, addressDto);

        Assert.assertEquals(response.jsonPath().getString("message"), ERROR_MESSAGE_FOR_CONTACT);
        // TODO Разобраться почему вставляет contactId = 0
        // разобраться именно с int
    }
}
