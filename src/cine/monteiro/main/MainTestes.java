package cine.monteiro.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import cine.monteiro.dados.CentralDeInformacoes;
import cine.monteiro.dados.Persistencia;
import cine.monteiro.email.Boleto;
import cine.monteiro.gerenciamento.Ingresso;
import cine.monteiro.gerenciamento.Sala;
import cine.monteiro.screens.*;
import cine.monteiro.screens.administrador.*;
import cine.monteiro.screens.cliente.WindowsComprarIngresso;
import cine.monteiro.screens.cliente.WindowsHomeCliente;
import cine.monteiro.usuarios.Cliente;

public class MainTestes {

	public static void main(String[] args) throws InterruptedException {
		//WindowsSplash ws = new WindowsSplash();
		//Thread.sleep(3000);
		//ws.dispose();
		//new WindowsLogin();
		//new WindowsCadastrarSala();
		//new WindowsSobre();
		//new WindowsRecuperarSenha();
		//new WindowsPainelDeControle();
		//new WindowsFilmes();
		//new WindowsCadastro();
		//new WindowsFilmes();
		//new WindowsMarketing();
		//new WindowsSessao();
		//new WindowsExcluirSala();
		//new WindowsCadastrarFilme();
		//new WindowsCadastrarSessao();
		//new WindowsInterromperSessao();
		//new WindowsListarFilmes();
		//new WindowsListarSalas();
		//new WindowsListarSessao();
		//new WindowsDefinirNovaSenha();
		//new WindowsComprarIngresso();
		Persistencia persistencia = new Persistencia();
		CentralDeInformacoes centralDeInformacoes = persistencia.recuperarCentralDeInformacoes();
		new WindowsHomeCliente(centralDeInformacoes.getUsuarios().get(1));
		
		
	}
}
