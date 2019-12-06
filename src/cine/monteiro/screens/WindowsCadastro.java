package cine.monteiro.screens;

// Pacotes
import cine.monteiro.dados.*;
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.*;
import cine.monteiro.usuarios.*;

// Bibliotecas
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class WindowsCadastro extends Windows {
	// Atributos
	private JTextField tfNome;
	private JFormattedTextField  tfCPF;
	private JFormattedTextField tfDataDeNascimento;
	private JFormattedTextField tfTelefone;
	private JTextField tfEmail;
	private JPasswordField tfSenha;
	private JPasswordField tfConfirmarSenha;
	
	// Construtor
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
		
			MaskFormatter mascaraDataDeNascimento = new MaskFormatter("##/##/####");
			tfDataDeNascimento = new JFormattedTextField(mascaraDataDeNascimento);
			tfDataDeNascimento.setBounds(400, 155, 195, 35);
			tfDataDeNascimento.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
			tfDataDeNascimento.setHorizontalAlignment(JTextField.CENTER);
			add(tfDataDeNascimento);
		
			MaskFormatter mascaraTelefone = new MaskFormatter("(##) #.####-####");
			tfTelefone = new JFormattedTextField(mascaraTelefone);
			tfTelefone.setBounds(195, 225, 400, 35);
			tfTelefone.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
			tfTelefone.setHorizontalAlignment(JTextField.CENTER);
			add(tfTelefone);
		} catch(ParseException e) {
			JOptionPane.showMessageDialog(null, "HOUVE UM ERRO DURANTE HÁ VALIDAÇÃO DOS DADOS!");
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
		// Modificar aqui
		JLabel lblSubTitulo = new JLabel("PREENCHA TODOS OS DADOS");
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
		JButton btnCadastrar = new ButtonPersonalizado("CADASTRAR", 195, 415, 195, 40);
		btnCadastrar.addActionListener(new OuvinteCadastrar());
		add(btnCadastrar);
		
		
		JButton btnCancelar = new ButtonPersonalizado("CANCELAR", 400, 415, 195, 40);
		btnCancelar.addActionListener(new OuvinteCancelar());
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
			if(!(new String(tfSenha.getPassword()).equals(new String(tfConfirmarSenha.getPassword())))) {
				JOptionPane.showMessageDialog(null, "SENHAS INCORRETAS!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
				tfSenha.setText("");
				tfConfirmarSenha.setText("");
				repaint();
			} else if (tfNome.getText().isEmpty() || tfCPF.getText().isEmpty() || tfTelefone.getText().isEmpty() || tfDataDeNascimento.getText().isEmpty() ||tfEmail.getText().isEmpty() || tfSenha.getPassword().length == 0){
				JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS DADOS!", "ATENÇÃO!", JOptionPane.WARNING_MESSAGE);
			} else if(!(cpd.validarEmail(tfEmail.getText()))) {
				JOptionPane.showMessageDialog(null, "E-MAIL INVÁLIDO!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
				tfEmail.setText("");
			} else {
				Usuario novoUsuario;
				Date dataDeNascimento = null;

				try {
					SimpleDateFormat formatoDataDeNascimento = new SimpleDateFormat("dd/MM/yyyy");
					formatoDataDeNascimento.setLenient(false);
					 dataDeNascimento = formatoDataDeNascimento.parse(tfDataDeNascimento.getText());
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(null, "DATA DE NASCIMENTO INVÁLIDA!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(cpd.getUsuarios().isEmpty()) {
					novoUsuario = new Administrador(tfNome.getText(), tfCPF.getText(), tfTelefone.getText(), dataDeNascimento, tfEmail.getText(), new String(tfSenha.getPassword()));
				} else {
					novoUsuario = new Cliente(tfNome.getText(), tfCPF.getText(), tfTelefone.getText(), dataDeNascimento, tfEmail.getText(), new String(tfSenha.getPassword()));
				}
				
				try {
					cpd.adicionarUsuario(novoUsuario);
					JOptionPane.showMessageDialog(null, "CADASTRO REALIZADO COM SUCESSO!", "NOVA CONTA", JOptionPane.INFORMATION_MESSAGE);
					bancoDeInformacoes.salvarCentralDeInformacoes(cpd);
					dispose();
					new WindowsLogin();
				} catch(Exception erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "ATENÇÃO!", JOptionPane.WARNING_MESSAGE);
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