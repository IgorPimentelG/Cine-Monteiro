package cine.monteiro.screens.administrador;

// APIs
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

// Pacotes
import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.gerenciamento.Filme;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;
import cine.monteiro.screens.componentes.*;

public class WindowsCadastrarSessao extends Windows {
	Persistencia bancoDeInformacoes = new Persistencia();
	CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
	
	Sessao novaSessao = new Sessao();
	
	public WindowsCadastrarSessao() {
		super("Sessões - Cadastrar Nova Sessão", 400, 425);
		adicionarLabels();
		adicionarInputs();
		adicionarComboBox();
		adicionarButtons();
		setVisible(true);
	}
	
	private void adicionarLabels() {
		JLabel lblTitulo = new RotuloTitulo("CADASTRAR NOVA SESSÃO", 0, 20, 400, 20);
		add(lblTitulo);
		
		JLabel lblID = new Rotulo("Identificação:", 20, 50, 150, 30);
		add(lblID);
		
		JLabel lblFilmeDaSessao = new Rotulo("Filme:", 20, 110, 50, 50);
		add(lblFilmeDaSessao);
		
		JLabel lblSalaDeExibicao = new Rotulo("Sala de exibição:", 20, 195, 150, 20);
		add(lblSalaDeExibicao);
		
		JLabel lblHorarioDaSessao = new Rotulo("Horário da sessão:", 20, 265, 150, 20);
		add(lblHorarioDaSessao);
		
		JLabel lblPeridoEmExibicao = new Rotulo("Período em exibição:", 175, 265, 150, 20);
		add(lblPeridoEmExibicao);
	}
	
	private void adicionarInputs() {
		JTextField tfIdentificacao = new JTextField(novaSessao.getID() + "");
		tfIdentificacao.setBounds(20, 80, 345, 30);
		tfIdentificacao.enable(false);
		tfIdentificacao.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		tfIdentificacao.setHorizontalAlignment(JTextField.CENTER);
		add(tfIdentificacao);
		
		try {
			MaskFormatter mascaraHorario = new MaskFormatter("##:##");
			JFormattedTextField tfHorarioDaSessao = new JFormattedTextField(mascaraHorario);
			tfHorarioDaSessao.setBounds(20, 290, 145, 30);
			tfHorarioDaSessao.setHorizontalAlignment(JFormattedTextField.CENTER);
			tfHorarioDaSessao.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
			add(tfHorarioDaSessao);
		} catch (Exception e) {
			
		}	
		
		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
			JFormattedTextField tfPeridoEmExibicao = new JFormattedTextField(mascaraData);
			tfPeridoEmExibicao.setBounds(175, 290, 185, 30);
			tfPeridoEmExibicao.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
			tfPeridoEmExibicao.setHorizontalAlignment(JFormattedTextField.CENTER);
			add(tfPeridoEmExibicao);
		} catch (Exception e) {
		
		}
	}
	
	private void adicionarComboBox() {
		String[] filmes = new String[cpd.getFilmes().size()];
		String[] salas = new String[cpd.getSalas().size()];
		
		ArrayList<Filme> filmesCadastrados = cpd.getFilmes();
		ArrayList<Sala> salasCadastradas = cpd.getSalas();
		
		for(int i = 0; i < filmesCadastrados.size(); i++) {
			filmes[i] = filmesCadastrados.get(i).getNomeDoFilme() + " - " + filmesCadastrados.get(i).getTipoDaProjecao();
		}
		
		JComboBox<String> cbFilmesDisponiveis = new JComboBox<String>(filmes);
		cbFilmesDisponiveis.setBounds(20, 150, 345, 30);
		add(cbFilmesDisponiveis);
		
		for (int i = 0; i < salasCadastradas.size(); i++) {
			salas[i] = salasCadastradas.get(i).getID() + " - " + salasCadastradas.get(i).getNomeDaSala() + " - " + salasCadastradas.get(i).getTipoDaProjecaoSuportada();
		}
		
		JComboBox<String> cbSalasCadastradas = new JComboBox<String>(salas);
		cbSalasCadastradas.setBounds(20, 220, 345, 30);
		add(cbSalasCadastradas);
		
	}

	private void adicionarButtons() {
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(20, 335, 165, 35);
		add(btnCadastrar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(200, 335, 165, 35);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new WindowsSessao();
			}
		});
		add(btnCancelar);
	}
}
