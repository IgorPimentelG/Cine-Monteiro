package cine.monteiro.email;

import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
// APIs
import java.util.ArrayList;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

// Pacotes
import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;
import cine.monteiro.usuarios.Usuario;

public class Marketing extends Email {
	Persistencia bancoDeDados = new Persistencia();
	CentralDeInformacoes cpd = bancoDeDados.recuperarCentralDeInformacoes();
	
	private void gerarProgramacao() throws Exception {
		Document programacao = new Document(PageSize.A4);
		PdfWriter.getInstance(programacao, new FileOutputStream("pdfs/cine_monteiro_programcao.pdf"));
		programacao.open();
		
		Image logoDoProjeto = Image.getInstance("imagens/logo-projeto_180x156.png");
		logoDoProjeto.setAlignment(1);
		programacao.add(logoDoProjeto);
		
		Font fonteTitulo = new Font(FontFamily.HELVETICA, 16, Font.BOLD);
		Paragraph titulo = new Paragraph("- - - -  • P R O G R A M A Ç Ã O • - - - -", fonteTitulo);
		titulo.setAlignment(1);
		programacao.add(titulo);
		
		LocalDate dataAtual = LocalDate.now();
		Paragraph dadosDataAtual = new Paragraph(dataAtual.toString());
		dadosDataAtual.setAlignment(1);
		programacao.add(dadosDataAtual);
		
		ArrayList<Sala> salasCadastradas = cpd.getSalas();
		for(Sala sala : salasCadastradas) {
			ArrayList<Sessao> sessoeCadastradas = sala.getSessoes();
			for(Sessao sessao : sessoeCadastradas) {
				if(sessao.isAtiva() && LocalTime.now().isBefore(sessao.getHoraDeInicio())) {
					Paragraph nomeDoFilme = new Paragraph("\nFilme: " + sessao.getFilme().getNomeDoFilme());
					programacao.add(nomeDoFilme);
					
					Paragraph genero = new Paragraph("Gênero: " + sessao.getFilme().getGenero());
					programacao.add(genero);
					
					Paragraph duracao = new Paragraph("Duração: " + sessao.getFilme().getDuracao() + " min");
					programacao.add(duracao);
					
					Paragraph classificacaoEtaria = new Paragraph("Classificação: " + sessao.getFilme().getClassificacaoEtaria());
					programacao.add(classificacaoEtaria);
					
					Paragraph local = new Paragraph("Local: " + sala.getNomeDaSala());
					programacao.add(local);
					
					Paragraph horario = new Paragraph("Horário: " + sessao.getHoraDeInicio() + " - " + sessao.getHoraDoTermino());
					programacao.add(horario);
					
					Paragraph vagasDisponiveis = new Paragraph("Vagas disponíveis: " + sessao.getVagasDisponiveis());
					programacao.add(vagasDisponiveis);
					
					Paragraph periodoDeExibicao = new Paragraph("Em exibição até: " + sessao.getTerminoDoPeriodoDeExibicao());
					programacao.add(periodoDeExibicao);
					
					Paragraph PrecoDoIngresso = new Paragraph("Preço do ingresso: " + sala.getPrecoDoIngresso());
					programacao.add(PrecoDoIngresso);
					
					Paragraph quebraDeLinha = new Paragraph(" ");
					programacao.add(quebraDeLinha);
				}
			}
		}
		programacao.close();
	}
	
	public void enviarProgramacao() throws Exception {
		// Gerar programação
		this.gerarProgramacao();
		
		ArrayList<Usuario> clientes = cpd.getUsuarios();
		String[] emailDosClientes = new String[cpd.getUsuarios().size()];
		
		// Recuperar e converter e-mails dos usuários
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
		
		MimeBodyPart texto = new MimeBodyPart();
		texto.setText("Olá, Confira Os Filmes Em Cartaz Hoje No Cine Monteiro.");
		
		MimeBodyPart anexo = new MimeBodyPart();
		FileDataSource arquivoDaProgramacao = new FileDataSource("pdfs/cine_monteiro_programcao.pdf");
		anexo.setDataHandler(new DataHandler(arquivoDaProgramacao));
		anexo.setFileName(arquivoDaProgramacao.getName());
		
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(texto);
		mp.addBodyPart(anexo);
		
		mensagem.setContent(mp);
		mensagem.setSentDate(new Date());
		Transport.send(mensagem);
	}
}
