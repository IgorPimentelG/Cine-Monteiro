package cine.monteiro.screens.administrador;

import java.awt.Menu;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

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
		
		JLabel lblFilmeDaSessao = new RotuloDetalhar("Filme da sessão: " + sessao.getFilme().getNomeDoFilme(), 20, 110, 200, 20);
		add(lblFilmeDaSessao);
		
		JLabel lblHorararioDaSessao = new RotuloDetalhar("Horário da sessão: " + sessao.getHoraDeInicio() + " - " + sessao.getHoraDoTermino(), 20, 135, 350, 20);
		add(lblHorararioDaSessao);
		
		SimpleDateFormat formatoDaData = new SimpleDateFormat("dd/MM/yyyy");
		JLabel lblPeridoDeExibicao = new RotuloDetalhar("Período de Exibição: " + formatoDaData.format(sessao.getInicioDoPeriodoDeExibicao()) + " - " + formatoDaData.format(sessao.getTerminoDoPeriodoDeExibicao()), 20, 160, 350, 20);
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
		
		JLabel lblQuantidadeDeIngressosVendidos = new RotuloDetalhar("Quantidade de ingressos vendidos: ", 20, 290, 300, 20);
		add(lblQuantidadeDeIngressosVendidos);
		
		JLabel lblQuantidadeArrecadado = new RotuloDetalhar("Quantidade arrecadado: ", 20, 315, 300, 20);
		add(lblQuantidadeArrecadado);
	}
	
	private void adicionarComBox() {
		String[] opcoes = {"10/11/2019"};
		JComboBox<String> cbData = new JComboBox<String>(opcoes);
		cbData.setBounds(215, 240, 100, 30);
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
		menu.add(opSair);
		
	}
}
