package Szt;

public class Estudante implements java.io.Serializable {
	private String nome,matricula;
	
	Estudante (String nome, String matricula){
		this.nome=nome;
		this.matricula=matricula;
	}
	
	public String toString(){
		return "nome do estudante: " + nome +" matricula:"+ matricula;
	}
}
