package cine.monteiro.screens;

// APIs
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

// Pacotes
import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.*;

import cine.monteiro.usuarios.Usuario;

public class WindowsDefinirNovaSenha extends Windows {
	// Atributos
	private JPasswordField pwNovaSenha;
	private JPasswordField pwConfirmarSenha;
	private Usuario usuarioSolicitante;
	
	
	
	// Construtor
	public WindowsDefinirNovaSenha(Usuario usuarioSolicitante) {
		super("Cine Monteiro - Definir Nova Senha", 470, 250);
		this.usuarioSolicitante = usuarioSolicitante;
		adicionarLabel();
		adicionarInput();
		adicionarImagem();
		adicionarSeparador();
		adicionarButtons();
		setVisible(true);
	}
	
	// Componentes
	private void adicionarLabel() {		
		JLabel lblNovaSenha = new Rotulo("Nova Senha: ", 140, 10, 100, 20);
		add(lblNovaSenha);
		
		JLabel lblConfirmarSenha = new Rotulo("Confirmar Senha:", 140, 85, 150, 20);
		add(lblConfirmarSenha);
		
		JLabel lblTitulo = new JLabel("NOVA SENHA");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		lblTitulo.setBounds(17, 115, 90, 20);
		add(lblTitulo);
	}
	
	private void adicionarInput() {
		pwNovaSenha = new JPasswordField();
		pwNovaSenha.setHorizontalAlignment(JPasswordField.CENTER);
		pwNovaSenha.setBounds(175, 35, 265, 36);
		add(pwNovaSenha);
		
		pwConfirmarSenha = new JPasswordField();
		pwConfirmarSenha.setBounds(175, 110, 265, 36);
		pwConfirmarSenha.setHorizontalAlignment(JTextField.CENTER);
		add(pwConfirmarSenha);
	}
	
	private void adicionarImagem() {
		JLabel lblIconeAlterar = new JLabel(Imagens.ALTERAR_64x64);
		lblIconeAlterar.setBounds(30, 55, 64, 64);
		add(lblIconeAlterar);
		
		
		JLabel lblIconeSenha = new Icone(Imagens.LOCK_25x25, 140, 35, 35, 35);
		add(lblIconeSenha);
		
		JLabel lblIconeConfirmarSenha = new Icone(Imagens.LOCK_25x25, 140, 110, 35, 35);
		add(lblIconeConfirmarSenha);
	}
	
	private void  adicionarSeparador() {
		JSeparator separador = new Separador(125, 10, 1, 190);
		add(separador);
	}
	
	private void adicionarButtons() {
		JButton btnConfirmar = new ButtonPersonalizado("CONFIRMAR", 140, 160, 145, 35);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pwNovaSenha.getPassword().length == 0 || pwConfirmarSenha.getPassword().length == 0) {
					JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS CAMPOS!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
				} else if(!(new String(pwNovaSenha.getPassword()).equals(new String(pwConfirmarSenha.getPassword())))) {
					JOptionPane.showMessageDialog(null, "AS SENHAS SÃO DIFERENTES!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
					pwNovaSenha.setText("");
					pwConfirmarSenha.setText("");
					repaint();
				} else {
					// Instâncias
					Persistencia bancoDeInformacoes = new Persistencia();
					CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
					
					ArrayList<Usuario> usuarios = cpd.getUsuarios();
					
					for(Usuario usuario : usuarios) {
						if(usuario.getCPF().equals(usuarioSolicitante.getCPF())) {
							usuario.setSenha(new String(pwNovaSenha.getPassword()));
						}
					}
				
					bancoDeInformacoes.salvarCentralDeInformacoes(cpd);
					JOptionPane.showMessageDialog(null, "SENHA ALTERADAD COM SUCESSO!");
					dispose();
					new WindowsLogin();
				}
			}
		});
		add(btnConfirmar);
		
		JButton btnVoltar = new ButtonPersonalizado("VOLTAR", 295, 160, 145, 35);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsLogin();
			}
		});
		add(btnVoltar);
	}
}
