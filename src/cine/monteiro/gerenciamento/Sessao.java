package cine.monteiro.gerenciamento;

import java.time.LocalDate;
// APIs
import java.time.LocalTime;
import java.util.ArrayList;


public class Sessao {
	// Atributos
	private final long ID; 
	private Filme filme;
	private LocalTime horaDeInicio;
	private LocalTime horaDoTermino;
	private LocalDate terminoDoPeriodoDeExibicao;
	private int vagasDisponiveis;
	private int totalDeIngressosVendidos;
	private float totalArrecadado;
	private boolean ativa;
	private boolean interrompidaEmUmDia;
	private boolean interrompida;
	private LocalTime horaDaInterrupcao;
	private LocalDate dataDaInterrupcao;
	private LocalDate dataAtualDaSessao;
	private ArrayList<ArrayList<String>> dadosDaSessaoDaSemana = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<String>> dadosDaSessaoDoMes = new ArrayList<ArrayList<String>>();
	private ArrayList<String> assentosReservado = new ArrayList<String>(); 
	
	// Construtor
	public Sessao() {
		interrompidaEmUmDia = false;
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
		if(isInterrompidaEmUmDia()) {
			if(dataDaInterrupcao.isBefore(dataAtualDaSessao) && (horaDaInterrupcao.equals(LocalTime.now())|| horaDaInterrupcao.isBefore(LocalTime.now()))) {
				ativa = true;
				interrompidaEmUmDia = false;
			} else {
				ativa = false;
			}
		} else if(isInterrompida()) {
			ativa = false;
		} else if((LocalDate.now().isBefore(terminoDoPeriodoDeExibicao) || LocalDate.now().equals(terminoDoPeriodoDeExibicao))) {
			ativa = true;
		} else {
			ativa = false;
		}
	}
	
	public void setAtiva(Boolean status) {
		ativa = status;
	}
	
	public void setTerminoDoPeridoDeExibicao(LocalDate terminoDoPeriodoDeExibicao) {
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
	
	public void setInterrompidaEmUmDia(boolean status) {
		this.interrompidaEmUmDia = status;
		
		if(status)  {
			this.horaDaInterrupcao = LocalTime.now();
			this.dataDaInterrupcao = this.dataAtualDaSessao;
		}
	}
	
	public void setInterrompida(boolean status) {
		this.interrompida = true;
	}
	
	public void setDataAtualDaSessao(LocalDate dataAtualDaSessao) {
		this.dataAtualDaSessao = dataAtualDaSessao;
	}
	
	public void adicionarDadosDaSessaoDaSeamana(ArrayList<String> dados) {
		this.dadosDaSessaoDaSemana.add(dados);
	}
	
	public void adicionDadosDaSessaoDoMes(ArrayList<String> dados) {
		for(ArrayList<String> dadosCadastrados : dadosDaSessaoDoMes) {
			if(dadosCadastrados.get(0).equals(dados.get(0))) {
				float totalArrecadadoNoMes = Float.parseFloat(dadosCadastrados.get(1)) + Float.parseFloat(dados.get(1));
				int totalDeIngressosVendidosNoMes = Integer.parseInt(dadosCadastrados.get(2)) + Integer.parseInt(dados.get(2));
				int totalDeVagasNoMes = Integer.parseInt(dadosCadastrados.get(3)) + Integer.parseInt(dados.get(3));
				dadosCadastrados.set(1, totalArrecadadoNoMes + "");
				dadosCadastrados.set(2, totalDeIngressosVendidosNoMes + " ");
				dadosCadastrados.set(3, totalDeVagasNoMes + "");
				return;
			}
		}
		
		dadosDaSessaoDoMes.add(dados);
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
	
	public LocalDate getTerminoDoPeriodoDeExibicao() {
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
	
	public boolean isInterrompidaEmUmDia() {
		return interrompidaEmUmDia;
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
	
	public LocalDate getDataAtualDaSessao() {
		return dataAtualDaSessao;
	}
	
	public ArrayList<ArrayList<String>> getDadosDaSessaoDaSemana()  {
		return dadosDaSessaoDaSemana;
	}
	
	public ArrayList<ArrayList<String>> getDadosDaSessaoDoMes() {
		return dadosDaSessaoDoMes;
	}
	
	public ArrayList<String> getAssentosReservado() {
		return assentosReservado;
	}
}
