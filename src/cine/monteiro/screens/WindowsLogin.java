package cine.monteiro.screens;

// APIs
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

// Pacotes
import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.administrador.WindowsPainelDeControle;
import cine.monteiro.screens.componentes.Icone;
import cine.monteiro.screens.componentes.Input;
import cine.monteiro.screens.componentes.Windows;
import cine.monteiro.screens.ouvintes.OuvinteLinkRecuperarSenha;
import cine.monteiro.usuarios.Administrador;
import cine.monteiro.usuarios.Usuario;

public class WindowsLogin extends Windows {
	// Atributos
	private JTextField tfLogin;
	private JPasswordField tfSenha;
	
	public WindowsLogin() {
		super("Login - Cine Monteiro", 410, 490);
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
		lblLogoProjeto.setBounds(85, 25, 200, 173);
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
		lblLogin.setToolTipText("Digite seu e-mail aqui");
		add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
		lblSenha.setBounds(30, 235, 100, 100);
		lblSenha.setToolTipText("Digite sua senha aqui");
		add(lblSenha);
		
		JLabel lblEsqueceuASenha = new JLabel("Esqueceu a senha?");
		lblEsqueceuASenha.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
		lblEsqueceuASenha.setForeground(new Color(42, 133, 175));
		lblEsqueceuASenha.setBounds(232, 350, 140, 20);
		lblEsqueceuASenha.addMouseListener(new OuvinteLinkRecuperarSenha(this));
		add(lblEsqueceuASenha);
	}
	
	private void adicionarInputs() {
		tfLogin = new Input(65, 225, 305, 36);
		tfLogin.setToolTipText("Digite seu e-mail aqui");
		add(tfLogin);
		
		tfSenha = new JPasswordField();
		tfSenha.setBounds(65, 300, 305, 36);
		tfSenha.setToolTipText("Digite sua senha aqui");
		tfSenha.setHorizontalAlignment(JTextField.CENTER);
		add(tfSenha);
	}
	
	private void adicionarButtons() {
		JButton btnEntrar = new JButton("ENTRAR");
		btnEntrar.setBounds(30, 385, 160, 40);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persistencia bancoDeInformacoes = new Persistencia();
				CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
				
				if(tfLogin.getText().isBlank() || tfSenha.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "ATEN��O", JOptionPane.WARNING_MESSAGE);
				} else {

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
		});
		add(btnEntrar);
		
		JButton btnNovaConta = new JButton("CRIAR CONTA");
		btnNovaConta.setBounds(210, 385, 160, 40);
		btnNovaConta.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e) {
				new WindowsCadastro();
				dispose();
			}
		});
		add(btnNovaConta);
	}

	private void adicionarCheckcBox() {
		JCheckBox cbLembrarDados = new JCheckBox("Lembrar e-mail", false);
		cbLembrarDados.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
		cbLembrarDados.setBounds(30, 340, 150, 40);
		add(cbLembrarDados);
	}

}