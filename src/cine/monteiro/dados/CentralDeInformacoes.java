package cine.monteiro.dados;

// Bibliotecas
import java.util.ArrayList;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

// Pacotes
import cine.monteiro.usuarios.*;
import cine.monteiro.gerenciamento.*;

public class CentralDeInformacoes {
	// Atributos
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<Sala> salas = new ArrayList<Sala>();
	private ArrayList<Filme> filmes = new ArrayList<Filme>();
	
	private String emailSalvo = "";
	private boolean statusCheckBox = false;
	
	// Usuário
	public void adicionarUsuario(Usuario usuario) throws Exception {
		if(!usuarios.isEmpty()) {
			for(Usuario u : usuarios) {
				if(u.getCPF().equals(usuario.getCPF())) {
					throw new Exception("Usuário já cadastrado.");
				} else if(u.getEmail().equals(usuario.getEmail())) {
					throw new Exception("E-mail já cadastrado.");
				} 
			}
		}
		usuarios.add(usuario);
	}
	
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public Usuario autenticarEmailDoUsuario(String email) throws Exception {
		for(Usuario usuario : usuarios) {
			if(usuario.getEmail().equals(email)) {
				return usuario;
			}
		}
		throw new Exception("Usuário não cadastrado.");
	}
	
	public Usuario autenticarUsuario(String email, String senha) throws Exception {
		for(Usuario usuario : usuarios) {
			if(usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
				return usuario;
			}
		}
		throw new Exception("Dados Inválidos!");
	}
	
	public boolean validarEmail(String email) {
		 boolean valido = true;
		 
		    try {
		        InternetAddress enderecoEmail = new InternetAddress(email);
		        enderecoEmail.validate();
		    } catch (AddressException ex) {
		        valido = false;
		    }
		    return valido;
	}
	
	// Sala
	public void adicionarSala(Sala novaSala) throws Exception {
		for(Sala s : salas) {
			if(s.getNomeDaSala().equalsIgnoreCase(novaSala.getNomeDaSala())) {
				throw new Exception("Já existe uma sala com o mesmo nome cadastrado!");
			}
		}
		salas.add(novaSala);
	}
	
	public void excluirSala(long ID)  {
		for(Sala s : salas) {
			if(s.getID() == ID) {
				salas.remove(s);
				break;
			}
		}
	}
	
	public ArrayList<Sala> getSalas() {
		return salas;
	}
	
	public Sala pesquisarSala(String nomeDaSala) {
		for(Sala sala : salas) {
			if(sala.getNomeDaSala().equalsIgnoreCase(nomeDaSala)) {
				return sala;
			}
		}
		return null;
	}
	
	// Filme
	public void adicionarFilme(Filme filme) throws Exception{
		for(Filme f : filmes) {
			if(f != null && f.getNomeDoFilme().equalsIgnoreCase(filme.getNomeDoFilme())) {
				throw new Exception("O filme já foi cadastrado");
			}
		}
		filmes.add(filme);
	}
		
	public ArrayList<Filme> getFilmes() {
		return filmes;
	}
	
	public Filme pesquisarFilme(String nomeDoFilme) {
		for(Filme filme : filmes) {
			if(filme.getNomeDoFilme().equalsIgnoreCase(nomeDoFilme)) {
				return filme;
			}
		}
		return null;
	}
	
	// Sessão
	public void adicionarSessao(Sessao novaSessao, Sala sala) throws Exception {
		if(salas.isEmpty()) {
			throw new Exception("Não existe salas cadastradas!");
		} else {
				ArrayList<Sessao> sessoesDaSala = sala.getSessoes();
					
				for(Sessao velhaSessao : sessoesDaSala) {
					if(!(novaSessao.getHoraDeInicio().isAfter(velhaSessao.getHoraDoTermino()) || novaSessao.getHoraDoTermino().isBefore(velhaSessao.getHoraDeInicio()))) {
						throw new Exception("Já existe uma sessão cadastrada neste período de exibição.");
					}
				}
				sala.addSessao(novaSessao);
			}
	}
	
	public void interromperSessao(long ID) throws Exception {
		for(int i = 0; i < salas.size(); i++) {
			ArrayList<Sessao> sessoes = salas.get(i).getSessoes();
			
			for(Sessao sessao : sessoes) {
				if(sessao.getID() == ID) {
					sessao.setAtiva(false);
				}
			}
		}
	}
	
	public void setEmailSalvo(String email) {
		this.emailSalvo = email;
	}
	
	public String getEmailSalvo() {
		return emailSalvo;
	}
	
	public void setStatusCheckBox(boolean status) {
		this.statusCheckBox = status;
	}
	
	public boolean getStatusCheckBox() {
		return statusCheckBox;
	}
}