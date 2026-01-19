package pt.ulusofona.lp2.greatprogrammingjourney;

// Casa del tablero
public class Slot {

    private int position;
    private BoardElement element;

    public Slot(int position) {
        this.position = position;
        this.element = null;
    }

    public int getPosition() {
        return position;
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
}
