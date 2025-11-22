package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserTest {

    private static final String DEFAULT_LOCAL = "http://localhost:3000";
    private String baseUrl;

    @BeforeEach
    public void setup() {
        baseUrl = System.getProperty("api.baseUrl");
        if (baseUrl == null || baseUrl.isBlank()) {
            baseUrl = System.getenv("API_BASE_URL");
        }
        if (baseUrl == null || baseUrl.isBlank()) {
            baseUrl = DEFAULT_LOCAL;
        }
    }

    @Test
    public void getUserTest() {
        int userId = 1;

        int actualId = given()
            .pathParam("id", userId)
        .when()
            .get(baseUrl + "/users/{id}")
        .then()
            .statusCode(200)
            .extract()
            .jsonPath()
            .getInt("id");

        assertEquals(userId, actualId, "Returned id should match requested userId");
    }
}
