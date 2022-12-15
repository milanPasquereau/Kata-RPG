package model;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toSet;

public class Character extends Target{
    private final FighterType fighterType;
    private final int level;
    private final List<Faction> factions;
    private boolean alive;
    private final double damage;
    private final double heal;

    public Character(int id, int level, FighterType fighterType, double health, double damage, double heal) {
        super(id, health);
        this.level = level;
        this.fighterType = fighterType;
        this.alive = true;
        this.damage = damage;
        this.heal = heal;
        this.factions = new ArrayList<>();
    }

    public void attack(Target target, int range) {
        if(target instanceof Prop prop) {
            attack(prop);
        } else if(target instanceof Character character) {
           attack(character, range);
        }
    }

    private void attack(Prop prop) {
        prop.setHealth(prop.getHealth() - this.getDamage());
        if(prop.getHealth() <= 0) {
            prop.setDestroyed(true);
            prop.setHealth(0);
        }
    }

    private void attack(Character character, int range) {
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
                character.setHealth(0);
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
}
