package cine.monteiro.screens.componentes;

import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;

public class ButtonPersonalizado extends JButton {
	public ButtonPersonalizado(String texto, int x, int y, int width, int height) {
		setText(texto);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBounds(x, y, width, height);
	}
}
