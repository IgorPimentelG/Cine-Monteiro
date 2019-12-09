package cine.monteiro.dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

import javax.swing.JOptionPane;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class Persistencia {
	private XStream xStream = new XStream(new DomDriver("utf-8"));
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
			JOptionPane.showMessageDialog(null, "ERRO AO SALVAR CENTRAL DE INFORMAÇÕES!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public CentralDeInformacoes recuperarCentralDeInformacoes() {
		try {
			if(arquivo.exists()) {
				FileInputStream file = new FileInputStream(arquivo);
				return (CentralDeInformacoes) xStream.fromXML(file);
			}
		} catch(FileNotFoundException erro) {
			JOptionPane.showMessageDialog(null, "ERRO AO RECUPERAR CENTRAL DE INFORMAÇÕES!", "ATENÇÃO", JOptionPane.ERROR_MESSAGE);
		}
		return new CentralDeInformacoes();
	}
}
