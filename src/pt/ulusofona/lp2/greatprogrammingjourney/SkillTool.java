package pt.ulusofona.lp2.greatprogrammingjourney;

// Herramienta que aumenta el nivel del programador
public class SkillTool extends Tool {

    public SkillTool() {
        super("Skill Tool");
    }

    @Override
    public void react(GameManager gameManager, Player player) {
        player.getProgrammer().increaseSkill();
    }
}
