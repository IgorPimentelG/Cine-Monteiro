package cine.monteiro.screens.administrador;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.*;
import cine.monteiro.screens.ouvintes.OuvinteBtnVoltar;

public class WindowsSala extends Windows {
	public WindowsSala() {
		super("Painel de Controle - Sala", 370, 220);
		adicionarImagens();
		adicionarLabels();
		adicionarSeparador();
		adicionarButtons();
		setVisible(true);
	}
	
	private void adicionarImagens() {
		JLabel iconeSala = new JLabel(Imagens.SALA_100x100);
		iconeSala.setBounds(27, 45, 64, 64);
		add(iconeSala);
	}
	
	private void adicionarLabels() {
		JLabel lblTitulo = new JLabel("SALAS");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblTitulo.setBounds(32, 95, 100, 50);
		add(lblTitulo);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new Separador(113, 5, 2, 167);
		add(separador);
	}
	
	private void adicionarButtons() {
		JButton btnCadastrarSala = new JButton("CADASTRAR NOVA SALA");
		btnCadastrarSala.setBounds(135, 15, 200, 30);
		add(btnCadastrarSala);
		
		JButton btnExcluirSala = new JButton("EXCLUIR SALA");
		btnExcluirSala.setBounds(135, 55, 200, 30);
		add(btnExcluirSala);
		
		JButton btnListarTodasAsSalas = new JButton("LISTAR TODAS AS SALAS");
		btnListarTodasAsSalas.setBounds(135, 95, 200, 30);
		add(btnListarTodasAsSalas);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(135, 135, 200, 30);
		btnVoltar.addActionListener(new OuvinteBtnVoltar(this));
		add(btnVoltar);
	}
}
