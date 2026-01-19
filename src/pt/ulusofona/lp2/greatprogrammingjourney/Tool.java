package pt.ulusofona.lp2.greatprogrammingjourney;

public abstract class Tool extends BoardElement {

    private String name;

    public Tool(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
