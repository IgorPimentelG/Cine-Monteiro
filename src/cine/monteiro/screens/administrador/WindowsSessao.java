package cine.monteiro.screens.administrador;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.*;
import cine.monteiro.screens.ouvintes.OuvinteBtnVoltarPainelDeControle;

public class WindowsSessao extends Windows {
	public WindowsSessao() {
		super("Painel de Controle - Sessões", 350, 220);
		adicionarImagens();
		adicionarLabels();
		adicionarButtons();
		adicionarSeparador();
		setVisible(true);
	}
	
	private void adicionarImagens() {
		JLabel iconeSessao = new JLabel(Imagens.SESSAO_64x64);
		iconeSessao.setBounds(23, 45, 64, 64);
		add(iconeSessao);
	}
	
	private void adicionarLabels() {
		JLabel lblTitulo = new JLabel("SESSÕES");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblTitulo.setBounds(15, 88, 100, 60);
		add(lblTitulo);
	}
	
	private void adicionarButtons() {
		JButton btnCadastrarSessao = new ButtonPersonalizado("CADASTRAR SESSÃO", 120, 15, 200, 30);
		btnCadastrarSessao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsCadastrarSessao();
			}
		});
		add(btnCadastrarSessao);
		
		JButton btnInterromperSessao = new ButtonPersonalizado("INTERROMPER SESSÃO", 120, 55, 200, 30);
		btnInterromperSessao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsInterromperSessao();
			}
		});
		add(btnInterromperSessao);
		
		JButton btnListarTodasAsSessoes = new ButtonPersonalizado("LISTAR TODAS AS SESSÕES", 120, 95, 200, 30);
		btnListarTodasAsSessoes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsListarSessao();
			}
		});
		add(btnListarTodasAsSessoes);
		
		JButton btnVoltar = new ButtonPersonalizado("VOLTAR", 120, 135, 200, 30);
		btnVoltar.addActionListener(new OuvinteBtnVoltarPainelDeControle(this));
		add(btnVoltar);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new Separador(105, 5, 2, 170);
		add(separador);
	}
}
