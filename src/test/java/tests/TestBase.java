package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;

import static com.codeborne.selenide.Selenide.closeWebDriver;


public class TestBase {
    @BeforeAll
    public static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        RestAssured.baseURI = "https://demoqa.com";
    }
    @AfterEach
    void shutDown() {
        closeWebDriver();
    }
}