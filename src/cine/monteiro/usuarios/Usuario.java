package cine.monteiro.usuarios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class Usuario {
	private String nome;
	private String CPF;
	private String telefone;
	private int idade;
	private Date dataDeNascimento;
	private String email;
	private String senha;
	
	// Construtor
	public Usuario(String nome, String CPF, String telefone, Date dataDeNascimento, String email, String senha) {
		this.nome = nome;
		this.CPF = CPF;
		this.telefone = telefone;
		this.dataDeNascimento = dataDeNascimento;
		this.setIdade(dataDeNascimento);
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
	public void setIdade(Date dataDeNascimento) {
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAtual = new Date();
	
		idade = dataAtual.getYear() - dataDeNascimento.getYear();
	}
	
	public void setDataDeNascimento(Date dataDeNascimento) {
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
	
	public Date getDataDeNascimento() {
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
