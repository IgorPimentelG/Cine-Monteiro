package cine.monteiro.screens.administrador;

import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
// Pacotes
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.*;
import cine.monteiro.screens.cliente.WindowsHomeCliente;
import cine.monteiro.screens.componentes.*;
import cine.monteiro.usuarios.Usuario;

// APIs
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WindowsPainelDeControle extends Windows {
	// Construtor
	public WindowsPainelDeControle() {
		super("Painel de Controle - Cine Monteiro", 700, 320);
		adicionarLabels();
		adicionarImagens();
		adicionarButtons();
		adicionarSeparador();
		adicionarMenuBar();
		setVisible(true);
	}
	
	// Componentes
	private void adicionarLabels() {
		JLabel lblTitulo = new JLabel("PAINEL DE CONTROLE");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblTitulo.setBounds(358, 15, 200, 20);
		add(lblTitulo);
		
		JLabel lblAdmin = new JLabel("ADMINISTRADOR");
		lblAdmin.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		lblAdmin.setBounds(35, 125, 150, 100);
		add(lblAdmin);
	}
	
	private void adicionarImagens() {
		JLabel lblLogoProjeto = new JLabel(Imagens.ADMIN_100x100);
		lblLogoProjeto.setBounds(10, 25, 180, 156);
		add(lblLogoProjeto);
	}
	
	private void adicionarButtons() {
		JButton btnFilmes = new ButtonPersonalizado("FILMES", 230, 55, 130, 80);
		btnFilmes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsFilme();
			}
		});
		add(btnFilmes);
		
		JButton btnSessoes = new ButtonPersonalizado("SESSÕES", 380, 55, 130, 80);
		btnSessoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsSessao();
			}
		});
		add(btnSessoes);
		
		JButton btnSalas = new ButtonPersonalizado("SALAS", 530, 55, 130, 80);
		btnSalas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsSala();
			}
		});
		add(btnSalas);
		
		JButton btnRelatorios = new ButtonPersonalizado("RELATÁRIOS", 230, 150, 130, 80);
		add(btnRelatorios);
		 
		JButton btnAreaDoCliente = new ButtonPersonalizado("<html><center>ÁREA<br> DO CLIENTE<center></html>", 380, 150, 130, 80);
		btnAreaDoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persistencia bancoDeInformacoes = new Persistencia();
				CentralDeInformacoes centralDeInformacoes = bancoDeInformacoes.recuperarCentralDeInformacoes();
				dispose();
				new WindowsHomeCliente(centralDeInformacoes.getUsuarios().get(0));
			}
		});
		add(btnAreaDoCliente);
		
		JButton btnMarketing = new ButtonPersonalizado("MARKETING", 530, 150, 130, 80);
		btnMarketing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsMarketing();
			}
		});
		add(btnMarketing);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new JSeparator(SwingConstants.VERTICAL);
		separador.setBounds(195, 10, 1, 240);
		separador.setForeground(Color.GRAY);
		add(separador);
	}

	private void adicionarMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("OPÇÕES");
		menuBar.add(menu);
			
		JMenuItem itemSobre = new JMenuItem("SOBRE");
		itemSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WindowsSobre();
			}
		});
		menu.add(itemSobre);
		
		JMenuItem itemSair = new JMenuItem("SAIR");
		itemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsLogin();
			}
		});
		menu.add(itemSair);
	}
}
