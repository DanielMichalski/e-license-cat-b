package model;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class Module {
    private int id;
    private String name;

    public Module(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
