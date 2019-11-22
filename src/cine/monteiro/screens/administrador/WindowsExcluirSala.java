package cine.monteiro.screens.administrador;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.screens.componentes.Rotulo;
import cine.monteiro.screens.componentes.Windows;
import cine.monteiro.screens.ouvintes.OuvinteBtnVoltar;

public class WindowsExcluirSala extends Windows {
	Persistencia bancoDeInformacoes = new Persistencia();
	CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
	
	private JComboBox<String> cbSalas;
	
	public WindowsExcluirSala() {
		super("Salas - Excluir Salas", 360, 215);
		adicionarLabels();
		adicionarButtons();
		adicionarCombox();
		setVisible(true);
	}
	
	private void adicionarLabels() {
		JLabel lblTitulo = new JLabel("EXCLUIR SALA");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		lblTitulo.setBounds(0, 20, 360, 20);
		lblTitulo.setHorizontalAlignment(JLabel.CENTER);
		add(lblTitulo);
		
		JLabel lblNomeDaSala = new Rotulo("Nome da sala:", 20, 55, 100, 20);
		add(lblNomeDaSala);
	}
	
	private void adicionarButtons() {
		JButton btnConfirmar = new JButton("CONFIRMAR");
		btnConfirmar.setBounds(20, 120, 145, 35);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dadosDaSala = (String) cbSalas.getSelectedItem();
				String[] dados = dadosDaSala.split("-");
				long ID = Long.parseLong(dados[0].trim());
				System.out.println(ID);
				cpd.excluirSala(ID);
				JOptionPane.showMessageDialog(null, "SALA EXCLUÍDA COM SUCESSO!", "AVISO!", JOptionPane.WARNING_MESSAGE);
				cbSalas.repaint();
				bancoDeInformacoes.salvarCentralDeInformacoes(cpd);
			}
		});
		add(btnConfirmar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(180, 120, 145, 35);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsSala();
			}
		});
		add(btnCancelar);	
	}
	
	private void adicionarCombox() {
		ArrayList<Sala> salasCadastradas = cpd.getSalas();
		String[] opcoes = new String[salasCadastradas.size()];
		
		for(int i = 0; i < salasCadastradas.size(); i++) {
			opcoes[i] = salasCadastradas.get(i).getID() + " - " +salasCadastradas.get(i).getNomeDaSala();
		}
		
		cbSalas = new JComboBox<String>(opcoes);
		cbSalas.setBounds(20, 80, 305, 30);
		add(cbSalas);
	}
}
