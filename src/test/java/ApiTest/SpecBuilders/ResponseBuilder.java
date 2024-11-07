package ApiTest.SpecBuilders;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

/**
 * The ResponseBuilder class provides utility methods to build response specifications
 * for testing API endpoints.
 */
public class ResponseBuilder {
    static ResponseSpecification rspec;
    static ResponseSpecBuilder responsebuild;

    public static ResponseSpecification postResponse() {
        responsebuild = new ResponseSpecBuilder();
        responsebuild.expectStatusCode(201);
        responsebuild.expectContentType("application/json");

        return responsebuild.build();
    }
}