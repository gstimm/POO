public class RegistroDeVisitante {
  private Pessoa quemAutorizou;
  private Pessoa visitante;
  private Unidade unidade;
  private Data dataDeEntrada;
  private Data dataDeSaida;

  public RegistroDeVisitante(Pessoa quemAutorizou, Pessoa visitante, Unidade unidade, Data dataDeEntrada,
      Data dataDeSaida) {
    this.quemAutorizou = quemAutorizou;
    this.visitante = visitante;
    this.unidade = unidade;
    this.dataDeEntrada = dataDeEntrada;
    this.dataDeSaida = dataDeSaida;
  }

  public String toString() {
    return ""
        + unidade.getNumeroDaUnidade() + " "
        + quemAutorizou.getNome() + " "
        + quemAutorizou.getCpf() + " "
        + visitante.getNome() + " "
        + visitante.getCpf() + " "
        + dataDeEntrada.toString() + " "
        + dataDeSaida.toString();
  }
}