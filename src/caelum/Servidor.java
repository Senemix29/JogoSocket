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
	private List<PrintStream> SaidaClientes;
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
		//this.SaidaClientes.add(ps);
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
	
	public void distribuiMensagem(String msg1, String msg2) throws IOException {
		PrintStream cli = new PrintStream(clientes.get(0).getOutputStream());
		PrintStream cli2 = new PrintStream(clientes.get(1).getOutputStream());
		
		if ((msg1.equals(null) | msg1.trim().equals("")) & (msg2.equals(null) | msg2.trim().equals(""))){
			
			cli.println("Mensagem nula");
			cli2.println("Mensagem nula");
		}else		
		//if (!(msg1.equals("Fim da partida")) & !(msg2.equals("Fim da partida"))){
		if (Integer.valueOf(msg1)>Integer.valueOf(msg2)){
			maior=Integer.valueOf(msg1);
			cliente=1;
			cont++;
			cont1++;
		}
		else if(Integer.valueOf(msg1)<Integer.valueOf(msg2)){
			maior=Integer.valueOf(msg2);
			cliente=2;
			cont++;
			cont2++;
			
		}	
		System.out.println("\nMaior valor é: "+maior+" - Enviado pelo cliente "+cliente);
		maior=0;
		if (cont==3){
			if (cont1>cont2){
				System.out.println("O cliente 1 venceu");
				cli.println("O cliente 1 venceu");
				cli2.println("O cliente 1 venceu");
				clientes.get(0).close();
			}
			else{
				System.out.println("O cliente 2 venceu");
				cli.println("O cliente 2 venceu");
				cli2.println("O cliente 2 venceu");
				clientes.get(1).close();
			}
		}
	}
		
		/*}
		else{
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
	public void privateMSG() throws IOException{
		PrintStream cli = new PrintStream(clientes.get(0).getOutputStream());
	}
	public int getId(){
		return lock;
	}
	
}