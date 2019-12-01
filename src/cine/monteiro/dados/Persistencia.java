package cine.monteiro.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class Persistencia {
	private XStream xStream = new XStream(new DomDriver("UTF-8"));
	private File arquivo = new File("dados.xml");
	
	OutputStream outputStream = null;
	Writer writer = null;

	public void salvarCentralDeInformacoes(CentralDeInformacoes cpd) {
		try {			
			String dados = xStream.toXML(cpd);
			arquivo.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivo);
			gravar.print(dados);
			gravar.close();
		} catch(IOException erro) {
			erro.printStackTrace();
		}
	}
	
	public CentralDeInformacoes recuperarCentralDeInformacoes() {
		try {
			if(arquivo.exists()) {
				FileInputStream file = new FileInputStream(arquivo);
				return (CentralDeInformacoes) xStream.fromXML(file);
			}
		} catch(FileNotFoundException erro) {
			erro.printStackTrace();
		}
		return new CentralDeInformacoes();
	}
}
