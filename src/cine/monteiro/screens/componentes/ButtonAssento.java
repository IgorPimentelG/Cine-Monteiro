package cine.monteiro.screens.componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class ButtonAssento extends JButton {

	public ButtonAssento(String assento) {
		super(assento);
		setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
		setEnabled(false);
		setBackground(new Color(198, 226, 255));
	}
}
