package cine.monteiro.gerenciamento;

import java.text.NumberFormat;
import java.util.ArrayList;
import cine.monteiro.usuarios.Usuario;

/**
 * Classe responvel por gerar o ingresso comprado pelo o cliente.
 * @author Igor Pimentel
 *
 */
public class Ingresso {
	// Atributos
	private Usuario cliente;
	private Sala local;
	private Sessao sessao;
	private float valorTotal;
	private ArrayList<String> assentoReservado = new ArrayList<String>();
	
	// Construtor
	/**
	 * Inicializa os atributos com as informacoes necessarias
	 * @param cliente Ciente que comprou o ingresso.
	 * @param local Sala onde a sessao esta sendo exebida.
	 * @param valorTotal Valor total a ser pago pelo o ingresso.
	 * @param assentosReservados Numero(s) do(s) assento(s) reservado(s) pelo o cliente para a sessao.
	 */
	public Ingresso(Usuario cliente, Sala local, Sessao sessao, float valorTotal, ArrayList<String> assentoReservado) {
		this.cliente = cliente;
		this.local = local;
		this.sessao = sessao;
		this.valorTotal = valorTotal;
		this.assentoReservado = assentoReservado;
	}
	
	// Getters
	/**
	 * Retorna o usuario que comprou o ingresso
	 */
	public Usuario getCliente() {
		return cliente;
	}
	
	/**
	 * Retorna a sala onde a sessao esta sendo exebida
	 */
	public Sala getLocal() {
		return local;
	}
	
	/**
	 * Retorna a sessao valida para o ingresso
	 */
	public Sessao getSessao() {
		return sessao;
	}
	
	/**
	 * Retorna o valor total do ingresso
	 */
	public String getValorTotal() {
		return NumberFormat.getCurrencyInstance().format(valorTotal);
	}
	
	/**
	 * Retorna o(s) Numero(s) do(a) assento(s) reservado(s) pelo o cliente para a sessao
	 */
	public ArrayList<String> getAssentoReservado() {
		return assentoReservado;
	}
	
	// Setters
	/**
	 * Configurar cliente.
	 * @param cliente Cliente que comprou o ingresso.
	 */
	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * Configurar local.
	 * @param local Sala onde a sessao esta ativa.
	 */
	public void setLocal(Sala local) {
		this.local = local;
	}
	
	/**
	 * Configurar sessao.
	 * @param sessao Sessao valida para o ingresso comprado
	 */
	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
	
	/**
	 * Configurar valor total.
	 * @param valorTotal Valor total a ser pago pelo o ingresso.
	 */
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	/**
	 * Configurar asssento(s) reservado(s)
	 * @param assentoReservado Lista contendo o(s) numero(s) do(s) asssento(s) reservado(s) escolhido(s) pelo o cliente.
	 */
	public void setAssentoReservado(ArrayList<String> assentoReservado) {
		this.assentoReservado = assentoReservado;
	}
}
