package pt.ulusofona.lp2.greatprogrammingjourney;

// Abismo base: por defecto mata al jugador
public class Abyss extends BoardElement {

    protected String name;

    public Abyss(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return "Abyss";
    }

    @Override
    public String[] getInfo() {
        return new String[]{"Abyss", name};
    }

    @Override
    public void react(GameManager gameManager, Player player) {
        player.kill();
    }
}
