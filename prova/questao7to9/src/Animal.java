public class Animal {
    private String identificador;

    public Animal(String identificador) {
        this.identificador = identificador;
    }

    public void atuar() {
        alimentar();
        alimentar();
        alimentar();
        dormir();
    }

    public void escreveNome() {
        System.out.println("Animal: " + this.identificador);
    }

    public static void alimentar() {
        System.out.println("Comendo");
    }

    public static void dormir() {
        System.out.println("Dormindo");
    }
}
