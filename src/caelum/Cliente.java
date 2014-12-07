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

	private String host, ParImpar,msg="";
	private int porta;
	private int lock;
	private Socket cliSock;
	
	public Cliente (String host, int porta) {
		 this.host = host;
		 this.porta = porta;
		 int i=0;
	}
	
	public void executa() throws UnknownHostException, IOException, ClassNotFoundException {
		Socket cliente = new Socket(this.host, this.porta);
		cliSock = cliente;
		System.out.println("O cliente se conectou ao servidor!");
		
		Recebedor r = new Recebedor(cliente.getInputStream(),this);
		new Thread(r).start();
	
		// le msgs do teclado e manda pro servidor
		Scanner teclado = new Scanner(System.in);
		PrintStream saida = new PrintStream(cliente.getOutputStream());
		int cont=0;
		try{
			while (teclado.hasNextLine()) {
				saida.println(teclado.nextLine());
				cont++;
				
				if (cont==4){
					teclado.close();
				}
				
			}
		}catch(Exception e){
			saida.println("Fim da partida");
		}
		
		
		saida.close();
		teclado.close();
		cliente.close();
		
	}
	
	public Socket getSock(){
		return cliSock;
	}
	public void setMSG(String sms){
		this.msg=sms;
	}
	public void setLock(int l){
		lock=l;
	}
	public void setParImpar(String opcao){
		this.ParImpar=opcao;
	}
}