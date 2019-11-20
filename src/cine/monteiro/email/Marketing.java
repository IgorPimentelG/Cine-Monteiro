package cine.monteiro.email;

import java.util.ArrayList;

import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.usuarios.Usuario;

public class Marketing extends Email {
	public void gerarProgramacao() {
		
	}
	
	public void enviarProgramacao() {
		Persistencia bancoDeDados = new Persistencia();
		CentralDeInformacoes cpd = bancoDeDados.recuperarCentralDeInformacoes();
		
		ArrayList<Usuario> usuarios = cpd.getUsuarios();
		
		ArrayList<String> emailsDosClientes = new ArrayList<String>;
	}
}
