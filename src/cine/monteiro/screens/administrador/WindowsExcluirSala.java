package cine.monteiro.screens.administrador;

// APIs
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

// Pacotes
import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.screens.componentes.ButtonPersonalizado;
import cine.monteiro.screens.componentes.Rotulo;
import cine.monteiro.screens.componentes.Windows;

public class WindowsExcluirSala extends Windows {
	// Instâncias
	Persistencia bancoDeInformacoes = new Persistencia();
	CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
	
	// Atributos
	private JComboBox<String> cbSalas;
	private ArrayList<Sala> salasCadastradas = cpd.getSalas();

	
	// Construtor
	public WindowsExcluirSala() {
		super("Salas - Excluir Salas", 360, 215);
		adicionarLabels();
		adicionarButtons();
		adicionarCombox();
		setVisible(true);
	}
	
	// Componentes
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
		JButton btnConfirmar = new ButtonPersonalizado("CONFIRMAR", 20, 125, 145, 35);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(salasCadastradas.isEmpty()) {
					JOptionPane.showMessageDialog(null, "NÃO EXISTE SALAS CADASTRADAS!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
					dispose();
				} else {
					String dadosDaSala = (String) cbSalas.getSelectedItem();
					String[] dados = dadosDaSala.split("-");
					long ID = Long.parseLong(dados[0].trim());
					
					try {
						cpd.excluirSala(ID);
						JOptionPane.showMessageDialog(null, "SALA EXCLUÍDA COM SUCESSO!", "AVISO!", JOptionPane.WARNING_MESSAGE);
						bancoDeInformacoes.salvarCentralDeInformacoes(cpd);
					} catch (Exception erro) {
						JOptionPane.showMessageDialog(null, erro.getMessage(), "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
					}	
					
					 
					dispose();
					new WindowsExcluirSala();
				}
			}
		});
		add(btnConfirmar);
		
		JButton btnCancelar = new ButtonPersonalizado("CANCELAR", 180, 125, 145, 35);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsSala();
			}
		});
		add(btnCancelar);	
	}
	
	private void adicionarCombox() {
		String[] opcoes = new String[salasCadastradas.size()];
		
		for(int i = 0; i < salasCadastradas.size(); i++) {
			opcoes[i] = salasCadastradas.get(i).getID() + " - " + salasCadastradas.get(i).getNomeDaSala();
		}
		
		cbSalas = new JComboBox<String>(opcoes);
		cbSalas.setBounds(20, 80, 305, 30);
		add(cbSalas);
	}
}
