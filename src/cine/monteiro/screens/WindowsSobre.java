package cine.monteiro.screens;

// APIs
import java.awt.Font;
import javax.swing.JLabel;

// Pacotes
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.Windows;

public class WindowsSobre extends Windows {
	// Construtor
	public WindowsSobre() {
		super("Cine Monteiro", 400, 400);
		adicionarImagens();	
		adicionarLabels();
		setVisible(true);
	}
	
	// Componentes
	private void adicionarImagens() {
		JLabel logoCineMonteiro = new JLabel(Imagens.LOGO_CINE_MONTEIRO_200x173, JLabel.LEFT);
		logoCineMonteiro.setBounds(105, 30, 400, 180);
		add(logoCineMonteiro);
		
		JLabel logoIFPB = new JLabel(Imagens.LOGO_IFPB_50x50);
		logoIFPB.setBounds(0, 220, 400, 60);
		logoIFPB.setHorizontalAlignment(JLabel.CENTER);
		add(logoIFPB);
	}
	
	private void adicionarLabels() {
		JLabel lblVersao = new JLabel("Versão 1.0");
		lblVersao.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		lblVersao.setHorizontalAlignment(JLabel.CENTER);
		lblVersao.setBounds(0, 270, 400, 30);
		add(lblVersao);
		
		JLabel lblDisciplina = new JLabel("A.D.S - Programação II");
		lblDisciplina.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		lblDisciplina.setHorizontalAlignment(JLabel.CENTER);
		lblDisciplina.setBounds(0, 290, 400, 30);
		add(lblDisciplina);
		
		JLabel lblEmailDeContato = new  JLabel("igor.pimentel.msi@hotmail.com");
		lblEmailDeContato.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
		lblEmailDeContato.setHorizontalAlignment(JLabel.CENTER);
		lblEmailDeContato.setBounds(0, 310, 400, 30);
		add(lblEmailDeContato);
	}
}
