package ApiTest.Test.User;

import ApiTest.POJO.userLogin;
import ApiTest.Utils.UserFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static ApiTest.SpecBuilders.RequestBuilder.requestSpec;
import static ApiTest.SpecBuilders.ResponseBuilder.postResponse;
import static ApiTest.endpoints.Routes.create_user_endpoint;
import static ApiTest.endpoints.Routes.user_login;
import static io.restassured.RestAssured.given;

/**
 * The UserTest class is a collection of unit test cases for testing user-related API functionalities,
 * including user creation and user login.
 * It uses various utilities and libraries to facilitate the API testing process.
 */
public class UserTest {
    Properties props = new Properties();
    private Map<String, String> user;
    private String token;

    @BeforeClass
    public void setup() {
        user = UserFactory.getRandomUser();
    }

    @Test
    public void testCreateUser() {
        Response response = RestAssured.given()
                .log().all()
                .spec(requestSpec())
                .body(user)
                // ... request specifications ...
                .when()
                .post(create_user_endpoint)
                .then()
                .log().all()
                .spec(postResponse())
                .extract().response();
        String token = response.jsonPath().get("token");
        user.put("token", token);
    }

    @Test(dependsOnMethods = "testCreateUser")
    public void userLogin() {
        userLogin userCredentials = new userLogin();
        userCredentials.setEmail(user.get("email"));
        userCredentials.setPassword(user.get("password"));

        RequestSpecification reqLogin = given().log().all().spec(requestSpec()).body(userCredentials);
        Response loginResponse = reqLogin.when().post(user_login).then().log().all().extract().response();
        token = loginResponse.jsonPath().get("token");

    }

    @AfterClass
    public void setToken() {

        props.setProperty("token", token);
        System.out.println("Token value1: " + token);
        try (FileOutputStream output = new FileOutputStream("src/test/resources/config.properties")) {
            props.store(output, "Configuration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}