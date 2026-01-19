package pt.ulusofona.lp2.greatprogrammingjourney;

// Da ayuda del profesor para anular ciertos abismos
public class ProfessorHelpTool extends Tool {

    public ProfessorHelpTool() {
        super("Ajuda do Professor");
    }

    @Override
    public void react(GameManager gameManager, Player player) {
        player.getProgrammer().giveProfessorHelp();
    }
}
