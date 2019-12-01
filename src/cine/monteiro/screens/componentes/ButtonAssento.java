package cine.monteiro.screens.componentes;

import java.awt.Font;

import javax.swing.JButton;

public class ButtonAssento extends JButton {

	public ButtonAssento(String assento, int x, int y) {
		super(assento);
		setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
		setBounds(x, y, 55, 55);
	}

}
