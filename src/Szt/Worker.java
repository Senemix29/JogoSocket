package Szt;
/**
 * Worker.java - cada conexão vai ser trabata por um worker
*/

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Worker extends Thread
{
   private Socket sock; private int i; private List<Estudante> estu; private List<ObjectOutputStream> saida;

   public Worker(Socket s, int n, List<Estudante> Aluno /* , List <ObjectOutputStream> out */) { // recebe o socket ativo no construtor
	   i = n;
	   sock = s;
	   estu= Aluno;
	   //saida = out;
   }

   public void run() {
      
      System.out.println("Worker - nova thread iniciando ...");

      try {

         // obtem os fluxos que fazem parte do Socket TCP
         // e "encapsula" os mesmos em fluxos de objeto
         ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
         ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());

         // Agora é fácil: le o objeto, calsse Object, do fluxo de entrada 
         // ... e faz o casting para a classe Struct. Depois exibe o conteúdo.
         // esse é o "processamento da requisicao"
         //Struct str = (Struct) in.readObject ();
         Estudante est = (Estudante) in.readObject();
         estu.add(est);
         //saida.add(out);
         
         System.out.println ("Recebemos uma mensagem do cliente " + i);
         
        /* for (Estudante estud : this.estu) {
        	 System.out.println(estud.toString());
         }
         System.out.println ("\n"); */
         //System.out.println (est);

         // Agora, a resposta. Monta uma string e simplesmente joga o objeto
         // no fluxo de saída de objetos ... vai direto para o "outro lado"
         // ... ou seja, para o cliente, que está conectado
         out.writeObject("Recebido Ok!" + new java.util.Date().toString());
         out.writeObject(estu);
         out.writeObject(i);

         // fecha o socket e acabou o serviço
         // se precisar de muitas interações, tem que fazer um loop...
         sock.close();
      }
      catch (Exception e) {
         System.out.println(e);
      }
   }
}

