package model;

public record Faction(String name) {
    @Override
    public String name() {
        return name;
    }
}
