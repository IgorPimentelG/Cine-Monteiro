package cine.monteiro.screens.administrador;

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

import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.ButtonPersonalizado;
import cine.monteiro.screens.componentes.RotuloTitulo;
import cine.monteiro.screens.componentes.Separador;
import cine.monteiro.screens.componentes.Windows;

public class WindowsListarSalas extends Windows {
	private JTable tabelaSalas;
	private ArrayList<Sala> salasCadastradas;
	
	public WindowsListarSalas() {
		super("Salas - Todos As Salas", 690, 400);
		adicionarLabels();
		adicionarButtons();	
		adicionarTabela();
		adicionarSeparador();
		adicionarImagem();
		setVisible(true);
	}
	
	private void adicionarLabels() {
		JLabel lblTitulo = new RotuloTitulo("TODAS AS SALAS CADASTRADAS", 160, 15, 500, 20);
		add(lblTitulo);
		
		JLabel lblSubTitulo = new JLabel("VISUALIZAR SALAS");
		lblSubTitulo.setBounds(25, 205, 150, 30);
		add(lblSubTitulo);
	}
	
	private void adicionarImagem() {
		JLabel iconeSala = new JLabel(Imagens.PASTA);
		iconeSala.setBounds(35, 115, 100, 100);
		add(iconeSala);
	}
	
	private void adicionarTabela() {
		Persistencia bancoDeInformacoes = new Persistencia();
		CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
		
		DefaultTableModel modeloDaTabela = new DefaultTableModel();
		
		modeloDaTabela.addColumn("ID");
		modeloDaTabela.addColumn("Nome da Sala");
		modeloDaTabela.addColumn("Preço do Ingresso");
		modeloDaTabela.addColumn("Qntd. Assentos");
		
		tabelaSalas = new JTable(modeloDaTabela);
		salasCadastradas = cpd.getSalas();
		
		for(Sala sala : salasCadastradas) {
			Object[] linha = new Object[4];
			
			linha[0] = sala.getID();
			linha[1] = sala.getNomeDaSala();
			linha[2] = sala.getPrecoDoIngresso();
			linha[3] = sala.getQuantidadeDeAssentos();
			
			modeloDaTabela.addRow(linha);
		}
		JScrollPane container = new JScrollPane(tabelaSalas);
		container.setBounds(185, 50, 465, 250);
		add(container);	
	}
	
	private void adicionarButtons() {
		JButton btnDetalhar = new ButtonPersonalizado("Detalhar", 260, 312, 150, 35);
		btnDetalhar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabelaSalas.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "SELECIONE UMA SALA!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
				} else {
					dispose();
					new WindowsDetalharSala(salasCadastradas.get(tabelaSalas.getSelectedRow()));
				}
				
			}
		}); 
		add(btnDetalhar);
		
		JButton btnVoltar = new ButtonPersonalizado("Voltar", 430, 312, 150, 35);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsPainelDeControle();
			}
		});
		add(btnVoltar);
		
		
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new Separador(160, 5, 1, 350);
		add(separador);
	}

}
