package Szt;
/**
 * Server.java													
*/

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;


public class TCPServer {

   // para deixer mais claro, estamos nos "livrando" das exce��es
   public static void main(String args[]) throws Exception {

     int port = Integer.parseInt(args[0]); // porta passada

     // criando o socket passivo, para receber conex��es
     // e j� coloca o mesmo para "listen" (poderia fazer isso explicitmente)
     ServerSocket ss; ss = new ServerSocket(port); List<Estudante> estu;List<ObjectOutputStream> saidas;

     System.out.println("Server is listening ...");

     // loop: aceita conex�o...
     //       cria Worker pasasndo o socket ativo
     //       inicia a thread do Worker
     //       volta para aguardar nova conex�o
     	int i =0;
     	estu= new ArrayList<Estudante>();
     	saidas = new ArrayList<ObjectOutputStream>();
     	
	    while (true) {
	    	 
	        Socket as = ss.accept(); // socket ativo � criado
	        i++;
	        
	        System.out.println("Nova conex�o estabelecida ...");
	 	   	
	 	   	
				
			  Worker w = new Worker(as,i, estu/*, saidas*/);
			  w.start();
			  
			  System.out.println("Server aguardando nova conaxao ....");
			  System.out.println(estu.size());
		
	     }
		
   	}
}


