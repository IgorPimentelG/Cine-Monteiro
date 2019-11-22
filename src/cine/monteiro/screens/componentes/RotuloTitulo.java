package cine.monteiro.screens.componentes;

import java.awt.Font;

import javax.swing.JLabel;

public class RotuloTitulo extends JLabel {
	public RotuloTitulo(String titulo, int x, int y, int width, int height) {
		super(titulo);
		setBounds(x, y, width, height);
		setHorizontalAlignment(JLabel.CENTER);
		setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
	}
}
