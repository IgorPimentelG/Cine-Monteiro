package cine.monteiro.main;

import java.util.ArrayList;

import javax.swing.JOptionPane;

// Pacotes
import cine.monteiro.dados.*;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;
import cine.monteiro.screens.*;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Persistencia bancoDeInformacoes = new Persistencia();
		CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
		
		WindowsSplash splash = new WindowsSplash();
		Thread.sleep(3000);
		splash.dispose();
		
		ArrayList<Sala> salas = cpd.getSalas();
		
		for(Sala sala : salas) {
			ArrayList<Sessao> sessoesDaSala = sala.getSessoes();
			
			for(Sessao sessao : sessoesDaSala) {
				sessao.setAtiva();
			}
		}
		
		if(cpd.getUsuarios().isEmpty()) {
			JOptionPane.showMessageDialog(null, "O ADMINISTRADOR DO SISTEMA NÃO FOI ENCONTRADO. REALIZE O CADASTRO DO ADMINISTRADOR.", "Administrador Não Cadastrado", JOptionPane.WARNING_MESSAGE);
			new WindowsCadastro();
		} else {
			new WindowsLogin();
		}
		bancoDeInformacoes.salvarCentralDeInformacoes(cpd);
	}
}
