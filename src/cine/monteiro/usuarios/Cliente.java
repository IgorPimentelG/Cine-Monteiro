package cine.monteiro.usuarios;

import java.util.Calendar;

public class Cliente extends Usuario {
	public Cliente(String nome, String CPF, String telefone, String dataDeNascimento, String email, String senha) {
		super(nome, CPF, telefone, dataDeNascimento, email, senha);
	}

}
