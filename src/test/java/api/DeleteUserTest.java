package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class DeleteUserTest {

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
    public void deleteUserTest() {
        int userId = 1;

        given()
            .pathParam("id", userId)
        .when()
            .delete(baseUrl + "/users/{id}")
        .then()
            .statusCode(200);
    }
}
