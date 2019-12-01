package cine.monteiro.screens.cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
import cine.monteiro.screens.componentes.ButtonPersonalizado;
import cine.monteiro.screens.componentes.RotuloTitulo;
import cine.monteiro.screens.componentes.Separador;
import cine.monteiro.screens.componentes.Windows;
import cine.monteiro.usuarios.Administrador;
import cine.monteiro.usuarios.Usuario;

public class WindowsHomeCliente extends Windows {
	private Usuario usuarioAtivo;
	private JTable tabelaDasSessoes;
	

	Persistencia bancoDeInformacoes = new Persistencia();
	CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
	
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
		JLabel lblTitulo = new RotuloTitulo("FILMES EM CARTAZ", 175, 15, 660, 30);
		add(lblTitulo);
	}
	
	private void adicionarImagem() {
		JLabel lblLogoProjeto = new JLabel(Imagens.LOGO_CINE_MONTEIRO_180x156);
		lblLogoProjeto.setBounds(10, 85, 180, 156);
		add(lblLogoProjeto);
	}
	
	private void adicionarSeparador() {
		JSeparator separator = new Separador(200, 5, 2, 327);
		add(separator);
	}
	
	private void adicionarButton() {
		JButton btnComprar = new ButtonPersonalizado("COMPRAR", 355, 290, 150, 35);
		btnComprar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaDasSessoes.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "NENHUM SESSÃO FOI SELECIONADA!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
				} else {
					Sala local = cpd.pesquisarSala((String) tabelaDasSessoes.getValueAt(tabelaDasSessoes.getSelectedRow(), 1));
					long idDaSessao = Long.parseLong(tabelaDasSessoes.getValueAt(tabelaDasSessoes.getSelectedRow(),  0) + "");
					Sessao sessaoEscolihada = cpd.pesquisarSessao(local, idDaSessao);
					
					dispose();
					new WindowsComprarIngresso(local, sessaoEscolihada);
				}
			}
		});
		add(btnComprar);
		
		JButton btnInformacoes = new ButtonPersonalizado("INFORMAÇÕES", 530, 290, 150, 35);
		add(btnInformacoes);
		
		if(usuarioAtivo instanceof Administrador) {
			btnComprar.setEnabled(false);
		}
	}
	
	private void adicionarTabela() {
		DefaultTableModel modeloDaTabela = new DefaultTableModel();	
		modeloDaTabela.addColumn("Sessão");
		modeloDaTabela.addColumn("Sala");
		modeloDaTabela.addColumn("Início da Sessão");
		modeloDaTabela.addColumn("Nome do Filme");
		modeloDaTabela.addColumn("Classificação Etária");
		modeloDaTabela.addColumn("Preço do Ingresso");
		
		tabelaDasSessoes = new JTable(modeloDaTabela);
		
		ArrayList<Sala> salas = cpd.getSalas();
		for(Sala sala : salas) {
			ArrayList<Sessao> sessoes = sala.getSessoes();
			
			for(Sessao sessao : sessoes) {
				if(sessao.isAtiva()) {		
					Object[] linha = new Object[] {sessao.getID(), sala.getNomeDaSala(), sessao.getHoraDeInicio(), sessao.getFilme().getNomeDoFilme(), sessao.getFilme().getClassificacaoEtaria(), sala.getPrecoDoIngresso()};
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
