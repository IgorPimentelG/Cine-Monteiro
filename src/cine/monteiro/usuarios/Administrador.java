package cine.monteiro.usuarios;

import java.util.Calendar;

public class Administrador extends Usuario {
	public Administrador(String nome, String CPF, String telefone, String dataDeNascimento, String email, String senha) {
		super(nome, CPF, telefone, dataDeNascimento, email, senha);
	}
}
