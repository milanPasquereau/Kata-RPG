package model;

import java.util.Objects;

public class Character {

    private int id;
    private int level;
    private double health;
    private boolean alive;
    private double damage;
    private double heal;

    public Character(int id, int level, double health, double damage, double heal) {
        this.id = id;
        this.level = level;
        this.health = health;
        this.alive = true;
        this.damage = damage;
        this.heal = heal;
    }

    public void attack(Character character) {
        if(!this.equals(character) && character.isAlive()) {
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
        if(this.equals(character) && character.isAlive()) {
            character.setHealth(character.getHealth() + this.getHeal());
            if(character.getHealth() > 1000) {
                character.setHealth(1000);
            }
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getHeal() {
        return heal;
    }

    public void setHeal(double heal) {
        this.heal = heal;
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
