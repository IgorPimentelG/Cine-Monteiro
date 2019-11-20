package cine.monteiro.screens.administrador;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.Windows;
import cine.monteiro.screens.ouvintes.OuvinteBtnVoltar;

public class WindowsFilmes extends Windows {
	public WindowsFilmes() {
		super("Filmes - Cine Monteiro", 360, 180);
		adicionarImagens();
		adicionarLabels();
		adicionarButtons();
		adicionarSeparador();
		setVisible(true);
	}
	
	private void adicionarImagens() {
		JLabel iconeFilme = new JLabel(Imagens.FILME_64x64);
		iconeFilme.setBounds(20, 20, 64, 64);
		add(iconeFilme);
	}
	
	private void adicionarLabels() {
		JLabel lblFilme = new JLabel("FILMES");
		lblFilme.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
		lblFilme.setBounds(24, 75, 60, 50);
		add(lblFilme);
	}
	
	private void adicionarButtons() {
		JButton btnCadastrarFilme = new JButton("CADASTRAR FILME");
		btnCadastrarFilme.setBounds(125, 15, 200, 30);
		btnCadastrarFilme.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		add(btnCadastrarFilme);
		
		JButton btnListarTodosFilmes = new JButton("LISTAR TODOS OS FILMES");
		btnListarTodosFilmes.setBounds(125, 55, 200, 30);
		btnListarTodosFilmes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		add(btnListarTodosFilmes);
		
		JButton btnVoltar = new JButton("VOLTAR");
		btnVoltar.setBounds(125, 95, 200, 30);
		btnVoltar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		btnVoltar.addActionListener(new OuvinteBtnVoltar(this));
		add(btnVoltar);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new JSeparator(SwingConstants.VERTICAL);
		separador.setBounds(105, 5, 1, 130);
		separador.setForeground(Color.GRAY);
		add(separador);
	}
}
