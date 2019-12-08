package cine.monteiro.gerenciamento;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Filme {
	// Atributos
	private String nomeDoFilme;
	private String sinopse;
	private String genero;
	private long duracao;
	private ClassificacaoEtaria classificacaoEtaria;
	private ArrayList<ArrayList<String>> dadosDaArrecadacao = new ArrayList<ArrayList<String>>();
	
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
	
	public void adicionarDadosArrecadao(ArrayList<String> dados) {
		for(ArrayList<String> dadosCadastrados : dadosDaArrecadacao) {
			if(dadosCadastrados.get(0).equals(dados.get(0))) {
				float totalArrecadado = Float.parseFloat(dadosCadastrados.get(1)) + Float.parseFloat(dados.get(1));
				dadosCadastrados.set(1,  totalArrecadado + "");
				return;
			}
		}
		dadosDaArrecadacao.add(dados);
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
	
	public ArrayList<ArrayList<String>> getDadosDaArrecadacao() {
		return dadosDaArrecadacao;
	}
}
