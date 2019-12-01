package cine.monteiro.screens.ouvintes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import cine.monteiro.screens.administrador.WindowsSessao;

public class OuvinteBtnVoltarSessao implements ActionListener {
	private JFrame janela;
	
	public OuvinteBtnVoltarSessao(JFrame janela) {
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent e) {
		janela.dispose();
		new WindowsSessao();
	}
}
