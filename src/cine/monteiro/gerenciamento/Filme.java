package cine.monteiro.gerenciamento;

public class Filme {
	// Atributos
	private String nomeDoFilme;
	private String sinopse;
	private String genero;
	private int duracao;
	private String classificacaoEtaria;
	private String tipoDaProjecao;
	
	// Construtor
	public Filme(String nomeDoFilme, String sinopse, String genero, int duracao, String classificacaoEtaria, String tipoDaProjecao) {
		this.nomeDoFilme = nomeDoFilme;
		this.sinopse = sinopse;
		this.genero = genero;
		this.duracao = duracao;
		this.classificacaoEtaria = classificacaoEtaria;
		this.tipoDaProjecao = tipoDaProjecao;
	}
	
	// Setters
	public void setNomeDoFilme(String nomeDoFilme) {
		this.nomeDoFilme = nomeDoFilme;
	}
	
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	
	public void setClassificacaoEtaria(String classificacaoEtaria) {
		this.classificacaoEtaria = classificacaoEtaria;
	}
	
	public void setTipoDaProjecao(String tipoDaProjecao) {
		this.tipoDaProjecao = tipoDaProjecao;
	}
	
	// Getters
	public String getNomeDoFilme() {
		return nomeDoFilme;
	}
	
	public String getSinopse() {
		return sinopse;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public int getDuracaco() {
		return duracao;
	}
		
	public String getClassificacaoEtaria() {
		return classificacaoEtaria;
	}
	
	public String getTipoDaProjecao() {
		return tipoDaProjecao;
	}
	
	// Sobreescritas
	public String toString() {
		return "\nNome do filme: " + this.nomeDoFilme + "\nSinopse: " + this.sinopse + "\nDuração: " + this.duracao + "\nClassificação etária: " + this.classificacaoEtaria + "\nTipo da Projeção: " + this.tipoDaProjecao;
	}
}
