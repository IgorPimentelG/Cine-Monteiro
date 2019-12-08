package cine.monteiro.screens.cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.gerenciamento.ClassificacaoEtaria;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.*;
import cine.monteiro.screens.administrador.WindowsPainelDeControle;
import cine.monteiro.screens.componentes.ButtonPersonalizado;
import cine.monteiro.screens.componentes.RotuloTitulo;
import cine.monteiro.screens.componentes.Separador;
import cine.monteiro.screens.componentes.Windows;
import cine.monteiro.usuarios.Administrador;
import cine.monteiro.usuarios.Usuario;

public class WindowsHomeCliente extends Windows {
	// Atributos
	private Usuario usuarioAtivo;
	private JTable tabelaDasSessoes;
	
	// Instâncias
	Persistencia bancoDeInformacoes = new Persistencia();
	CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
	
	// Construtor
	public WindowsHomeCliente(Usuario usuarioAtivo) {
		super("Cine Monteiro - Home", 855, 400);
		this.usuarioAtivo = usuarioAtivo;
		adicionarMenuBar();
		adicionarLabels();
		adicionarTabela();
		adicionarSeparador();
		adicionarImagem();
		adicionarButton();
		setVisible(true);
	}
	
	// Componentes
	private void adicionarMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("OPÇÕES");
		menuBar.add(menu);
		
		JMenuItem itemAlterarSenha = new JMenuItem("ALTERAR SENHA");
		itemAlterarSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsDefinirNovaSenha(usuarioAtivo);	
			}
		});
		menu.add(itemAlterarSenha);
		
		JMenuItem itemSobre = new JMenuItem("SOBRE");
		itemSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WindowsSobre();
			}
		});
		menu.add(itemSobre);
		
		if(usuarioAtivo instanceof Administrador) {
			JMenuItem itemVoltar = new JMenuItem("PAINEL DE CONTROLE");
			itemVoltar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					new WindowsPainelDeControle();
				}
			});
			menu.add(itemVoltar);
		}
		
		
		JMenuItem itemSair = new JMenuItem("SAIR");
		itemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsLogin();
			}
		});
		menu.add(itemSair);
	}
	
	private void adicionarLabels() {
		JLabel lblTitulo = new RotuloTitulo("FILMES EM CARTAZ", 165, 15, 660, 30);
		lblTitulo.setForeground(new Color(225, 112, 85));
		add(lblTitulo);
	}
	
	private void adicionarImagem() {
		JLabel lblLogoProjeto = new JLabel(Imagens.LOGO_CINE_MONTEIRO_180x156);
		lblLogoProjeto.setBounds(5, 90, 180, 156);
		add(lblLogoProjeto);
	}
	
	private void adicionarSeparador() {
		JSeparator separator = new Separador(200, 5, 2, 327);
		add(separator);
	}
	
	private void adicionarButton() {
		JButton btnComprar = new ButtonPersonalizado("COMPRAR", 300, 290, 200, 35);
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sala local = cpd.pesquisarSala((String) tabelaDasSessoes.getValueAt(tabelaDasSessoes.getSelectedRow(), 1));
				long idDaSessao = Long.parseLong(tabelaDasSessoes.getValueAt(tabelaDasSessoes.getSelectedRow(),  0) + "");
				Sessao sessaoEscolihada = cpd.pesquisarSessao(local, idDaSessao);
				
				if(tabelaDasSessoes.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "NENHUMA SESSÃO FOI SELECIONADA!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
				} else if(sessaoEscolihada.getVagasDisponiveis() == 0) { 
					JOptionPane.showMessageDialog(null, "NÃO HÁ MAIS VAGAS DISÓNÍVEIS PARA ESTA SESSÃO!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				} else {
					if(sessaoEscolihada.getFilme().getClassificacaoEtaria() != ClassificacaoEtaria.LIVRE) {
						int classificacaoEtaria = 0;
						
						if(sessaoEscolihada.getFilme().getClassificacaoEtaria() == ClassificacaoEtaria.I10) {
							classificacaoEtaria = 10;
						} else if(sessaoEscolihada.getFilme().getClassificacaoEtaria() == ClassificacaoEtaria.I12) {
							classificacaoEtaria = 12;
						} else if(sessaoEscolihada.getFilme().getClassificacaoEtaria() == ClassificacaoEtaria.I14) {
							classificacaoEtaria = 14;
						} else if(sessaoEscolihada.getFilme().getClassificacaoEtaria() == ClassificacaoEtaria.I16) {
							classificacaoEtaria = 16;
						} else if(sessaoEscolihada.getFilme().getClassificacaoEtaria() == ClassificacaoEtaria.I18) {
							classificacaoEtaria = 18;
						}
						
						if(usuarioAtivo.getIdade() <= classificacaoEtaria) {
							JOptionPane.showMessageDialog(null, "A CLASSIFICAÇÃO ETÁRIA DESTA SESSÃO É IMPRÓPRIA PARA SUA IDADE.", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
					dispose();
					new WindowsComprarIngresso(usuarioAtivo, local, sessaoEscolihada);
				}
			}
		});
		btnComprar.setForeground(Color.WHITE);
		btnComprar.setBackground(new Color(0, 184, 148));
		add(btnComprar);
		
		JButton btnInformacoes = new ButtonPersonalizado("INFORMAÇÕES", 520, 290, 200, 35);
		btnInformacoes.setForeground(Color.WHITE);
		btnInformacoes.setBackground(new Color(120, 111, 166));
		btnInformacoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sala local = cpd.pesquisarSala((String) tabelaDasSessoes.getValueAt(tabelaDasSessoes.getSelectedRow(), 1));
				long idDaSessao = Long.parseLong(tabelaDasSessoes.getValueAt(tabelaDasSessoes.getSelectedRow(),  0) + "");
				Sessao sessaoEscolihada = cpd.pesquisarSessao(local, idDaSessao);
				
				if(tabelaDasSessoes.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "NENHUMA SESSÃO FOI SELECIONADA!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
				} else {
					dispose();
					new WindowsInformacoesSessao(local, sessaoEscolihada, usuarioAtivo);
				}
			}
		});
		add(btnInformacoes);
		
	}
	
	private void adicionarTabela() {
		DefaultTableModel modeloDaTabela = new DefaultTableModel();	
		modeloDaTabela.addColumn("Sessão");
		modeloDaTabela.addColumn("Sala");
		modeloDaTabela.addColumn("Nome do Filme");
		modeloDaTabela.addColumn("Classificação");
		modeloDaTabela.addColumn("Ingresso");
		modeloDaTabela.addColumn("Vagas");
		
		tabelaDasSessoes = new JTable(modeloDaTabela);
		
		LocalTime horaAtual = LocalTime.now();
		ArrayList<Sala> salas = cpd.getSalas();
		for(Sala sala : salas) {
			ArrayList<Sessao> sessoes = sala.getSessoes();
			
			for(Sessao sessao : sessoes) {
				if(sessao.isAtiva() && horaAtual.isBefore(sessao.getHoraDeInicio())) {		
					Object[] linha = new Object[] {sessao.getID(), sala.getNomeDaSala(),  sessao.getFilme().getNomeDoFilme(), sessao.getFilme().getClassificacaoEtaria(), sala.getPrecoDoIngresso(), sessao.getVagasDisponiveis()};
					modeloDaTabela.addRow(linha);
				}
			}
			
		}
		
		JScrollPane container = new JScrollPane(tabelaDasSessoes);
		container.setBounds(220, 55, 600, 225);
		add(container);
		repaint();
	}
}
