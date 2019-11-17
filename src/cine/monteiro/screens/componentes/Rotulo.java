package cine.monteiro.screens.componentes;

import java.awt.Font;

import javax.swing.JLabel;

public class Rotulo extends JLabel {
	public Rotulo(String texto, int x, int y, int width, int height) {
		super(texto);
		setBounds(x, y, width, height);
		setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
	}
}
