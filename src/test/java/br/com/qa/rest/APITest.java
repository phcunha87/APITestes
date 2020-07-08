package br.com.qa.rest;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class APITest {
	
	public Integer ID;
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "http://localhost:8001/tasks-backend";
		
		
	}
	
	
	@Test
	public void deveRetornarAsTasks() {
		 RestAssured.given()
		.when()
		   .get("/todo")
		.then()
		   .statusCode(200)
		;
		
	}
	
	@Test
	public void deveSalvarUmaTask() {
	    RestAssured.given()
		.body("{\"task\":\"Teste QA 01\",\"dueDate\":\"2020-12-30\"}")
		.contentType(ContentType.JSON)
		   .when()
		.post("/todo")
		    .then()
		    .log().all()
		 .statusCode(201)
		;
		
	}
	
	@Test
	public void naoDevecadastrartarefaInvalida() {
		RestAssured.given()
		.body("{\"task\":\"Teste QA 01\",\"dueDate\":\"1010-12-30\"}")
		.contentType(ContentType.JSON)
		.when()
		.post("/todo")
		.then()
		.log().all()
		.statusCode(400)
		.body("message", CoreMatchers.is("Due date must not be in past"))
		;
	}
	
	

}
