package com.registerstudent;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public abstract class BaseTest {
	
	public static RequestSpecification requestSpecification;
	
	@BeforeClass
	public static void setup() {
		
		requestSpecification = new RequestSpecBuilder()
				.log(LogDetail.METHOD)
				.log(LogDetail.URI)
				.build();
		
		requestSpecification.auth().basic("studantapi","@studant@123");
		
		RestAssured.requestSpecification = requestSpecification;
		
	}
}
