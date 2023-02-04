package api.tests.contact;

import api.enums.EndPoint;
import api.model.ContactDto;
import api.tests.ApiBase;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class DeleteContactTest extends ApiBase {
    int id;
    Response response;
    ContactDto contactDto;

    String errorMessage = "Error! This contact doesn't exist in our DB";

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
        Random rnd = new Random();
        int wrongId = 100000 + rnd.nextInt(900000);
        response = doDeleteRequest(EndPoint.DELETE_CONTACT, 500, wrongId);
        Assert.assertEquals(response.jsonPath().getString("message"), errorMessage);
    }

}

