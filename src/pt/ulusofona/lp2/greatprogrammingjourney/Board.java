package pt.ulusofona.lp2.greatprogrammingjourney;

public class Board {

    private Slot[] slots;

    public Board(int worldSize) {
        slots = new Slot[worldSize];
        for (int i = 0; i < worldSize; i++) {
            slots[i] = new Slot(i);
        }
    }

    public int getSize() {
        return slots.length;
    }

    public Slot getSlot(int position) {
        if (position < 0 || position >= slots.length) {
            return null;
        }
        return slots[position];
    }
}
