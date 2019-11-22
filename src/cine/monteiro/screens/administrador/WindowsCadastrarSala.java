package cine.monteiro.screens.administrador;

// APIs
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JTextField tfNomeDaSala;
	private JFormattedTextField tfPrecoDoIngresso;
	private JTextField tfQuantidadeDeAssentos;
	private JRadioButton rb2D;
	private JRadioButton rb3D;
	private JRadioButton rb2D3D;
	
	Sala novaSala = new Sala();
	
	// Construtor
	public WindowsCadastrarSala() {
		super("Salas - Cadastrar Nova Sala", 360, 420);
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
		
		JLabel lblQuantidadeDeAssentos = new Rotulo("Qntd. de Assentos:", 185, 195, 200, 30);
		add(lblQuantidadeDeAssentos);
		
		JLabel lblProjecaoSuportada = new Rotulo("Projeção Suportada:", 20, 260, 200, 30);
		add(lblProjecaoSuportada);
	}
	
	private void adicionarInputs() {
		JTextField tfID = new JTextField(novaSala.getID() + "");
		tfID.setBounds(20, 95, 305, 30);
		tfID.enable(false);
		tfID.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		tfID.setHorizontalAlignment(JTextField.CENTER);
		add(tfID);
		
		tfNomeDaSala = new Input(20, 160, 305, 30);
		add(tfNomeDaSala);
		
		try {
			MaskFormatter mascaraPrecoDoIngresso = new MaskFormatter("R$ ##,##");
			tfPrecoDoIngresso = new JFormattedTextField(mascaraPrecoDoIngresso);
			tfPrecoDoIngresso.setBounds(20, 225, 140, 30);
			tfPrecoDoIngresso.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
			tfPrecoDoIngresso.setHorizontalAlignment(JFormattedTextField.CENTER);
			add(tfPrecoDoIngresso);
		} catch(Exception e) {
			
		}
		
		
		tfQuantidadeDeAssentos = new Input(185, 225, 140, 30);
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
		
		rb2D = new JRadioButton("2D", true);
		rb2D.setBounds(90, 285, 50, 30);
		add(rb2D);
		
		rb3D = new JRadioButton("3D");
		rb3D.setBounds(150, 285, 50, 30);
		add(rb3D);
		
		rb2D3D = new JRadioButton("2D/3D");
		rb2D3D.setBounds(200, 285, 80, 30);
		add(rb2D3D);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rb2D);
		group.add(rb3D);
		group.add(rb2D3D);
		
		
	}
	
	private void adicionarButtons() {
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(20, 325, 145, 35);
		btnCadastrar.addActionListener(new ActionListener() {
			Persistencia bancoDeInformacoes = new Persistencia();
			CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
			
			public void actionPerformed(ActionEvent e) {
				if(tfNomeDaSala.getText().isEmpty() || tfPrecoDoIngresso.getText().isEmpty() || tfQuantidadeDeAssentos.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "PREENCHA TODOS OS DADOS!", "ATENÇÃO!", JOptionPane.WARNING_MESSAGE);
				} else {
					// Configurar Nova Sala
					novaSala.setNomeDaSala(tfNomeDaSala.getText());
					novaSala.setPrecoDoIngresso(tfPrecoDoIngresso.getText());
					novaSala.setQuantidadeDeAssentos(Integer.parseInt(tfQuantidadeDeAssentos.getText()));
					
					if(rb2D.isSelected()) {
						novaSala.setTipoDaProjecaoSuportada("2D");
					} else if(rb3D.isSelected()) {
						novaSala.setTipoDaProjecaoSuportada("3D");
					} else {
						novaSala.setTipoDaProjecaoSuportada("2D/3D");
					}
					
					// Adicionar Nova Sala
					try {
						cpd.adicionarSala(novaSala);
						JOptionPane.showMessageDialog(null, "SALA CADASTRADA COM SUCESSO!", "NOVA SALA", JOptionPane.INFORMATION_MESSAGE);
						bancoDeInformacoes.salvarCentralDeInformacoes(cpd);
						dispose();
						new WindowsSala();
					} catch(Exception erro) {
						JOptionPane.showMessageDialog(null, erro.getMessage(), "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
					}
				}
			}	
			
		});
		add(btnCadastrar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(175, 325, 145, 35);
		btnCancelar.addActionListener(new OuvinteBtnVoltar(this));
		add(btnCancelar);
	}
	
	// Ouvintes
	public class OuvinteBtnVoltar implements ActionListener {
		private JFrame windows;
		
		public OuvinteBtnVoltar(JFrame windows) {
			this.windows = windows;
		}
		
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new WindowsSala();
		}
	}
}