import java.util.Scanner;

/**
 *
 * @author Gabriel Timm
 */
public class AppGestaoDeCondominios {
  private final Condominio condominio;
  public Pessoa usuario;

  public AppGestaoDeCondominios() {
    condominio = new Condominio("Alphaville", new Administrador("Admin", "53999999999", "12345678900"));
    usuario = null;
  }

  public static void main(String[] args) {
    AppGestaoDeCondominios app = new AppGestaoDeCondominios();
    app.condominio.seedForTests();

    System.out.println("Bem-vindo ao sistema de gestão de condominios\n");

    app.login();

    System.out.println("Bem-vindo " + app.usuario.getNome());

    int opcao;

    if (app.usuario.nivelDeAcesso() == 1) {
      do {
        opcao = condominoMenu();
        switch (opcao) {
          case 1:
            app.incluirVisitante();
            break;
          case 2:
            app.excluirVisitante();
            break;
          case 3:
            app.incluirMorador();
            break;
          case 4:
            app.registrarVisitante(app.usuario);
            break;
          case 5:
            System.out.println("Saindo do sistema\n");
            System.exit(0);
            break;
        }
      } while (true);
    }

    if (app.usuario.nivelDeAcesso() == 2) {
      do {
        opcao = adminMenu();
        switch (opcao) {
          case 1:
            app.incluirUnidade();
            break;
          case 2:
            app.excluirUnidade();
            break;
          case 3:
            app.listarUnidades();
            break;
          case 4:
            app.incluirVisitante();
            break;
          case 5:
            app.excluirVisitante();
            break;
          case 6:
            app.listarVisitantes();
            break;
          case 7:
            app.incluirMorador();
            break;
          case 8:
            app.excluirMorador();
            break;
          case 9:
            app.listarMoradores();
            break;
          case 10:
            app.registrarVisitante(app.usuario);
            break;
          case 11:
            app.relatorioRegistroDeVisitantes();
            break;
          case 12:
            System.out.println("Saindo do sistema\n");
            System.exit(0);
            break;
        }
      } while (true);
    }
  }

  public void login() {
    Scanner scanner = new Scanner(System.in);
    Pessoa user = null;
    String cpf = null;
    do {
      do {
        System.out.println("Digite seu CPF: (somente números / 11 dígitos)");
        cpf = scanner.nextLine();
      } while (cpf == null || (cpf != null && !cpf.matches("\\d{11}")));

      if (condominio.getAdministrador().getCpf().equals(cpf)) {
        user = condominio.getAdministrador();
      } else {
        for (Pessoa pessoa : condominio.getListaDeCondominos()) {
          if (pessoa.getCpf().equals(cpf)) {
            user = pessoa;
          }
        }
      }

      if (user == null) {
        System.out.println("Usuário não encontrado no sistema!\n");
      }

      if (user != null && user.nivelDeAcesso() == 0) {
        System.out.println("Visitantes não possuem acesso à esse sistema!\n");
      }

    } while (user == null || user.nivelDeAcesso() == 0);

    usuario = user;
  }

  private static int adminMenu() {
    int opcao = 0;
    Scanner scanner = new Scanner(System.in);
    do {
      System.out.println("Escolha uma das seguintes opções:\n");
      System.out.println("1 - Incluir unidade");
      System.out.println("2 - Excluir unidade");
      System.out.println("3 - Listar Unidades");
      System.out.println("4 - Incluir visitante");
      System.out.println("5 - Excluir visitante");
      System.out.println("6 - Listar visitantes");
      System.out.println("7 - Incluir morador");
      System.out.println("8 - Excluir morador");
      System.out.println("9 - Listar Moradores");
      System.out.println("10 - Registrar visitante");
      System.out.println("11 - Gerar relatório de registro de visitantes");
      System.out.println("12 - Sair do sistema");

      opcao = scanner.nextInt();
    } while (opcao < 1 || opcao > 12);

    return opcao;
  }

  private static int condominoMenu() {
    int opcao = 0;
    Scanner scanner = new Scanner(System.in);
    do {
      System.out.println("Escolha uma das seguintes opções:\n");
      System.out.println("1 - Incluir visitante");
      System.out.println("2 - Excluir visitante");
      System.out.println("3 - Incluir morador");
      System.out.println("4 - Registrar visitante");
      System.out.println("5 - Sair do sistema");
      opcao = scanner.nextInt();
    } while (opcao < 1 || opcao > 5);

    return opcao;
  }

  private void incluirUnidade() {
    Scanner scanner = new Scanner(System.in);
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
      if (condominio.getUnidade(numeroDaUnidade) != null) {
        System.out.println("Unidade já existe!\n");
        continue;
      }
    } while (numeroDaUnidade < 1 || condominio.getUnidade(numeroDaUnidade) != null);
    condominio.incluirUnidade(new Unidade(numeroDaUnidade));
  };

  private void excluirUnidade() {
    Scanner scanner = new Scanner(System.in);
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
    } while (numeroDaUnidade < 1);

    Unidade unidade = condominio.getUnidade(numeroDaUnidade);
    if (unidade == null) {
      System.out.println("Unidade não encontrada!\n");
    } else {
      condominio.excluirUnidade(numeroDaUnidade);
      System.out.println("Unidade " + numeroDaUnidade + " excluída com sucesso!\n");
    }
  }

  private void listarUnidades() {
    if (condominio.getListaDeUnidades().size() == 0) {
      System.out.println("Não há unidades cadastradas\n");
    } else {
      System.out.println("Lista de unidades:\n");
      for (Unidade unidade : condominio.getListaDeUnidades()) {
        System.out.println("Unidade: " + unidade.getNumeroDaUnidade());
      }
    }
  }

  private void incluirVisitante() {
    String nome = null;
    String telefone = null;
    String cpf = null;

    Scanner scanner = new Scanner(System.in);
    do {
      System.out.println("Digite o nome do visitante:");
      nome = scanner.nextLine();
    } while (nome == null || nome.isEmpty());

    do {
      System.out.println("Digite o telefone do visitante: (somente números / 11 dígitos)");
      telefone = scanner.nextLine();
    } while (telefone == null || (telefone != null && !telefone.matches("\\d{11}")));

    do {
      System.out.println("Digite o CPF do visitante: (somente números / 11 dígitos)");
      cpf = scanner.nextLine();
    } while (cpf == null || (cpf != null && !cpf.matches("\\d{11}")));

    condominio.incluirVisitante(new Visitante(nome, telefone, cpf));
  }

  private void excluirVisitante() {
    Scanner scanner = new Scanner(System.in);
    String cpf = null;

    do {
      System.out.println("Digite o CPF do visitante: (somente números / 11 dígitos)");
      cpf = scanner.nextLine();
    } while (cpf == null || (cpf != null && !cpf.matches("\\d{11}")));

    Visitante visitante = condominio.getVisitante(cpf);
    if (visitante == null) {
      System.out.println("Visitante não encontrado!\n");
    } else {
      condominio.excluirVisitante(cpf);
      System.out.println("Visitante excluído com sucesso!\n");
    }
  }

  private void listarVisitantes() {
    if (condominio.getListaDeVisitantes().size() == 0) {
      System.out.println("Não há visitantes cadastrados\n");
    } else {
      System.out.println("Lista de visitantes:\n");
      for (Visitante visitante : condominio.getListaDeVisitantes()) {
        System.out.println(visitante.toString());
      }
    }
  }

  private void incluirMorador() {
    Scanner scanner = new Scanner(System.in);
    String nome = null;
    String telefone = null;
    String cpf = null;

    do {
      System.out.println("Digite o nome do morador:");
      nome = scanner.nextLine();
    } while (nome == null || nome.isEmpty());

    do {
      System.out.println("Digite o telefone do morador: (somente números / 11 dígitos)");
      telefone = scanner.nextLine();
    } while (telefone == null || (telefone != null && !telefone.matches("\\d{11}")));

    do {
      System.out.println("Digite o CPF do morador: (somente números / 11 dígitos)");
      cpf = scanner.nextLine();
    } while (cpf == null || (cpf != null && !cpf.matches("\\d{11}")));

    condominio.incluirMorador(new Condomino(nome, telefone, cpf));
  }

  private void excluirMorador() {
    Scanner scanner = new Scanner(System.in);
    String cpf = null;
    do {
      System.out.println("Digite o CPF do morador: (somente números / 11 dígitos)");
      cpf = scanner.nextLine();
    } while (cpf == null || (cpf != null && !cpf.matches("\\d{11}")));

    Condomino condomino = condominio.getMorador(cpf);
    if (condomino == null) {
      System.out.println("Morador não encontrado!\n");
    } else {
      condominio.excluirMorador(cpf);
      System.out.println("Morador excluído com sucesso!\n");
    }

  }

  private void listarMoradores() {
    if (condominio.getListaDeCondominos().size() == 0) {
      System.out.println("Não há moradores cadastrados\n");
    } else {
      System.out.println("Lista de moradores:\n");
      for (Condomino condomino : condominio.getListaDeCondominos()) {
        System.out.println(condomino.toString());
      }
    }
  };

  private void registrarVisitante(Pessoa administrador) {
    Visitante visitante = null;
    Unidade unidade = null;
    String dataS = null;
    String dataE = null;
    String cpf = null;

    Scanner scanner = new Scanner(System.in);

    do {
      do {
        System.out.println("Digite o CPF do visitante: (somente números / 11 dígitos)");
        cpf = scanner.nextLine();
      } while (cpf == null || (cpf != null && !cpf.matches("\\d{11}")));

      for (Visitante v : condominio.getListaDeVisitantes()) {
        if (v.getCpf().equals(cpf)) {
          visitante = v;
        }
      }

      if (visitante == null) {
        System.out.println("Visitante não encontrado!");
      }
    } while (visitante == null);

    do {
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
        if (condominio.getUnidade(numeroDaUnidade) == null) {
          System.out.println("Unidade não encontrada\n");
          continue;
        }
      } while (numeroDaUnidade < 1 || condominio.getUnidade(numeroDaUnidade) == null);

      unidade = condominio.getUnidade(numeroDaUnidade);
    } while (unidade == null);

    do {
      System.out.println("Digite a data de entrada no formato dd/mm/yyyy:");
      dataE = scanner.next();

      if (dataE != null && !dataE.matches("\\d{2}/\\d{2}/\\d{4}")) {
        System.out.println("Data inválida\n");
      }
    } while (!dataE.matches("\\d{2}/\\d{2}/\\d{4}") || dataE == null);

    do {
      System.out.println("Digite a data de saida no formato dd/mm/yyyy:");
      dataS = scanner.next();

      if (dataS != null && !dataS.matches("\\d{2}/\\d{2}/\\d{4}")) {
        System.out.println("Data inválida:\n");
      }
    } while (!dataS.matches("\\d{2}/\\d{2}/\\d{4}") || dataS == null);

    Data dataEntrada = new Data(dataE);
    Data dataSaida = new Data(dataS);

    condominio.registrarVisitante(administrador, visitante, unidade, dataEntrada, dataSaida);
  };

  private void relatorioRegistroDeVisitantes() {
    condominio.relatorioRegistroDeVisitantes();
  }
}