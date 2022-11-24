public class Reserva {
    private Animal[] animais;

    public void main() {
        this.animais = new Animal[8];
        this.animais[0] = new Leao("Leao_1");
        this.animais[1] = new Guepardo("Guepardo_1");
        this.animais[2] = new Guepardo("Guepardo_2");
        this.animais[3] = new Zebra("Zebra_1");
        this.animais[4] = new Zebra("Zebra_2");
        this.animais[5] = new Antilope("Antilope_1");
        this.animais[6] = new Antilope("Antilope_2");
        this.animais[7] = new Antilope("Antilope_3");
    }

    public void simularAtuacao() {
        for (int i = 0; i < 30; i++) {
            this.escreverDia(i + 1);
            for (Animal animal : animais) {
                animal.escreveNome();
                animal.atuar();
            }
        }
    }

    public void escreverDia(int dia) {
        System.out.println("Dia " + (dia));
    }

}
