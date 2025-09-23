Estimación de Pi (pi) con el Método de Montecarlo en Java

Este repositorio contiene un sencillo programa en Java que aproxima el valor de pi utilizando una simulación basada en el método de Montecarlo. El algoritmo simula el lanzamiento de "dardos" aleatorios sobre un cuadrado que contiene un círculo inscrito.

¿CÓMO FUNCIONA?

El método se basa en una idea simple de probabilidad y geometría. Si lanzas una gran cantidad de puntos aleatorios sobre un cuadrado que tiene un círculo inscrito, la proporción de puntos que caen dentro del círculo es aproximadamente igual a la proporción del área del círculo respecto al área del cuadrado.

Configuración: Se define un cuadrado centrado en el origen (0,0) que va de -1 a 1 en los ejes X e Y. Su lado es de 2 unidades. Dentro de este cuadrado, se inscribe un círculo de radio 1.

Áreas:

Área del círculo = pi * radio^2 = pi

Área del cuadrado = lado^2 = 4

Proporción: La relación entre las áreas es: (Área del círculo) / (Área del cuadrado) = pi / 4.

Simulación: El programa genera miles de puntos aleatorios (x, y) dentro del cuadrado y cuenta cuántos de ellos caen dentro del círculo. Un punto está dentro del círculo si se cumple que x^2 + y^2 es menor o igual a 1.

Estimación: A partir de la simulación, obtenemos la fórmula final para estimar pi: pi es aproximadamente 4 multiplicado por (Puntos que cayeron en el círculo) dividido entre (Total de puntos generados).

USO

Para ejecutar este programa, necesitas tener instalado un Kit de Desarrollo de Java (JDK).

Clona el repositorio:
git clone https://github.com/ArthurHack4u/67406_P02.git
cd 67406_P02

Compila el archivo Java:
javac PiMonteCarlo.java

Ejecuta el programa:
java PiMonteCarlo

SALIDA DE EJEMPLO

Al ejecutar el código, verás una salida similar a esta:

Simulación con 1000000 puntos.
Valor estimado de Pi: 3.141508
Valor real de Pi: 3.141592653589793
Diferencia: 8.46535897931341E-5

Nota: El valor estimado variará ligeramente en cada ejecución debido a la naturaleza aleatoria de la simulación.

CONFIGURACIÓN

Puedes ajustar la precisión de la estimación cambiando el valor de la variable totalPuntos dentro del archivo PiMonteCarlo.java. Un número mayor de puntos generalmente resultará en una aproximación más precisa de pi, a costa de un mayor tiempo de cálculo.

// Cambia este valor para aumentar o disminuir la precisión
int totalPuntos = 1000000;