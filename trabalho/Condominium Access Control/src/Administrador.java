public class Administrador extends Pessoa {
  public Administrador(String nome, String telefone, String cpf) {
    super(nome, telefone, cpf);
  }

  @Override
  byte nivelDeAcesso() {
    return 2;
  }
}
