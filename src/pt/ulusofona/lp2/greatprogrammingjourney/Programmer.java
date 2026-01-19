package pt.ulusofona.lp2.greatprogrammingjourney;

// Programador que se mueve por el tablero
public class Programmer extends BoardElement {

    private int id;
    private String name;
    private int skillLevel;
    private boolean hasProfessorHelp;

    public Programmer(int id, String name, int skillLevel) {
        this.id = id;
        this.name = name;
        this.skillLevel = skillLevel;
        this.hasProfessorHelp = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void increaseSkill() {
        skillLevel++;
    }

    public boolean hasProfessorHelp() {
        return hasProfessorHelp;
    }

    public void giveProfessorHelp() {
        hasProfessorHelp = true;
    }

    public void useProfessorHelp() {
        hasProfessorHelp = false;
    }

    @Override
    public String getType() {
        return "Programmer";
    }

    @Override
    public String[] getInfo() {
        return new String[]{"Programmer", String.valueOf(id), name};
    }

    @Override
    public void react(GameManager gameManager, Player player) {
        // No hace nada
    }
}
