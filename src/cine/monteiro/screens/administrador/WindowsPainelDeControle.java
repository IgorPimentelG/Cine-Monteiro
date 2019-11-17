package cine.monteiro.screens.administrador;

import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.*;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class WindowsPainelDeControle extends Windows {
	public WindowsPainelDeControle() {
		super("Painel de Controle - Cine Monteiro", 700, 320);
		adicionarLabels();
		adicionarImagens();
		adicionarButtons();
		adicionarSeparador();
		setVisible(true);
	}
	
	private void adicionarLabels() {
		JLabel lblTitulo = new JLabel("PAINEL DE CONTROLE");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblTitulo.setBounds(358, 20, 200, 20);
		add(lblTitulo);
		
		JLabel lblAdmin = new JLabel("ADMINISTRADOR");
		lblAdmin.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		lblAdmin.setBounds(35, 135, 150, 100);
		add(lblAdmin);
	}
	
	private void adicionarImagens() {
		JLabel lblLogoProjeto = new JLabel(Imagens.ADMIN_100x100);
		lblLogoProjeto.setBounds(10, 45, 180, 156);
		add(lblLogoProjeto);
	}
	
	private void adicionarButtons() {
		JButton btnFilmes = new JButton("FILMES");
		btnFilmes.setBounds(230, 70, 130, 80);
		add(btnFilmes);
		
		JButton btnSessoes = new JButton("SESSÕES");
		btnSessoes.setBounds(380, 70, 130, 80);
		add(btnSessoes);
		
		JButton btnSalas = new JButton("SALAS");
		btnSalas.setBounds(530, 70, 130, 80);
		add(btnSalas);
		
		JButton btnRelatorios = new JButton("RELATÓRIOS");
		btnRelatorios.setBounds(230, 170, 130, 80);
		add(btnRelatorios);
		 
		JButton btnAreaDoCliente = new JButton("<html><center>ÁREA<br> DO CLIENTE<center></html>");
		btnAreaDoCliente.setBounds(380, 170, 130, 80);
		add(btnAreaDoCliente);
		
		JButton btnMarketing = new JButton("MARKETING");
		btnMarketing.setBounds(530, 170, 130, 80);
		add(btnMarketing);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new JSeparator(SwingConstants.VERTICAL);
		separador.setBounds(195, 10, 1, 260);
		separador.setForeground(Color.GRAY);
		add(separador);
	}
}
