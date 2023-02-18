package api.tests;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Random;

public class ApiBase {
    //То что постоянно
    Faker faker = new Faker();
    ContactDto contactDto;

    final String BASE_URI = "http://phonebook.telran-edu.de:8080";
    final String API_KEY = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VybmFtZSI6InRlc3RAZ21haWwuY29tIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImV4cCI6MjEwNjk3ODI5Nn0.GM1wsoRV2QoAsD6wKmIk7N49DDpuCejK4BC9H9YItJvesH5vft8HO2uqTPnGQJwJ5oXKS2OILqP1yoanMnIMkA";

    protected final String ERROR_MESSAGE_FOR_CONTACT = "Error! This contact doesn't exist in our DB";
    protected final String ERROR_MESSAGE_FOR_EMAIL = "Error! This email doesn't exist in our DB";
        RequestSpecification spec = new RequestSpecBuilder() // чтото обшее для всех нашых методов
                .setBaseUri(BASE_URI)
                .setContentType(ContentType.JSON)
                .addHeader("Access-Token", API_KEY)
                .build();

        public Response doPostRequest(EndPoint endPoint, Integer responseCode, Object dto) {
            Response resp = RestAssured.given()
                    .spec(spec)
                    .body(dto)
                    .when()
                    .log().all()
                    .post(endPoint.getValue())
                    .then().log().all()
                    .extract().response();
            resp.then().assertThat().statusCode(responseCode);
            return resp;

        }
    public Response doDeleteRequest(EndPoint endPoint, Integer responseCode, int id){
        Response resp = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam("id", id)
                .log().all()
                .delete(endPoint.getValue())
                .then().log().all()
                .extract().response();
        resp.then().assertThat().statusCode(responseCode);
        return resp;
    }

        public Response doGetRequestWithParam(EndPoint endPoint, Integer responseCode, int id){
            Response resp = RestAssured.given()
            .spec(spec)
            .when()
            .pathParam("id", id)
            .log().all()
            .get(endPoint.getValue())
            .then().log().all()
            .extract().response();
            resp.then().assertThat().statusCode(responseCode);
            return resp;
        }
    public Response doGetRequest(EndPoint endPoint, Integer responseCode){
        Response resp = RestAssured.given()
                .spec(spec)
                .when()
                .log().all()
                .get(endPoint.getValue())
                .then().log().all()
                .extract().response();
        resp.then().assertThat().statusCode(responseCode);
        return resp;
    }
    public Response doPutRequest(EndPoint endPoint, Integer responseCode, Object dto) {
        Response resp = RestAssured.given()
                .spec(spec)
                .body(dto)
                .when()
                .log().all()
                .put(endPoint.getValue())
                .then().log().all()
                .extract().response();
        resp.then().assertThat().statusCode(responseCode);
        return resp;

    }
        public ContactDto createContact(){
            //все что внизу записано стандартом в ApiBase
            contactDto = new ContactDto(); // получился пустым
            contactDto.setFirstName(faker.name().firstName());
            contactDto.setLastName(faker.name().lastName());
            contactDto.setDescription(faker.lorem().sentence(5));// сгенерирует строку с пяти слов
            return contactDto;
        }
        public int getWrongId(){
            Random rnd = new Random();
            int wrongId = 100000 + rnd.nextInt(900000);
            return wrongId;
        }
    }


