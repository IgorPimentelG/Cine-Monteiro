package cine.monteiro.screens.administrador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

// Pacotes
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;
import cine.monteiro.screens.componentes.RotuloDetalhar;
import cine.monteiro.screens.componentes.RotuloTitulo;
import cine.monteiro.screens.componentes.Windows;

public class WindowsDetalharSessao extends Windows {
	// Atributos
	private Sala sala;
	private long idDaSessao;
	private Sessao sessao;
	private JComboBox<String> cbData;
	private JLabel lblQuantidadeDeIngressosVendidos;
	private JLabel lblQuantidadeArrecadado;
	
	// Construtor
	public WindowsDetalharSessao(Sala sala, long idDaSessao) {
		super("Sessões - Detalhar Sessão", 500, 415);
		this.sala = sala;
		this.idDaSessao = idDaSessao;
		pesquisarSessao();
		adicionarLabels();
		adicionarSeparador();
		adicionarMenuBar();
		adicionarComBox();
		setVisible(true);
	}
	
	// Componentes
	private void pesquisarSessao() {
		ArrayList<Sessao> sessoesDaSala = sala.getSessoes();
		for(Sessao sessaoCadastrada : sessoesDaSala) {
			if(sessaoCadastrada.getID() == idDaSessao) {
				sessao = sessaoCadastrada;
			}
		}
	}
	
	private void adicionarLabels() {
		JLabel lblTitulo = new RotuloTitulo("DETALHAR SESSÃO", 0, 15, 500, 30);
		add(lblTitulo);
		
		JLabel lblIDDaSala = new RotuloDetalhar("Identificação da sessão: " + idDaSessao, 20, 60, 300, 20);
		add(lblIDDaSala);
		
		JLabel lblSalaDaSessao = new RotuloDetalhar("Sala da sessão: " + sala.getNomeDaSala(), 20, 85, 300, 20);
		add(lblSalaDaSessao);
		
		JLabel lblFilmeDaSessao = new RotuloDetalhar("Filme da sessão: " + sessao.getFilme().getNomeDoFilme(), 20, 110, 400, 20);
		add(lblFilmeDaSessao);
		
		JLabel lblHorararioDaSessao = new RotuloDetalhar("Horário da sessão: " + sessao.getHoraDeInicio() + " - " + sessao.getHoraDoTermino(), 20, 135, 350, 20);
		add(lblHorararioDaSessao);
		
		SimpleDateFormat formatoDaData = new SimpleDateFormat("dd/MM/yyyy");
		
		JLabel lblPeridoDeExibicao = new RotuloDetalhar("Em Exibição até: " + formatoDaData.format(sessao.getTerminoDoPeriodoDeExibicao()), 20, 160, 350, 20);
		add(lblPeridoDeExibicao);
		
		String status = "";
		if(sessao.isAtiva()) {
			status = "Ativa";
		} else { 
			status = "Não ativa";
		}
		
		JLabel lblStatus = new RotuloDetalhar("Status: " + status, 20, 185, 200, 20);
		add(lblStatus);
		
		JLabel lblDia = new RotuloDetalhar("DIA:", 180, 245, 50, 20);
		add(lblDia);
		
		lblQuantidadeDeIngressosVendidos = new RotuloDetalhar("Quantidade de ingressos vendidos: ", 20, 290, 300, 20);
		add(lblQuantidadeDeIngressosVendidos);
		
		lblQuantidadeArrecadado = new RotuloDetalhar("Quantidade arrecadado: ", 20, 315, 300, 20);
		add(lblQuantidadeArrecadado);
	}
	
	private void adicionarComBox() {
		String[] opcoes = new String[sessao.getDadosDaSessao().size()];
		ArrayList<ArrayList<String>> dadosDaSessao = sessao.getDadosDaSessao();
		
		for(int i = 0; i < dadosDaSessao.size(); i++) {
			ArrayList<String> dados = dadosDaSessao.get(i);
			System.out.println(dados.get(0));
			opcoes[i] = dados.get(0);
		}
		
		cbData = new JComboBox<String>(opcoes);
		cbData.setBounds(215, 240, 100, 30);
		cbData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dataSelecionada = (String) cbData.getSelectedItem();
				for(int i = 0; i < dadosDaSessao.size(); i++) {
					ArrayList<String> dados = dadosDaSessao.get(i);
					if(dados.get(0).equals(dataSelecionada)) {
						lblQuantidadeDeIngressosVendidos.setText("Quantidade de ingressos vendidos: " + dados.get(1));
						lblQuantidadeArrecadado.setText("Quantidade arrecadado: " + dados.get(2));
						repaint();
					}
				}
			}
		});
		add(cbData);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new JSeparator(SwingConstants.HORIZONTAL);
		separador.setBounds(10, 220, 465, 2);
		add(separador);
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
				new WindowsListarSessao();
			}
		});
		menu.add(opSair);	
	}
}
