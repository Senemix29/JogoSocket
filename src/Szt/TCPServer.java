package Szt;
/**
 * Server.java													
*/

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class TCPServer {

   // para deixer mais claro, estamos nos "livrando" das exceções
   public static void main(String args[]) throws Exception {

     int port = Integer.parseInt(args[0]); // porta passada

     // criando o socket passivo, para receber conexções
     // e já coloca o mesmo para "listen" (poderia fazer isso explicitmente)
     ServerSocket ss; ss = new ServerSocket(port); List<Estudante> estu;List<ObjectOutputStream> saidas;

     System.out.println("Server is listening ...");

     // loop: aceita conexão...
     //       cria Worker pasasndo o socket ativo
     //       inicia a thread do Worker
     //       volta para aguardar nova conexão
     	int i =0;
     	estu= new ArrayList<Estudante>();
     	saidas = new ArrayList<ObjectOutputStream>();
     	
	    while (true) {
	    	 
	        Socket as = ss.accept(); // socket ativo é criado
	        i++;
	        
	        System.out.println("Nova conexão estabelecida ...");
	 	   	
	 	   	
				
			  Worker w = new Worker(as,i, estu/*, saidas*/);
			  w.start();
			  
			  System.out.println("Server aguardando nova conaxao ....");
			  System.out.println(estu.size());
		
	     }
		
   	}
}


