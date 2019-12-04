package cine.monteiro.main;

import java.awt.Button;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class Painel {
	public static void main(String[] args) {
		JFrame janela = new JFrame();
		
		janela.setSize(900, 900);
		janela.setVisible(true);
		janela.setResizable(false);
		janela.setLocationRelativeTo(null);
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLayout(null);
		
		JPanel painel = new JPanel(new GridLayout(5, 8, 10, 10));
		
		int contador = 1;
		for(int l = 0; l < 5; l++) {
			for(int i = 0; i < 8; i++) {
				painel.add(new JButton(contador + ""));
				contador++;
			}
		}
		
		JScrollPane c = new JScrollPane(painel);
		c.setBounds(50, 50, 500, 400);
		janela.add(c);
		
	}
}
