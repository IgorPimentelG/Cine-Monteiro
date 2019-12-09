package cine.monteiro.gerenciamento;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Sala {
	//Atributos
	private final long ID;
	private String nomeDaSala;
	private int quantidadeDeAssentos;
	private String precoDoIngresso;
	private int quantidadeDeIngressoVendidos;
	private float totalArrecadado;
	private float totalArrecadadoNaSemena;
	private ArrayList<Sessao> sessoes = new ArrayList<Sessao>();
	private ArrayList<Filme> todosOsFilmesExibidos = new ArrayList<Filme>();
	private ArrayList<ArrayList<Float>> dadosDaSemana = new ArrayList<ArrayList<Float>>();
	
	// Construtor
	public Sala() {
		this.ID = System.currentTimeMillis();
		quantidadeDeAssentos = 0;
		totalArrecadado = 0f;
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
		
	public void setQuantidadeDeIngressoVendidos(int quantidadeDeIngressoVendidos) {
		this.quantidadeDeIngressoVendidos += quantidadeDeIngressoVendidos;
	}
	
	public void setTotalArrecadado(float valor) {
		this.totalArrecadado += valor;
	}
	
	public void setTotalArrecadadoNaSemana() {
		float total = 0;
		
		for(ArrayList<Float> dados : dadosDaSemana) {
			for(float dado : dados) {
				total += dado;
			}
		}
		
		this.totalArrecadadoNaSemena = total;
	}
	
	public void adicionarDadosDaSemana(ArrayList<Float> dados) {
		if(dadosDaSemana.size() == 7) {
			dadosDaSemana.remove(0);
			dadosDaSemana.add(dados);
		} else {
			dadosDaSemana.add(dados);
		}
		
		setTotalArrecadadoNaSemana();
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
	
	public ArrayList<Sessao> getSessoes() {
		return sessoes;
	}
	
	public ArrayList<Filme> getTodosOsFilmesExibidos() {
		return todosOsFilmesExibidos;
	}
	
	public int getQuantidadeDeIngressoVendidos() {
		return quantidadeDeIngressoVendidos;
	}
	
	public String getTotalArrecadado() {
		return NumberFormat.getCurrencyInstance().format(totalArrecadado);
	}
	
	public String getTotalArrecadadoNaSemana() {
		return NumberFormat.getCurrencyInstance().format(totalArrecadadoNaSemena);
	}
	
	public ArrayList<ArrayList<Float>> getDadosDaSemana() {
		return dadosDaSemana;
	}
	
	// Métodos
	public void adicionarSessao(Sessao novaSessao) {
		sessoes.add(novaSessao);
	}
	
	public void adicionarFilmeExibido(Filme filme) {
		for(Filme filmeCadastrado : todosOsFilmesExibidos) {
			if(filmeCadastrado.getNomeDoFilme().equals(filme.getNomeDoFilme())) {
				return;
			}
		}
		todosOsFilmesExibidos.add(filme);
	}
}
