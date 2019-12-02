package cine.monteiro.screens.cliente;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.ButtonAssento;
import cine.monteiro.screens.componentes.ButtonPersonalizado;
import cine.monteiro.screens.componentes.Input;
import cine.monteiro.screens.componentes.RotuloDetalhar;
import cine.monteiro.screens.componentes.RotuloTitulo;
import cine.monteiro.screens.componentes.Separador;
import cine.monteiro.screens.componentes.Windows;

public class WindowsComprarIngresso extends Windows {
	// Atributos
	private Sala local;
	private Sessao sessao;
	private float total;
	private JLabel lblTotal;
	private JTextField tfQntdIngressos;
	
	JButton[] buttons = new JButton[40];
	
	// Instâncias	
	private ArrayList<String> assentosReservados = new ArrayList<String>();
	
	public WindowsComprarIngresso(Sala local, Sessao sessao) {
		super("Carrinho de Compra", 840, 505);
		this.local = local;
		this.sessao = sessao;
		total = Float.parseFloat(local.getPrecoDoIngresso().split(" ")[1]);
		adicionarImagem();
		adicionarSeparador();
		adicionarLabels();
		adicionarInput();
		adicionarButtons();
		configurarButtons();
		setVisible(true);
	}
	
	private void adicionarImagem() {
		JLabel lblIconeIngresso = new JLabel(Imagens.INGRESSO);
		lblIconeIngresso.setBounds(80, 20, 100, 100);
		add(lblIconeIngresso);
	}
	
	private void adicionarSeparador() {
		JSeparator separador = new Separador(250, 5, 2, 460);
		add(separador);
		
		JSeparator separador2 = new JSeparator(SwingConstants.HORIZONTAL);
		separador2.setBounds(5, 330, 240, 2);
		add(separador2);
	}
	
	private void adicionarLabels() {
		JLabel lblEscolhaOAssento = new RotuloTitulo("ESCOLHA SEU ASSENTO", 250, 15, 590, 20);
		add(lblEscolhaOAssento);
		
		JLabel lblTela = new JLabel("TELA");
		Border bordar = BorderFactory.createLineBorder(Color.GRAY, 1);
		lblTela.setOpaque(true);
		lblTela.setBackground(Color.WHITE);
		lblTela.setBorder(bordar);
		lblTela.setHorizontalAlignment(JLabel.CENTER);
		lblTela.setBounds(270, 55, 535, 35);
		add(lblTela);
		
		JLabel lblIngresso = new RotuloTitulo("INGRESSO", 0, 115, 250, 20);
		add(lblIngresso);
		
		JLabel lblNomeDoFilme = new RotuloTitulo(sessao.getFilme().getNomeDoFilme(), 0, 160, 250, 30);
		add(lblNomeDoFilme);
		
		JLabel lblLocal = new RotuloDetalhar("Local: " + local.getNomeDaSala(), 20, 200, 150, 20);
		add(lblLocal);
		
		JLabel lblHoraDeInicio = new RotuloDetalhar("Horário: " + sessao.getHoraDeInicio(), 20, 225, 150, 20);
		add(lblHoraDeInicio);
		
		JLabel lblClassificacaoEtaria = new RotuloDetalhar("Classificação Etária: " + sessao.getFilme().getClassificacaoEtaria(), 20, 250, 250, 20);
		add(lblClassificacaoEtaria);
		
		JLabel lblQntdIngressos = new RotuloDetalhar("Qntd. Ingressos:", 20, 282, 150, 20);
		add(lblQntdIngressos);
		
		JLabel lblPrecoDoIngresso = new RotuloDetalhar("Preço do Ingresso: " + local.getPrecoDoIngresso(), 20, 350, 200, 20);
		add(lblPrecoDoIngresso);
		
		lblTotal = new RotuloDetalhar("Total: " + NumberFormat.getCurrencyInstance().format(total), 20, 380, 200, 20);
		lblTotal.setForeground(new Color(0, 184, 148));
		add(lblTotal);
	}
	
	private void adicionarInput() {
		tfQntdIngressos = new Input(135, 280, 30, 30);
		tfQntdIngressos.setText("1");
		tfQntdIngressos.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char caractere = e.getKeyChar();
				if(!Character.isDigit(caractere)) {
					e.consume();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		tfQntdIngressos.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
			}
			
			public void mousePressed(MouseEvent e) {
			}
			
			public void mouseExited(MouseEvent e) {
				if(Integer.parseInt(tfQntdIngressos.getText()) > sessao.getVagasDisponiveis()) {
					JOptionPane.showMessageDialog(null, "QUANTIDADE DE INGRESSOS SUPERIOR A QUANTIDADE DE VAGAS DISPONÍVEIS!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
					tfQntdIngressos.setText("1");
				} else {
					tfQntdIngressos.setEditable(false);
					total = Float.parseFloat(local.getPrecoDoIngresso().split(" ")[1]) * Integer.parseInt(tfQntdIngressos.getText());
					lblTotal.setText("Total: " + NumberFormat.getCurrencyInstance().format(total));
					repaint();
				}
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}
		});
		add(tfQntdIngressos);
	}
	
	private void adicionarButtons() {
		JButton btnComprar = new ButtonPersonalizado("COMPRAR", 20, 415, 210, 35);
		add(btnComprar);
		
		// Assentos
		// Fila A
		buttons[0] = new ButtonAssento("A1", 270, 115);
		buttons[0].addActionListener(new OuvinteBtnAssento(buttons[0]));
		buttons[0].setEnabled(false);
		add(buttons[0]);
		buttons[1] = new ButtonAssento("A2", 335, 115);
		buttons[1].addActionListener(new OuvinteBtnAssento(buttons[1]));
		buttons[1].setEnabled(false);
		add(buttons[1]);
		buttons[2] = new ButtonAssento("A3", 400, 115);
		buttons[2].addActionListener(new OuvinteBtnAssento(buttons[2]));
		buttons[2].setEnabled(false);
		add(buttons[2]);
		buttons[3] = new ButtonAssento("A4", 465, 115);
		buttons[3].addActionListener(new OuvinteBtnAssento(buttons[3]));
		buttons[3].setEnabled(false);
		add(buttons[3]);
		buttons[4] = new ButtonAssento("A5", 555, 115);
		buttons[4].addActionListener(new OuvinteBtnAssento(buttons[4]));
		buttons[4].setEnabled(false);
		add(buttons[4]);
		buttons[5] = new ButtonAssento("A6", 620, 115);
		buttons[5].addActionListener(new OuvinteBtnAssento(buttons[5]));
		buttons[5].setEnabled(false);
		add(buttons[5]);
		buttons[6] = new ButtonAssento("A7", 685, 115);
		buttons[6].addActionListener(new OuvinteBtnAssento(buttons[6]));
		buttons[6].setEnabled(false);
		add(buttons[6]);
		buttons[7] =  new ButtonAssento("A8", 750, 115);
		buttons[7].addActionListener(new OuvinteBtnAssento(buttons[7]));
		buttons[7].setEnabled(false);
		add(buttons[7]);
		
	
		// Fila B
		buttons[8] = new ButtonAssento("B1", 270, 185);
		buttons[8].addActionListener(new OuvinteBtnAssento(buttons[8]));
		add(buttons[8]);
		buttons[9] = new ButtonAssento("B2", 335, 185);
		buttons[9].addActionListener(new OuvinteBtnAssento(buttons[9]));
		add(buttons[9]);
		buttons[10] = new ButtonAssento("B3", 400, 185);
		buttons[10].addActionListener(new OuvinteBtnAssento(buttons[10]));
		add(buttons[10]);
		buttons[11] = new ButtonAssento("B4", 465, 185);
		buttons[11].addActionListener(new OuvinteBtnAssento(buttons[11]));
		add(buttons[11]);
		buttons[12] = new ButtonAssento("B5", 555, 185);
		buttons[12].addActionListener(new OuvinteBtnAssento(buttons[12]));
		add(buttons[12]);
		buttons[13] = new ButtonAssento("B6", 620, 185);
		buttons[13].addActionListener(new OuvinteBtnAssento(buttons[13]));
		add(buttons[13]);
		buttons[14] = new ButtonAssento("B7", 685, 185);
		buttons[14].addActionListener(new OuvinteBtnAssento(buttons[14]));
		add(buttons[14]);
		buttons[15] = new ButtonAssento("B8", 750, 185);
		buttons[15].addActionListener(new OuvinteBtnAssento(buttons[15]));
		add(buttons[15]);
		
		// Fila C
		buttons[16] = new ButtonAssento("C1", 270, 255);
		buttons[16].addActionListener(new OuvinteBtnAssento(buttons[16]));
		add(buttons[16]);
		buttons[17] = new ButtonAssento("C2", 335, 255);
		buttons[17].addActionListener(new OuvinteBtnAssento(buttons[17]));
		add(buttons[17]);
		buttons[18] = new ButtonAssento("C3", 400, 255);
		buttons[18].addActionListener(new OuvinteBtnAssento(buttons[18]));
		add(buttons[18]);
		buttons[19] = new ButtonAssento("C4", 465, 255);
		buttons[19].addActionListener(new OuvinteBtnAssento(buttons[19]));
		add(buttons[19]);
		buttons[20] = new ButtonAssento("C5", 555, 255);
		buttons[20].addActionListener(new OuvinteBtnAssento(buttons[20]));
		add(buttons[20]);
		buttons[21] = new ButtonAssento("C6", 620, 255);
		buttons[21].addActionListener(new OuvinteBtnAssento(buttons[21]));
		add(buttons[21]);
		buttons[22] = new ButtonAssento("C7", 685, 255);
		buttons[22].addActionListener(new OuvinteBtnAssento(buttons[22]));
		add(buttons[22]);
		buttons[23] = new ButtonAssento("C8", 750, 255);
		buttons[23].addActionListener(new OuvinteBtnAssento(buttons[23]));
		add(buttons[23]);
		
		// Fila D
		buttons[24] = new ButtonAssento("D1", 270, 325);
		buttons[24].addActionListener(new OuvinteBtnAssento(buttons[24]));
		add(buttons[24]);
		buttons[25] = new ButtonAssento("D2", 335, 325);
		buttons[25].addActionListener(new OuvinteBtnAssento(buttons[25]));
		add(buttons[25]);
		buttons[26] = new ButtonAssento("D3", 400, 325);
		buttons[26].addActionListener(new OuvinteBtnAssento(buttons[26]));
		add(buttons[26]);
		buttons[27] = new ButtonAssento("D4", 465, 325);
		buttons[27].addActionListener(new OuvinteBtnAssento(buttons[27]));
		add(buttons[27]);
		buttons[28] = new ButtonAssento("D5", 555, 325);
		buttons[28].addActionListener(new OuvinteBtnAssento(buttons[28]));
		add(buttons[28]);
		buttons[29] = new ButtonAssento("D6", 620, 325);
		buttons[29].addActionListener(new OuvinteBtnAssento(buttons[29]));
		add(buttons[29]);
		buttons[30] = new ButtonAssento("D7", 685, 325);
		buttons[30].addActionListener(new OuvinteBtnAssento(buttons[30]));
		add(buttons[30]);
		buttons[31] = new ButtonAssento("D8", 750, 325);
		buttons[31].addActionListener(new OuvinteBtnAssento(buttons[31]));
		add(buttons[31]);
		
		// Fila E
		buttons[32] = new ButtonAssento("E1", 270, 395);
		buttons[32].addActionListener(new OuvinteBtnAssento(buttons[32]));
		add(buttons[32]);
		buttons[33] = new ButtonAssento("E2", 335, 395);
		buttons[33].addActionListener(new OuvinteBtnAssento(buttons[33]));
		add(buttons[33]);
		buttons[34] = new ButtonAssento("E3", 400, 395);
		buttons[34].addActionListener(new OuvinteBtnAssento(buttons[34]));
		add(buttons[34]);
		buttons[35] = new ButtonAssento("E4", 465, 395);
		buttons[35].addActionListener(new OuvinteBtnAssento(buttons[35]));
		add(buttons[35]);
		buttons[36] = new ButtonAssento("E5", 555, 395);
		buttons[36].addActionListener(new OuvinteBtnAssento(buttons[36]));
		add(buttons[36]);
		buttons[37] = new ButtonAssento("E6", 620, 395);
		buttons[37].addActionListener(new OuvinteBtnAssento(buttons[37]));
		add(buttons[37]);
		buttons[38] = new ButtonAssento("E7", 685, 395);
		buttons[38].addActionListener(new OuvinteBtnAssento(buttons[38]));
		add(buttons[38]);
		buttons[39] = new ButtonAssento("E8", 750, 395);
		buttons[39].addActionListener(new OuvinteBtnAssento(buttons[39]));
		add(buttons[39]);
	}

	private void configurarButtons() {
		for(int i = 0; i < local.getQuantidadeDeAssentos(); i++) {
			buttons[i].setEnabled(true);
		}
	}
	
	// Ouvintes
	private class OuvinteBtnAssento implements ActionListener {
		private JButton btn;
		
		public OuvinteBtnAssento(JButton btn) {
			this.btn = btn;
		}
		
		public void actionPerformed(ActionEvent e) {
			if(assentosReservados.size() < Integer.parseInt(tfQntdIngressos.getText())) {
				for(String assento : assentosReservados) {
					if(assento.equals(btn.getText())) {
						JOptionPane.showMessageDialog(null, "ASSENTO JÁ FOI SELECIONADO!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				assentosReservados.add(btn.getText());
				btn.setBackground(new Color(78, 238, 148));
				repaint();
				
				if(assentosReservados.size() == Integer.parseInt(tfQntdIngressos.getText())) {
					
					for(int i = 0; i < local.getQuantidadeDeAssentos(); i++) {
						for(String assento : assentosReservados) {
							if(!(assento.equals(buttons[i].getText()))) {
								buttons[i].setEnabled(false);
							}
						}
						
					}
				}
			} 
		}
	}
}
