package caelum;

import java.io.InputStream;
import java.util.Scanner;

public class TrataCliente implements Runnable {

	private InputStream cliente;
	private Servidor servidor;
	private int id;

	public TrataCliente(InputStream cliente, Servidor servidor, int i) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.id=i;
	}

	
	public void run() {
		// quando chegar uma msg, distribui pra todos
		Scanner s = new Scanner(this.cliente);
		while (s.hasNextLine()) {
			servidor.distribuiMensagem(s.nextLine(), id);
		}
		s.close();
	}
}