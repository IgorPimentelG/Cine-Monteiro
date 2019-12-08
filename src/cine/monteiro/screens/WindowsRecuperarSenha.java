package cine.monteiro.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.mail.MessagingException;
import javax.swing.*;

import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.email.RecuperarSenha;
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.ButtonPersonalizado;
import cine.monteiro.screens.componentes.Icone;
import cine.monteiro.screens.componentes.Input;
import cine.monteiro.screens.componentes.Rotulo;
import cine.monteiro.screens.componentes.RotuloTitulo;
import cine.monteiro.screens.componentes.Windows;
import cine.monteiro.usuarios.Usuario;


public class WindowsRecuperarSenha extends Windows {
	// Atributos
	private JTextField tfEmail;
	
	// Construtor
	public WindowsRecuperarSenha() {
		super("Recuperar Senha - Cine Monteiro", 545, 225);
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
		separador.setBounds(160, 5, 1, 175);
		separador.setForeground(Color.GRAY);
		add(separador);
	}
	
	private void adicionarLabels() {	
		JLabel lblTitulo = new RotuloTitulo("RECUPERAR SENHA", 260, 10, 200, 50);
		add(lblTitulo);
		
		JLabel lblEmail = new Rotulo("E-mail cadastrado:", 180, 40, 200, 50);
		add(lblEmail);
	}
	
	private void adicionarInputs() {
		tfEmail = new Input(214, 80, 300, 36);
		add(tfEmail);
	}
	
	private void adicionarButtons() {
		JButton btnConfirmar = new ButtonPersonalizado("CONFIRMAR", 180, 130, 165, 35);
		btnConfirmar.addActionListener(new ActionListener() {
			Persistencia bancoDeInformacoes = new Persistencia();
			CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
			
			public void actionPerformed(ActionEvent e) {
				RecuperarSenha rs = new RecuperarSenha();
				try {
					rs.recuperarSenha(cpd.autenticarEmailDoUsuario(tfEmail.getText()));
					JOptionPane.showMessageDialog(null, "Verifique seu e-mail.", "AVISO!", JOptionPane.PLAIN_MESSAGE, Imagens.EMAIL_ENVIADO_25x25);
					dispose();
					new WindowsLogin();
				} catch(MessagingException e2) {
					JOptionPane.showMessageDialog(null, "Erro ao enviar e-mail", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
				} catch(Exception erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "ATENÇÃO!",JOptionPane.ERROR_MESSAGE);
				}	
			}
			
		});
		add(btnConfirmar);
		
		JButton btnCancelar = new ButtonPersonalizado("CANCELAR", 350, 130, 165, 35);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsLogin();
			}
		});
		add(btnCancelar);
	}
}
