package cine.monteiro.screens;

// Pacotes
import cine.monteiro.dados.*;
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.Input;
import cine.monteiro.screens.componentes.Rotulo;
import cine.monteiro.screens.componentes.Windows;
import cine.monteiro.usuarios.*;

// Bibliotecas
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class WindowsCadastro extends Windows {
	private JTextField tfNome;
	private JFormattedTextField  tfCPF;
	private JFormattedTextField tfDataDeNascimento;
	private JFormattedTextField tfTelefone;
	private JTextField tfEmail;
	private JPasswordField tfSenha;
	private JPasswordField tfConfirmarSenha;
	
	
	public WindowsCadastro() {
		super("Cadastro - Cine Monteiro", 635, 515);
		adicionarSeparador();
		adicionarImagens();
		adicionarInputs();
		adicionarLabels();
		adicionarButtons();
		setVisible(true);
	}
	
	// Componentes
	private void adicionarImagens() {
		JLabel iconeNovoUsuario = new JLabel(Imagens.NOVO_USER_100x100);
		iconeNovoUsuario.setBounds(40, 165, 100, 100);
		add(iconeNovoUsuario);
	}
	
	private void adicionarInputs() {
		tfNome = new Input(195, 85, 400, 35);
		add(tfNome);
		
		try {
			MaskFormatter mascaraDoCPF = new MaskFormatter("###.###.###-##");
			tfCPF = new JFormattedTextField(mascaraDoCPF);
			tfCPF.setBounds(195, 155, 195, 35);
			tfCPF.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
			tfCPF.setHorizontalAlignment(JTextField.CENTER);
			add(tfCPF);
		} catch(ParseException e) {
			
		}
		
		try {
			MaskFormatter mascaraDataDeNascimento = new MaskFormatter("##/##/####");
			tfDataDeNascimento = new JFormattedTextField(mascaraDataDeNascimento);
			tfDataDeNascimento.setBounds(400, 155, 195, 35);
			tfDataDeNascimento.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
			tfDataDeNascimento.setHorizontalAlignment(JTextField.CENTER);
			add(tfDataDeNascimento);
		} catch(ParseException e) {
			
		}
		
		try {
			MaskFormatter mascaraTelefone = new MaskFormatter("(##) #.####-####");
			tfTelefone = new JFormattedTextField(mascaraTelefone);
			tfTelefone.setBounds(195, 225, 400, 35);
			tfTelefone.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
			tfTelefone.setHorizontalAlignment(JTextField.CENTER);
			add(tfTelefone);
		} catch(ParseException e) {
			
		}
		
		
		tfEmail = new Input(195, 295, 400, 35);
		add(tfEmail);
		
		tfSenha = new JPasswordField();
		tfSenha.setBounds(195, 365, 195, 35);
		tfSenha.setHorizontalAlignment(JPasswordField.CENTER);
		add(tfSenha);
		
		tfConfirmarSenha = new JPasswordField();
		tfConfirmarSenha.setBounds(400, 365, 195, 35);
		tfConfirmarSenha.setHorizontalAlignment(JPasswordField.CENTER);
		add(tfConfirmarSenha);
	}
	
	private void adicionarLabels() {
		JLabel lblSubTitulo = new JLabel("Preencha todos os dados");
		lblSubTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		lblSubTitulo.setBounds(285, 20, 300, 20);
		add(lblSubTitulo);
		
		JLabel lblTitulo = new JLabel("NOVA CONTA");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblTitulo.setBounds(35, 275, 200, 20);
		add(lblTitulo);
		
		JLabel lblNome = new Rotulo("Nome:", 195, 45, 50, 50);
		add(lblNome);
		
		JLabel lblCPF = new Rotulo("CPF:", 195, 115, 50, 50);
		add(lblCPF);
		
		JLabel lblDataDeNascimento = new Rotulo("Data de Nascimento:", 400, 115, 200, 50);
		add(lblDataDeNascimento);
		
		JLabel lblTelefone = new Rotulo("Telefone:", 195, 185, 100, 50);
		add(lblTelefone);
		
		JLabel lblEmail = new Rotulo("E-mail:", 195, 255, 100, 50);
		add(lblEmail);
		
		JLabel lblSenha = new Rotulo("Senha:", 195, 325, 100, 50);
		add(lblSenha);
		
		JLabel lblConfirmarSenha = new Rotulo("Confirmar Senha:", 400, 325, 200, 50);
		add(lblConfirmarSenha);
	}
	
	private void adicionarButtons() {
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(195, 415, 195, 40);
		btnCadastrar.addActionListener(new OuvinteCadastrar());
		btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnCadastrar);
		
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(400, 415, 195, 40);
		btnCancelar.addActionListener(new OuvinteCancelar());
		btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(btnCancelar);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new JSeparator(SwingConstants.VERTICAL);
		separador.setForeground(Color.GRAY);
		separador.setBounds(170, 5, 2, 465);
		add(separador);
	}
	
	// Ouvintes
	public class OuvinteCadastrar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Persistencia bancoDeInformacoes = new Persistencia();
			CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
			
			// Validar Dados
			if(!(tfSenha.getText().equals(tfConfirmarSenha.getText()))) {
				JOptionPane.showMessageDialog(null, "ATEN플O! Senha Incorretas.", "ATEN플O!", JOptionPane.WARNING_MESSAGE);
				tfSenha.setText("");
				tfConfirmarSenha.setText("");
				repaint();
			} else if (tfNome.getText().isBlank() || tfCPF.getText().isBlank() || tfTelefone.getText().isBlank() || tfDataDeNascimento.getText().isBlank() ||tfEmail.getText().isBlank() || tfSenha.getText().isBlank()){
				JOptionPane.showMessageDialog(null, "Preencha Todos os Dados.", "ATEN플O!", JOptionPane.WARNING_MESSAGE);
			} else {
				Usuario novoUsuario;
				Date dataDeNascimento = null;
			
				
				try {
					SimpleDateFormat formatoDataDeNascimento = new SimpleDateFormat("dd/MM/yyyy");
					 dataDeNascimento = formatoDataDeNascimento.parse(tfDataDeNascimento.getText());
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "DATA DE NASCIMENTO INV핶IDA!", "ATEN플O!", JOptionPane.ERROR_MESSAGE);
				}
				
				if(cpd.getUsuarios().isEmpty()) {
					novoUsuario = new Administrador(tfNome.getText(), tfCPF.getText(), tfTelefone.getText(), dataDeNascimento, tfEmail.getText(), tfSenha.getText());
				} else {
					novoUsuario = new Cliente(tfNome.getText(), tfCPF.getText(), tfTelefone.getText(), dataDeNascimento, tfEmail.getText(), tfSenha.getText());
				}
				
				
				try {
					cpd.adicionarUsuario(novoUsuario);
					bancoDeInformacoes.salvarCentralDeInformacoes(cpd);
					dispose();
					new WindowsLogin();
				} catch(Exception erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "ATEN플O!", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
	}
	
	public class OuvinteCancelar implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
			new WindowsLogin();
		}
	}
}