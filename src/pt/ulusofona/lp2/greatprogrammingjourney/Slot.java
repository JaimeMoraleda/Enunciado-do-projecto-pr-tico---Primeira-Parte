package pt.ulusofona.lp2.greatprogrammingjourney;

public class Slot {

    private int row;
    private int column;
    private BoardElement element;

    public Slot(int row, int column) {
        this.row = row;
        this.column = column;
        this.element = null;
    }

    public boolean isEmpty() {
        return element == null;
    }

    public BoardElement getElement() {
        return element;
    }

    public void setElement(BoardElement element) {
        this.element = element;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
