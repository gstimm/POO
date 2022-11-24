public class Visitante extends Pessoa {
  public Visitante(String nome, String telefone, String cpf) {
    super(nome, telefone, cpf);
  }

  @Override
  byte nivelDeAcesso() {
    return 0;
  }
}
