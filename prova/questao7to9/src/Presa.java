public class Presa extends Animal {
  public Presa(String identificador) {
    super(identificador);
  }

  public void atuar() {
    alimentar();
    alimentar();
    dormir();
  }
}
