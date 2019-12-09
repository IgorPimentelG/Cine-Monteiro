package cine.monteiro.screens.administrador;

// APIs
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.gerenciamento.Sala;
// Pacotes
import cine.monteiro.screens.componentes.*;

public class WindowsCadastrarSala extends Windows {
	// Atributos
	private JTextField tfNomeDaSala;
	private JFormattedTextField tfPrecoDoIngresso;
	private JTextField tfQuantidadeDeAssentos;
	
	// Instâncias
	Sala novaSala = new Sala();
	
	// Construtor
	public WindowsCadastrarSala() {
		super("Salas - Cadastrar Nova Sala", 360, 360);
		adicionarLabels();
		adicionarInputs();
		adicionarButtons();
		setVisible(true);
	}
	
	// Componentes
	private void adicionarLabels() {
		JLabel lblCadastrarNovaSala = new RotuloTitulo("CADASTRAR NOVA SALA", 0, 5, 360, 64);
		add(lblCadastrarNovaSala);
		
		JLabel lblID = new Rotulo("Identificação:", 20, 65, 150, 30);
		add(lblID);
		
		JLabel lblNomeDaSala = new Rotulo("Nome da Sala:", 20, 130, 100, 30);
		add(lblNomeDaSala);
		
		JLabel lblPrecoDoIngresso = new Rotulo("Preço do Ingresso:", 20 , 195, 200, 30);
		add(lblPrecoDoIngresso);
		
		JLabel lblQuantidadeDeAssentos = new Rotulo("Qntd. de Assentos:", 175, 195, 200, 20);
		add(lblQuantidadeDeAssentos);;		
	}
	
	private void adicionarInputs() {
		JTextField tfID = new JTextField(novaSala.getID() + "");
		tfID.setBounds(20, 95, 305, 30);
		tfID.setEditable(false);
		tfID.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		tfID.setHorizontalAlignment(JTextField.CENTER);
		add(tfID);
		
		tfNomeDaSala = new Input(20, 160, 305, 30);
		add(tfNomeDaSala);
		
		try {
			MaskFormatter mascaraPrecoDoIngresso = new MaskFormatter("R$ ##.##");
			tfPrecoDoIngresso = new JFormattedTextField(mascaraPrecoDoIngresso);
			tfPrecoDoIngresso.setBounds(20, 225, 145, 30);
			tfPrecoDoIngresso.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
			tfPrecoDoIngresso.setHorizontalAlignment(JFormattedTextField.CENTER);
			add(tfPrecoDoIngresso);
		} catch(Exception e) {
			
		}
		
		tfQuantidadeDeAssentos = new JTextField("Max. 40");
		tfQuantidadeDeAssentos.setBounds(175, 225, 145, 30);
		tfQuantidadeDeAssentos.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		tfQuantidadeDeAssentos.setHorizontalAlignment(JTextField.CENTER);
		tfQuantidadeDeAssentos.addFocusListener(new FocusListener() {
			public void focusLost(FocusEvent e) {
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				tfQuantidadeDeAssentos.setText("");
			}
		});
		tfQuantidadeDeAssentos.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char caractere =  e.getKeyChar();
				
				if(!Character.isDigit(caractere)) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
					
			}

			public void keyReleased(KeyEvent e) {
				
			}
		});
		add(tfQuantidadeDeAssentos);
	}
	
	private void adicionarButtons() {
		JButton btnCadastrar = new ButtonPersonalizado("CADASTRAR", 20, 270, 145, 35);
		btnCadastrar.addActionListener(new ActionListener() {
			Persistencia bancoDeInformacoes = new Persistencia();
			CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
			
			public void actionPerformed(ActionEvent e) {
				if(tfNomeDaSala.getText().isEmpty() || tfPrecoDoIngresso.getText().equals("R$  .  ") || tfQuantidadeDeAssentos.getText().equals("Max. 40")) {
					JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS DADOS!", "ATENÇÃO!", JOptionPane.WARNING_MESSAGE);
				} else {
					// Configurar Nova Sala
					novaSala.setNomeDaSala(tfNomeDaSala.getText());
					novaSala.setPrecoDoIngresso(tfPrecoDoIngresso.getText());
					
					if(Integer.parseInt(tfQuantidadeDeAssentos.getText()) <= 40) {
						novaSala.setQuantidadeDeAssentos(Integer.parseInt(tfQuantidadeDeAssentos.getText()));
					} else {
						JOptionPane.showMessageDialog(null, "MÁXIMO DE ASSENTOS PERMITIDO É 40.", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
						tfQuantidadeDeAssentos.setText("");
						return;
					}
					
					
					// Adicionar Nova Sala
					try {
						cpd.adicionarSala(novaSala);
						JOptionPane.showMessageDialog(null, "SALA CADASTRADA COM SUCESSO!", "NOVA SALA", JOptionPane.INFORMATION_MESSAGE);
						bancoDeInformacoes.salvarCentralDeInformacoes(cpd);
						dispose();
						new WindowsSala();
					} catch(Exception erro) {
						JOptionPane.showMessageDialog(null, erro.getMessage(), "ATENÇãO!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}	
			
		});
		add(btnCadastrar);
		
		JButton btnCancelar = new ButtonPersonalizado("CANCELAR", 175, 270, 145, 35);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsSala();
			}
		});
		add(btnCancelar);
	}
}