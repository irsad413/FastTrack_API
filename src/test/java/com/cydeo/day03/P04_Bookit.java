package com.cydeo.day03;

import com.cydeo.pojo.Campus;
import com.cydeo.utility.BookitTestBase;
import com.cydeo.utility.BookitUtils;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_Bookit extends BookitTestBase {

    @Test
    public void getCampuses(){

     String email = "raymond@cydeo.com" ;
     String password = "abs123" ;



        // Get Token

        String token = BookitUtils.getToken(email, password);


        // Get Campuses
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when().get("/api/campuses").prettyPeek()
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();


        // De-serialization
        List<Campus> allCampuses = jsonPath.getList("" , Campus.class) ;

        System.out.println("allCampuses = " + allCampuses);


        // Find out how many campus we have
        System.out.println("allCampuses.size() = " + allCampuses.size());

        // Find out how many cluster we have in VA
        System.out.println("allCampuses.get(0).getClusterList().size() = " + allCampuses.get(0).getClusterList().size());

        // Find out how many room we have in light-side
        System.out.println("allCampuses.get(0).getClusterList().get(0).getRoomList().size() = " + allCampuses.get(0).getClusterList().get(0).getRoomList().size());


        // Find me all rooms where capacity is 6?





    }


}
