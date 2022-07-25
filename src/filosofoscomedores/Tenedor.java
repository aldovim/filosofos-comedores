package filosofoscomedores;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Tenedor {
    private ReentrantLock lock;

    public Tenedor() {
        this.lock = new ReentrantLock();
    }

    public boolean agarrar() throws InterruptedException {
        return lock.tryLock(250, TimeUnit.MILLISECONDS);
    }

    public boolean estaEnUso() {
        return lock.isHeldByCurrentThread();
    }

    public void soltar() throws InterruptedException {
        if (this.estaEnUso()) {
            lock.unlock();
        }
    }
}
