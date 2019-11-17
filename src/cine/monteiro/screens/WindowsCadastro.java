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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WindowsCadastro extends Windows {
	private JTextField tfNome;
	private JTextField  tfCPF;
	private JTextField tfDataDeNascimento;
	private JTextField tfTelefone;
	private JTextField tfEmail;
	private JTextField tfSenha;
	private JTextField tfConfirmarSenha;
	
	
	public WindowsCadastro() {
		super("Cadastro - Cine Monteiro", 635, 575);
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
		iconeNovoUsuario.setBounds(40, 185, 100, 100);
		add(iconeNovoUsuario);
	}
	
	private void adicionarInputs() {
		tfNome = new Input(195, 85, 400, 30);
		add(tfNome);
		
		tfCPF = new Input(195, 155, 195, 30);
		add(tfCPF);
		
		tfDataDeNascimento = new Input(400, 155, 195, 30);
		add(tfDataDeNascimento);
		
		tfTelefone = new Input(195, 225, 400, 30);
		add(tfTelefone);
		
		tfEmail = new Input(195, 295, 400, 30);
		add(tfEmail);
		
		tfSenha = new Input(195, 365, 400, 30);
		add(tfSenha);
		
		tfConfirmarSenha = new Input(195, 435, 400, 30);
		add(tfConfirmarSenha);
	}
	
	private void adicionarLabels() {
		JLabel lblSubTitulo = new JLabel("Preencha todos os dados");
		lblSubTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		lblSubTitulo.setBounds(285, 20, 300, 20);
		add(lblSubTitulo);
		
		JLabel lblTitulo = new JLabel("NOVA CONTA");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblTitulo.setBounds(35, 295, 200, 20);
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
		
		JLabel lblConfirmarSenha = new Rotulo("Confirmar Senha:", 195, 395, 200, 50);
		add(lblConfirmarSenha);
	}
	
	private void adicionarButtons() {
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(195, 480, 190, 35);
		btnCadastrar.addActionListener(new OuvinteCadastrar());
		add(btnCadastrar);
		
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(405, 480, 190, 35);
		btnCancelar.addActionListener(new OuvinteCancelar());
		add(btnCancelar);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new JSeparator(SwingConstants.VERTICAL);
		separador.setForeground(Color.GRAY);
		separador.setBounds(170, 5, 2, 525);
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
				
				if(cpd.getUsuarios().isEmpty()) {
					novoUsuario = new Administrador(tfNome.getText(), tfCPF.getText(), tfTelefone.getText(), tfDataDeNascimento.getText(), tfEmail.getText(), tfSenha.getText());
				} else {
					novoUsuario = new Cliente(tfNome.getText(), tfCPF.getText(), tfTelefone.getText(), tfDataDeNascimento.getText(), tfEmail.getText(), tfSenha.getText());
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