package tests;

import io.restassured.response.Response;
import models.LoginModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.BaseSpec.baseRequestSpec;
import static specs.BaseSpec.baseResponseSpec;

public class LoginTests extends TestBase {


    @Test
    @Tag("api")
    void successfulLoginTest() {
        LoginModel authData = new LoginModel("drzdvsk", "qW3rrrty!!");

        Response response = step("Login request", () ->
                given(baseRequestSpec)
                        .body(authData)

                        .when()
                        .post("/Account/v1/Login")

                        .then()
                        .spec(baseResponseSpec)
                        .extract().response());

        step("Check response", () -> {
            assertThat(response.statusCode()).isEqualTo(200);
        });
    }
}
    /*    @Test
    @Tag("api")
    void successfulLoginWithSpecsTest1() {
        LoginModel authData = new LoginModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        Response response = step("Login request", () ->
                given(baseRequestSpec)
                        .body(authData)

                        .when()
                        .post("/login")

                        .then()
                        .spec(baseResponseSpec)
                        .extract().response());

        LoginResponseModel responseModel = response.as(LoginResponseModel.class);

        step("Check response", () -> {
            assertThat(response.statusCode()).isEqualTo(200);
            assertThat(responseModel.getToken()).isNotNull();
        });
    }
}
*/