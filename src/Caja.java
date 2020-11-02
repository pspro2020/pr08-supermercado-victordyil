import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Caja {
    private final int numeroCaja;

    private final Random random = new Random();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Caja(int printerNumber) {
        this.numeroCaja = printerNumber;
    }

    public void atenderCliente(String producto) throws InterruptedException {
        System.out.printf("%s -> %s: Ha entrado en la caja %d\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(), numeroCaja);
        TimeUnit.SECONDS.sleep(random.nextInt(3)+1);
        System.out.printf("%s -> %s: Salio de la tienda, dejo disponible la caja %d\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(), numeroCaja);
    }
}
