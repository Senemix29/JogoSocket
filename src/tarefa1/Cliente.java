package tarefa1;

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
	try{
		String ip = String.valueOf(args[0]);
		int porta = Integer.parseInt(args[1]);
		new Cliente(ip,porta).executa();	
		}catch(Exception e){
			System.out.println("Erro nos parametros. Lembre-se, o primeiro parametro é o " +
					"Ip/host do servidor e o segundo é o numero da porta. Verifique se possui os dados corretos e Execute o cliente novamente");
		}
}

	private String host,ParImpar,msg="";
	private int porta;
	private int lock;
	private Socket cliSock;
	Socket cliente;
	Scanner teclado;
	PrintStream saida;
	
	public Cliente (String host, int porta) {
		 this.host = host;
		 this.porta = porta;
		 int i=0;
	}
	
	public void executa() throws UnknownHostException, IOException, ClassNotFoundException {
		cliente = new Socket(this.host, this.porta);
		cliSock = cliente;
		System.out.println("O cliente se conectou ao servidor!");
		
		Recebedor r = new Recebedor(cliente.getInputStream(),this);
		new Thread(r).start();
	
		// le msgs do teclado e manda pro servidor
		teclado = new Scanner(System.in);
		saida = new PrintStream(cliente.getOutputStream());
		
		int cont=0;
		
			while (teclado.hasNextLine() & cont<3) {
				try{
				int n = Integer.parseInt(teclado.nextLine());
				saida.println(n);
				cont++;
				}catch(Exception e){
					System.out.println("Digite apenas numeros");
				}	
					
			}
		
		System.out.println("Conexão iterrompida. Obrigado por jogar !");
		saida.close();
		teclado.close();
		cliente.close();
		
	}
	
	public void fechaSocket() throws IOException{
		saida.close();
		teclado.close();
		cliente.close();	
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