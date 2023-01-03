package model;

import model.abilities.AttackService;
import model.abilities.HealService;
import model.faction.Faction;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toSet;

public class Character extends Target {
    private final FighterType fighterType;
    private int level;
    private final List<Faction> factions;
    private boolean alive;
    private final double damage;
    private final double heal;

    public Character(int id, FighterType fighterType, double damage, double heal) {
        super(id, 1000);
        this.level = 1;
        this.fighterType = fighterType;
        this.alive = true;
        this.damage = damage;
        this.heal = heal;
        this.factions = new ArrayList<>();
    }

    public boolean areAllies(Character character) {
        return factions.stream()
                .map(Faction::getId)
                .anyMatch(
                        character.factions.stream()
                                .map(Faction::getId)
                                .collect(toSet())
                                ::contains);
    }

    public void levelUp(int level) {
        this.level = level;
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
