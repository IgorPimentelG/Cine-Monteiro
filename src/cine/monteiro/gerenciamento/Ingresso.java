package cine.monteiro.gerenciamento;

import java.text.NumberFormat;
import java.util.ArrayList;

import cine.monteiro.usuarios.Cliente;
import cine.monteiro.usuarios.Usuario;

public class Ingresso {
	// Atributos
	private Usuario cliente;
	private Sala local;
	private Sessao sessao;
	private float valorTotal;
	private ArrayList<String> assentoReservado = new ArrayList<String>();
	
	// Construtor
	public Ingresso(Usuario cliente, Sala local, Sessao sessao, float valorTotal, ArrayList<String> assentoReservado) {
		this.cliente = cliente;
		this.local = local;
		this.sessao = sessao;
		this.valorTotal = valorTotal;
		this.assentoReservado = assentoReservado;
	}
	
	// Getters
	public Usuario getCliente() {
		return cliente;
	}
	
	public Sala getLocal() {
		return local;
	}
	
	public Sessao getSessao() {
		return sessao;
	}
	
	public String getValorTotal() {
		return NumberFormat.getCurrencyInstance().format(valorTotal);
	}
	
	public ArrayList<String> getAssentoReservado() {
		return assentoReservado;
	}
	
	// Setters
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public void setLocal(Sala local) {
		this.local = local;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
	
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void setAssentoReservado(ArrayList<String> assentoReservado) {
		this.assentoReservado = assentoReservado;
	}
}
