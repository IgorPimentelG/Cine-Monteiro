package cine.monteiro.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.usuarios.*;

public class RecuperarSenha extends Email {
	private String novaSenha = "";
	
	private void gerarNovaSenha() {
		String[] caracteres = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", 
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
				"N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
		
		for(int c = 0; c < 8; c++) {
			int caractereSorteado = (int) (Math.random() * caracteres.length);
			this.novaSenha += caracteres[caractereSorteado];
		}
	}
	
	public void recuperarSenha(Usuario usuario) throws MessagingException{
		Persistencia bancoDeInformacoes = new Persistencia();
		CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
		
		this.gerarNovaSenha();
		
		cpd.getUsuarios().get(0).setSenha(this.novaSenha);
		//usuario.setSenha(this.novaSenha);
		System.out.print(usuario.getSenha());
		// Criar Mensagem
		Message mensagem = super.configuracao();
		mensagem.setFrom(new InternetAddress(super.getRemetente()));
		mensagem.setRecipients(Message.RecipientType.TO, InternetAddress.parse(usuario.getEmail()));
		mensagem.setSubject("Cine Monteiro - Recuperação da Senha");
		mensagem.setText("Olá " + usuario.getNome() + ",\n\nVocê solicitou uma nova senha para sua conta do Cine Monteiro. \nSua senha temporária é: " + this.novaSenha);
		Transport.send(mensagem);
		bancoDeInformacoes.salvarCentralDeInformacoes(cpd);
	}
}
