package br.com.involves.controller.funcionario;

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
import org.springframework.web.client.RestClientException;

import br.com.involves.model.Funcionario;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class FuncionarioControllerFailTest {

	@LocalServerPort
	private int port;
	
	@Autowired 
	TestRestTemplate restTemplate;
	
	@Test
	public void testGetOneWhoNotExists() {
		
        try {
        	ResponseEntity<Funcionario> exchange = restTemplate.exchange("/funcionarios/100", HttpMethod.GET, null, Funcionario.class);
        	if(exchange != null) {
        		System.out.println("Veio");
        	}else {
        		System.out.println("Não Veio");
        	}
        }catch(RestClientException e) {
        	System.out.println(e.getMessage());
        }

	}
	
	@Test
	public void testUpdateWhoNotExists() {
		
		Funcionario func = new Funcionario();
		func.setLatitude(1l);
		func.setLongitude(2L);
		
		HttpEntity<Funcionario> entity = new HttpEntity<Funcionario>(func);
		
        try {
        	ResponseEntity<Funcionario> exchange = restTemplate.exchange("/funcionarios/100", HttpMethod.POST, entity, Funcionario.class);
        	if(exchange != null) {
        		System.out.println("Veio");
        	}else {
        		System.out.println("Não Veio");
        	}
        }catch(RestClientException e) {
        	System.out.println(e.getMessage());
        }

	}
	
	@Test
	public void testDeleteWhoNotExists() {
		
		
		
        try {
        	ResponseEntity<Funcionario> exchange = restTemplate.exchange("/funcionarios/100", HttpMethod.DELETE, null, Funcionario.class);
        	if(exchange != null) {
        		System.out.println("Veio");
        	}else {
        		System.out.println("Não Veio");
        	}
        }catch(RestClientException e) {
        	System.out.println(e.getMessage());
        }

	}

}
