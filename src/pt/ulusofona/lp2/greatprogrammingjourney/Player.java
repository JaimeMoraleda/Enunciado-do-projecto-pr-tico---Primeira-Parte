package pt.ulusofona.lp2.greatprogrammingjourney;

public class Player {

    private int id;
    private int currentPosition;
    private Programmer programmer;
    private boolean alive;

    private int moveCount;
    private int lastMove;

    public Player(int id, Programmer programmer) {
        this.id = id;
        this.programmer = programmer;
        this.currentPosition = 0;
        this.alive = true;
        this.moveCount = 0;
        this.lastMove = 0;
    }

    public int getId() {
        return id;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public int getLastMove() {
        return lastMove;
    }

    public void kill() {
        alive = false;
    }

    public void moveTo(int position, int steps) {
        this.currentPosition = position;
        this.lastMove = steps;
        this.moveCount++;
    }

    // usado para recuos/avanços automáticos
    public void forceMoveTo(int position) {
        this.currentPosition = position;
    }
}
