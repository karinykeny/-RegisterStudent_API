package com.registerstudent.resource;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.registerstudent.BaseTest;
import com.registerstudent.modal.Endereco;
import com.registerstudent.modal.Estudante;
import com.registerstudent.modal.Telefone;
import com.registerstudent.modal.enums.UfEnum;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

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

}
