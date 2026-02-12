package br.com.sus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class TriagemResourceTest {

    @Test
    public void testCreateTriagemAndHistorico() {
        String pacienteJson = "{\"nome\":\"Maria\",\"cpf\":\"99988877766\",\"idade\":65,\"doencasPreExistentes\":\"\"}";

        Number pacienteId = given()
                .contentType("application/json")
                .body(pacienteJson)
                .when().post("/pacientes")
                .then()
                .statusCode(200)
                .extract().path("id");

        String triagemJson = "{\"pacienteId\":" + pacienteId.longValue() + ",\"sintomas\":\"dor no peito\"}";

        given()
                .contentType("application/json")
                .body(triagemJson)
                .when().post("/triagens")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("classificacao", is("VERMELHO"));

        given()
                .when().get("/pacientes/" + pacienteId.longValue() + "/historico")
                .then()
                .statusCode(200)
                .body("size()", is(1))
                .body("[0].classificacao", is("VERMELHO"));

        given()
                .when().get("/triagens")
                .then()
                .statusCode(200)
                .body("size()", greaterThanOrEqualTo(1));
    }

}
