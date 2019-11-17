package cine.monteiro.usuarios;

import java.util.Calendar;

public abstract class Usuario {
	private String nome;
	private String CPF;
	private String telefone;
	private int idade;
	private String dataDeNascimento;
	private String email;
	private String senha;
	
	// Construtor
	public Usuario(String nome, String CPF, String telefone, String dataDeNascimento, String email, String senha) {
		this.nome = nome;
		this.CPF = CPF;
		this.telefone = telefone;
		this.dataDeNascimento = dataDeNascimento;
		//this.setIdade(dataDeNascimento);
		this.email = email;
		this.senha = senha;
	}
	
	// Setters
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	// Tratar dia e mês
	public void setIdade(Calendar dataDeNascimento) {
		Calendar dataAtual = Calendar.getInstance();
		this.idade = dataAtual.get(Calendar.YEAR) - dataDeNascimento.get(Calendar.YEAR);
	}
	
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	// Getters
	public String getNome() {
		return nome;
	}
	
	public String getCPF() {
		return CPF;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public int getIdade() {
		return idade;
	}
	
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getSenha() {
		return senha;
	}

	// Sobreescritas
	public String toString() {
		return "Nome: " + this.nome +"\nCPF: " + this.CPF + "\nTelefone: " + this.telefone + "\nIdade: "+ this.idade + "\nE-mail: " + this.email;
	}
}
