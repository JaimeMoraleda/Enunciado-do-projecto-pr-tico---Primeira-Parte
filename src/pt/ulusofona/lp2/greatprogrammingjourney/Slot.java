package pt.ulusofona.lp2.greatprogrammingjourney;
//casilla del tablero
public class Slot {
    //fila de casillas
    private int row;
    //columna de casillas
    private int column;
    //programador que ocupa espacio(null si vacio)
    private Programmer programmer;

    public Slot(int row, int column) {
        this.row = row;
        this.column = column;
        this.programmer = null;
    }
    //casilla vacia?
    public boolean isEmpty() {
        return programmer == null;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
