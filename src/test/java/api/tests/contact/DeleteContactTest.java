package api.tests.contact;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import api.tests.ApiBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteContactTest extends ApiBase {
    int id;
    int wrongId;
    Response response;
    ContactDto contactDto;



    @BeforeMethod(onlyForGroups = {"positive"})
    public void precondition(){
        contactDto = createContact(); // все что сверху
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto); // contactDto тело нашего запроса = эта строчка записывает весь ответ
        id = response.jsonPath().getInt("id");// из ответа получим id
    }


    @Test(groups = ("positive")) //{} - ?
    public void deleteContactTest(){
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);
    }

    @Test
    public void deleteContactWithoutId() {
        int wrongId = getWrongId();
        response = doDeleteRequest(EndPoint.DELETE_CONTACT, 500, wrongId);
        Assert.assertEquals(response.jsonPath().getString("message"), ERROR_MESSAGE_FOR_CONTACT);
    }

}

