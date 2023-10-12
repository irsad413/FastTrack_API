package com.cydeo.day04;

import com.cydeo.utility.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_SpartanSpecTest extends SpartanAuthTestBase {

    @Test
    public void test1(){

        given().spec(reqSpec("user" , "user"))
                .when().get("/spartans")
                .then().spec(resSpec(200)) ;
    }


    @Test
    public void test2(){

        // Request Spec
        RequestSpecification requestSpecification = given().accept(ContentType.JSON)
                .auth().basic("admin", "admin");

        // Response Spec
        ResponseSpecification responseSpecification = expect().contentType(ContentType.JSON)
                .statusCode(200);


        given().spec(requestSpecification)
                .pathParam("id" , 2)
                .when().get("/spartans/{id}")
                .then().spec(responseSpecification) ;


    }


    @Test
    public void test3(){



        given().spec(reqSpec("admin" , "admin"))
                .pathParam("id" , 2)
                .when().get("/spartans/{id}")
                .then().spec(resSpec(200)) ;


    }






}
