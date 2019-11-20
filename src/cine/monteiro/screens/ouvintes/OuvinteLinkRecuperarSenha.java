package cine.monteiro.screens.ouvintes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import cine.monteiro.screens.WindowsRecuperarSenha;

public class OuvinteLinkRecuperarSenha implements MouseListener {
	private JFrame windows;
	
	public OuvinteLinkRecuperarSenha(JFrame windows) {
		this.windows = windows;
	}
	
	public void mouseClicked(MouseEvent e) {
		windows.dispose();
		new WindowsRecuperarSenha();
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		e.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
		e.getComponent().setForeground(new Color(241, 94, 36));
	}

	public void mouseExited(MouseEvent e) {
		e.getComponent().setForeground(new Color(42, 133, 175));
	}
}
