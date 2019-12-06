package cine.monteiro.main;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.benchmark.jmh.Base64Benchmark.Data;

// Pacotes
import cine.monteiro.dados.*;
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
		
		SimpleDateFormat formatoDaData = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAtualString;
		Date dataAtual = null;
		try {
			dataAtualString = formatoDaData.parse("08/12/2019");
			String dataAtualS = formatoDaData.format(dataAtualString);
			dataAtual = formatoDaData.parse(dataAtualS);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ArrayList<Sala> salas = cpd.getSalas();
		
		for(Sala sala : salas) {
			ArrayList<Sessao> sessoesDaSala = sala.getSessoes();
			for(Sessao sessao : sessoesDaSala) {
				if(sessao.getDataAtualDaSessao().before(dataAtual)) {
					ArrayList<String> dados = new ArrayList<String>();
					
					// Salvar Dados
					dados.add(formatoDaData.format(sessao.getDataAtualDaSessao()));
					dados.add(sessao.getTotalDeIngressosVendidos() + "");
					dados.add(NumberFormat.getCurrencyInstance().format(sessao.getTotalArrecadado()));
					sessao.setDadosDaSessao(dados);
					
					// Configurar Nova Sessão
					sessao.setDataAtualDaSessaoDate(dataAtual);
					sessao.setTotalArrecadado(0);
					sessao.setTotalDeIngressosVendidos(0);
					sessao.setAssentosReservado(new ArrayList<String>());
					sessao.setVagasDisponiveis(sala.getQuantidadeDeAssentos());
				}
				sessao.setAtiva();
			}
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
