package cine.monteiro.usuarios;

import java.util.Date;

public class Administrador extends Usuario {
	public Administrador(String nome, String CPF, String telefone, Date dataDeNascimento, String email, String senha) {
		super(nome, CPF, telefone, dataDeNascimento, email, senha);
	}
}
