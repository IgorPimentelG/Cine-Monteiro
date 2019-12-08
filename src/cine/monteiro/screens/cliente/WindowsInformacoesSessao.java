package cine.monteiro.screens.cliente;

import java.awt.Color;
import java.awt.Font;
import java.awt.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;


import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;
import cine.monteiro.screens.componentes.RotuloDetalhar;
import cine.monteiro.screens.componentes.RotuloTitulo;
import cine.monteiro.screens.componentes.Windows;
import cine.monteiro.usuarios.Usuario;

public class WindowsInformacoesSessao extends Windows {
	// Atributos
	private Sala local;
	private Sessao sessao;
	private Usuario usuarioAtivo;
	
	// Construtor
	public WindowsInformacoesSessao(Sala local, Sessao sessao, Usuario usuarioAtivo) {
		super("Informações sobre a Sessão", 500, 460);
		this.local = local;
		this.sessao = sessao;
		this.usuarioAtivo = usuarioAtivo;
		adicionarLabels();
		adicionarMenuBar();
		setVisible(true);
	}
	
	// Componentes
	private void adicionarLabels() {
		JLabel lblTitulo = new RotuloTitulo(sessao.getFilme().getNomeDoFilme(), 0, 10, 500, 30);
		add(lblTitulo);
		
		JLabel lblLocal = new RotuloDetalhar("Local: " + local.getNomeDaSala(), 20, 45, 300, 30);
		add(lblLocal);
		
		JLabel lblPrecoDoIngresso = new RotuloDetalhar("Preço do ingresso: " + local.getPrecoDoIngresso() , 20, 70, 200, 30);
		add(lblPrecoDoIngresso);
		
		JLabel lblHorario = new RotuloDetalhar("Horário: " + sessao.getHoraDeInicio() + " - " + sessao.getHoraDoTermino(), 20, 95, 200, 30);
		add(lblHorario);
		
		JLabel lblVagasDisponiveis = new RotuloDetalhar("Qntd. vagas disponíveis: " + sessao.getVagasDisponiveis(), 20, 120, 300, 30);
		add(lblVagasDisponiveis);
		
		JLabel lblPeriodo = new RotuloDetalhar("Ativa até: " + sessao.getTerminoDoPeriodoDeExibicao(), 20, 145, 300, 30);
		add(lblPeriodo);
		
		JLabel lblGenero = new RotuloDetalhar("Gênero: " + sessao.getFilme().getGenero(), 20, 170, 200, 30);
		add(lblGenero);
		
		JLabel lblClassificacaoEtaria = new RotuloDetalhar("Classificação: " + sessao.getFilme().getClassificacaoEtaria(), 20, 195, 250, 30);
		add(lblClassificacaoEtaria);
		
		JLabel lblSinopse = new RotuloDetalhar("Sinopse:", 20, 220, 200, 30);
		add(lblSinopse);
		
		JTextArea areaSinopse = new JTextArea();
		areaSinopse.setBounds(20, 250, 445, 130);
		areaSinopse.setLineWrap(true);
		areaSinopse.setWrapStyleWord(true);
		areaSinopse.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		areaSinopse.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		areaSinopse.setText(sessao.getFilme().getSinopse());
		areaSinopse.setEnabled(false);
		add(areaSinopse);
	}
	
	private void adicionarMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("OPÇÕES");
		menuBar.add(menu);
		
		JMenuItem opSair = new JMenuItem("VOLTAR");
		opSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsHomeCliente(usuarioAtivo);
			}
		});
		menu.add(opSair);
	}
}
