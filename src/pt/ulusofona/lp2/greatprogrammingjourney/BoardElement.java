package pt.ulusofona.lp2.greatprogrammingjourney;

public abstract class BoardElement {

    public abstract String getType();

    public abstract void react(GameManager gameManager, Player player);
}
