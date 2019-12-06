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
	private int totalDeIngressosVendidos;
	private float totalArrecadado;
	private boolean ativa;
	private boolean interrompida;
	private Date dataAtualDaSessao;
	private ArrayList<ArrayList<String>> dadosDaSessao = new ArrayList<ArrayList<String>>();
	private ArrayList<String> assentosReservado = new ArrayList<String>(); 
	
	// Construtor
	public Sessao() {
		interrompida = false;
		totalDeIngressosVendidos = 0;
		totalArrecadado = 0;
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
	
	public void setAssentosReservado(ArrayList<String> assentosReservado) {
		this.assentosReservado = assentosReservado;
	}
	
	public void setTotalDeIngressosVendidos(int totalDeIngressosVendidos) {
		this.totalDeIngressosVendidos = totalDeIngressosVendidos;
	}
	
	public void setTotalArrecadado(float totalArrecadado) {
		this.totalArrecadado = totalArrecadado;
	}
	
	public void setInterrompida(boolean status) {
		this.interrompida = status;
	}
	
	public void setDataAtualDaSessaoDate(Date dataAtualDaSessao) {
		this.dataAtualDaSessao = dataAtualDaSessao;
	}
	
	public void setDadosDaSessao(ArrayList<String> dados) {
		this.dadosDaSessao.add(dados);
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
	
	public boolean isInterrompida() {
		return interrompida;
	}
	
	public float getTotalArrecadado() {
		return totalArrecadado;
	}
	
	public int getTotalDeIngressosVendidos() {
		return totalDeIngressosVendidos;
	}
	
	public Date getDataAtualDaSessao() {
		return dataAtualDaSessao;
	}
	
	public ArrayList<ArrayList<String>> getDadosDaSessao()  {
		return dadosDaSessao;
	}
	
	public ArrayList<String> getAssentosReservado() {
		return assentosReservado;
	}
}
