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

public class WindowsSessao extends Windows {
	public WindowsSessao() {
		super("Painel de Controle - Sessões", 350, 180);
		adicionarImagens();
		adicionarLabels();
		adicionarButtons();
		adicionarSeparador();
		setVisible(true);
	}
	
	private void adicionarImagens() {
		JLabel iconeSessao = new JLabel(Imagens.SESSAO_64x64);
		iconeSessao.setBounds(23, 25, 64, 64);
		add(iconeSessao);
	}
	
	private void adicionarLabels() {
		JLabel lblTitulo = new JLabel("SESSÕES");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblTitulo.setBounds(15, 68, 100, 60);
		add(lblTitulo);
	}
	
	private void adicionarButtons() {
		Persistencia bancoDeInformacoes = new Persistencia();
		CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
		
		JButton btnCadastrarSessao = new ButtonPersonalizado("CADASTRAR SESSÃO", 120, 15, 200, 30);
		btnCadastrarSessao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cpd.getSalas().isEmpty()) {
					JOptionPane.showMessageDialog(null, "NÃO EXISTE SALAS CADASTRADAS!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				} else if(cpd.getFilmes().isEmpty()) {
					JOptionPane.showMessageDialog(null, "NÃO EXISTE FILMES CADASTRADOS!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				} else {
					dispose();
					new WindowsCadastrarSessao();
				}	
			}
		});
		add(btnCadastrarSessao);
		

		JButton btnListarTodasAsSessoes = new ButtonPersonalizado("LISTAR TODAS AS SESSÕES", 120, 55, 200, 30);
		btnListarTodasAsSessoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsListarSessao();
			}
		});
		add(btnListarTodasAsSessoes);
		
		JButton btnVoltar = new ButtonPersonalizado("VOLTAR", 120, 95, 200, 30);
		btnVoltar.addActionListener(new OuvinteBtnVoltarPainelDeControle(this));
		add(btnVoltar);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new Separador(105, 5, 2, 130);
		add(separador);
	}
}
