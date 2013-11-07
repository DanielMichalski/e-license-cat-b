package model;

/**
 * Author: Daniel
 * Date: 05.11.13
 */
public class Module {
    private int id;
    private String name;

    public Module( int id, String name) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Module{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public int getId() {
        return id;
    }
}
