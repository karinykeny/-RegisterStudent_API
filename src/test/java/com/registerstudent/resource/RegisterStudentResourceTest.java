package com.registerstudent.resource;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.google.gson.Gson;
import com.registerstudent.BaseTest;
import com.registerstudent.modal.Endereco;
import com.registerstudent.modal.Estudante;
import com.registerstudent.modal.Telefone;
import com.registerstudent.modal.enums.UfEnum;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class RegisterStudentResourceTest extends BaseTest {
	
	@Test
	public void saveStudent() throws Exception {
		
		Telefone telefone1 = new Telefone();
		telefone1.setNumeroTelefone("81999999999");
		
		Telefone telefone2 = new Telefone();
		telefone2.setNumeroTelefone("81991919191");
		
		Telefone telefone3 = new Telefone();
		telefone3.setNumeroTelefone("8134582929");
		
		List<Telefone> telefones = new ArrayList<Telefone>();
		telefones.add(telefone1);
		telefones.add(telefone2);
		telefones.add(telefone3);
		
		Endereco endereco = new Endereco();
		endereco.setRua("Rua de teste");
		endereco.setNumero(10);
		endereco.setBairro("teste");
		endereco.setCidade("Olinda");
		endereco.setUf(UfEnum.PE);
		endereco.setCep("50000000");
		endereco.setComplemento("apto 01");
		
		Estudante estudante = new Estudante();
		estudante.setNome("Teste");
		estudante.setSobrenome("da Silva Dantas");
		estudante.setTelefones(telefones);
		estudante.setEmail("teste@teste.com.br");
		estudante.setCpf("49481992063");
		estudante.setEndereco(endereco);
		
		Gson gson = new Gson();
		String json = gson.toJson(estudante);
		
		RestAssured.given().contentType(ContentType.JSON)
			.body(json).log().body().when().post("/estudantes")
			.then().assertThat().statusCode(201).log().body();
		
	}
	
	@Test
	public void consultStudant() throws Exception {
		
		RestAssured.given().when().get("/estudantes/1")
			.then().log().body().assertThat().statusCode(200)
			.body("cpf", Matchers.is("49481992063"));
	}
	
	@Test
	public void updateStudant() throws Exception {
		
		Response response = RestAssured.request(Method.GET, "/estudantes/2");
		Gson gson = new Gson();
		Estudante estudante = gson.fromJson(response.asString(), Estudante.class);
		estudante.setEmail("testeupdate@teste.com.br");
		
		String json = gson.toJson(estudante);
		
		RestAssured.given().contentType(ContentType.JSON)
			.body(json).when().put("/estudantes").then()
			.log().body().assertThat().statusCode(200)
			.body("email", Matchers.is("testeupdate@teste.com.br"));
	}
	
	@Test
	public void ListStudant() throws Exception {
		
		RestAssured.given().when().get("/estudantes")
			.then().log().body().assertThat().statusCode(200);
	}
	
	@Test
	public void deletStudant() throws Exception {
		
		RestAssured.given().when().delete("/estudantes/1")
			.then().assertThat().statusCode(204);
	}

}
