package com.sparta.graphql;

import com.sparta.utils.Config;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class TestBase {



    protected static final String BASE_URI = Config.getGitHubBaseUri();
    protected static final String TOKEN = Config.getToken();
    protected static final String OWNER = Config.getOwner();
    protected static final String REPO = Config.getRepo();

    // Read a .graphql file from resources.graphql
    protected static String readQuery(String filename) throws IOException {
        return Files.readString(Paths.get("src/test/resources/graphql/" +filename));
    }

    protected static Response executeQuery(String query, String operationName, Map<String, Object> variables){
        Map<String, Object> body = Map.of(
                "query", query,
                "operationName", operationName,
                "variables", variables
        );

        return RestAssured
                .given()
                    .baseUri(BASE_URI) // Set the GraphQL endpoint URL
                    .header("Authorization", "Bearer " + TOKEN) // Add authentication header
                    .contentType(ContentType.JSON) // Set Content-Type to application/json
                    .body(body) // Attach the request body (the map above)
                    .log().all() // Log request details for debugging
                .when()
                    .post() // Send a POST request
                .then()
                .   log().all() // Log response details
                    .extract().response(); // Extract the response for assertions
    }
}
