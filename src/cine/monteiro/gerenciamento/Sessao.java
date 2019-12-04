package cine.monteiro.gerenciamento;

import java.text.SimpleDateFormat;
// APIs
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

public class Sessao {
	// Atributos
	private final long ID; 
	private Filme filme;
	private LocalTime horaDeInicio;
	private LocalTime horaDoTermino;
	private Date terminoDoPeriodoDeExibicao;
	private int vagasDisponiveis;
	private boolean ativa;
	
	// Construtor
	public Sessao() {
		ID = System.currentTimeMillis();
	}
	
	// Setters
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public void setHoraDeInicio(LocalTime horaDeInicio) {
		this.horaDeInicio = horaDeInicio;
	}
	
	public void setHoraDoTermino(long duracaoDoFilme) {
		horaDoTermino = horaDeInicio.plusMinutes(duracaoDoFilme);
	}
	
	public void setVagasDisponiveis(int vagasDisponveis) {
		this.vagasDisponiveis = vagasDisponveis;
	}
	
	public void setAtiva() {
		SimpleDateFormat formatoDaData = new SimpleDateFormat("dd/MM/yyyy");
		Date obterDataAtual = new Date();
		String dataString = formatoDaData.format(obterDataAtual);
		
		try {
			Date dataAtual = formatoDaData.parse(dataString);
			
			if((dataAtual.before(terminoDoPeriodoDeExibicao) || dataAtual.equals(terminoDoPeriodoDeExibicao))) {
				ativa = true;
			} else {
				ativa = false;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO AO CONVERTER DATA DO TERMINO DA SESSÃO", "ATENÇÃO!", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	public void setAtiva(Boolean status) {
		ativa = status;
	}
	
	public void setTerminoDoPeridoDeExibicao(Date terminoDoPeriodoDeExibicao) {
		this.terminoDoPeriodoDeExibicao = terminoDoPeriodoDeExibicao;
	}
	
	// Getters
	public Filme getFilme() {
		return filme;
	}
	
	public LocalTime getHoraDeInicio() {
		return horaDeInicio;
	}
	
	public LocalTime getHoraDoTermino() {
		return horaDoTermino;
	}
	
	public Date getTerminoDoPeriodoDeExibicao() {
		return terminoDoPeriodoDeExibicao;
	}
	
	public long getID() {
		return ID;
	}
	
	public int getVagasDisponiveis() {
		return vagasDisponiveis;
	}
	
	public boolean isAtiva() {
		return ativa;
	}
	
	// Sobreescritas
	public String toString() {
		DateTimeFormatter formatoDoTempo = DateTimeFormatter.ofPattern("HH:mm:ss");
		return "\nID: " + this.ID + "\nAtiva: " + this.ativa + "\nFilme: " + this.filme.toString() + "\nHora de ínicio: " + this.horaDeInicio.format(formatoDoTempo) + "\nHora do termíno: " + this.horaDoTermino.format(formatoDoTempo);
	}

}
