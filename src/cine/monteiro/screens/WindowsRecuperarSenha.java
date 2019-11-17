package cine.monteiro.screens;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.Icone;
import cine.monteiro.screens.componentes.Windows;


public class WindowsRecuperarSenha extends Windows {
	public WindowsRecuperarSenha() {
		super("Recuperar Senha - Cine Monteiro", 540, 225);
		adicionarImagens();
		adicionarSeparador();
		adicionarLabels();
		adicionarInputs();
		adicionarButtons();
		setVisible(true);
	}
	
	private void adicionarImagens() {
		JLabel lblLogoProjeto = new JLabel(Imagens.LOGO_CINE_MONTEIRO_125x100);
		lblLogoProjeto.setBounds(20, 40, 125, 100);
		add(lblLogoProjeto);
		
		
		JLabel lblIconeEmail = new Icone(Imagens.EMAIL_25X25, 180, 80, 35, 35);
		add(lblIconeEmail);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new JSeparator(SwingConstants.VERTICAL);
		separador.setBounds(160, 15, 1, 160);
		separador.setForeground(Color.GRAY);
		add(separador);
	}
	
	private void adicionarLabels() {	
		JLabel lblTitulo = new JLabel("RECUPERAR SENHA");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		lblTitulo.setBounds(270, 5, 200, 50);
		add(lblTitulo);
		
		JLabel lblEmail = new JLabel("E-Mail Cadastrado:");
		lblEmail.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
		lblEmail.setBounds(180, 40, 200, 50);
		add(lblEmail);
	}
	
	private void adicionarInputs() {
		JTextField tfEmail = new JTextField();
		tfEmail.setHorizontalAlignment(JTextField.CENTER);
		tfEmail.setBounds(220, 80, 300, 35);
		tfEmail.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		add(tfEmail);
	}
	
	private void adicionarButtons() {
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setBounds(180, 130, 165, 35);
		add(btnConfirmar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(350, 130, 165, 35);
		add(btnCancelar);
	}
}
