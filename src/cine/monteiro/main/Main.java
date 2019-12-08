package cine.monteiro.main;

import java.nio.channels.NonReadableChannelException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.benchmark.jmh.Base64Benchmark.Data;
import com.thoughtworks.xstream.converters.time.LocalDateConverter;

// Pacotes
import cine.monteiro.dados.*;
import cine.monteiro.gerenciamento.Filme;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;
import cine.monteiro.screens.*;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Persistencia bancoDeInformacoes = new Persistencia();
		CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
		
		//WindowsSplash splash = new WindowsSplash();
		//Thread.sleep(3000);
		//splash.dispose();
	
		LocalDate dataAtual = LocalDate.parse("2019-12-09");
		
		ArrayList<Sala> salas = cpd.getSalas();
		
		for(Sala sala : salas) {
			ArrayList<Sessao> sessoesDaSala = sala.getSessoes();
			ArrayList<Float> dadosDaSala = new ArrayList<Float>();
			for(Sessao sessao : sessoesDaSala) {
 				if(sessao.getDataAtualDaSessao().isBefore(dataAtual) && sessao.isAtiva()) {
					ArrayList<String> dadosDaSessao = new ArrayList<String>();
					// Salvar dados do dia atual da sessão
					dadosDaSessao.add(sessao.getDataAtualDaSessao() + "");
					dadosDaSessao.add(sessao.getTotalDeIngressosVendidos() + "");
					dadosDaSessao.add(NumberFormat.getCurrencyInstance().format(sessao.getTotalArrecadado()));
					sessao.adicionarDadosDaSessaoDaSeamana(dadosDaSessao);
					
					// Salvar dados da sala
					dadosDaSala.add(sessao.getTotalArrecadado());
					
					// Salvar dados do relatório
					ArrayList<String> dadosDoMes = new ArrayList<String>();
					dadosDoMes.add(dataAtual.getMonthValue() + "/" + dataAtual.getYear());
					dadosDoMes.add(sessao.getTotalArrecadado() + "");
					dadosDoMes.add(sessao.getTotalDeIngressosVendidos() + "");
					dadosDoMes.add(sala.getQuantidadeDeAssentos() - sessao.getTotalDeIngressosVendidos() + "");
					sessao.adicionDadosDaSessaoDoMes(dadosDoMes);
					
					ArrayList<Filme> filmes = cpd.getFilmes();
					for(Filme filme : filmes) {
						if(filme.getNomeDoFilme().equals(sessao.getFilme().getNomeDoFilme())) {
							ArrayList<String> dadosDaArrecadacao = new ArrayList<String>();
							dadosDaArrecadacao.add(dataAtual.getMonthValue() + "/" + dataAtual.getYear());
							dadosDaArrecadacao.add(sessao.getTotalArrecadado() + "");
							filme.adicionarDadosArrecadao(dadosDaArrecadacao);
						}
					}
								
					// Configurar Nova Sessão
					sessao.setDataAtualDaSessao(dataAtual);
					sessao.setTotalArrecadado(0);
					sessao.setTotalDeIngressosVendidos(0);
					sessao.setAssentosReservado(new ArrayList<String>());
					sessao.setVagasDisponiveis(sala.getQuantidadeDeAssentos());
				}
				sessao.setAtiva();
			}
			sala.adicionarDadosDaSemana(dadosDaSala);
		}
		
		bancoDeInformacoes.salvarCentralDeInformacoes(cpd);
		
		if(cpd.getUsuarios().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O ADMINISTRADOR DO SISTEMA NÃO FOI ENCONTRADO. REALIZE O CADASTRO DO ADMINISTRADOR.", "Administrador Não Cadastrado", JOptionPane.WARNING_MESSAGE);
			new WindowsCadastro();
		} else {
			new WindowsLogin();
		}
		
	}
}
