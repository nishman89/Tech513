package com.sparta.graphql;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class GetAllIssuesTests extends TestBase {

    private static Response response;

    @BeforeAll
    static void setUp() throws IOException {
        // Read the graphql query from the file
        String query = readQuery("GetAllOpenIssues.graphql");

        //prepare variables
        Map<String, Object> variables = Map.of(
                "owner", OWNER,
                "repo", REPO
        );

        response = executeQuery(query, "GetAllOpenIssues", variables);
    }

    @Test
    @DisplayName("Get All Issues - Status code is 200")
    void testStatusCode(){
        assertThat(response.statusCode(), is(200));
    }

    @Test
    @DisplayName("No errors")
    void testErrors(){
        assertThat(response.jsonPath().getList("errors"), is(nullValue()));
    }

    @Test
    @DisplayName("Get All Issues — First issue has correct title")
    void testFirstIssueTitle() {
        String firstIssueTitle = response.jsonPath().getString("data.repository.issues.nodes[0].title");
        assertThat("First issue title should be 'My first issue'",
                firstIssueTitle, is("My first issue"));
    }

    @Test
    @DisplayName("Get All Issues — First issue has correct body")
    void testFirstIssueBody() {
        String firstIssueBody = response.jsonPath().getString("data.repository.issues.nodes[0].body");
        assertThat("First issue body should be 'Hello everyone'",
                firstIssueBody, is("Hello everyone!"));
    }

    @Test
    @DisplayName("Get All Issues — All issues have state OPEN")
    void testAllIssuesAreOpen() {
        List<Map<String, Object>> nodes = response.jsonPath()
                .getList("data.repository.issues.nodes");

        for (Map<String, Object> issue : nodes) {
            String state = (String) issue.get("state");
            assertThat("All issues should be OPEN", state, is("OPEN"));
        }
}
}
