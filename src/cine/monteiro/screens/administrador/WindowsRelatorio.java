package cine.monteiro.screens.administrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;


import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.email.Relatorio;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.ButtonPersonalizado;
import cine.monteiro.screens.componentes.Rotulo;
import cine.monteiro.screens.componentes.RotuloTitulo;
import cine.monteiro.screens.componentes.Separador;
import cine.monteiro.screens.componentes.Windows;
import cine.monteiro.screens.ouvintes.OuvinteBtnVoltarPainelDeControle;

public class WindowsRelatorio extends Windows {
	private JComboBox<String> cbDatas;
	
	public WindowsRelatorio() {
		super("Painel de Controle - Relatório", 475, 185);
		adicionarLabels();
		adicionarComBox();
		adicionarButtons();
		adicionarImagem();
		adicionarSeparador();
		setVisible(true);
	}
	
	private void adicionarLabels() {
		JLabel lblTitulo = new RotuloTitulo("GERAR RELATÓRIO", 50, 15, 475, 20);
		add(lblTitulo);
		
		JLabel lblData = new Rotulo("Mês:", 130, 40, 50, 20);
		add(lblData);
	}
	
	private void adicionarComBox() {
		Persistencia bancoDeInformacoes = new Persistencia();
		CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
		ArrayList<String> datasSalvas = new ArrayList<String>();
		
		ArrayList<Sala> salas = cpd.getSalas();
		for(Sala sala : salas) {
			ArrayList<Sessao> sessoes = sala.getSessoes();
			for(Sessao sessao : sessoes) {
				 ArrayList<ArrayList<String>> dadosDaSessaoDoMes = sessao.getDadosDaSessaoDoMes();
				 for(ArrayList<String> dados : dadosDaSessaoDoMes) {
					 boolean naoExiste = true;
					 for(String data : datasSalvas) {
						 if(data.equals(dados.get(0))) {
							 naoExiste = false;
						 }
					 }
					 
					 if(naoExiste) {
						 datasSalvas.add(dados.get(0));
					 }
				 }
			}
		}
		
		String[] opcoes = new String[datasSalvas.size()];
		for(int i = 0; i < datasSalvas.size(); i++) {
			opcoes[i] = datasSalvas.get(i);
		}
		
		cbDatas = new JComboBox<String>(opcoes);
		cbDatas.setBounds(130, 60, 310, 30);
		add(cbDatas);
	}
	
	private void adicionarButtons() {
		JButton btnConfirmar = new ButtonPersonalizado("CONFIRMAR", 130, 100, 150, 30);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Relatorio relatorio = new Relatorio();
				try {
					relatorio.enviarRelatorio((String) cbDatas.getSelectedItem());
					JOptionPane.showMessageDialog(null, "RELATÓRIO GERADO COM SUCESSO. VERIFIQUE SEU E-MAIL!", "ATENÇÃO", JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "ERRO AO ENVIAR RELATÓRIO!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		add(btnConfirmar);
		
		JButton btnCancelar = new ButtonPersonalizado("CANCELAR", 290, 100, 150, 30);
		btnCancelar.addActionListener(new OuvinteBtnVoltarPainelDeControle(this));
		add(btnCancelar);
	}
	
	private void adicionarImagem() {
		JLabel iconeRelatorio = new JLabel(Imagens.ICONE_RELATORIO);
		iconeRelatorio.setBounds(25, 40, 64, 64);
		add(iconeRelatorio);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new Separador(110, 5, 2, 135);
		add(separador);
	}
}
