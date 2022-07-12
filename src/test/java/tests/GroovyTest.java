package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static specs.Spec.request;

public class GroovyTest {
    static String userEmail = "lindsay.ferguson@reqres.in";


    @Test
    void testListUser(){
        given()
                .spec(request)
                .when()
                .get("/users?page=2")
                .then()
                .log().body()
                .body("data.findAll{it.email =~/.*?@reqres.in/}.email.flatten()",
                        hasItem(userEmail));
    }
}
