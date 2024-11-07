package ApiTest.Test.Contact;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static ApiTest.SpecBuilders.RequestBuilder.requestSpec;
import static ApiTest.SpecBuilders.ResponseBuilder.postResponse;
import static ApiTest.Utils.ContactFactory.readJsonData;
import static ApiTest.Utils.ContactFactory.readToken;
import static ApiTest.endpoints.Routes.add_contact;
import static ApiTest.endpoints.Routes.delete_contact;

/**
 * The ContactTest class contains unit tests for performing CRUD operations on contacts
 * via API endpoints. It uses the RestAssured library to send HTTP requests and verify responses.
 */
public class ContactTest {
    private Map<String, Object> contactData;
    private String token;
    private String contactId;

    @BeforeClass
    public void setup() {
        contactData = readJsonData();
        token = readToken();
        System.out.println(token);
    }

    @Test
    public void testCreateContact() {
        Response response = RestAssured.given()
                .log().all()
                .spec(requestSpec())
                .header("Authorization", "Bearer " + token)
                .body(contactData)
                .when()
                .post(add_contact)
                .then()
                .log().all()
                .spec(postResponse())
                .extract().response();
        Assert.assertEquals(contactData.get("firstname"), response.jsonPath().get("firstname"));
        contactId = response.jsonPath().get("_id");
    }

    @Test(dependsOnMethods = "testCreateContact")
    public void testDeleteContact() {
        RequestSpecification deleteProd = RestAssured.given()
                .log().all()
                .header("Authorization", "Bearer " + token)
                .spec(requestSpec());

        Response deleteContactResponse = deleteProd.when().delete(delete_contact + "/" + contactId)
                .then().log().all()
                .extract().response();

        String responseBody = deleteContactResponse.getBody().asString();
        Assert.assertTrue(responseBody.contains("Contact deleted"));

    }


}
