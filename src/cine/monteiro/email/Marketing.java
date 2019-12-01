package cine.monteiro.email;

// APIs
import java.util.ArrayList;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

// Pacotes
import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.usuarios.Usuario;

public class Marketing extends Email {
	public void gerarProgramacao() {
		
	}
	
	public void enviarProgramacao() throws MessagingException {
		Persistencia bancoDeDados = new Persistencia();
		CentralDeInformacoes cpd = bancoDeDados.recuperarCentralDeInformacoes();
		
		ArrayList<Usuario> clientes = cpd.getUsuarios();
		String[] emailDosClientes = new String[cpd.getUsuarios().size()];
		
		for(int i = 0; i < clientes.size(); i++) {
			emailDosClientes[i] = clientes.get(i).getEmail();
		}
		
		Address[] destinatarios = new Address[emailDosClientes.length];
		
		for(int i = 0; i < emailDosClientes.length; i++) {
			destinatarios[i] = new InternetAddress(emailDosClientes[i]);
		}
		
		// Criar Mensagem
		Message mensagem = super.configuracao();
		mensagem.setFrom(new InternetAddress(super.getRemetente()));
		mensagem.setRecipients(Message.RecipientType.TO,  destinatarios);
		mensagem.setSubject("Cine Monteiro - Programação");
		mensagem.setText("Programação");
		Transport.send(mensagem);
	}
}
