package cine.monteiro.email;

// APIs
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

// Pacotes
import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.gerenciamento.Filme;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.gerenciamento.Sessao;

public class Relatorio extends Email {
	Persistencia bancoDeInformacoes = new Persistencia();
	CentralDeInformacoes cpd = bancoDeInformacoes.recuperarCentralDeInformacoes();
	
	private void gerarRelatorio(String mes) throws Exception {
		Document relatorio = new Document(PageSize.A4);
		PdfWriter.getInstance(relatorio, new FileOutputStream("pdfs/relatorio_cine_monteiro.pdf"));
		relatorio.open();
		
		Image logoDoProjeto = Image.getInstance("imagens/logo-projeto_180x156.png");
		logoDoProjeto.setAlignment(1);
		relatorio.add(logoDoProjeto);
		
		Font fonteTitulo = new Font(FontFamily.HELVETICA, 16, Font.BOLD);
		Paragraph titulo = new Paragraph("- - - -  • R E L A T Ó R I O • - - - -", fonteTitulo);
		titulo.setAlignment(1);
		relatorio.add(titulo);
		
		Paragraph dadosDaData = new Paragraph(mes);
		dadosDaData.setAlignment(1);
		relatorio.add(dadosDaData);
		
		Paragraph tituloSessao = new Paragraph("\n• S E S S O E S •", fonteTitulo);
		tituloSessao.setAlignment(1);
		relatorio.add(tituloSessao);
		
		ArrayList<Sala> salasCadastradas = cpd.getSalas();
		for(Sala sala : salasCadastradas) {
			ArrayList<Sessao> sessoeCadastradas = sala.getSessoes();
			for(Sessao sessao : sessoeCadastradas) {
				ArrayList<ArrayList<String>> dadosDaSessaoDoMes = sessao.getDadosDaSessaoDoMes();
				for(ArrayList<String> dados : dadosDaSessaoDoMes) {
					if(dados.get(0).equals(mes)) {
						Paragraph idDaSessao = new Paragraph("\nSessão: " + sessao.getID());
						relatorio.add(idDaSessao);
						
						Paragraph dadosTotalArrecadado = new Paragraph("Total arrecadado: " + NumberFormat.getCurrencyInstance().format(Float.parseFloat(dados.get(1))));
						relatorio.add(dadosTotalArrecadado);
						
						Paragraph dadosTotalDeIngressosVendidos = new Paragraph("Total de Ingressos Vendidos: " + dados.get(2));
						relatorio.add(dadosTotalDeIngressosVendidos);
				
						Paragraph dadosTotalDeVagas = new Paragraph("Total de assentos vagos: " + dados.get(3));
						relatorio.add(dadosTotalDeVagas);
					}	
				}
			}
		}
		
		Paragraph tituloFilme = new Paragraph("\n• F I L M E S •", fonteTitulo);
		tituloFilme.setAlignment(1);
		relatorio.add(tituloFilme);
		
		ArrayList<Filme> filmesCadastrados = cpd.getFilmes();
		for(Filme filme : filmesCadastrados) {
			ArrayList<ArrayList<String>> dadosDaArrecadacao = filme.getDadosDaArrecadacao();
			for(ArrayList<String> dados : dadosDaArrecadacao) {
				if(dados.get(0).equals(mes)) {
					Paragraph dadosDoFilme = new Paragraph("\nFilme: " + filme.getNomeDoFilme() + "\nTotal Arrecadado: " + NumberFormat.getCurrencyInstance().format(Float.parseFloat(dados.get(1))));
					relatorio.add(dadosDoFilme);
				}
			}	
		}
		relatorio.close();
	}
	
	public void enviarRelatorio(String mes) throws Exception {
		// Gerar Relatório
		this.gerarRelatorio(mes);
		
		// Criar Mensagem
		Message mensagem = super.configuracao();
		mensagem.setFrom(new InternetAddress(super.getRemetente()));
		mensagem.setRecipient(Message.RecipientType.TO, new InternetAddress(cpd.getUsuarios().get(0).getEmail()));
		mensagem.setSubject("Cine Monteiro - Relatório");
		
		MimeBodyPart texto = new MimeBodyPart();
		texto.setText("Relatório do cine monteiro referênte: " + mes);
		
		MimeBodyPart anexo = new MimeBodyPart();
		FileDataSource arquivoDoRelatorio = new FileDataSource("pdfs/relatorio_cine_monteiro.pdf");
		anexo.setDataHandler(new DataHandler(arquivoDoRelatorio));
		anexo.setFileName(arquivoDoRelatorio.getName());
		
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(texto);
		mp.addBodyPart(anexo);
		
		mensagem.setContent(mp);
		mensagem.setSentDate(new Date());
		Transport.send(mensagem);
	}
	
}
