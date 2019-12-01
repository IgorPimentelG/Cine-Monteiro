package cine.monteiro.screens.componentes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import cine.monteiro.screens.WindowsLogin;
import cine.monteiro.screens.WindowsSobre;

public class MenuBar extends JMenuBar {
	public MenuBar(Windows janela) {
		
		JMenuItem itemAlterarSenha = new JMenuItem();
		
		JMenuItem itemSobre = new JMenuItem("SOBRE");
		itemSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new WindowsSobre();
			}
		});
		add(itemSobre);
		
		JMenuItem itemSair = new JMenuItem("SAIR");
		itemSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janela.dispose();
				new WindowsLogin();
			}
		});
		add(itemSair);
	}
}
