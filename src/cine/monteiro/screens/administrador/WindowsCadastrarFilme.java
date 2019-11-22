package cine.monteiro.screens.administrador;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import cine.monteiro.screens.componentes.Input;
import cine.monteiro.screens.componentes.Rotulo;
import cine.monteiro.screens.componentes.RotuloTitulo;
import cine.monteiro.screens.componentes.Windows;


public class WindowsCadastrarFilme extends Windows {
	public WindowsCadastrarFilme() {
		super("Filme - Cadastrar Filmes", 400, 475);
		adicionarLabels();
		adicionarInputs();
		adicionarButtons();
		setVisible(true);
	}
	
	private void adicionarLabels() {
		JLabel lblTitulo = new RotuloTitulo("CADASTRAR NOVO FILMES", 0, 20, 400, 20);
		add(lblTitulo);
		
		JLabel lblNomeDoFilme = new Rotulo("Nome do Filme:", 20, 55, 150, 30);
		add(lblNomeDoFilme);
		
		JLabel lblSinopse = new Rotulo("Sinopse:", 20, 120, 100, 30);
		add(lblSinopse);
		
		JLabel lblDuracao = new Rotulo("Duração: (m)", 20, 255, 100, 30);
		add(lblDuracao);
		
		JLabel lblGenero = new Rotulo("Gênero:", 205, 255, 100, 30);
		add(lblGenero);
		
		JLabel lblClassificacaoEtaria = new Rotulo("Classificação Etária:", 20, 320, 200, 30);
		add(lblClassificacaoEtaria);
	}
	
	private void adicionarInputs() {
		JTextField tfNomeDoFilme = new Input(20, 85, 345, 30);
		add(tfNomeDoFilme);
		
		Border borda = BorderFactory.createLineBorder(Color.GRAY, 1);
		
		JTextArea areaSinopse = new JTextArea();
		areaSinopse.setLineWrap(true);
		areaSinopse.setWrapStyleWord(true);
		areaSinopse.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		areaSinopse.setBounds(20, 150, 345, 100);
		areaSinopse.setBorder(borda);
		add(areaSinopse);
		
		JTextField tfDuracao = new Input(20, 285, 160, 30);
		tfDuracao.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char caractere = e.getKeyChar();
				
				if(!Character.isDigit(caractere)) {
					e.consume();
				}
			}

			public void keyPressed(KeyEvent e) {
				
			}

			public void keyReleased(KeyEvent e) {
				
			}
			
		});
		add(tfDuracao);
		
		String[] opcoes = {"Ação", "Aventura", "Terror", "Ficção Científica", "Drama", "Comédia"};
		JComboBox<String> cbGenero = new JComboBox<String>(opcoes);
		cbGenero.setBounds(205, 285, 160, 30);
		add(cbGenero);
		
		JRadioButton rbLivre = new JRadioButton("Livre", true);
		rbLivre.setBounds(40, 345, 55, 30);
		add(rbLivre);
		
		JRadioButton rb10 = new JRadioButton("10");
		rb10.setBounds(105, 345, 40, 30);
		add(rb10);
		
		JRadioButton rb12 = new JRadioButton("12");
		rb12.setBounds(155, 345, 40, 30);
		add(rb12);
		
		JRadioButton rb14 = new JRadioButton("14");
		rb14.setBounds(205, 345, 40, 30);
		add(rb14);
		
		JRadioButton rb16 = new JRadioButton("16");
		rb16.setBounds(255, 345, 40, 30);
		add(rb16);
		
		JRadioButton rb18= new JRadioButton("18");
		rb18.setBounds(305, 345, 40, 30);
		add(rb18);
		
		ButtonGroup grupoRadioButton = new ButtonGroup();
		grupoRadioButton.add(rbLivre);
		grupoRadioButton.add(rb10);
		grupoRadioButton.add(rb12);
		grupoRadioButton.add(rb14);
		grupoRadioButton.add(rb16);
		grupoRadioButton.add(rb18);
		
	}
	
	private void adicionarButtons() {
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(20, 385, 160, 35);
		add(btnCadastrar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(205, 385, 160, 35);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsFilmes();
			}
		});
		add(btnCancelar);
	}
}
