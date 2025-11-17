package com.sparta.rest.tests;

import com.sparta.rest.pojos.BulkPostcodeResponse;
import com.sparta.utils.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BulkPostcodeLookup {
    private static Response response;
    private static final String BASE_URI = Config.getBasePostcodeUri();

    private static Map<String, String[]> requestBody = new HashMap<>();

    private static BulkPostcodeResponse pojoResponse;
    @BeforeAll
    public static void beforeAll(){
        requestBody.put("postcodes", new String[]{"EC2Y 5AS", "W1A 1AA", "M32 0JG"});
//        response = RestAssured
//                .given()
//                    .baseUri(BASE_URI)
//                    .basePath("postcodes/")
//                    .header("Accept", "application.json")
//                    .header("Content-Type", "application/json")
//                    //.contentType(ContentType.JSON)
//                    .body(requestBody)
//                .when()
//                    .post()
//                .thenReturn();


        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath("postcodes/")
                .headers(Map.of(
                        "Accept", "application.json",
                        "Content-Type", "application/json"
                ))

                //.contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post()
                .thenReturn();

        pojoResponse = response.as(BulkPostcodeResponse.class);

    }


    @Test
    @DisplayName("Status code 200 returned")
    public void testStatusCode200(){
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("Response contains all requested postcodes")
    public void testAllPostcodesPresent(){
        var result = response.jsonPath().getList("result");
        assertThat(result.size(), is(3));
        assertThat(response.jsonPath().getString("result[0].query"), is("EC2Y 5AS"));
        assertThat(response.jsonPath().getString("result[1].query"), is("W1A 1AA"));
        assertThat(response.jsonPath().getString("result[2].query"), is("M32 0JG"));
    }

    @Test
    @DisplayName("Response contains all requested postcodes - with Pojo")
    public void testAllPostcodesPresent_withPojo(){
        assertThat(pojoResponse.getStatus(), is(200));
        assertThat(pojoResponse.getResult().size(), is(3));
        assertThat(pojoResponse.getResult().get(0).getQuery(), is("EC2Y 5AS"));
        assertThat(pojoResponse.getResult().get(1).getQuery(), is("W1A 1AA"));
        assertThat(pojoResponse.getResult().get(2).getQuery(), is("M32 0JG"));
    }


}
