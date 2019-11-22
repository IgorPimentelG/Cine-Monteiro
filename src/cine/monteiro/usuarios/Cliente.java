package cine.monteiro.usuarios;

import java.util.Date;

public class Cliente extends Usuario {
	public Cliente(String nome, String CPF, String telefone, Date dataDeNascimento, String email, String senha) {
		super(nome, CPF, telefone, dataDeNascimento, email, senha);
	}

}
