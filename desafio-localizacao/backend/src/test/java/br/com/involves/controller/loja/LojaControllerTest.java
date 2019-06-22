package br.com.involves.controller.loja;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LojaControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired 
	TestRestTemplate restTemplate;
	
	@Test
	public void testGetAll() {
		Response post = RestAssured.given().port(port).body("{\"username\" : \"driver04\", \"password\" : \"driver04pw\"}").accept(ContentType.JSON).contentType(ContentType.JSON).post("/token/generate");
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", post.getBody().asString());

        HttpEntity<String> entity = new HttpEntity<String>(headers);
        
        ResponseEntity<List> response = restTemplate.exchange("/lojas", HttpMethod.GET, entity, List.class);

	}

}
