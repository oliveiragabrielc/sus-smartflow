package br.com.sus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class PacienteResourceTest {

    @Test
    public void testCreateListAndGetPaciente() {
        String pacienteJson = "{\"nome\":\"Joao\",\"cpf\":\"11122233344\",\"idade\":30,\"doencasPreExistentes\":\"\"}";

        Number id = given()
                .contentType("application/json")
                .body(pacienteJson)
                .when().post("/pacientes")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("nome", is("Joao"))
                .extract().path("id");

        given()
                .when().get("/pacientes")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));

        given()
                .when().get("/pacientes/" + id.longValue())
                .then()
                .statusCode(200)
                .body("id", is(id.intValue()))
                .body("nome", is("Joao"));

        given()
                .when().get("/pacientes/999999")
                .then()
                .statusCode(404);
    }

}
