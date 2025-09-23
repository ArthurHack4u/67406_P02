import java.util.Random;

public class PiMonteCarlo {

    public static void main(String[] args) {
        
        int totalPuntos = 1000000;

        double piEstimado = estimarPi(totalPuntos);

        System.out.println("Simulación con " + totalPuntos + " puntos.");
        System.out.println("Valor estimado de Pi: " + piEstimado);

    }

    public static double estimarPi(int numeroDePuntos) {
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
}