package cine.monteiro.screens.componentes;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class RotuloDetalhar extends JLabel {
	public RotuloDetalhar(String texto, int x, int y, int width, int height) {
		super(texto);
		setBounds(x, y, width, height);
		setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
		setForeground(Color.GRAY);
	}

}
