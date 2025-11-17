package com.sparta.rest.tests;

import com.sparta.rest.pojos.SinglePostcodeResponse;
import com.sparta.utils.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class SinglePostCodeTest {

    private static Response response;

    private static SinglePostcodeResponse pojoResponse;

    private static final String BASE_URI = Config.getBasePostcodeUri();
    @BeforeAll
    static void beforeAll(){
        response = RestAssured
                .given()
                .baseUri(BASE_URI)
                .basePath("/postcodes")
                .header("Accept", "text/json")
                .when()
                .log().all()
                .get("/EC2Y5AS")
                .thenReturn();
        pojoResponse = response.as(SinglePostcodeResponse.class);
    }

    @Test
    @DisplayName("Status code 200 returned")
    void testStatusCode200(){
        assertThat(response.statusCode(), is(200));
        assertThat(pojoResponse.getStatus(), is(200));
    }

    @Test
    @DisplayName("The server name in the headers is cloudflare")
    void testServerName(){
        assertThat(response.header("Server"), is("cloudflare"));
    }


    @Test
    @DisplayName("Correct postcode returned in response")
    void testCorrectPostcodeInResponse(){
        assertThat(response.jsonPath().getString("result.postcode"), is("EC2Y 5AS"));
        assertThat(pojoResponse.getResult().getPostcode(), is("EC2Y 5AS"));
    }

//    @Test
//    @DisplayName("Status code 200 returned Alt")
//    void testStatusCode200_alt(){
//        RestAssured
//                .get("https://api.postcodes.io/postcodes/EC2Y5AS")
//                .then()
//                .assertThat()
//                .statusCode(200);
//    }
}
