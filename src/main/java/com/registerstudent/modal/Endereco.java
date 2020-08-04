package com.registerstudent.modal;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.registerstudent.modal.enums.UfEnum;

@Embeddable
public class Endereco {
	
	private String rua;
	private Integer numero;
	private String bairro;
	private String cidade;
	
	@Enumerated(EnumType.STRING)
	private UfEnum uf;
	
	private String cep;
	
	private String complemento;
	
	public Endereco() {}
	
	public String getRua() {
		return rua;
	}
	
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public Integer getNumero() {
		return numero;
	}
	
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public UfEnum getUf() {
		return uf;
	}
	
	public void setUf(UfEnum uf) {
		this.uf = uf;
	}
	
	public String getCep() {
		return cep;
	}
	
	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
}
