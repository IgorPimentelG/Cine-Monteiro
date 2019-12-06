package cine.monteiro.gerenciamento;

public class Filme {
	// Atributos
	private String nomeDoFilme;
	private String sinopse;
	private String genero;
	private long duracao;
	private ClassificacaoEtaria classificacaoEtaria;
	
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
	
	public void setClassificacaoEtaria(ClassificacaoEtaria classificacaoEtaria) {
		this.classificacaoEtaria = classificacaoEtaria;
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
	
	public long getDuracao() {
		return duracao;
	}
		
	public ClassificacaoEtaria getClassificacaoEtaria() {
		return classificacaoEtaria;
	}
	
	// Sobreescritas
	public String toString() {
		return "\nNome do filme: " + this.nomeDoFilme + "\nSinopse: " + this.sinopse + "\nDura��o: " + this.duracao + "\nClassifica��o Et�ria: " + this.classificacaoEtaria;
	}
}
