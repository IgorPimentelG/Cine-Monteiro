package cine.monteiro.screens.administrador;

// APIs
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

// Pacotes
import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.gerenciamento.Filme;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;
import cine.monteiro.screens.componentes.*;
import cine.monteiro.screens.ouvintes.OuvinteBtnVoltarSessao;

public class WindowsCadastrarSessao extends Windows {
	// Atributos
	private JComboBox<String> cbFilmesDisponiveis;
	private JComboBox<String> cbSalasCadastradas;
	private JTextField tfIdentificacao;
	private JFormattedTextField tfPeriodoEmExibicao;
	private JFormattedTextField tfHorarioDaSessao;
	
	// Instâncias
	Persistencia bancoDeInformacoes = new Persistencia();
	CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
	Sessao novaSessao = new Sessao();
	
	// Construtor
	public WindowsCadastrarSessao() {
		super("Sessões - Cadastrar Nova Sessão", 400, 425);
		adicionarLabels();
		adicionarInputs();
		adicionarComboBox();
		adicionarButtons();
		setVisible(true);
	}
	
	// Componentes
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
		
		JLabel lblPeridoEmExibicao = new Rotulo("Em exibição até:", 175, 265, 150, 20);
		add(lblPeridoEmExibicao);
		
		JLabel lblFormatoDaData = new JLabel("[ano-mês-ano]");
		lblFormatoDaData.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 10));
		lblFormatoDaData.setBounds(285, 266, 150, 20);
		add(lblFormatoDaData);
	}
	
	private void adicionarInputs() {
		tfIdentificacao = new JTextField(novaSessao.getID() + "");
		tfIdentificacao.setBounds(20, 80, 345, 30);
		tfIdentificacao.setEditable(false);
		tfIdentificacao.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
		tfIdentificacao.setHorizontalAlignment(JTextField.CENTER);
		add(tfIdentificacao);
		
		try {
			MaskFormatter mascaraHorario = new MaskFormatter("##:##");
			tfHorarioDaSessao = new JFormattedTextField(mascaraHorario);
			tfHorarioDaSessao.setBounds(20, 290, 145, 30);
			tfHorarioDaSessao.setHorizontalAlignment(JFormattedTextField.CENTER);
			tfHorarioDaSessao.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
			add(tfHorarioDaSessao);
			
			MaskFormatter mascaraData = new MaskFormatter("####-##-##");
			tfPeriodoEmExibicao = new JFormattedTextField(mascaraData);
			tfPeriodoEmExibicao.setBounds(175, 290, 190, 30);
			tfPeriodoEmExibicao.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
			tfPeriodoEmExibicao.setHorizontalAlignment(JFormattedTextField.CENTER);
			add(tfPeriodoEmExibicao);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO DURANTE A VALIDAÇÃO DOS DADOS. TENTE NOVAMENTE!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void adicionarComboBox() {
		String[] filmes = new String[cpd.getFilmes().size()];
		String[] salas = new String[cpd.getSalas().size()];
		
		ArrayList<Filme> filmesCadastrados = cpd.getFilmes();
		ArrayList<Sala> salasCadastradas = cpd.getSalas();
		
		// Adicionar Filmes aos itens do ComboBox
		for(int i = 0; i < filmesCadastrados.size(); i++) {
			filmes[i] = filmesCadastrados.get(i).getNomeDoFilme();
		}
		
		cbFilmesDisponiveis = new JComboBox<String>(filmes);
		cbFilmesDisponiveis.setBounds(20, 150, 345, 30);
		add(cbFilmesDisponiveis);
		
		// Adicionar Salas aos itens do ComboBox
		for (int i = 0; i < salasCadastradas.size(); i++) {
			salas[i] = salasCadastradas.get(i).getNomeDaSala();
		}
		
		cbSalasCadastradas = new JComboBox<String>(salas);
		cbSalasCadastradas.setBounds(20, 220, 345, 30);
		add(cbSalasCadastradas);
	}

	private void adicionarButtons() {
		JButton btnCadastrar = new ButtonPersonalizado("CADASTRAR", 20, 335, 165, 35);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Filme filmeDaSessao = cpd.pesquisarFilme((String) cbFilmesDisponiveis.getSelectedItem());
					novaSessao.setFilme(filmeDaSessao);
					
					LocalTime horaDeInicio = LocalTime.parse(tfHorarioDaSessao.getText());
					novaSessao.setHoraDeInicio(horaDeInicio);
				
					long duracao = filmeDaSessao.getDuracao();
					novaSessao.setHoraDoTermino(duracao);
					
					try {	
						LocalDate terminoDoPeriodoDeExibicao = LocalDate.parse(tfPeriodoEmExibicao.getText());
						LocalDate dataAtual = LocalDate.now();
						if(terminoDoPeriodoDeExibicao.isAfter(dataAtual)) {
							novaSessao.setTerminoDoPeridoDeExibicao(terminoDoPeriodoDeExibicao);
						} else {
							JOptionPane.showMessageDialog(null, "A DATA DO PERÍODO DE EXIBIÇÃO NÃO PODE SER UMA DATA ANTIGA!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
							tfPeriodoEmExibicao.setText("");
							return;
						}
					} catch (Exception erroDaData) {
						JOptionPane.showMessageDialog(null, "A DATA DO PERÍODO DE EXIBIÇÃO INVÁLIDA!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					
					
					novaSessao.setAtiva();
					
					String salaSelecionada = (String) cbSalasCadastradas.getSelectedItem();
					Sala sala = cpd.pesquisarSala(salaSelecionada);
					
					cpd.adicionarSessao(novaSessao, sala);
					sala.adicionarFilmeExibido(filmeDaSessao);
					novaSessao.setVagasDisponiveis(sala.getQuantidadeDeAssentos());
					novaSessao.setDataAtualDaSessao(LocalDate.now());
					JOptionPane.showMessageDialog(null, "SESSÃO CADASTRADA COM SUCESSO!", "AVISO!", JOptionPane.INFORMATION_MESSAGE);
					bancoDeInformacoes.salvarCentralDeInformacoes(cpd);
					dispose();
					new WindowsSessao();
				} catch(Exception erro) {
					JOptionPane.showMessageDialog(null, erro.getMessage(), "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(btnCadastrar);
		
		JButton btnCancelar = new ButtonPersonalizado("CANCELAR", 200, 335, 165, 35);
		btnCancelar.addActionListener(new OuvinteBtnVoltarSessao(this));
		add(btnCancelar);
	}
}
