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
	private DefaultTableModel modeloDaTabela;
	
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
	private DefaultTableModel getModeloDaTabela() {
		modeloDaTabela = new DefaultTableModel();
		modeloDaTabela.addColumn("Nome Do Filme");
		modeloDaTabela.addColumn("Genêro");
		modeloDaTabela.addColumn("Duração");
		modeloDaTabela.addColumn("Classificação Etária");
		return modeloDaTabela;
	}
	
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
		modeloDaTabela = getModeloDaTabela();
		
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
		tabelaDeTodosOsFilmes.setAutoscrolls(true);
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
					modeloDaTabela = getModeloDaTabela();
					
					// Filtrar filmes ativos
					ArrayList<Filme> filmesComSessaoAtiva = new ArrayList<Filme>();
					ArrayList<Sala> salas = cpd.getSalas();
					for(Sala sala : salas) {
						ArrayList<Sessao> sessoes = sala.getSessoes();
						for(Sessao sessao : sessoes) {
							if(sessao.isAtiva()) {
								boolean filmeNaoRepetido = true;
								for(Filme filmeAtivo : filmesComSessaoAtiva) {
									if(filmeAtivo.getNomeDoFilme().equals(sessao.getFilme().getNomeDoFilme())) {
										filmeNaoRepetido = false;
									}
								}
								
								if(filmeNaoRepetido) {
									filmesComSessaoAtiva.add(sessao.getFilme());
								}
							}
						}
					}
					
					for(Filme filmeAtivo : filmesComSessaoAtiva) {
						 Object[] linha = new Object[4]; 
						 linha[0] = filmeAtivo.getNomeDoFilme(); 
						 linha[1] = filmeAtivo.getGenero();
						 linha[2] = filmeAtivo.getDuracao() + " min";
						 linha[3] = filmeAtivo.getClassificacaoEtaria();
						 modeloDaTabela.addRow(linha);
					}
					
					tabelaDeTodosOsFilmes = new JTable(modeloDaTabela);
					tabelaDeTodosOsFilmes.setAutoscrolls(true);
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
