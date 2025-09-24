import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class PiMonteCarloHilos {

    public static void main(String[] args) throws InterruptedException {

        int totalPuntos = 1000000;
        int numeroDeHilos = Runtime.getRuntime().availableProcessors();
        
        AtomicInteger puntosEnElCirculo = new AtomicInteger(0);
        
        System.out.println("simulación con " + totalPuntos + " puntos en " + numeroDeHilos + " hilos");

        long tiempoInicio = System.nanoTime();

        Thread[] hilos = new Thread[numeroDeHilos];
        int puntosPorHilo = totalPuntos / numeroDeHilos;

        for (int i = 0; i < numeroDeHilos; i++) {
            hilos[i] = new Thread(new TareaSimulacion(puntosPorHilo, puntosEnElCirculo));
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }

        long tiempoFin = System.nanoTime();
        long tiempoTotalMs = (tiempoFin - tiempoInicio) / 1_000_000;

        double piEstimado = 4.0 * puntosEnElCirculo.get() / totalPuntos;

        System.out.println("Tiempo de ejecución: " + tiempoTotalMs + " ms.");
        System.out.println("Valor estimado de Pi: " + piEstimado);
        System.out.println("Valor real de Pi:    " + Math.PI);
        System.out.println("Diferencia:          " + Math.abs(Math.PI - piEstimado));
    }

    static class TareaSimulacion implements Runnable {
        private final int numeroDePuntos;
        private final AtomicInteger puntosEnElCirculoGlobal;

        public TareaSimulacion(int numeroDePuntos, AtomicInteger puntosEnElCirculoGlobal) {
            this.numeroDePuntos = numeroDePuntos;
            this.puntosEnElCirculoGlobal = puntosEnElCirculoGlobal;
        }

        @Override
        public void run() {
            Random generadorAleatorio = new Random();
            int puntosLocales = 0;

            for (int i = 0; i < numeroDePuntos; i++) {
                double x = -1 + (2 * generadorAleatorio.nextDouble());
                double y = -1 + (2 * generadorAleatorio.nextDouble());
                double distanciaAlCuadrado = x * x + y * y;

                if (distanciaAlCuadrado <= 1) {
                    puntosLocales++;
                }
            }

            puntosEnElCirculoGlobal.addAndGet(puntosLocales);
        }
    }
}