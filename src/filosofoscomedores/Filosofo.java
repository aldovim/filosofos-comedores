package filosofoscomedores;

import java.util.Iterator;
import java.util.Random;

public class Filosofo implements Runnable {
    public String nombre;
    public Tenedor tenedorIzquierdo;
    public Tenedor tenedorDerecho;
    private Iterator<Long> tiemposGenerados;

    public Filosofo(String name, Tenedor tenedorIzquierdo, Tenedor tenedorDerecho) {
        this.nombre = name;
        this.tenedorIzquierdo = tenedorIzquierdo;
        this.tenedorDerecho = tenedorDerecho;
        this.tiemposGenerados = new Random().longs(2000, 7000).iterator();
    }

    public void comer() throws InterruptedException {
        try {
            System.out.println(this.nombre + ": intenta comer");

            boolean fueTenedorIzqAgarrado = this.tenedorIzquierdo.agarrar();

            if (fueTenedorIzqAgarrado) {
                boolean fueTenedorDerAgarrado = this.tenedorDerecho.agarrar();

                if (fueTenedorDerAgarrado) {
                    long time = this.tiemposGenerados.next();

                    System.out.println(this.nombre + ": comio durante " + time + "ms");

                    this.perderTiempo(time);
                }
            }

            if (!this.tenedorIzquierdo.estaEnUso()) {
                System.out.println(this.nombre + ": no puede comer porque su tenedor izquierdo esta siendo usado");
            }
            if (!this.tenedorDerecho.estaEnUso()) {
                System.out.println(this.nombre + ": no puede comer porque su tenedor derecho esta siendo usado");
            }
        } finally {
            this.tenedorIzquierdo.soltar();
            this.tenedorDerecho.soltar();
        }
    }

    public void pensar() throws InterruptedException {
        long time = this.tiemposGenerados.next();

        System.out.println(this.nombre + ": penso durante " + time + "ms");

        this.perderTiempo(time);
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.pensar();
                this.comer();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void perderTiempo(long time) throws InterruptedException {
        Thread.sleep(time);
    }
}