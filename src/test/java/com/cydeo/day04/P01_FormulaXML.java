package com.cydeo.day04;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;


public class P01_FormulaXML {

    // http://ergast.com/api/f1/drivers

    @Test
    public void xmlTest(){

        Response response = get("http://ergast.com/api/f1/drivers").prettyPeek()
                .then()
                .statusCode(200).extract().response();

        XmlPath xmlPath = response.xmlPath() ;

        // get me first Driver Given Name
        // path  --> MRData.DriverTable.Driver[0].GivenName
        String firstDriverName = xmlPath.getString("MRData.DriverTable.Driver[0].GivenName") ;
        System.out.println("firstDriverName = " + firstDriverName);

        // all Drivers Given Name
        // path --> MRData.DriverTable.Driver.GivenName
        List <String> allDriverName = xmlPath.getList("MRData.DriverTable.Driver.GivenName") ;
        System.out.println("allDriverName = " + allDriverName);
        System.out.println(allDriverName.size());

        //print out first driver driverId attribute
        // path--> MRData.DriverTable.Driver[0].@driverId
        String driverID = xmlPath.getString("MRData.DriverTable.Driver[0].@driverId") ;
        System.out.println("driverID = " + driverID);

        //print out all driverId attribute
        List<String> allDriverID = xmlPath.getList("MRData.DriverTable.Driver.@driverId") ;
        System.out.println("allDriverID = " + allDriverID);

        // Print out a;; driver given name if their nationality is Italian
        // path--> MRData.DriverTable.Driver.findAll {it.Nationality=='Italian'}.GivenName
        List<String> allItalianDrivers = xmlPath.getList("MRData.DriverTable.Driver.findAll {it.Nationality=='Italian'}.GivenName") ;
        System.out.println("allItalianDrivers = " + allItalianDrivers);

    }
}
