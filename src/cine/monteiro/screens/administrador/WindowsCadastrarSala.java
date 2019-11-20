package cine.monteiro.screens.administrador;

// APIs
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

// Pacotes
import cine.monteiro.screens.componentes.*;

public class WindowsCadastrarSala extends Windows {
	// Construtor
	public WindowsCadastrarSala() {
		super("Salas - Cadastrar Nova Sala", 360, 420);
		adicionarLabels();
		adicionarInputs();
		adicionarButtons();
		setVisible(true);
	}
	
	// Componentes
	private void adicionarLabels() {
		JLabel lblCadastrarNovaSala = new JLabel("CADASTRAR NOVA SALA");
		lblCadastrarNovaSala.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		lblCadastrarNovaSala.setBounds(0, 5, 360, 64);
		lblCadastrarNovaSala.setHorizontalAlignment(JLabel.CENTER);
		add(lblCadastrarNovaSala);
		
		JLabel lblID = new Rotulo("Identificação:", 20, 65, 150, 30);
		add(lblID);
		
		JLabel lblNomeDaSala = new Rotulo("Nome da Sala:", 20, 130, 100, 30);
		add(lblNomeDaSala);
		
		JLabel lblPrecoDoIngresso = new Rotulo("Preço do Ingresso:", 20 , 195, 200, 30);
		add(lblPrecoDoIngresso);
		
		JLabel lblQuantidadeDeAssentos = new Rotulo("Quantidade de Assentos:", 155, 195, 200, 30);
		add(lblQuantidadeDeAssentos);
		
		JLabel lblProjecaoSuportada = new Rotulo("Projeção Suportada:", 20, 260, 200, 30);
		add(lblProjecaoSuportada);
	}
	
	private void adicionarInputs() {
		JTextField tfID = new Input(20, 95, 305, 30);
		add(tfID);
		
		JTextField tfNomeDaSala = new Input(20, 160, 305, 30);
		add(tfNomeDaSala);
		
		JTextField tfPrecoDoIngresso = new Input(20, 225, 125, 30);
		add(tfPrecoDoIngresso);
		
		JTextField tfQuantidadeDeAssentos = new Input(155, 225, 170, 30);
		add(tfQuantidadeDeAssentos);
		
		JRadioButton rb2D = new JRadioButton("2D");
		rb2D.setBounds(90, 285, 50, 30);
		add(rb2D);
		
		JRadioButton rb3D = new JRadioButton("3D");
		rb3D.setBounds(150, 285, 50, 30);
		add(rb3D);
		
		JRadioButton rb2D3D = new JRadioButton("2D/3D");
		rb2D3D.setBounds(200, 285, 80, 30);
		add(rb2D3D);
	}
	
	private void adicionarButtons() {
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(20, 325, 145, 35);
		add(btnCadastrar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.setBounds(175, 325, 145, 35);
		btnCancelar.addActionListener(new OuvinteBtnVoltar(this));
		add(btnCancelar);
	}
	
	// Ouvintes
	public class OuvinteBtnVoltar implements ActionListener {
		private JFrame windows;
		
		public OuvinteBtnVoltar(JFrame windows) {
			this.windows = windows;
		}
		
		public void actionPerformed(ActionEvent e) {
			windows.dispose();
			new WindowsSala();
		}
	}
}