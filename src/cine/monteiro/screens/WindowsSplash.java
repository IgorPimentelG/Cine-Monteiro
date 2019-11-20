package cine.monteiro.screens;

// APIs
import java.awt.Color;
import javax.swing.JLabel;

// Pacotes
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.Windows;

public class WindowsSplash extends Windows {
	// Construtor
	public WindowsSplash() {
		super("", 400, 400);
		adicionarImagem();	
		setUndecorated(true);
		setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		setVisible(true);
	}
	
	// Componentes
	private void adicionarImagem() {
		JLabel logoCineMonteiro = new JLabel(Imagens.LOGO_CINE_MONTEIRO_200x173, JLabel.LEFT);
		logoCineMonteiro.setBounds(0, 0, 400, 400);
		logoCineMonteiro.setHorizontalAlignment(JLabel.CENTER);
		logoCineMonteiro.setVerticalAlignment(JLabel.CENTER);
		add(logoCineMonteiro);
	}
}
