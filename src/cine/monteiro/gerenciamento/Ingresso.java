package cine.monteiro.gerenciamento;

import cine.monteiro.usuarios.Cliente;

public class Ingresso {
	// Atributos
	private Cliente cliente;
	private Sala local;
	private Sessao sessao;
	private int assentoReservado;
	
	// Construtor
	public Ingresso(Cliente cliente, Sala local, Sessao sessao, int assentoReservado) {
		this.cliente = cliente;
		this.local = local;
		this.sessao = sessao;
		this.assentoReservado = assentoReservado;
	}
	
	// Getters
	public Cliente getCliente() {
		return cliente;
	}
	
	public Sala getLocal() {
		return local;
	}
	
	public Sessao getSessao() {
		return sessao;
	}
	
	public int getAssentoReservado() {
		return assentoReservado;
	}
	
	// Setters
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setLocal(Sala local) {
		this.local = local;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public void setAssentoReservado(int assentoReservado) {
		this.assentoReservado = assentoReservado;
	}
}
