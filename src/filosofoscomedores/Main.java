package filosofoscomedores;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Configurando cena...");
        Mesa mesa = new Mesa();
        Thread hiloCena = new Thread(mesa);

        System.out.println("Comenzando cena...");
        hiloCena.start();
        hiloCena.join();
    }
}
