package cine.monteiro.screens.componentes;

import java.awt.Color;

import javax.swing.*;
import javax.swing.border.Border;

public class Icone extends JLabel {
	public Icone(ImageIcon imagem,int x, int y, int width, int height) {
		super(imagem);
		Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
		setOpaque(true);
		setBackground(Color.WHITE);
		setBorder(border);
		setBounds(x, y, width, height);
	}
}
