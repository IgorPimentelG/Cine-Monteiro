package cine.monteiro.gerenciamento;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Sessao {
	// Atributos
	private final long ID; 
	private Filme filme;
	private LocalTime horaDeInicio;
	private LocalTime horaDoTermino;
	private Calendar periodoDeExibicao;
	private boolean ativa;
	
	// Construtor
	public Sessao(Filme filme, LocalTime horaDeInicio, Calendar periodoDeExibicao) {
		this.ID = System.currentTimeMillis();
		this.ativa = true;
		this.filme = filme;
		this.horaDeInicio = horaDeInicio;
		this.periodoDeExibicao = periodoDeExibicao;
		this.horaDoTermino = horaDeInicio.plusMinutes(this.filme.getDuracaco());
	}
	
	// Setters
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public void setHoraDeInicio(LocalTime horaDeInicio) {
		this.horaDeInicio = horaDeInicio;
	}
	
	public void setHoraDoTermino(LocalTime horaDoTermino) {
		this.horaDoTermino = horaDoTermino;
	}
	
	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
	
	public void setPeriodoDeExibicao(Calendar periodoDeExibicao) {
		this.periodoDeExibicao = periodoDeExibicao;
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
	
	public Calendar getPeriodoDeExibicao() {
		return periodoDeExibicao;
	}
	
	public long getID() {
		return ID;
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
