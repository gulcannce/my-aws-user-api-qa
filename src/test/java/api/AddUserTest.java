package api;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class AddUserTest {

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
    public void addUserTest() {
        String requestBody = """
                {
                    "name": "John Doe",
                    "email": "john@example.com"
                }
                """;

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post(baseUrl + "/users")
        .then()
            .statusCode(201)
            .body("id", notNullValue());
    }
}
