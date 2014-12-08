package caelum;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class TrataClientexMaquina implements Runnable {

	private Socket cliente;
	private Servidor servidor;
	private int id;
	private int auxid;
	

	public TrataClientexMaquina(Socket cliente, Servidor servidor, int i) {
		this.cliente = cliente;
		this.servidor = servidor;
		this.id=i;
	}

	
	public void run() {
		// quando chegar uma msg, distribui pra todos
		InputStream c1; Scanner s1 = null,s2 = null;
		
		try {
			c1 = this.cliente.getInputStream();
			s1 = new Scanner(c1);
			//while (s1.hasNextLine()) 
				//servidor.distribuiMensagem(s1.nextLine(), 1);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		Random r = new Random();  
		int i = r.nextInt(100); 
		
		while (s1.hasNextInt())
			try {
				if (s1.hasNextInt()==false){
					PrintStream cli = new PrintStream(cliente.getOutputStream());
					cli.println("Perae");
				}
				servidor.distribuiMensagemPc(s1.nextLine(), String.valueOf(i));
			} catch (IOException e) {
				e.printStackTrace();

			}
		
		s1.close(); s2.close();
	}
}