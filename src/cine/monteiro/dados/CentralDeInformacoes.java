package cine.monteiro.dados;

// Bibliotecas
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
					throw new Exception("USUÁRIO JÁ CADASTRADO!");
				} else if(u.getEmail().equals(usuario.getEmail())) {
					throw new Exception("E-MAIL JÁ CADASTRADO!");
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
		throw new Exception("USUÁRIO NÃO CADASTRADO!");
	}
	
	public Usuario autenticarUsuario(String email, String senha) throws Exception {
		for(Usuario usuario : usuarios) {
			if(usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
				return usuario;
			}
		}
		throw new Exception("DADOS INVÁLIDOS!");
	}
	
	public boolean validarEmail(String email) {
		boolean emailValido = false;
		
		if(email != null && email.length() > 0) {
			String caracteresValidos = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
			Pattern pattern = Pattern.compile(caracteresValidos, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(email);
			
			if(matcher.matches()) {
				emailValido = true;
			}
		}
		return emailValido;
	}
	
	// Sala
	public void adicionarSala(Sala novaSala) throws Exception {
		for(Sala s : salas) {
			if(s.getNomeDaSala().equalsIgnoreCase(novaSala.getNomeDaSala()) || s.getID() == novaSala.getID()) {
				throw new Exception("JÁ UMA SALA COM O MESMO NOME CADASTRADO!");
			}
		}
		salas.add(novaSala);
	}
	
	public void excluirSala(long ID) throws Exception {
		for(Sala s : salas) {
			if(s.getID() == ID) {
				ArrayList<Sessao> sessoes = s.getSessoes();
				for(Sessao sessao : sessoes) {
					if(sessao.isAtiva()) {
						throw new Exception("A SALA POSSUI SESSÃO ATIVA!");
					}
				}
				salas.remove(s);
				break;
			}
		}
	}
	
	public ArrayList<Sala> getSalas() {
		return salas;
	}
	
	public Sala pesquisarSala(String nomeDaSala) {
		for(Sala salaCadastrada : salas) {
			if(salaCadastrada.getNomeDaSala().equalsIgnoreCase(nomeDaSala)) {
				return salaCadastrada;
			}
		}
		return null;
	}
	
	// Filme
	public void adicionarFilme(Filme filme) throws Exception{
		if(pesquisarFilme(filme.getNomeDoFilme()) == null) {
			filmes.add(filme);
		} else {
			throw new Exception("O FILME JÁ FOI CADASTRADO!");
		}
		
	}
		
	public ArrayList<Filme> getFilmes() {
		return filmes;
	}
	
	public Filme pesquisarFilme(String nomeDoFilme) {
		for(Filme filmeCadastrado : filmes) {
			if(filmeCadastrado.getNomeDoFilme().equalsIgnoreCase(nomeDoFilme)) {
				return filmeCadastrado;
			}
		}
		return null;
	}
		
	// Sessão
	public void adicionarSessao(Sessao novaSessao, Sala sala) throws Exception {
		if(salas.isEmpty()) {
			throw new Exception("NÃO EXISTE SALAS CADASTRADAS!");
		} else {
				ArrayList<Sessao> sessoesDaSala = sala.getSessoes();
					
				for(Sessao velhaSessao : sessoesDaSala) {
					if(!(novaSessao.getHoraDeInicio().isAfter(velhaSessao.getHoraDoTermino()) || novaSessao.getHoraDoTermino().isBefore(velhaSessao.getHoraDeInicio()))) {
						throw new Exception("J� existe uma sess�o cadastrada neste per�odo de exibi��o.");
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
	
	public Sessao pesquisarSessao(Sala local,long idDaSessao) {
		for(Sala salaCadastrada : salas) {
			if(salaCadastrada.getNomeDaSala().equals(local.getNomeDaSala())) {
				ArrayList<Sessao> sessoesCadastradas = salaCadastrada.getSessoes();
				for(Sessao sessaoCadastrada : sessoesCadastradas) {
					if(sessaoCadastrada.getID() == idDaSessao) {
						return sessaoCadastrada;
					}
				}
			}
		}
		return null;
	}
	
	// Setters & Getters
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