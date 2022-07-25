package filosofoscomedores;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Mesa implements Runnable {
    static int CANTIDAD_FILOSOFOS = 5;
    private List<Filosofo> filosofos;

    public Mesa() {
        this.filosofos = new ArrayList<>();

        Tenedor tenedorIzquierdo = new Tenedor();

        for (int i = 1; i <= Mesa.CANTIDAD_FILOSOFOS; i++) {
            Tenedor tenedorDerecho = (i == Mesa.CANTIDAD_FILOSOFOS) // Verificamos que el filosofo sea el ultimo
                ? filosofos.get(0).tenedorIzquierdo
                : new Tenedor();

            String nombre = "Filosofo " + i;

            this.filosofos.add(new Filosofo(nombre, tenedorIzquierdo, tenedorDerecho));
            tenedorIzquierdo = tenedorDerecho;
	}
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newFixedThreadPool(this.filosofos.size());

        for (Filosofo filosofoActual : filosofos) {
            executorService.submit(filosofoActual);
        }
    }
}