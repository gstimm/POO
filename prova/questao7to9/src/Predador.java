public class Predador extends Animal {
  public Predador(String identificador) {
    super(identificador);
  }

  public void atuar() {
    alimentar();
    dormir();
    dormir();
  }
}
