import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cola {

    private final Caja[]  cajas;
    private final boolean[] cajaDisponible;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final Semaphore semaphore;
    private final Lock reentrantLock = new ReentrantLock(true);

    public Cola(int numeroCajas) {
        cajas = new Caja[numeroCajas];
        cajaDisponible = new boolean[numeroCajas];
        semaphore = new Semaphore(numeroCajas, true);
        for (int i = 0; i < numeroCajas; i++) {
            cajas[i] = new Caja(i);
            cajaDisponible[i] = true;
        }
    }
    public void pasarProducto(String producto) throws InterruptedException {
        try {
            semaphore.acquire();
            int numeroAtendido = elegirCaja();
            if (numeroAtendido != -1) {
                cajas[numeroAtendido].atenderCliente(producto);
                cajaDisponible[numeroAtendido] = true;
            } else {
                System.out.printf("%s -> %s: No se ha podido atender al cliente\n",
                        LocalTime.now().format(dateTimeFormatter),
                        Thread.currentThread().getName());
            }
        } finally {
            // This is called even if an exception is thrown.
            semaphore.release();
        }
    }

    private int elegirCaja() {
        reentrantLock.lock();
        try {
            for (int i = 0; i < cajas.length; i++) {
                if (cajaDisponible[i]) {
                    cajaDisponible[i] = false;
                    return i;
                }
            }
        } finally {
            reentrantLock.unlock();
        }
        return -1;
    }
}
