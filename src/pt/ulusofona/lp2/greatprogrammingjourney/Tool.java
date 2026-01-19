package pt.ulusofona.lp2.greatprogrammingjourney;

// Clase base de todas las herramientas
public abstract class Tool extends BoardElement {

    protected String name;

    public Tool(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return "Tool";
    }

    @Override
    public String[] getInfo() {
        return new String[]{"Tool", name};
    }

    public String getName() {
        return name;
    }
}
