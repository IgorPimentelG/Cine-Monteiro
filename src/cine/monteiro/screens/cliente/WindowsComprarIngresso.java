package cine.monteiro.screens.cliente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.border.Border;

import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.ButtonAssento;
import cine.monteiro.screens.componentes.RotuloDetalhar;
import cine.monteiro.screens.componentes.RotuloTitulo;
import cine.monteiro.screens.componentes.Separador;
import cine.monteiro.screens.componentes.Windows;

public class WindowsComprarIngresso extends Windows {
	private ArrayList<String> assentosReservados = new ArrayList<String>();
	private Sala local;
	private Sessao sessao;
	
	public WindowsComprarIngresso(Sala local, Sessao sessao) {
		super("Carrinho de Compra", 840, 510);
		this.local = local;
		this.sessao = sessao;
		adicionarImagem();
		adicionarSeparador();
		adicionarLabels();
		adicionarButtons();
		setVisible(true);
	}
	
	private void adicionarImagem() {
		JLabel lblIconeIngresso = new JLabel(Imagens.INGRESSO);
		lblIconeIngresso.setBounds(80, 20, 100, 100);
		add(lblIconeIngresso);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new Separador(250, 5, 2, 460);
		add(separador);
	}
	
	private void adicionarLabels() {
		JLabel lblEscolhaOAssento = new RotuloTitulo("ESCOLHA SEU ASSENTO", 250, 15, 590, 20);
		add(lblEscolhaOAssento);
		
		JLabel lblTela = new JLabel("TELA");
		Border bordar = BorderFactory.createLineBorder(Color.GRAY, 1);
		lblTela.setOpaque(true);
		lblTela.setBackground(Color.WHITE);
		lblTela.setBorder(bordar);
		lblTela.setHorizontalAlignment(JLabel.CENTER);
		lblTela.setBounds(270, 55, 535, 35);
		add(lblTela);
		
		JLabel lblIngresso = new RotuloTitulo("INGRESSO", 0, 115, 250, 20);
		add(lblIngresso);
		
		JLabel lblNomeDoFilme = new RotuloTitulo(sessao.getFilme().getNomeDoFilme(), 0, 160, 250, 30);
		add(lblNomeDoFilme);
		
		JLabel lblLocal = new RotuloDetalhar("Local: " + local.getNomeDaSala(), 20, 200, 150, 20);
		add(lblLocal);
		
		
	}
	
	private void adicionarButtons() {
		// Fila A
		JButton btnA1 = new ButtonAssento("A1", 270, 115);
		btnA1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		add(btnA1);
		JButton btnA2 = new ButtonAssento("A2", 335, 115);
		add(btnA2);
		JButton btnA3 = new ButtonAssento("A3", 400, 115);
		add(btnA3);
		JButton btnA4 = new ButtonAssento("A4", 465, 115);
		add(btnA4);
		JButton btnA5 = new ButtonAssento("A5", 555, 115);
		add(btnA5);
		JButton btnA6 = new ButtonAssento("A6", 620, 115);
		add(btnA6);
		JButton btnA7 = new ButtonAssento("A7", 685, 115);
		add(btnA7);
		JButton btnA8 = new ButtonAssento("A8", 750, 115);
		add(btnA8);
		
		// Fila B
		JButton btnB1 = new ButtonAssento("B1", 270, 185);
		add(btnB1);
		JButton btnB2 = new ButtonAssento("B2", 335, 185);
		add(btnB2);
		JButton btnB3 = new ButtonAssento("B3", 400, 185);
		add(btnB3);
		JButton btnB4 = new ButtonAssento("B4", 465, 185);
		add(btnB4);
		JButton btnB5 = new ButtonAssento("B5", 555, 185);
		add(btnB5);
		JButton btnB6 = new ButtonAssento("B6", 620, 185);
		add(btnB6);
		JButton btnB7 = new ButtonAssento("B7", 685, 185);
		add(btnB7);
		JButton btnB8 = new ButtonAssento("B8", 750, 185);
		add(btnB8);
		
		// Fila C
		JButton btnC1 = new ButtonAssento("C1", 270, 255);
		add(btnC1);
		JButton btnC2 = new ButtonAssento("C2", 335, 255);
		add(btnC2);
		JButton btnC3 = new ButtonAssento("C3", 400, 255);
		add(btnC3);
		JButton btnC4 = new ButtonAssento("C4", 465, 255);
		add(btnC4);
		JButton btnC5 = new ButtonAssento("C5", 555, 255);
		add(btnC5);
		JButton btnC6 = new ButtonAssento("C6", 620, 255);
		add(btnC6);
		JButton btnC7 = new ButtonAssento("C7", 685, 255);
		add(btnC7);
		JButton btnC8 = new ButtonAssento("C8", 750, 255);
		add(btnC8);
		
		// Fila D
		JButton btnD1 = new ButtonAssento("D1", 270, 325);
		add(btnD1);
		JButton btnD2 = new ButtonAssento("D2", 335, 325);
		add(btnD2);
		JButton btnD3 = new ButtonAssento("D3", 400, 325);
		add(btnD3);
		JButton btnD4 = new ButtonAssento("D4", 465, 325);
		add(btnD4);
		JButton btnD5 = new ButtonAssento("D5", 555, 325);
		add(btnD5);
		JButton btnD6 = new ButtonAssento("D6", 620, 325);
		add(btnD6);
		JButton btnD7 = new ButtonAssento("D7", 685, 325);
		add(btnD7);
		JButton btnD8 = new ButtonAssento("D8", 750, 325);
		add(btnD8);
		
		// Fila E
		JButton btnE1 = new ButtonAssento("E1", 270, 395);
		add(btnE1);
		JButton btnE2 = new ButtonAssento("E2", 335, 395);
		add(btnE2);
		JButton btnE3 = new ButtonAssento("E3", 400, 395);
		add(btnE3);
		JButton btnE4 = new ButtonAssento("E4", 465, 395);
		add(btnE4);
		JButton btnE5 = new ButtonAssento("E5", 555, 395);
		add(btnE5);
		JButton btnE6 = new ButtonAssento("E6", 620, 395);
		add(btnE6);
		JButton btnE7 = new ButtonAssento("E7", 685, 395);
		add(btnE7);
		JButton btnE8 = new ButtonAssento("E8", 750, 395);
		add(btnE8);	
	}
}
