package cine.monteiro.screens.administrador;

// APIs
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

// Pacotes
import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.ButtonPersonalizado;
import cine.monteiro.screens.componentes.RotuloTitulo;
import cine.monteiro.screens.componentes.Separador;
import cine.monteiro.screens.componentes.Windows;

public class WindowsListarSessao extends Windows {
	// Atributos
	private JTable tabelaDasSessoes;
	
	// Construtor
	public WindowsListarSessao() {
		super("Sessão - Listar todas as Sessões", 690, 400);
		adicionarLabels();
		adicionarImagem();
		adicionarSeparador();
		adicionarTabela();
		adicionarButtons();
		setVisible(true);
	}
	
	// Componentes
	private void adicionarLabels() {
		JLabel lblTitulo = new RotuloTitulo("TODAS AS SESSÕES", 160, 15, 500, 20);
		add(lblTitulo);
		
		JLabel lblSubTitulo = new JLabel("<html><center>VISUALIZAR <br>SESSÕES<center></html>");
		lblSubTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		lblSubTitulo.setBounds(40, 210, 150, 30);
		add(lblSubTitulo);
	}
	
	private void adicionarImagem() {
		JLabel iconeVisualizar = new JLabel(Imagens.PASTA);
		iconeVisualizar.setBounds(35, 105, 100, 100);
		add(iconeVisualizar);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new Separador(160, 5, 1, 350);
		add(separador);
	}
	
	private void adicionarTabela() {
		Persistencia bancoDeInformacoes = new Persistencia();
		CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
		
		DefaultTableModel modeloDaTabela = new DefaultTableModel();
		modeloDaTabela.addColumn("ID");
		modeloDaTabela.addColumn("SALA");
		modeloDaTabela.addColumn("FILME");
		modeloDaTabela.addColumn("STATUS");
		
		ArrayList<Sala> salas = cpd.getSalas();
		
		for(Sala sala : salas) {
			ArrayList<Sessao> sessoes = sala.getSessoes();
			
			for(Sessao sessao : sessoes) {
				Object[] linha = new Object[4];
				linha[0] = sessao.getID();
				linha[1] = sala.getNomeDaSala();
				linha[2] = sessao.getFilme().getNomeDoFilme();
				
				if(sessao.isAtiva() && !(sessao.isInterrompidaEmUmDia())) {
					linha[3] = "ATIVA";
				} else {
					linha[3] = "NÃO ATIVA";
				}
				modeloDaTabela.addRow(linha);
			}
		}
		tabelaDasSessoes = new JTable(modeloDaTabela);
		JScrollPane container = new JScrollPane(tabelaDasSessoes);
		container.setBounds(185, 50, 465, 250);
		add(container);	
	}
	
	private void adicionarButtons() {
		JButton btnDetalhar = new ButtonPersonalizado("DETALHAR", 260, 312, 150, 35);
		btnDetalhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persistencia bancoDeInformacoes = new Persistencia();
				CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
				
				if(tabelaDasSessoes.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "SELECIONE UMA SALA!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
				} else {
					int linhaSelecionada = tabelaDasSessoes.getSelectedRow();
				
					long idDaSessao = Long.parseLong(tabelaDasSessoes.getValueAt(linhaSelecionada, 0) + "");
					String nomeDaSala = tabelaDasSessoes.getValueAt(linhaSelecionada, 1) + "";
					
					Sala salaSelecionada = cpd.pesquisarSala(nomeDaSala);
					
					dispose();
					new WindowsDetalharSessao(salaSelecionada, idDaSessao);
				}	
			}
		}); 
		add(btnDetalhar);
	
		JButton btnVoltar = new ButtonPersonalizado("VOLTAR", 430, 312, 150, 35);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsSessao();
			}
		});
		add(btnVoltar);
	}
}
