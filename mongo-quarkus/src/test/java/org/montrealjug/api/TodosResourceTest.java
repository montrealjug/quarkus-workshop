package org.montrealjug.api;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@QuarkusTestResource(DataResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TodosResourceTest {

    @Test
    @Order(1)
    public void testPutEndpoint() {
        Todo todo = new Todo("thisIsMyTodoTitle", true);
        JsonPath result = given()
                .body(todo)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when()
                .put("/todos")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .extract()
                .response()
                .jsonPath();


        assertEquals("thisIsMyTodoTitle", result.getString("title"));
        assertEquals(true, result.getBoolean("completed"));
    }

    @Test
    @Order(2)
    public void testGetEndpoint() {
        JsonPath result = given()
                .when()
                .get("/todos")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .extract()
                .response()
                .jsonPath();

        System.out.println(result.prettyPrint());

        assertEquals("thisIsMyTodoTitle", result.getString("title[0]"));
        assertEquals(true, result.getBoolean("completed[0]"));
    }

}
