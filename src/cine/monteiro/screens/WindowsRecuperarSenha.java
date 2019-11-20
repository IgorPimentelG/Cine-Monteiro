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
import cine.monteiro.screens.componentes.Icone;
import cine.monteiro.screens.componentes.Windows;
import cine.monteiro.usuarios.Usuario;


public class WindowsRecuperarSenha extends Windows {
	// Atributos
	private JTextField tfEmail;
	
	// Construtor
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
		lblTitulo.setBounds(260, 10, 200, 50);
		add(lblTitulo);
		
		JLabel lblEmail = new JLabel("E-mail cadastrado:");
		lblEmail.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
		lblEmail.setBounds(180, 40, 200, 50);
		add(lblEmail);
	}
	
	private void adicionarInputs() {
		tfEmail = new JTextField();
		tfEmail.setHorizontalAlignment(JTextField.CENTER);
		tfEmail.setBounds(220, 80, 300, 35);
		tfEmail.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		add(tfEmail);
	}
	
	private void adicionarButtons() {
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setBounds(180, 130, 165, 35);
		btnConfirmar.addActionListener(new ActionListener() {
			Persistencia bancoDeInformacoes = new Persistencia();
			CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
			
			public void actionPerformed(ActionEvent e) {
				try {
					Usuario usuario = cpd.autenticarEmailDoUsuario(tfEmail.getText());
					
					if(usuario != null) {
						RecuperarSenha rs = new RecuperarSenha();
						try {
							rs.recuperarSenha(usuario);
							JOptionPane.showMessageDialog(null, "Verifique seu e-mail.", "ATENÇÃO!", JOptionPane.WARNING_MESSAGE);
							dispose();
							new WindowsLogin();
						} catch(MessagingException e2) {
							JOptionPane.showMessageDialog(null, "Erro ao enviar e-mail", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
						}
						
					}
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "ATENÇÃO!",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		add(btnConfirmar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(350, 130, 165, 35);
		btnCancelar.addActionListener(new OuvinteBtnVoltar(this));
		add(btnCancelar);
	}
	
	public class OuvinteBtnVoltar implements ActionListener {
		private JFrame windows;
		
		public OuvinteBtnVoltar(JFrame windows) {
			this.windows = windows;
		}
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new WindowsLogin();
		}
	}
}
