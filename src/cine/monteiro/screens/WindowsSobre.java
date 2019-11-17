package cine.monteiro.screens;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

import cine.monteiro.imagens.Imagens;

public class WindowsSobre extends JFrame {
	public WindowsSobre() {
		configurarScreen();
		adicionarImagens();	
		adicionarLabels();
		setVisible(true);
	}
	
	private void configurarScreen() {
		setTitle("Cine Monteiro");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void adicionarImagens() {
		JLabel logoCineMonteiro = new JLabel(Imagens.LOGO_CINE_MONTEIRO_200x173, JLabel.LEFT);
		logoCineMonteiro.setBounds(105, 20, 400, 180);
		add(logoCineMonteiro);
		
		JLabel logoIFPB = new JLabel(Imagens.LOGO_IFPB_50x50);
		logoIFPB.setBounds(0, 210, 400, 60);
		add(logoIFPB);
	}
	
	private void adicionarLabels() {
		JLabel lblVersao = new JLabel("Versão 1.0");
		lblVersao.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		lblVersao.setHorizontalAlignment(JLabel.CENTER);
		lblVersao.setBounds(0, 260, 400, 30);
		add(lblVersao);
		
		JLabel lblDisciplina = new JLabel("A.D.S - Programação II");
		lblDisciplina.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		lblDisciplina.setHorizontalAlignment(JLabel.CENTER);
		lblDisciplina.setBounds(0, 280, 400, 30);
		add(lblDisciplina);
		
		JLabel lblEmailDeContato = new  JLabel("igor.pimentel.msi@hotmail.com");
		lblEmailDeContato.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		lblEmailDeContato.setHorizontalAlignment(JLabel.CENTER);
		lblEmailDeContato.setBounds(0, 300, 400, 30);
		add(lblEmailDeContato);
	}
}
