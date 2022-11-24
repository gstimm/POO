import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Condominio {
  private String nome;
  private Pessoa administrador;
  private ArrayList<Unidade> unidades;
  private ArrayList<Visitante> visitantes;
  private ArrayList<RegistroDeVisitante> registroDeVisitantes;

  public Condominio(String nome, Pessoa administrador) {
    this.nome = nome;
    this.administrador = administrador;
    this.unidades = new ArrayList<Unidade>();
    this.visitantes = new ArrayList<Visitante>();
    this.registroDeVisitantes = new ArrayList<RegistroDeVisitante>();
  }

  public Administrador getAdministrador() {
    return (Administrador) administrador;
  }

  public boolean incluirUnidade(Unidade unidade) {
    return unidades.add(unidade);
  }

  public Unidade excluirUnidade(short numeroDaUnidade) {
    Unidade unidadeRemovida = null;
    for (int i = 0; i < unidades.size(); i++) {
      if (unidades.get(i).getNumeroDaUnidade() == numeroDaUnidade) {
        unidadeRemovida = unidades.get(i);
        unidades.remove(i);
      }
    }
    return unidadeRemovida;
  }

  public ArrayList<Unidade> getListaDeUnidades() {
    return unidades;
  }

  public String getListaDeUnidadesString() {
    String str = "";
    for (int i = 0; i < unidades.size(); i++) {
      str += "Unidade: " + unidades.get(i).getNumeroDaUnidade() + "\n";
    }
    return str;
  }

  public boolean incluirVisitante(Visitante visitante) {
    return visitantes.add(visitante);
  }

  public Pessoa excluirVisitante(String cpf) {
    Pessoa pessoaRemovida = null;
    for (int i = 0; i < visitantes.size(); i++) {
      if (visitantes.get(i).getCpf().equals(cpf)) {
        pessoaRemovida = visitantes.get(i);
        visitantes.remove(i);
      }
    }
    return pessoaRemovida;
  }

  public ArrayList<Visitante> getListaDeVisitantes() {
    return visitantes;
  }

  public boolean incluirMorador(Condomino morador) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Unidades disponíveis: ");
    System.out.println(getListaDeUnidadesString());

    short numeroDaUnidade = 0;
    do {
      System.out.println("Digite o número da unidade: ");
      if (scanner.hasNextShort()) {
        numeroDaUnidade = scanner.nextShort();
      } else {
        System.out.println("Número da unidade inválido!\n");
        scanner.next();
        continue;
      }
      if (getUnidade(numeroDaUnidade) == null) {
        System.out.println("Unidade não encontrada!\n");
        continue;
      }
    } while (numeroDaUnidade < 1 || getUnidade(numeroDaUnidade) == null);

    for (int i = 0; i < unidades.size(); i++) {
      if (unidades.get(i).getNumeroDaUnidade() == numeroDaUnidade) {
        return unidades.get(i).incluirMorador(morador);
      }
    }
    return true;
  }

  public Pessoa excluirMorador(String cpf) {
    Pessoa pessoaRemovida = null;
    for (Unidade unidade : unidades) {
      Pessoa pr = unidade.excluirMorador(cpf);
      if (pr != null) {
        pessoaRemovida = pr;
        return pr;
      }
    }

    return pessoaRemovida;
  }

  public ArrayList<Condomino> getListaDeCondominos() {
    ArrayList<Condomino> listaDeCondominosAuxiliar = new ArrayList<Condomino>();
    for (Unidade unidade : unidades) {
      listaDeCondominosAuxiliar.addAll(unidade.getMoradores());
    }
    return listaDeCondominosAuxiliar;
  }

  public String registrarVisitante(Pessoa administrador, Pessoa visitante,
      Unidade unidade, Data dataDeEntrada,
      Data dataDeSaida) {
    RegistroDeVisitante registro = new RegistroDeVisitante(administrador,
        visitante, unidade,
        dataDeEntrada, dataDeSaida);
    registroDeVisitantes.add(registro);
    return registro.toString();
  }

  public void relatorioRegistroDeVisitantes() {
    if (registroDeVisitantes.size() == 0) {
      System.out.println("Não há registros de visitantes.");
      return;
    }
    try {
      FileWriter fstream = new FileWriter(
          new File("").getAbsolutePath() + "//relatorios//" + new Date().toString() + "_relatorioDeVisitantes.txt",
          true);

      PrintWriter gravarArq = new PrintWriter(fstream);

      for (RegistroDeVisitante registro : registroDeVisitantes) {
        gravarArq.println(registro.toString());
      }

      fstream.close();
      System.out.println("Relatório de visitantes gerado com sucesso!\n");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void seedForTests() {
    unidades.add(new Unidade((short) 123));
    unidades.add(new Unidade((short) 456));

    unidades.get(0).incluirMorador(new Condomino("Morador 1", "53987654321", "12345678901"));
    unidades.get(1).incluirMorador(new Condomino("Morador 2", "53987654322", "12345678902"));
  }

  public Unidade getUnidade(short numeroDaUnidade) {
    for (Unidade unidade : unidades) {
      if (unidade.getNumeroDaUnidade() == numeroDaUnidade) {
        return unidade;
      }
    }
    return null;
  }

  public Visitante getVisitante(String cpf) {
    for (Visitante visitante : visitantes) {
      if (visitante.getCpf().equals(cpf)) {
        return visitante;
      }
    }
    return null;
  }

  public Condomino getMorador(String cpf) {
    for (Unidade unidade : unidades) {
      for (Condomino morador : unidade.getMoradores()) {
        if (morador.getCpf().equals(cpf)) {
          return morador;
        }
      }
    }
    return null;
  }
}
