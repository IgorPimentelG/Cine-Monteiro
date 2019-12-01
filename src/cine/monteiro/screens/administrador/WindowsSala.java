package cine.monteiro.screens.administrador;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.*;
import cine.monteiro.screens.ouvintes.OuvinteBtnVoltarPainelDeControle;

public class WindowsSala extends Windows {
	// Construtor
	public WindowsSala() {
		super("Painel de Controle - Sala", 370, 220);
		adicionarImagens();
		adicionarLabels();
		adicionarSeparador();
		adicionarButtons();
		setVisible(true);
	}
	
	// Componentes
	private void adicionarImagens() {
		JLabel iconeSala = new JLabel(Imagens.SALA_100x100);
		iconeSala.setBounds(27, 45, 64, 64);
		add(iconeSala);
	}
	
	private void adicionarLabels() {
		JLabel lblTitulo = new JLabel("SALAS");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblTitulo.setBounds(32, 95, 100, 50);
		add(lblTitulo);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new Separador(113, 5, 2, 167);
		add(separador);
	}
	
	private void adicionarButtons() {
		JButton btnCadastrarSala = new JButton("CADASTRAR NOVA SALA");
		btnCadastrarSala.setBounds(135, 15, 200, 30);
		btnCadastrarSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsCadastrarSala();
			}
		});
		add(btnCadastrarSala);
		
		JButton btnExcluirSala = new JButton("EXCLUÍR SALA");
		btnExcluirSala.setBounds(135, 55, 200, 30);
		btnExcluirSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persistencia bancoDeInformacoes = new Persistencia();
				CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
				
				if(cpd.getSalas().isEmpty()) {
					JOptionPane.showMessageDialog(null, "NÃO EXISTE SALAS CADASTRADAS!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
				} else {
					dispose();
					new WindowsExcluirSala();
				}
			}
		});
		add(btnExcluirSala);
		
		JButton btnListarTodasAsSalas = new JButton("LISTAR TODAS AS SALAS");
		btnListarTodasAsSalas.setBounds(135, 95, 200, 30);
		btnListarTodasAsSalas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsListarSalas();
			}
		});
		add(btnListarTodasAsSalas);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(135, 135, 200, 30);
		btnVoltar.addActionListener(new OuvinteBtnVoltarPainelDeControle(this));
		add(btnVoltar);
	}
	
	// Ouvintes
	public class OuvinteBtnCadastrarSala implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
}
