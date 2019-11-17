package cine.monteiro.dados;

// Bibliotecas
import java.util.ArrayList;

// Pacotes
import cine.monteiro.usuarios.*;
import cine.monteiro.gerenciamento.*;

public class CentralDeInformacoes {
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	private ArrayList<Sala> salas = new ArrayList<Sala>();
	private ArrayList<Filme> filmes = new ArrayList<Filme>();
	
	// Usu√°rio
	public void adicionarUsuario(Usuario usuario) throws Exception {
		if(!usuarios.isEmpty()) {
			for(Usuario u : usuarios) {
				if(u.getCPF().equals(usuario.getCPF())) {
					throw new Exception("Usu·rio j· cadastrado.");
				} else if(u.getEmail().equals(usuario.getEmail())) {
					throw new Exception("E-mail j· cadastrado.");
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
		throw new Exception("Usu·rio n„o cadastrado.");
	}
	
	public Usuario autenticarUsuario(String email, String senha) throws Exception {
		for(Usuario usuario : usuarios) {
			if(usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
				return usuario;
			}
		}
		throw new Exception("Usu·rio n„o cadastrado.");
	}

	// Sala
	public void adicionarSala(Sala sala) throws Exception {
		if(salas.isEmpty()) {
			salas.add(sala);
		} else {
			for(Sala s : salas) {
				if(s.getID() == sala.getID() || s.getNomeDaSala().equalsIgnoreCase(sala.getNomeDaSala())) {
					throw new Exception("Sala j√° cadastrada!");
				}
			}
			salas.add(sala);
		}
	}
	
	public void excluirSala(long ID) throws Exception {
		if(salas.isEmpty()) {
			throw new Exception("N√£o existe salas cadastradas!");
		} else {
			for(Sala s : salas) {
				if(s.getID() == ID) {
					salas.remove(s);
					return;
				}
			}
			throw new Exception("Sala n√£o existe!");
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
		if(filmes.isEmpty()) {
			filmes.add(filme);
		} else {
			for(Filme f : filmes) {
				if(f.getNomeDoFilme().equalsIgnoreCase(filme.getNomeDoFilme())) {
					throw new Exception("O filme j√° foi cadastrado");
				}
			}
			filmes.add(filme);
		}
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
	
	// Sess√£o
	public void adicionarSessao(Sessao novaSessao, Sala sala) throws Exception {
		if(salas.isEmpty()) {
			throw new Exception("N√£o existe salas cadastradas!");
		} else {
				ArrayList<Sessao> sessoesDaSala = sala.getSessoes();
					
				for(Sessao velhaSessao : sessoesDaSala) {
					if(!(novaSessao.getHoraDeInicio().isAfter(velhaSessao.getHoraDoTermino()) || novaSessao.getHoraDoTermino().isBefore(velhaSessao.getHoraDeInicio()))) {
						throw new Exception("J√° existe uma sess√£o cadastrada neste per√≠odo de exibi√ß√£o.");
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
}