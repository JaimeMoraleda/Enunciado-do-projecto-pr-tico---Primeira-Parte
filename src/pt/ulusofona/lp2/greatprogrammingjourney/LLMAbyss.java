package pt.ulusofona.lp2.greatprogrammingjourney;

public class LLMAbyss extends Abyss {

    public LLMAbyss() {
        super("LLM");
    }

    @Override
    public void react(GameManager gameManager, Player player) {

        if (player.getMoveCount() < 4) {

            if (player.getProgrammer().hasProfessorHelp()) {
                player.getProgrammer().useProfessorHelp();
                return;
            }

            int backPos = player.getCurrentPosition() - player.getLastMove();
            if (backPos < 0) backPos = 0;

            gameManager.getBoard().getSlot(player.getCurrentPosition()).setElement(null);
            player.forceMoveTo(backPos);
            gameManager.getBoard().getSlot(backPos).setElement(player.getProgrammer());
            return;
        }

        int forwardPos = player.getCurrentPosition() + player.getLastMove();
        if (forwardPos >= gameManager.getBoard().getSize()) {
            forwardPos = gameManager.getBoard().getSize() - 1;
        }

        gameManager.getBoard().getSlot(player.getCurrentPosition()).setElement(null);
        player.forceMoveTo(forwardPos);
        gameManager.getBoard().getSlot(forwardPos).setElement(player.getProgrammer());
    }
}
