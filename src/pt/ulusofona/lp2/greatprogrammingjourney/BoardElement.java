package pt.ulusofona.lp2.greatprogrammingjourney;

// Clase base de todo lo que puede estar en una casilla del tablero
public abstract class BoardElement {

    // Tipo genérico (Abyss, Tool, Programmer)
    public abstract String getType();

    // Información que el visualizador necesita
    public abstract String[] getInfo();

    // Lo que acontece cuando un jugador cae aquí
    public abstract void react(GameManager gameManager, Player player);
}
