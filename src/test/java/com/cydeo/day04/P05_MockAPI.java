package com.cydeo.day04;


import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P05_MockAPI {

    // https://e1023aa1-2ba3-455a-b8bd-32e628f58b48.mock.pstmn.io

    @BeforeAll
    public static void init(){

        baseURI = "https://e1023aa1-2ba3-455a-b8bd-32e628f58b48.mock.pstmn.io" ;
    }

    @Test
    public void test1(){

        given().log().uri().accept(ContentType.JSON)
                .when().get("/spartans").prettyPeek()
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .header("Transfer-Encoding" , notNullValue()) ;
    }
}
