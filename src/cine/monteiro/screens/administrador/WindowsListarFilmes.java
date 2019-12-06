package cine.monteiro.screens.administrador;

// APIs
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// Pacotes
import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.gerenciamento.ClassificacaoEtaria;
import cine.monteiro.gerenciamento.Filme;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;
import cine.monteiro.screens.componentes.RotuloTitulo;
import cine.monteiro.screens.componentes.Windows;

public class WindowsListarFilmes extends Windows {
	// Instâncias
	Persistencia bancoDeInformacoes = new Persistencia();
	CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
	
	// Atributos
	private JCheckBox filtroFilmesAtivos;
	private JTable tabelaDeTodosOsFilmes;
	private JLabel lblTitulo;
	private JScrollPane painelDaTabela;
	
	// Construtor
	public WindowsListarFilmes() {
		super("Filme - Todos os Filmes", 700, 500);
		adicionarLabels();
		adicionarMenuBar();
		adicionarCheckBox();
		adicionarTabela();
		setVisible(true);
	}
	
	// Componentes
	private void adicionarLabels() {
	 lblTitulo = new RotuloTitulo("TODOS OS FILMES CADASTRADOS", 0, 15, 700, 30);
		add(lblTitulo);
	}
	
	private void adicionarMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("OPÇÕES");
		menuBar.add(menu);
		
		JMenuItem itemSair = new JMenuItem("VOLTAR");
		itemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsFilme();
			}
		});
		menu.add(itemSair);
	}
	
	private void adicionarTabela() {
		Persistencia bancoDeInformacoes = new Persistencia();
		CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
		DefaultTableModel modeloDaTabela = new DefaultTableModel();
		modeloDaTabela.addColumn("Nome Do Filme");
		modeloDaTabela.addColumn("Genêro");
		modeloDaTabela.addColumn("Duração");
		modeloDaTabela.addColumn("Classificação Etária");
		
	
		ArrayList<Filme> todosFilmes = cpd.getFilmes();				
		
		for(Filme f : todosFilmes) {
			Object[] linha = new Object[4];
			linha[0] = f.getNomeDoFilme();
			linha[1] = f.getGenero();
			linha[2] = f.getDuracao() + " min";
			linha[3] = f.getClassificacaoEtaria();
			modeloDaTabela.addRow(linha);
		}
		
		
		tabelaDeTodosOsFilmes = new JTable(modeloDaTabela);
		painelDaTabela = new JScrollPane(tabelaDeTodosOsFilmes);
		painelDaTabela.setBounds(0, 100, 685, 500);	
		add(painelDaTabela);
		repaint();
	}
	
	private void adicionarCheckBox()  {
		filtroFilmesAtivos = new JCheckBox("FILTRAR FILMES COM SESSÕES ATIVAS", false);
		filtroFilmesAtivos.setBounds(0, 60, 700, 20);
		filtroFilmesAtivos.setHorizontalAlignment(JCheckBox.CENTER);
		filtroFilmesAtivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filtroFilmesAtivos.isSelected()) {	
					remove(painelDaTabela);
					DefaultTableModel modeloDaTabela = new DefaultTableModel();
					modeloDaTabela.addColumn("Nome Do Filme");
					modeloDaTabela.addColumn("Genêro");
					modeloDaTabela.addColumn("Duração");
					modeloDaTabela.addColumn("Classificação Etária");

					ArrayList<Sala> salas = cpd.getSalas();
					for(Sala sala : salas) {
						ArrayList<Sessao> sessoes = sala.getSessoes();
						for(Sessao sessao : sessoes) {
							if(sessao.isAtiva()) {
								Object[] linha = new Object[4];
								linha[0] = sessao.getFilme().getNomeDoFilme();
								linha[1] = sessao.getFilme().getGenero();
								linha[2] = sessao.getFilme().getDuracao() + " min";
								linha[3] = sessao.getFilme().getClassificacaoEtaria();
								modeloDaTabela.addRow(linha);
							}
						}
					}
					
					tabelaDeTodosOsFilmes = new JTable(modeloDaTabela);
					painelDaTabela = new JScrollPane(tabelaDeTodosOsFilmes);
					painelDaTabela.setBounds(0, 100, 685, 500);	
					add(painelDaTabela);
					repaint();
				} else {
					remove(painelDaTabela);
					adicionarTabela();
					repaint();
				}
				repaint();
			}
		});
		add(filtroFilmesAtivos);
	}
}
