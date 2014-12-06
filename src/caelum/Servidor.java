package caelum;



import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Servidor {

	public static void main(String[] args) throws IOException {
		// inicia o servidor
		new Servidor(12345).executa();
		
	}

	private int porta;
	private List<PrintStream> clientes;
	

	public Servidor (int porta) {
		this.porta = porta;
		this.clientes = new ArrayList<PrintStream>();
	}

	public void executa () throws IOException {
	ServerSocket servidor = new ServerSocket(this.porta);
	System.out.println("Porta 12345 aberta!");
	int i=0;
	int cont=0;
	while (true) {
		
		// aceita um cliente
		Socket cliente = servidor.accept();
		System.out.println("Nova conexao com o cliente " +
		cliente.getInetAddress().getHostAddress());
		// adiciona saida do cliente a lista
		PrintStream ps = new PrintStream(cliente.getOutputStream());
		this.clientes.add(ps);

		// cria tratador de cliente numa nova thread
		TrataCliente tc =
		new TrataCliente(cliente.getInputStream(), this,i);
		new Thread(tc).start();
		i++;
	}
			
		

	}
	int soma=0, maior=0, cliente=0;
	public void distribuiMensagem(String msg, int i) {
		
		// envia msg para todo mundo
		for (PrintStream cliente : this.clientes) {
			if ( clientes.indexOf(cliente) != i ){
				cliente.println("Cliente "+i +": " + msg);
				
			}
			
		}
		if (!(msg.equals("Se desconectou"))){
			if (Integer.valueOf(msg)>maior){
				maior=Integer.valueOf(msg);
				cliente=i;
			}
			System.out.println("\nMaior valor até o momento: "+maior+" - Enviado pelo cliente "+cliente);
			//soma+= Integer.valueOf(msg);
			//System.out.println("\nCom o valor enviado pelo Cliente: "+i+ " - soma = "+soma+"\n");
			//clientes.get(i).println("valor da soma até o momento " + soma);
		}else{
			System.out.println("O cliente "+i+" se desconectou");
			this.clientes.remove(i);
			
		}
	}
}