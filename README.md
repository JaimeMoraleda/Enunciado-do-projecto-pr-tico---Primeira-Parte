# The Great Programming Journey — LP2 (Época de Recuperacion)

## 📌 Diagrama UML

![](UML/Diagrama.png?raw=true "Diagrama UML")

---

## 🧠 Justificación del Modelo

El modelo fue diseñado usando herencia y polimorfismo para representar correctamente los distintos tipos de elementos que pueden existir en el tablero.  
Todos los elementos del tablero heredan de la clase abstracta `BoardElement`, lo que permite que el `GameManager` interactúe con ellos de forma genérica a través del método `react`.

Los abismos y las herramientas son especializaciones de `BoardElement`, y cada uno implementa su propio comportamiento al ser activado por un jugador. Esto permite añadir nuevos tipos de abismos o herramientas fácilmente, como el abismo LLM de la época de recuperacion, sin modificar la lógica principal del juego.

La clase `GameManager` concentra la lógica de turnos, movimiento, final de juego, empate y guardado/carga de partidas, mientras que las restantes clases representan entidades específicas del domínio do jogo, manteniendo el código organizado y fácil de mantener.


