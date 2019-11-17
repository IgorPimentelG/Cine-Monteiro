package cine.monteiro.screens.componentes;

import java.awt.Color;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Separador extends JSeparator {
	public Separador(int x, int y, int width, int height) {
		super(SwingConstants.VERTICAL);
		setBounds(x, y, width, height);
		setForeground(Color.GRAY);
	}
}
