package model;

public enum FighterType {

    MELEE("MELEE", 2),
    RANGED("RANGED", 20);

    private final String type;
    private final int range;

    FighterType(String type, int range) {
        this.type = type;
        this.range = range;
    }

    public int getRange() {
        return range;
    }
}
