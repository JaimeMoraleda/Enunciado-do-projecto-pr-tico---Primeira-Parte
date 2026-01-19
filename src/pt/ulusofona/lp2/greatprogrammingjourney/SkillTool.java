package pt.ulusofona.lp2.greatprogrammingjourney;

public class SkillTool extends Tool {

    public SkillTool() {
        super("Skill Tool");
    }

    @Override
    public String getType() {
        return "Tool";
    }

    @Override
    public void react(GameManager gameManager, Player player) {
        player.getProgrammer().increaseSkill();
    }
}
