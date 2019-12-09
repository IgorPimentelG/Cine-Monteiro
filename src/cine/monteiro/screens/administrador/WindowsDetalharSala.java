package cine.monteiro.screens.administrador;

// APIs
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

// Pacotes
import cine.monteiro.gerenciamento.Filme;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;
import cine.monteiro.screens.componentes.*;

public class WindowsDetalharSala extends Windows {
	// Atributos
	private Sala sala;
	
	// Construtor
	public WindowsDetalharSala(Sala sala) {
		super("Salas - Detalhar Sala", 500, 550);
		this.sala = sala;
		adicionarLabels();
		adicionarMenuBar();
		adicionarTabela();
		adicionarSeparador();
		setVisible(true);
	}
	
	// Componentes
	private void adicionarLabels() {
		JLabel lblTitulo = new RotuloTitulo("DETALHAR SALA", 0, 15, 500, 30);
		add(lblTitulo);
		
		JLabel lblFilmes = new JLabel("FILMES");
		lblFilmes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		lblFilmes.setBounds(105, 230, 100, 20);
		add(lblFilmes);
		
		JLabel lblSessoes = new JLabel("SESSÕES ATIVAS");
		lblSessoes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		lblSessoes.setBounds(305, 230, 200, 20);
		add(lblSessoes);
		
		JLabel lblIDDaSala = new RotuloDetalhar("Identificação da sala: " + sala.getID(), 20, 60, 250, 20);
		add(lblIDDaSala);
		
		JLabel lblNomeDaSala = new RotuloDetalhar("Nome da sala: " + sala.getNomeDaSala(), 20, 85, 150, 20);
		add(lblNomeDaSala);
		
		JLabel lblQuantidadeDeAssentos = new RotuloDetalhar("Quantidadde de Assentos: " + sala.getQuantidadeDeAssentos(), 20, 110, 200, 20);
		add(lblQuantidadeDeAssentos);
		
		JLabel lblQuantidadeDePessoas = new RotuloDetalhar("Quantidade de pessoas que já frequentou a sala: " + sala.getQuantidadeDeIngressoVendidos(), 20, 135, 350, 20);
		add(lblQuantidadeDePessoas);
		
		JLabel lblTotalArrecadado = new RotuloDetalhar("Total arrecadado: " + sala.getTotalArrecadado(), 20, 160, 300, 20);
		add(lblTotalArrecadado);
		
		JLabel lblTotalArrecadadoSemana = new RotuloDetalhar("Total arrecadado nos últimos 7 dias: " + sala.getTotalArrecadadoNaSemana(), 20, 185, 350, 20);
		add(lblTotalArrecadadoSemana);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
		separador.setBounds(10, 220, 465, 2);
		add(separador);
		
	}
	
	private void adicionarTabela() {
		DefaultTableModel modeloDaTabelaFilme = new DefaultTableModel();
		modeloDaTabelaFilme.addColumn("Filme");
		modeloDaTabelaFilme.addColumn("Genêro");
		JTable tabelaDeTodosOsFilmes = new JTable(modeloDaTabelaFilme);
		
		ArrayList<Filme> todosOsFilmesExibidos = sala.getTodosOsFilmesExibidos();
		
		for(Filme filme : todosOsFilmesExibidos) {
			Object[] linha = new Object[2];
			
			linha[0] = filme.getNomeDoFilme();
			linha[1] = filme.getGenero();
			
			modeloDaTabelaFilme.addRow(linha);
		}
		
		JScrollPane containerFilme = new JScrollPane(tabelaDeTodosOsFilmes);
		containerFilme.setBounds(30, 260, 200, 200);
		add(containerFilme);
		
		
		DefaultTableModel modeloTabelaSessao = new DefaultTableModel();
		modeloTabelaSessao.addColumn("ID");
		modeloTabelaSessao.addColumn("Filme");
		
		JTable tabelaDasSessoes = new JTable(modeloTabelaSessao);
		
		ArrayList<Sessao> sessoes = sala.getSessoes();
		for(Sessao sessao : sessoes) {
			if(sessao.isAtiva() && !sessao.isInterrompidaEmUmDia()) {
				Object[] linha = new Object[2];
				linha[0] = sessao.getID();
				linha[1] = sessao.getFilme().getNomeDoFilme();
				modeloTabelaSessao.addRow(linha);
			}
		}
		
		JScrollPane containerSessao = new JScrollPane(tabelaDasSessoes);
		containerSessao.setBounds(260, 260, 200, 200);
		add(containerSessao);
	}
	
	private void adicionarMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("OPÇÕES");
		menuBar.add(menu);
		
		JMenuItem opSair = new JMenuItem("SAIR");
		opSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsListarSalas();
			}
		});
		menu.add(opSair);
	}
}