package caelum;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.List;

public class TrataCliente implements Runnable {

	private List<Socket> cliente;
	private Servidor servidor;
	private int id;
	private int auxid;
	

	public TrataCliente(List<Socket> cliente, Servidor servidor, int i) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.id=i;
	}

	
	public void run() {
		// quando chegar uma msg, distribui pra todos
		InputStream c1; Scanner s1 = null,s2 = null;
		
		try {
			c1 = this.cliente.get(0).getInputStream();
			s1 = new Scanner(c1);
			while (s1.hasNextLine()) 
				servidor.distribuiMensagem(s1.nextLine(), 0);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InputStream c2;
		try {
			c2 = this.cliente.get(1).getInputStream();
			s2 = new Scanner(c2);
			while (s2.hasNextLine()) 
				servidor.distribuiMensagem(s2.nextLine(), 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		
		s1.close(); s2.close();
	}
}