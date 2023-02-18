package api.tests.contact;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.model.contact.UpdateContactDTO;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateContactTest extends ApiBase {

    Faker faker = new Faker();
    ContactDto contactDto;
   // UpdateContactDTO updateContactDto;
    Response response;
    int id;
    @BeforeMethod
    public void precondition(){
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");
    }

    @AfterMethod()
    public void afterTest() {

        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);
    }

    @Test
    public void updateContactTest(){

        UpdateContactDTO updateContactDTO = new UpdateContactDTO();
        updateContactDTO.setId(id);
        updateContactDTO.setFirstName(faker.name().firstName());
        updateContactDTO.setLastName(faker.name().lastName());
        updateContactDTO.setDescription(faker.lorem().sentence(4));

        doPutRequest(EndPoint.UPDATE_CONTACT, 200, updateContactDTO);
        response = doGetRequestWithParam(EndPoint.GET_CONTACT_BY_CONTACT_ID, 200, id);
        Assert.assertEquals(response.jsonPath().getInt("id"), id);
        Assert.assertEquals(response.jsonPath().getString("firstName"), updateContactDTO.getFirstName());
        Assert.assertEquals(response.jsonPath().getString("lastName"), updateContactDTO.getLastName());
        Assert.assertEquals(response.jsonPath().getString("description"), updateContactDTO.getDescription());
    }
}
