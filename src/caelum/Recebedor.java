package caelum;

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
			System.out.println(s.nextLine());
		}
		
	 }
 }