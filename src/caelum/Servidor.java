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
	private List<Socket> clientes;

	public Servidor (int porta) {
		this.porta = porta;
		this.clientes = new ArrayList<Socket>();
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
		this.clientes.add(cliente);
		if (this.clientes.size()==2){
			//Broadcast("Todos conectados, vamos jogar?");
			// cria tratador de cliente numa nova thread
			TrataCliente tc = new TrataCliente(this.clientes, this,i);
			new Thread(tc).start();
			i++;
		}
		else{
			ps.println("Ainda não temos clientes suficientes. Aguarde");
		}
	}
		
	}
	int soma=0, maior=0, cliente=0; int cont=0; int lock=1; int cont1=0,cont2=0;
	
	public void distribuiMensagem(String msg1, String msg2) {
		cont++;		
		
		if (!(msg1.equals("Fim da partida")) & !(msg2.equals("Fim da partida"))){
			if (Integer.valueOf(msg1)>Integer.valueOf(msg2)){
				maior=Integer.valueOf(msg1);
				cliente=1;
			}
			else if(Integer.valueOf(msg1)<Integer.valueOf(msg2)){
				maior=Integer.valueOf(msg2);
				cliente=2;
				
			}	
			System.out.println("\nMaior valor é: "+maior+" - Enviado pelo cliente "+cliente);
			maior=0;
			
		}else{
			System.out.println("O cliente  se desconectou");
			//this.clientes.remove(i);		
		}
	}
	/*public void pare(int i){
		lock=0;	
		for (Socket cli : this.clientes) {
			if (clientes.indexOf(cli)==i){
				clientes.get(i).getOutputStream().println("aguarde");
				clientes.get(i).println(1);
			}
			else
				clientes.get(i).println(0);
		}
	}
	public void Broadcast(String msg){
		for(PrintStream cli: this.clientes){
			cli.println(msg);
		}
	}*/
	public int getLock(){
		return lock;
	}
	public int getId(){
		return lock;
	}
	
}