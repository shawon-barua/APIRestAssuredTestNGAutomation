package ApiTest.SpecBuilders;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static ApiTest.endpoints.Routes.base_URL;

/**
 * The RequestBuilder class provides methods to configure and build request specifications
 * for API testing. It utilizes a RequestSpecBuilder to set common request parameters such as
 * the base URI and content type.
 */
public class RequestBuilder {
    static RequestSpecification rspec;
    static RequestSpecBuilder build;

    public static RequestSpecification requestSpec() {
        build = new RequestSpecBuilder();
        build.setBaseUri(base_URL);
        build.setContentType(ContentType.JSON);

        return build.build();
    }
}