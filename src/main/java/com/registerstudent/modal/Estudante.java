package com.registerstudent.modal;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity(name = "estudante")
public class Estudante implements Serializable {

	private static final long serialVersionUID = 7578163422956834163L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "matricula")
	private Integer matricula;
	
	@NotNull
	@Size(min=3)
	private String nome;
	
	@NotNull
	@Size(min=3)
	private String sobrenome;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="matricula")
	private List<Telefone> telefones;
	
	@Email
	private String email;
	
	@CPF
	private String cpf;
	
	@Embedded
	private Endereco endereco;

	public Estudante() {}

	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Estudante [matricula=" + matricula + ", nome=" + nome + ", sobrenome=" + sobrenome + ", telefones="
				+ telefones + ", email=" + email + ", cpf=" + cpf + ", endereco=" + endereco + "]";
	}

}
