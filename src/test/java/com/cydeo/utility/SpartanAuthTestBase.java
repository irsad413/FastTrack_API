package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class SpartanAuthTestBase {


    @BeforeAll
    public static void init(){
        baseURI= "http://34.202.158.24:7000" ;
        basePath = "/api" ;


    }

    @AfterAll
    public static void destroy(){

        RestAssured.reset();
    }

}
