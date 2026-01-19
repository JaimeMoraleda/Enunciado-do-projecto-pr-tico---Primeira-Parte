package pt.ulusofona.lp2.greatprogrammingjourney;

public class Abyss extends BoardElement {

    private String name;

    public Abyss(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return "Abyss";
    }

    public String getName() {
        return name;
    }

    @Override
    public void react(GameManager gameManager, Player player) {
        Board board = gameManager.getBoard();

        // limpiar casilla actual
        Slot current = board.getSlot(player.getCurrentRow(), player.getCurrentColumn());
        if (current != null) {
            current.setElement(null);
        }

        // mover al inicio
        player.moveTo(0, 0);
        board.getSlot(0, 0).setElement(player.getProgrammer());
    }
}
