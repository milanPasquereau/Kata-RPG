package model;

public class Prop extends Target{
    private boolean destroyed;

    public Prop(int id, double health) {
        super(id, health);
        this.destroyed = false;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }
}
