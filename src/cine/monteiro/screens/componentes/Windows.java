package cine.monteiro.screens.componentes;

import javax.swing.*;

public abstract class Windows extends JFrame {
	// Construtor
	public Windows(String titulo, int width, int height) {
		setSize(width, height);
		setTitle(titulo);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}