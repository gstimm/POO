abstract class Pessoa {
  private String nome, telefone, cpf;

  public Pessoa(String nome, String telefone, String cpf) {
    this.nome = nome;
    this.telefone = telefone;
    this.cpf = cpf;
  }

  abstract byte nivelDeAcesso();

  public String getNome() {
    return nome;
  }

  public String getTelefone() {
    return telefone;
  }

  public String getCpf() {
    return cpf;
  }

  public String toString() {
    return "Nome: " + nome + ", Telefone: " + telefone + ", CPF: " + cpf;
  }
}
