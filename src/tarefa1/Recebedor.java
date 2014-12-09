package tarefa1;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Recebedor implements Runnable {
private InputStream servidor;
private Cliente cliente;
	public Recebedor(InputStream servidor, Cliente cli) {
		this.servidor = servidor;
		this.cliente = cli;
	}
	public void run() {
		// recebe msgs do servidor e imprime na tela
		Scanner s = new Scanner(this.servidor);
		while (s.hasNextLine()) {
		/*	if(s.nextLine().equals("FIM")){
				try {
					System.out.println("Fim de jogo");
					//cliente.fechaSocket();				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{*/
				System.out.println(s.nextLine());
			//}
		}
		
	 }
 }