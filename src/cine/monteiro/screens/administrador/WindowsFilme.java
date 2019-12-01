package cine.monteiro.screens.administrador;

// APIs
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
// Pacotes
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.ButtonPersonalizado;
import cine.monteiro.screens.componentes.Windows;
import cine.monteiro.screens.ouvintes.OuvinteBtnVoltarPainelDeControle;

public class WindowsFilme extends Windows {
	// Construtor
	public WindowsFilme() {
		super("Filmes - Cine Monteiro", 360, 180);
		adicionarImagens();
		adicionarLabels();
		adicionarButtons();
		adicionarSeparador();
		setVisible(true);
	}
	
	// Componentes
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
		JButton btnCadastrarFilme = new ButtonPersonalizado("CADASTRAR FILME", 125, 15, 200, 30);
		btnCadastrarFilme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsCadastrarFilme();
			}
		});
		add(btnCadastrarFilme);
		
		JButton btnListarTodosFilmes = new ButtonPersonalizado("LISTAR TODOS OS FILMES", 125, 55, 200, 30);
		btnListarTodosFilmes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persistencia bancoDeInformacoes = new Persistencia();
				CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
				
				if(cpd.getFilmes().isEmpty()) {
					JOptionPane.showMessageDialog(null, "NÃO EXISTE FILMES CADASTRADOS!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
				} else {
					dispose();
					new WindowsListarFilmes();
				}
			}
		});
		add(btnListarTodosFilmes);
		
		JButton btnVoltar = new ButtonPersonalizado("VOLTAR", 125, 95, 200, 30);
		btnVoltar.addActionListener(new OuvinteBtnVoltarPainelDeControle(this));
		add(btnVoltar);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new JSeparator(SwingConstants.VERTICAL);
		separador.setBounds(105, 5, 1, 130);
		separador.setForeground(Color.GRAY);
		add(separador);
	}
}
