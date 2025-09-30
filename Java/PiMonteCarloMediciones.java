import java.util.Random;

public class PiMonteCarloMediciones {
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
                totalDentro += dentroLocal;
            }
        }
    }

    // Versión secuencial
    public static double estimarPiSecuencial(int numeroDePuntos) {
        Random generadorAleatorio = new Random();
        int puntosEnElCirculo = 0;

        for (int i = 0; i < numeroDePuntos; i++) {
            double x = -1 + (2 * generadorAleatorio.nextDouble());
            double y = -1 + (2 * generadorAleatorio.nextDouble());
            double distanciaAlCuadrado = x * x + y * y;

            if (distanciaAlCuadrado <= 1) {
                puntosEnElCirculo++;
            }
        }
        return 4.0 * puntosEnElCirculo / numeroDePuntos;
    }

    public static void main(String[] args) {
        int totalPuntos = 1_000_000;
        int numHilos = 4;

        // ---- Medición Secuencial ----
        long inicioSecuencial = System.nanoTime();
        double piSecuencial = estimarPiSecuencial(totalPuntos);
        long finSecuencial = System.nanoTime();
        double tiempoSecuencial = (finSecuencial - inicioSecuencial) / 1e9; // en segundos

        // ---- Medición Paralela ----
        int puntosPorHilo = totalPuntos / numHilos;
        totalDentro = 0;
        Thread[] hilos = new Thread[numHilos];

        long inicioParalelo = System.nanoTime();
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
        double piParalelo = (4.0 * totalDentro) / totalPuntos;
        long finParalelo = System.nanoTime();
        double tiempoParalelo = (finParalelo - inicioParalelo) / 1e9;

        double speedup = tiempoSecuencial / tiempoParalelo;
        double eficiencia = speedup / numHilos;
        double overhead = tiempoParalelo - (tiempoSecuencial / numHilos);

        System.out.println("---- RESULTADO DE LAS PRUEBAS ----");
        System.out.println("Puntos totales: " + totalPuntos);
        System.out.println("Hilos usados: " + numHilos);

        System.out.println("\n--- Secuencial ---");
        System.out.println("Pi estimado: " + piSecuencial);
        System.out.println("Tiempo: " + tiempoSecuencial + " s");

        System.out.println("\n--- Paralelo ---");
        System.out.println("Pi estimado: " + piParalelo);
        System.out.println("Tiempo: " + tiempoParalelo + " s");

        System.out.println("\n--- Comparación ---");
        System.out.printf("Speedup: %.2fx%n", speedup);
        System.out.printf("Eficiencia: %.2f%%%n", eficiencia * 100);
        System.out.printf("Overhead: %.6f segundos%n", overhead);
    }
}
