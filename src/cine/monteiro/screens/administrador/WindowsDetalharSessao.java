package cine.monteiro.screens.administrador;

// APIs
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

//Pacotes
import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
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
		super("Sessões - Detalhar Sessão", 500, 480);
		this.sala = sala;
		this.idDaSessao = idDaSessao;
		pesquisarSessao();
		adicionarLabels();
		adicionarCheckBox();
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
		JLabel lblTitulo = new RotuloTitulo("DETALHAR SESSÃO", 0, 10, 500, 30);
		add(lblTitulo);
		
		JLabel lblEstatisticas = new RotuloTitulo("ESTATÍSTICAS", 0, 255, 500, 30);
		add(lblEstatisticas);
		
		JLabel lblIDDaSala = new RotuloDetalhar("Identificação da sessão: " + idDaSessao, 20, 95, 300, 20);
		add(lblIDDaSala);
		
		JLabel lblSalaDaSessao = new RotuloDetalhar("Sala da sessão: " + sala.getNomeDaSala(), 20, 120, 300, 20);
		add(lblSalaDaSessao);
		
		JLabel lblFilmeDaSessao = new RotuloDetalhar("Filme da sessão: " + sessao.getFilme().getNomeDoFilme(), 20, 145, 400, 20);
		add(lblFilmeDaSessao);
		
		JLabel lblHorararioDaSessao = new RotuloDetalhar("Horário da sessão: " + sessao.getHoraDeInicio() + " - " + sessao.getHoraDoTermino(), 20, 170, 350, 20);
		add(lblHorararioDaSessao);
		
		JLabel lblPeridoDeExibicao = new RotuloDetalhar("Em Exibição até: " + sessao.getTerminoDoPeriodoDeExibicao(), 20, 195, 350, 20);
		add(lblPeridoDeExibicao);
		
		String status = "";
		if(sessao.isAtiva()) {
			status = "Ativa";
		} else { 
			status = "Não ativa";
		}
		
		JLabel lblStatus = new RotuloDetalhar("Status: " + status, 20, 220, 200, 20);
		add(lblStatus);
		
		JLabel lblDia = new RotuloDetalhar("DIA:", 175, 305, 50, 20);
		add(lblDia);
		
		lblQuantidadeDeIngressosVendidos = new RotuloDetalhar("Quantidade de ingressos vendidos: 0", 0, 345, 500, 20);
		lblQuantidadeDeIngressosVendidos.setHorizontalAlignment(JLabel.CENTER);
		add(lblQuantidadeDeIngressosVendidos);
		
		lblQuantidadeArrecadado = new RotuloDetalhar("Quantidade arrecadado: R$ 0", 0, 370, 500, 20);
		lblQuantidadeArrecadado.setHorizontalAlignment(JLabel.CENTER);
		add(lblQuantidadeArrecadado);
	}
	
	private void adicionarCheckBox() {
		Persistencia bancoDeInformacoes = new Persistencia();
		CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
		
		JCheckBox cbInterromperSessaoEmUmDia = new JCheckBox("INTERROMPER SESSÃO EM UM DIA", sessao.isInterrompidaEmUmDia());
		JCheckBox cbInterromperSessao = new JCheckBox("INTERROMPER SESSÃO", sessao.isInterrompida());
		
		if(sessao.isInterrompidaEmUmDia() || sessao.isInterrompida()) {
			cbInterromperSessaoEmUmDia.setEnabled(false);
			cbInterromperSessao.setEnabled(false);
		}
		
		cbInterromperSessaoEmUmDia.setBounds(0, 45, 500, 20);
		cbInterromperSessaoEmUmDia.setHorizontalAlignment(JCheckBox.CENTER);
		cbInterromperSessaoEmUmDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbInterromperSessaoEmUmDia.isSelected()) {
					ArrayList<Sala> salas = cpd.getSalas();
					for(Sala sala : salas) {
						ArrayList<Sessao> sessoes = sala.getSessoes();
						for(Sessao s : sessoes) {
							if(s.getID() == sessao.getID()) {
								s.setInterrompidaEmUmDia(true);
								s.setAtiva();
							}
						}
					cbInterromperSessaoEmUmDia.setEnabled(false);
					cbInterromperSessao.setEnabled(false);
					JOptionPane.showMessageDialog(null, "A SESSÃO FOI INTERROMPIDA EM UM DIA!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
					bancoDeInformacoes.salvarCentralDeInformacoes(cpd);
					}
				}
			}
		});
		add(cbInterromperSessaoEmUmDia);
		
		cbInterromperSessao.setBounds(0, 65, 500, 20);
		cbInterromperSessao.setHorizontalAlignment(JCheckBox.CENTER);
		cbInterromperSessao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbInterromperSessao.isSelected()) {
					ArrayList<Sala> salas = cpd.getSalas();
					for(Sala sala : salas) {
						ArrayList<Sessao> sessoes = sala.getSessoes();
						for(Sessao s : sessoes) {
							if(s.getID() == sessao.getID()) {
								s.setInterrompida(true);
								s.setAtiva();
							}
						}
					}
					cbInterromperSessaoEmUmDia.setEnabled(false);
					cbInterromperSessao.setEnabled(false);
					JOptionPane.showMessageDialog(null, "A SESSÃO FOI INTERROMPIDA!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
					bancoDeInformacoes.salvarCentralDeInformacoes(cpd);
				}
			}
		});
		add(cbInterromperSessao);		
	}
	
	private void adicionarComBox() {
		ArrayList<ArrayList<String>> dadosDaSessao = sessao.getDadosDaSessaoDaSemana();
		ArrayList<String> datas = new ArrayList<String>();
		
		for(ArrayList<String> dados : dadosDaSessao) {
			boolean dataNaoExiste = true;
			for(String dataSalva : datas) {
				if(dataSalva.equals(dados.get(0))) {
					dataNaoExiste = false;
				}
			}
			
			if(dataNaoExiste) {
				datas.add(dados.get(0));
			}
		}
		
		String[] opcoes = new String[datas.size()];
		
		for(int i = 0; i < datas.size(); i++) {
			opcoes[i] = datas.get(i);
		}
		
		cbData = new JComboBox<String>(opcoes);
		cbData.setBounds(205, 300, 120, 30);
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
		separador.setBounds(10, 250, 465, 2);
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
