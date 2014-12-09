package tarefa1;

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
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InputStream c2;
		try {
			c2 = this.cliente.get(1).getInputStream();
			s2 = new Scanner(c2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		while (s1.hasNextInt() & s2.hasNextInt())
			try {
				if (s1.hasNextInt()==true & s2.hasNextInt()==false){
					PrintStream cli = new PrintStream(cliente.get(0).getOutputStream());
					cli.println("Perae");
				}
				if (s2.hasNextInt()==true & s1.hasNextInt()==false){
					PrintStream cli = new PrintStream(cliente.get(1).getOutputStream());
					cli.println("Perae");
				}
				
				servidor.distribuiMensagem(s1.nextLine(),s2.nextLine());
			} catch (IOException e) {
				e.printStackTrace();

			}
		
		s1.close(); s2.close();
	}
}