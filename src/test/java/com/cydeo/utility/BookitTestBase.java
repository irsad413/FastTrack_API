package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class BookitTestBase {

    @BeforeAll
    public static void init(){
        baseURI= "https://api.qa.bookit.cydeo.com" ;



    }

    @AfterAll
    public static void destroy(){

        RestAssured.reset();
    }

}
