package tests;

import io.restassured.response.Response;
import models.LoginModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.name;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.BaseSpec.baseRequestSpec;
import static specs.BaseSpec.baseResponseSpec;
import static tests.TestData.login;
import static tests.TestData.password;

public class LoginTests extends TestBase {


    @Test
    void successfulLoginUiTest() {
        open("/login");
        $("#userName").setValue(login);
        $("#password").setValue(password).pressEnter();
        $("#userName-value").shouldHave(text(login));
    }

    @Test
    @Tag("api")
    void successfulLoginApiTest() {
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

            open("/favicon.ico");
            getWebDriver().manage().addCookie(new Cookie("userID", response.path("userId")));
            getWebDriver().manage().addCookie(new Cookie("expires", response.path("expires")));
            getWebDriver().manage().addCookie(new Cookie("token", response.path("token")));


            open("/profile");
            $("#userName-value").shouldHave(text(login));
        });

    }
}
