public class Main {
    public static void main(String[] args) {
        Cola cola = new Cola(4);
        Thread[] atenders = new Thread[50];
        for (int i = 0; i < 50; i++) {
            atenders[i] = new Thread(new Atender(cola, "Compra #" + i), "Cliente #" + i);
        }
        for (int i = 0; i < 50; i++) {
            atenders[i].start();
        }
        
    }
}
