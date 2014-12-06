package Szt;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class TCPClient
{
   // observe que estamos nos "livrando" do tratamento das
   // excecoes...
   
   public static void main(String args[]) throws Exception{
      String host = args[0];
      int port = Integer.parseInt(args[1]);
      
      //int intParm = Integer.parseInt(args[2]);
     // String stringParm = args[3];
      String nomeEstudante = args[2];
      String matriEstudante = args[3];
      
      // verificar se apenas passando a String do host o
      // socket consegue resolver o nome o se precisamos de
      // InetAddress IPAddress = InetAddress.getByName(args[0]);
      // e passar o objeto da classe InetAddress... 
      Socket sock = new Socket(host,port); // cria socket

      // pega os fluxos out e in do socket TCP e encapusla em
      // ... fluxos de objeto
      ObjectOutputStream out = new ObjectOutputStream (sock.getOutputStream()); 
      ObjectInputStream in = new ObjectInputStream(sock.getInputStream());

      // cria a estrutura de dados a ser enviada
      //Struct str = new Struct (intParm, stringParm);
      Estudante est = new Estudante(nomeEstudante, matriEstudante);
      

      //out.writeObject (str); // envia objeto para o servidor (Worker)
      
      out.writeObject (est);
      
      String resp = (String)in.readObject(); // recebe objeto de resposta

      System.out.println ("Resposta = " + resp);
      
      ArrayList<Estudante> aluno;
      aluno = new ArrayList<Estudante>();
      aluno = (ArrayList<Estudante>) in.readObject();
      
      int i; 
      Object num; 
      num = (Object) in.readObject();
      
      i=Integer.valueOf(num.toString());
     
      if (aluno.size() % 2==0 ){
    	  for (Estudante estud : aluno) {
         	 System.out.println(estud.toString());
          }
      }
      //System.out.println(aluno.size());


      sock.close(); // fecha socket
   }
}
