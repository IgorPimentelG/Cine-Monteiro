package cine.monteiro.gerenciamento;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Sala {
	//Atributos
	private final long ID;
	private String nomeDaSala;
	private int quantidadeDeAssentos;
	private String precoDoIngresso;
	private String tipoDaProjecaoSuportada; // Criar enum
	private ArrayList<Sessao> sessoes = new ArrayList<Sessao>();
	
	// Construtor
	public Sala() {
		this.ID = System.currentTimeMillis();
	}
	
	// Setters
	public void setNomeDaSala(String nomeDaSala) {
		this.nomeDaSala = nomeDaSala;
	}
	
	public void setQuantidadeDeAssentos(int quantidadeDeAssentos) {
		this.quantidadeDeAssentos = quantidadeDeAssentos;
	}
	
	public void setPrecoDoIngresso(String precoDoIngresso) {
		this.precoDoIngresso = precoDoIngresso;
	}
	
	public void setTipoDaProjecaoSuportada(String tipoDaProjecaoSuportada) {
		this.tipoDaProjecaoSuportada = tipoDaProjecaoSuportada;
	}
	
	// Getters
	public long getID() {
		return ID;
	}
	
	public String getNomeDaSala() {
		return nomeDaSala;
	}
	
	public int getQuantidadeDeAssentos() {
		return quantidadeDeAssentos;
	}
	
	public String getPrecoDoIngresso() {
		return precoDoIngresso;
	}
	
	public String getTipoDaProjecaoSuportada() {
		return tipoDaProjecaoSuportada;
	}
	
	public ArrayList<Sessao> getSessoes() {
		return sessoes;
	}
	
	// Métodos
	public void addSessao(Sessao novaSessao) {
		sessoes.add(novaSessao);
	}
	
	// Sobreescritas
	public String toString() {
		return "\nID: " + this.ID + "\nNome da sala: " + this.nomeDaSala + "\nQuantidade de Assentos: " + this.quantidadeDeAssentos + "\nPreço do ingresso: " + this.precoDoIngresso + "\nTipo da Projeção SUportada: " + this.tipoDaProjecaoSuportada;
	}
}
