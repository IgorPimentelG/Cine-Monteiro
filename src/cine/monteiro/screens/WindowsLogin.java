package cine.monteiro.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;

import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.administrador.WindowsPainelDeControle;
import cine.monteiro.screens.componentes.Icone;
import cine.monteiro.screens.componentes.Windows;
import cine.monteiro.usuarios.Administrador;
import cine.monteiro.usuarios.Usuario;

public class WindowsLogin extends Windows {
	// Atributos
	private JTextField tfLogin;
	private JTextField tfSenha;
	
	public WindowsLogin() {
		super("Login - Cine Monteiro", 400, 500);
		adicionarImagens();
		adicionarLabels();
		adicionarInputs();
		adicionarButtons();
		adicionarCheckcBox();
		setVisible(true);
	}
	
	// Componentes
	private void adicionarImagens() {
		JLabel lblLogoProjeto = new JLabel(Imagens.LOGO_CINE_MONTEIRO_200x173);
		lblLogoProjeto.setBounds(90, 20, 200, 173);
		add(lblLogoProjeto);
		
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
		
		JLabel lblUser = new Icone(Imagens.USER_25x25, 30, 225, 35, 35);
		add(lblUser);
		
		JLabel lblLock = new Icone(Imagens.LOCK_25x25, 30, 300, 35, 35);
		add(lblLock);
	}
	
	private void adicionarLabels() {
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
		lblLogin.setBounds(30, 160, 100, 100);
		add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
		lblSenha.setBounds(30, 235, 100, 100);
		add(lblSenha);
		
		JLabel lblEsqueceuASenha = new JLabel("Esqueceu a senha?");
		lblEsqueceuASenha.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
		lblEsqueceuASenha.setBounds(230, 310, 150, 100);
		add(lblEsqueceuASenha);
	}
	
	private void adicionarInputs() {
		tfLogin = new JTextField();
		tfLogin.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		tfLogin.setHorizontalAlignment(JTextField.CENTER);
		tfLogin.setBounds(70, 225, 300, 36);
		add(tfLogin);
		
		tfSenha = new JTextField();
		tfSenha.setBounds(70, 300, 300, 36);
		tfSenha.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		tfSenha.setHorizontalAlignment(JTextField.CENTER);
		add(tfSenha);
	}
	
	private void adicionarButtons() {
		JButton btnEntrar = new JButton("ENTRAR");
		btnEntrar.setBounds(30, 385, 160, 40);
		btnEntrar.addActionListener(new OuvinteEntrar());
		add(btnEntrar);
		
		JButton btnNovaConta = new JButton("CRIAR CONTA");
		btnNovaConta.setBounds(210, 385, 160, 40);
		btnNovaConta.addActionListener(new OuvinteNovaConta());
		add(btnNovaConta);
	}

	private void adicionarCheckcBox() {
		JCheckBox cbLembrarDados = new JCheckBox("Lembrar e-mail", false);
		cbLembrarDados.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
		cbLembrarDados.setBounds(30, 310, 150, 100);
		add(cbLembrarDados);
	}
	
	// Ouvintes
	public class OuvinteNovaConta implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new WindowsCadastro();
			dispose();
		}
	}
	
	public class OuvinteEntrar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Persistencia bancoDeInformacoes = new Persistencia();
			CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
			
			try {
				Usuario usuarioAtivo = cpd.autenticarUsuario(tfLogin.getText(), tfSenha.getText());
				
				if(usuarioAtivo instanceof Administrador) {
					dispose();
					new WindowsPainelDeControle();
				}
			} catch(Exception erro) {
				JOptionPane.showMessageDialog(null, erro.getMessage());
			}
		}
	}
}
