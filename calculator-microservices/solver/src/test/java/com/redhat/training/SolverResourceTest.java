package com.redhat.training;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import javax.ws.rs.core.Response;

@QuarkusTest
@TestHTTPEndpoint(SolverResource.class)
public class SolverResourceTest {


	@Test
public void simpleSum() {
    given()
    .when().get("3+2")
    .then()
        .statusCode(200)
        .body(is("5.0"));
}


	@Test
public void simpleMultiplication() {
    given()
    .when().get("3*2")
    .then()
        .statusCode(200)
        .body(is("6.0"));
}



@Test
public void wrongValue() {
    given()
    .when().get("3*a")
    .then()
        .statusCode(Response.Status.BAD_REQUEST.getStatusCode());
}




}
