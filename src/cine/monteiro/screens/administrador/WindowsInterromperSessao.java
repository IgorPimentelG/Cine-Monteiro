package cine.monteiro.screens.administrador;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.screens.componentes.ButtonPersonalizado;
import cine.monteiro.screens.componentes.Rotulo;
import cine.monteiro.screens.componentes.RotuloTitulo;
import cine.monteiro.screens.componentes.Windows;
import cine.monteiro.screens.ouvintes.OuvinteBtnVoltarSessao;

public class WindowsInterromperSessao extends Windows {
	// Instâncias
	Persistencia bancoDeInformacoes = new Persistencia();
	CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
	
	// Construtor
	public WindowsInterromperSessao() {
		super("Sessões - Interromper Sessão", 360, 275);
		adicionarLabels();
		adicionarComboBox();
		adicionarButtons();
		setVisible(true);
	}
	
	// Componentes
	private void adicionarLabels() {
		JLabel lblTitulo = new RotuloTitulo("INTERROMPER SESSÃO", 0, 15, 360, 30);
		add(lblTitulo);
		
		JLabel lblSala = new Rotulo("Sala:", 20, 50, 100, 20);
		add(lblSala);
		
		JLabel lblSessao = new Rotulo("Sess�o:", 20, 115, 100, 20);
		add(lblSessao);
	}
	
	private void adicionarComboBox() {
		String[] salas = {"194318321 - Sala 1"};
		JComboBox<String> salasCadastradas = new JComboBox<String>(salas);
		salasCadastradas.setBounds(20, 75, 305, 30);
		add(salasCadastradas);
		
		String[] sessoes = {"1204924141 - Coringa"};
		JComboBox<String> sessoesCadastradas = new JComboBox<String>(sessoes);
		sessoesCadastradas.setBounds(20, 140, 305, 30);
		add(sessoesCadastradas);
	}
	
	private void adicionarButtons() {
		JButton btnConfirmar = new ButtonPersonalizado("CONFIRMAR", 20, 185, 145, 35);
		add(btnConfirmar);
		
		JButton btnCancelar = new ButtonPersonalizado("CANCELAR", 180, 185, 145, 35);
		btnCancelar.addActionListener(new OuvinteBtnVoltarSessao(this));
		add(btnCancelar);
	}
}
