package cine.monteiro.email;

// APIs
import java.io.FileOutputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
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
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

// Pacotes
import cine.monteiro.gerenciamento.Ingresso;

public class Boleto extends Email {
	private Ingresso ingresso;
	
	public Boleto(Ingresso ingresso) {
		this.ingresso = ingresso;
	}
	
	public void gerarBoleto() throws Exception {
		Document doc = new Document(PageSize.A4);
		PdfWriter.getInstance(doc, new FileOutputStream("cine_monteiro_boleto.pdf"));
		doc.open();
		
		Image logoDoProjeto = Image.getInstance("imagens/logo-projeto_180x156.png");
		logoDoProjeto.setAlignment(1);
		doc.add(logoDoProjeto);
		
		Font fonteTitulo = new Font(FontFamily.HELVETICA, 16, Font.BOLD);
		Paragraph titulo = new Paragraph("   BOLETO DO INGRESSO", fonteTitulo);
		titulo.setAlignment(1);
		doc.add(titulo);
		
		Paragraph subTitulo = new Paragraph("\n- - - - • D A D O S • - - - -", fonteTitulo);
		subTitulo.setAlignment(1);
		doc.add(subTitulo);
		
		Font fonteDoTexto= new Font(FontFamily.HELVETICA, 14);
		Paragraph dadosDoCliente = new Paragraph("\nNome: " + ingresso.getCliente().getNome() + "\nCPF: " + ingresso.getCliente().getCPF(), fonteDoTexto);
		doc.add(dadosDoCliente);
		
		Paragraph dadosDoLocal = new Paragraph("Local: " + ingresso.getLocal().getNomeDaSala(), fonteDoTexto);
		doc.add(dadosDoLocal);
		
		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
		Paragraph dadosDaSessao = new Paragraph("Início da Sessão: " + ingresso.getSessao().getHoraDeInicio() + "\nFilme: " + ingresso.getSessao().getFilme().getNomeDoFilme() + "\nDuração: " + ingresso.getSessao().getFilme().getDuracao() + " min" + "\nClassificação Etária: " + ingresso.getSessao().getFilme().getClassificacaoEtaria() + "\nVálido até: " + formatoData.format(ingresso.getSessao().getTerminoDoPeriodoDeExibicao()), fonteDoTexto);
		doc.add(dadosDaSessao);
		
		Paragraph dadosDoAssento = new Paragraph("Assento(s) reservado(s): " + ingresso.getAssentoReservado(), fonteDoTexto);
		doc.add(dadosDoAssento);
		
		Paragraph valores = new Paragraph("\n\nQUANTIDADE: " + ingresso.getAssentoReservado().size() + "\nVALOR UNITÁRIO: " + ingresso.getLocal().getPrecoDoIngresso() + "\nVALOR TOTAL: " + ingresso.getValorTotal(), fonteTitulo);
		valores.setAlignment(1);
		doc.add(valores);

		Paragraph l2 = new Paragraph("\n\n\n\n- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		doc.add(l2);
		
		Image codigoDeBarra = Image.getInstance("imagens/codigo_barra.png");
		codigoDeBarra.setAbsolutePosition(110, 20);
		doc.add(codigoDeBarra);
		
		doc.close();
		
	}
	
	public void enviarBoleto() throws Exception {
		gerarBoleto();
		
		Message mensagem = super.configuracao();
		mensagem.setFrom(new InternetAddress(super.getRemetente()));
		mensagem.setRecipient(Message.RecipientType.TO, new InternetAddress(ingresso.getCliente().getEmail()));
		mensagem.setSubject("Cine Monteiro - Boleto");
		
		MimeBodyPart texto = new MimeBodyPart();
		texto.setText("Seu Ingresso será válido logo após o pagamento do boleto.");
		
		MimeBodyPart anexo = new MimeBodyPart();
		FileDataSource arquivo = new FileDataSource("cine_monteiro_boleto.pdf");
		anexo.setDataHandler(new DataHandler(arquivo));
		anexo.setFileName(arquivo.getName());
		
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(texto);
		mp.addBodyPart(anexo);
		
		mensagem.setContent(mp);
		mensagem.setSentDate(new Date());
		
		Transport.send(mensagem);
	}
}
