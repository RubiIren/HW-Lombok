package tests;

import models.UserCreated;
import models.UserData;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.Spec.request;
import static specs.Spec.responseSpec;

public class ModelsTests {
    static String UserEmail = "janet.weaver@reqres.in",
            UserFirstName = "Janet",
            UserLastName = "Weaver",
            bodyRegistration = "{ \"email\": \"eve.holt@reqres.in\", " +
                    "\"password\": \"pistol\" }",
            token = "QpwL5tke4Pnpja7X4";


    @Test
    void testUser() {
        UserData data = given()
                .spec(request)
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec)
                .log().body()
                .extract().as(UserData.class);

        assertEquals(2, data.getUser().getId());
        assertEquals(UserEmail, data.getUser().getEmail());
        assertEquals(UserFirstName, data.getUser().getFirstName());
        assertEquals(UserLastName, data.getUser().getLastName());
    }

    @Test
    void postRegistrationUserTestSpecAndLombok() {
        UserCreated data = given()
                .spec(request)
                .body(bodyRegistration)
                .when()
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .spec(responseSpec)
                .extract().as(UserCreated.class);
        assertEquals(token, data.getToken());
        assertEquals(4, data.getId());
    }
}
