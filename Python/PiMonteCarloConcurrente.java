import java.util.Random;

//moran escalante bryan arturo 67406
public class PiMonteCarloConcurrente {
    private static long totalDentro = 0;
    private static final Object candado = new Object();

    static class CalculadoraParalela implements Runnable {
        private final int puntos;
        private final int id;

        public CalculadoraParalela(int puntos, int id) {
            this.puntos = puntos;
            this.id = id;
        }

        @Override
        public void run() {
            Random random = new Random();
            long dentroLocal = 0;

            for (int i = 0; i < puntos; i++) {
                double x = -1 + 2 * random.nextDouble();
                double y = -1 + 2 * random.nextDouble();

                if (x * x + y * y <= 1.0) {
                    dentroLocal++;
                }
            }

            synchronized (candado) {
                System.out.println("Hilo " + id + " aporta " + dentroLocal + " puntos.");
                totalDentro += dentroLocal;
            }
        }
    }

    public static void main(String[] args) {
        int totalPuntos = 1_000_000;
        int numHilos = 4;
        int puntosPorHilo = totalPuntos / numHilos;

        Thread[] hilos = new Thread[numHilos];

        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new Thread(new CalculadoraParalela(puntosPorHilo, i));
            hilos[i].start();
        }

        for (int i = 0; i < numHilos; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        double piEstimado = (4.0 * totalDentro) / totalPuntos;

        System.out.println("Simulación con " + totalPuntos + " puntos.");
        System.out.println("Valor estimado de Pi: " + piEstimado);
        System.out.println("Valor real de Pi: " + Math.PI);
        System.out.println("Diferencia: " + Math.abs(Math.PI - piEstimado));
    }
}
