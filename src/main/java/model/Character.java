package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class Character {

    private final int id;
    private final FighterType fighterType;
    private int level;
    private List<Faction> factions;
    private double health;
    private boolean alive;
    private double damage;
    private double heal;

    public Character(int id, int level, FighterType fighterType, double health, double damage, double heal) {
        this.id = id;
        this.level = level;
        this.fighterType = fighterType;
        this.health = health;
        this.alive = true;
        this.damage = damage;
        this.heal = heal;
        this.factions = new ArrayList<>();
    }

    public void attack(Character character, int range) {
        if(!this.equals(character) && character.isAlive() && this.getFighterType().getRange() >= range && !areAllies(character)) {
            double newDamage = getDamage();
            if(character.getLevel() - this.getLevel() >= 5) {
                newDamage *= 0.5;
            } else if(this.getLevel() - character.getLevel()  >= 5) {
                newDamage /= 0.5;
            }
            character.setHealth(character.getHealth() - newDamage);
            if(character.getHealth() <= 0) {
                character.setAlive(false);
            }
        }
    }

    public void heal(Character character) {
        if((this.equals(character) || areAllies(character)) && character.isAlive()) {
            character.setHealth(character.getHealth() + this.getHeal());
            if(character.getHealth() > 1000) {
                character.setHealth(1000);
            }
        }
    }

    public boolean areAllies(Character character) {
        return factions.stream()
                .map(Faction::name)
                .anyMatch(
                        character.factions.stream()
                                .map(Faction::name)
                                .collect(toSet())
                                ::contains);
    }

    public void joinFaction(Faction faction) {
        if(!factions.contains(faction)) {
            factions.add(faction);
        }
    }

    public void leaveFaction(Faction faction) {
        factions.remove(faction);
    }

    public int getLevel() {
        return level;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public double getDamage() {
        return damage;
    }

    public double getHeal() {
        return heal;
    }

    public FighterType getFighterType() {
        return fighterType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return id == character.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
