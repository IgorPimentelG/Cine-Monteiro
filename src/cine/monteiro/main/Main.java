package cine.monteiro.main;

import javax.swing.JOptionPane;

// Pacotes
import cine.monteiro.dados.*;
import cine.monteiro.screens.*;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Persistencia bancoDeInformacoes = new Persistencia();
		CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
		
		WindowsSobre windowsSobre = new WindowsSobre();
		Thread.sleep(3000);
		windowsSobre.dispose();
		
		if(cpd.getUsuarios().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Atenção! Realize o Cadastro do Administrador.", "Administrador Não Cadastrado", JOptionPane.WARNING_MESSAGE);
			new WindowsCadastro();
		} else {
			new WindowsLogin();
		}
		bancoDeInformacoes.salvarCentralDeInformacoes(cpd);
	}
}
