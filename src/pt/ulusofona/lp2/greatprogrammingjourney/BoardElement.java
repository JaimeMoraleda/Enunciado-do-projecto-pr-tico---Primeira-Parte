package pt.ulusofona.lp2.greatprogrammingjourney;

public abstract class BoardElement {

    public abstract String getType();

    public abstract String[] getInfo();

    public abstract void react(GameManager gameManager, Player player);
}
