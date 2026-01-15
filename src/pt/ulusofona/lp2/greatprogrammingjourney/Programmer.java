package pt.ulusofona.lp2.greatprogrammingjourney;

public class Programmer {
    // ID del programador
    private int id;
    // Nombre
    private String name;
    //Nivel
    private int skillLevel;
    //Constructor
    public Programmer(int id, String name, int skillLevel) {
        this.id = id;
        this.name = name;
        this.skillLevel = skillLevel;
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
}
