package cine.monteiro.screens.administrador;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.Separador;
import cine.monteiro.screens.componentes.Windows;

public class WindowsMarketing extends Windows {
	public WindowsMarketing() {
		super("´Painel De Controle - Marketing", 475, 185);
		adicionarImagens();
		adicionarLabels();
		adicionarSeparador();
		adicionarButtons();
		setVisible(true);
	}
	
	public void adicionarImagens() {
		JLabel iconeEmail = new JLabel(Imagens.EMAIL_64x64);
		iconeEmail.setBounds(25, 40, 64, 64);
		add(iconeEmail);
	}
	
	public void adicionarLabels() {
		JLabel lblTitulo = new JLabel("Marketing");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		lblTitulo.setBounds(240, 10, 100, 30);
		add(lblTitulo);
		
		JLabel lblTexto = new JLabel("<html><center>Enviar programação do cine monteiro para todos os clientes cadastrados?</center></html>");
		lblTexto.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		lblTexto.setBounds(137, 15, 300, 100);
		add(lblTexto);
	}
	
	public void adicionarSeparador() {
		JSeparator separador = new Separador(110, 5, 2, 135);
		add(separador);
	}
	
	public void adicionarButtons() {
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setBounds(130, 100, 150, 30);
		add(btnConfirmar);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(290, 100, 150, 30);
		add(btnVoltar);
	}
}
