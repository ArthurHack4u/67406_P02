MORAN ESCALANTE BRYAN ARTURO 67406...

# Estimación de π con Monte Carlo y Programación Concurrente en Java

Este proyecto implementa el método de Monte Carlo para estimar el valor de π, utilizando tanto una versión secuencial como una versión paralela con múltiples hilos.

## 📌 Descripción

El método de Monte Carlo consiste en generar puntos aleatorios dentro de un cuadrado y calcular cuántos caen dentro del círculo inscrito.  
La proporción entre los puntos dentro del círculo y el total de puntos permite aproximar el valor de π.

- **Versión secuencial**: calcula todos los puntos en un solo hilo.  
- **Versión concurrente**: reparte el trabajo entre varios hilos para mejorar el rendimiento.

## 📂 Estructura del código

- `PiMonteCarlo.java` → Implementación secuencial.  
- `PiMonteCarloConcurrente.java` → Implementación concurrente con hilos.  

## ▶️ Ejecución

Compilar los archivos:

```bash
javac PiMonteCarlo.java PiMonteCarloConcurrente.java

