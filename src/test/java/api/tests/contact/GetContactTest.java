package api.tests.contact;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.tests.ApiBase;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class GetContactTest extends ApiBase {
    int id;
    Response response;
    ContactDto contactDto;
    int wrongId;
    @BeforeMethod(onlyForGroups = {"positive"})
    public void precondition(){
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto); // contactDto тело нашего запроса = эта строчка записывает весь ответ
        id = response.jsonPath().getInt("id");// из ответа получим id
    }
    @AfterMethod(onlyForGroups = {"positive"})
    public void afterTest() {
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);
    }

    @Test(groups = ("positive"))
    public void getListContactsTest(){
        response = doGetRequest(EndPoint.GET_LIST_OF_CONTACTS, 200);
        List<Map<String, Object>> contacts = response.as(new TypeRef<List<Map<String, Object>>>() {});
        Assert.assertTrue(contacts.size() > 0);
    }

@Test(groups = ("positive"))
    public void getContactById(){
        response = doGetRequestWithParam(EndPoint.GET_CONTACT_BY_CONTACT_ID, 200, id);
        Assert.assertEquals(response.jsonPath().getInt("id"), id);
        Assert.assertEquals(response.jsonPath().getString("firstName"), contactDto.getFirstName());
        Assert.assertEquals(response.jsonPath().getString("lastName"), contactDto.getLastName());
        Assert.assertEquals(response.jsonPath().getString("description"), contactDto.getDescription());
}
@Test
    public void getContactByIdWithWrongId(){
        wrongId = getWrongId();
    response = doGetRequestWithParam(EndPoint.GET_CONTACT_BY_CONTACT_ID, 200, wrongId);
    Assert.assertEquals(response.jsonPath().getString("message"), ERROR_MESSAGE_FOR_CONTACT);

}

}
