package pt.ulusofona.lp2.greatprogrammingjourney;

public class InfiniteLoopAbyss extends Abyss {

    public InfiniteLoopAbyss() {
        super("Ciclo Infinito");
    }

    @Override
    public void react(GameManager gameManager, Player player) {

        if (player.getProgrammer().hasProfessorHelp()) {
            player.getProgrammer().useProfessorHelp();
            return;
        }

        player.kill();
    }
}
