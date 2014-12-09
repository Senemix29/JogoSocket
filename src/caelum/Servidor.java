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
		try{
			int n = Integer.parseInt(args[0]);
			new Servidor(n).executa();	
			}catch(Exception e){
				System.out.println("Digite apenas numeros no parametro da porta. Execute o código do Servidor novamente.");
			}
		
	}

	private int porta;
	private int confirma=1;
	private List<Socket> clientes;
	private List<PrintStream> SaidaClientes;
	ServerSocket servidor;
	
	public Servidor (int porta) {
		this.porta = porta;
		this.clientes = new ArrayList<Socket>();
	}

	public void executa () throws IOException {
		
		try {
			servidor = new ServerSocket(this.porta);
		}catch(Exception e){
			confirma=0;
		}
		
		if (confirma==0) {
			System.out.println("Porta ocupada!");
		}
		else {
			
			System.out.println("A Porta acaba de ser aberta! \nAguardando a conexão dos clientes. ");
			int i=0;
			int cont=0;
			
			while (true) {
				
				// aceita um cliente
				Socket cliente = servidor.accept();
				System.out.println("Nova conexao com o cliente " +cliente.getInetAddress().getHostAddress());
				
				// adiciona o socket do cliente a lista
				PrintStream ps = new PrintStream(cliente.getOutputStream());
				this.clientes.add(cliente);
				
				if (this.clientes.size()==2){
					
					// cria tratador de jogo com 2 clientes numa nova thread
					TrataCliente tc = new TrataCliente(this.clientes, this,i);
					Broadcast("Todos prontos. Vamos jogar!");
					Broadcast("Digite um numero:");
					new Thread(tc).start();
					
					
					i++;
				}
				else{
					ps.println("Ainda não temos clientes suficientes. Aguarde");
					
				}
				
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
		}
		else if (Integer.valueOf(msg1)>Integer.valueOf(msg2)){
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
				Broadcast("O cliente 1 venceu");
				Broadcast("FIM");
				//clientes.get(0).close();
				//clientes.get(1).close();
				
			}
			else{
				System.out.println("O cliente 1 venceu");
				Broadcast("O cliente 1 venceu");
				Broadcast("FIM");
				//clientes.get(0).close();
				//clientes.get(1).close();
			}
		}
	}
	
	public void distribuiMensagemPc(String msg1, String msg2) throws IOException {
		PrintStream cli = new PrintStream(clientes.get(0).getOutputStream());
		PrintStream cli2 = new PrintStream(clientes.get(1).getOutputStream());
		
		if ((msg1.equals(null) | msg1.trim().equals("")) & (msg2.equals(null) | msg2.trim().equals(""))){
			
			cli.println("Mensagem nula");
			cli2.println("Mensagem nula");
		}
		else if (Integer.valueOf(msg1)>Integer.valueOf(msg2)){
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
				System.out.println("O computador venceu");
				cli.println("O computador venceu");
			}
		}
	}
		
		
	public void Broadcast(String msg) throws IOException{
		for(Socket cli: this.clientes){
			PrintStream clis = new PrintStream(cli.getOutputStream());
			clis.println(msg);
		}
	}
	
}