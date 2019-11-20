package cine.monteiro.screens.administrador;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.*;
import cine.monteiro.screens.ouvintes.OuvinteBtnVoltar;

public class WindowsSessao extends Windows {
	public WindowsSessao() {
		super("Painel de Controle - Cine Monteiro", 350, 220);
		adicionarImagens();
		adicionarLabels();
		adicionarButtons();
		adicionarSeparador();
		setVisible(true);
	}
	
	private void adicionarImagens() {
		JLabel iconeSessao = new JLabel(Imagens.SESSAO_64x64);
		iconeSessao.setBounds(23, 45, 64, 64);
		add(iconeSessao);
	}
	
	private void adicionarLabels() {
		JLabel lblTitulo = new JLabel("SESSÕES");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblTitulo.setBounds(15, 88, 100, 60);
		add(lblTitulo);
	}
	
	private void adicionarButtons() {
		JButton btnCadastrarSessao = new JButton("CADASTRAR SESSÃO");
		btnCadastrarSessao.setBounds(120, 15, 200, 30);
		add(btnCadastrarSessao);
		
		JButton btnInterromperSessao = new JButton("INTERROMPER SESSÃO");
		btnInterromperSessao.setBounds(120, 55, 200, 30);
		add(btnInterromperSessao);
		
		JButton btnListarTodasAsSessoes = new JButton("LISTAR TODAS AS SESSÕES");
		btnListarTodasAsSessoes.setBounds(120, 95, 200, 30);
		add(btnListarTodasAsSessoes);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.addActionListener(new OuvinteBtnVoltar(this));
		btnVoltar.setBounds(120, 135, 200, 30);
		add(btnVoltar);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new Separador(105, 5, 2, 170);
		add(separador);
	}
}
