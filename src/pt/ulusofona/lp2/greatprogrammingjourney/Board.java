package pt.ulusofona.lp2.greatprogrammingjourney;
//matriz de casillas
public class Board {
    //Num filas
    private int rows;
    //NUm columnas
    private int columns;
    //Matriz casillas
    private Slot[][] slots;
    // Constructor
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.slots = new Slot[rows][columns];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                slots[r][c] = new Slot(r, c);
            }
        }
    }

    public Slot getSlot(int row, int column) {
        return slots[row][column];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
