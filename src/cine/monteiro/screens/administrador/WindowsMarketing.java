package cine.monteiro.screens.administrador;

// APIs
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.mail.MessagingException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

// Pacotes
import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.email.Marketing;
import cine.monteiro.imagens.Imagens;
import cine.monteiro.screens.componentes.ButtonPersonalizado;
import cine.monteiro.screens.componentes.Separador;
import cine.monteiro.screens.componentes.Windows;
import cine.monteiro.screens.ouvintes.OuvinteBtnVoltarPainelDeControle;
import cine.monteiro.usuarios.Usuario;

public class WindowsMarketing extends Windows {
	// Atributos
	private Usuario usuarioAtivo;
	
	// Instâncias
	Persistencia bancoDeInformacoes = new Persistencia();
	CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
	
	// Construtor
	public WindowsMarketing(Usuario usuarioAtivo) {
		super("Painel De Controle - Marketing", 475, 185);
		this.usuarioAtivo = usuarioAtivo;
		adicionarImagens();
		adicionarLabels();
		adicionarSeparador();
		adicionarButtons();
		setVisible(true);
	}
	
	// Componentes
	public void adicionarImagens() {
		JLabel iconeEmail = new JLabel(Imagens.EMAIL_64x64);
		iconeEmail.setBounds(25, 40, 64, 64);
		add(iconeEmail);
	}
	
	public void adicionarLabels() {
		JLabel lblTitulo = new JLabel("Marketing");
		lblTitulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 18));
		lblTitulo.setBounds(240, 10, 100, 30);
		add(lblTitulo);
		
		JLabel lblTexto = new JLabel("<html><center>Enviar programação do cine monteiro para todos os clientes cadastrados?</center></html>");
		lblTexto.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
		lblTexto.setBounds(137, 15, 300, 100);
		add(lblTexto);
	}
	
	public void adicionarSeparador() {
		JSeparator separador = new Separador(110, 5, 2, 135);
		add(separador);
	}
	
	public void adicionarButtons() {
		JButton btnConfirmar = new ButtonPersonalizado("CONFIRMAR", 130, 100, 150, 30);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Marketing marketing = new Marketing();
				try {
					marketing.enviarProgramacao();
					JOptionPane.showMessageDialog(null, "PROGRAMAÇÃO ENVIADA COM SUCESSO PARA TODOS OS CLIENTES!", "AVISO!", JOptionPane.INFORMATION_MESSAGE);
					dispose();
					new WindowsPainelDeControle(usuarioAtivo);
				} catch(MessagingException erro) {
					JOptionPane.showMessageDialog(null, "HOUVE UM ERRO AO ENVIAR A PROGRAMAÇÃO!", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(btnConfirmar);
		
		JButton btnVoltar = new ButtonPersonalizado("VOLTAR", 290, 100, 150, 30);
		btnVoltar.addActionListener(new OuvinteBtnVoltarPainelDeControle(this));
		add(btnVoltar);
	}
}
