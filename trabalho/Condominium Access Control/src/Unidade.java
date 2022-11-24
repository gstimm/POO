import java.util.ArrayList;

public class Unidade {
  private short numeroDaUnidade;
  private ArrayList<Condomino> moradores;

  public Unidade(short numeroDaUnidade) {
    this.numeroDaUnidade = numeroDaUnidade;
    this.moradores = new ArrayList<Condomino>();
  }

  public boolean incluirMorador(Condomino morador) {
    return moradores.add(morador);
  }

  public Pessoa excluirMorador(String cpf) {
    Pessoa pessoaRemovida = null;
    for (int i = 0; i < moradores.size(); i++) {
      if (moradores.get(i).getCpf().equals(cpf)) {
        pessoaRemovida = moradores.get(i);
        moradores.remove(i);
      }
    }
    return pessoaRemovida;
  }

  public short getNumeroDaUnidade() {
    return numeroDaUnidade;
  }

  public ArrayList<Condomino> getMoradores() {
    return moradores;
  }

  public String toString() {
    return "Unidade " + this.numeroDaUnidade + "\n" + moradores.toString();
  }
}
