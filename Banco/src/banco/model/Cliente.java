package banco.model;

public class Cliente {
  private String nome;
  private String cpf;
  private String telefone;
  
  //construtor
  public Cliente(String nome, String cpf, String telefone) {
	this.nome = nome;
	this.cpf = cpf;
	this.telefone = telefone;
  }

 //getters e setters 
  public String getNome() {
	return nome;
  }


  public void setNome(String nome) {
	this.nome = nome;
  }


  public String getCpf() {
	return cpf;
  }


  public void setCpf(String cpf) {
	this.cpf = cpf;
  }


  public String getTelefone() {
	return telefone;
  }


  public void setTelefone(String telefone) {
	this.telefone = telefone;
  }
  // Representação textual do cliente

  public String toString() {
      return "Cliente: " + nome + " | CPF: " + cpf + " | Telefone: " + telefone;
  }
}
  
