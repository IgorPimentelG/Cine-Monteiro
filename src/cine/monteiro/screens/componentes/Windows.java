package cine.monteiro.screens.componentes;

import javax.swing.*;

import cine.monteiro.imagens.Imagens;

public abstract class Windows extends JFrame {
	// Construtor
	public Windows(String titulo, int width, int height) {
		setSize(width, height);
		setTitle(titulo);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setIconImage(Imagens.ICONE_PROGRAMA.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}