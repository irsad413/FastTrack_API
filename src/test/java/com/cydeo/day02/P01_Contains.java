package com.cydeo.day02;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class P01_Contains extends HrTestBase {

    @Test
    public void getSingleRegion(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("id", 2)
                .when().get("/regions/{id}").prettyPeek();

        assertEquals(200 , response.statusCode());


        assertEquals("application/json" , response.contentType());


        assertTrue(response.asString().contains("Americas"));










    }
}
