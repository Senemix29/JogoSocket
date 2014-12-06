package caelum;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
	 // dispara cliente
	 new Cliente("127.0.0.1", 12345).executa();
	}

	private String host, ParImpar;
	private int porta;
	private int dedos;
	
	public Cliente (String host, int porta) {
		 this.host = host;
		 this.porta = porta;
		 
		 int i=0;
	}
	
	public void executa() throws UnknownHostException, IOException, ClassNotFoundException {
		Socket cliente = new Socket(this.host, this.porta);
		
		System.out.println("O cliente se conectou ao servidor!");
		
		Recebedor r = new Recebedor(cliente.getInputStream());
		new Thread(r).start();
	
		// le msgs do teclado e manda pro servidor
		Scanner teclado = new Scanner(System.in);
		PrintStream saida = new PrintStream(cliente.getOutputStream());
		int cont=0;
		try{
			while (teclado.hasNextLine()) {
				saida.println(teclado.nextLine());
				cont++;
				if (cont==2){
					teclado.close();
				}
			}
		}catch(Exception e){
			saida.println("Se desconectou");
		}
		
		saida.close();
		teclado.close();
		cliente.close();
		
	}
	
	public int getDedos(){
		return getDedos();
	}
	public String getParImpar(){
		return ParImpar;
	}
	public void setDedos(int numDedo){
		this.dedos=numDedo;
	}
	public void setParImpar(String opcao){
		this.ParImpar=opcao;
	}
}