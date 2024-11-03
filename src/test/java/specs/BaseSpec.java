package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.*;

public class BaseSpec {

    public static RequestSpecification baseRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType("application/json; charset=utf-8")
            .log().all();


    public static ResponseSpecification baseResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(ALL)
            .build();
}