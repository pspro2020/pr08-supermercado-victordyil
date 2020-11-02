import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Atender implements Runnable {
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final Cola cola;
    private final String producto;
    private Random random = new Random();

    public Atender(Cola cola, String producto) {
        Objects.requireNonNull(cola);
        Objects.requireNonNull(producto);
        this.cola = cola;
        this.producto = producto;
    }

    @Override
    public void run() {
        int tardar = random.nextInt(2) + 1;
        try {
            System.out.printf("%s -> %s: Comenzo a comprar..\n",
                    LocalTime.now().format(dateTimeFormatter),
                    Thread.currentThread().getName(), producto);
            sleep(tardar);
            System.out.printf("%s -> %s: Termino de comprar..\n",
                    LocalTime.now().format(dateTimeFormatter),
                    Thread.currentThread().getName(), producto);
            cola.pasarProducto(producto);
        } catch (InterruptedException e) {
            System.out.printf("%s -> Ha sido interrumpido cuando estaba atendiendole\n",
                    Thread.currentThread().getName());
        }
    }
}
