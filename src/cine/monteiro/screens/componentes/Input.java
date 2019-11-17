package cine.monteiro.screens.componentes;

import java.awt.Font;

import javax.swing.*;

public class Input extends JTextField {
	public Input(int x, int y, int width, int height) {
		setBounds(x, y, width, height);
		setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		setHorizontalAlignment(JTextField.CENTER);
	}
}
