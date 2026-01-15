package pt.ulusofona.lp2.greatprogrammingjourney;

public class Player {
    // ID del jugador
    private int id;
    //Fila en el tablero
    private int currentRow;
    //Columna en el tablero
    private int currentColumn;
    //Programador asociado
    private Programmer programmer;
    //Constructor
    public Player(int id, Programmer programmer) {
        this.id = id;
        this.programmer = programmer;
        this.currentRow = 0;
        this.currentColumn = 0;
    }

    public int getId() {
        return id;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public Programmer getProgrammer() {
        return programmer;
    }
    //Actualizar posicion del jugador
    public void moveTo(int row, int column) {
        this.currentRow = row;
        this.currentColumn = column;
    }
}
