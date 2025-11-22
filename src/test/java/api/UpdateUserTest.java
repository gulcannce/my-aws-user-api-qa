package api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdateUserTest {

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
    public void updateUserTest() {
        int userId = 1;

        String requestBody = """
                {
                    "name": "Updated Name",
                    "email": "updated@example.com"
                }
                """;

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
            .pathParam("id", userId)
        .when()
            .put(baseUrl + "/users/{id}")
        .then()
            .statusCode(200)
            .body("name", equalTo("Updated Name"))
            .body("email", equalTo("updated@example.com"));
    }
}
