package model;

public class Character {
    private int level;
    private int health;
    private boolean alive;
    private int damage;
    private int heal;

    public Character(int level, int health, int damage, int heal) {
        this.level = level;
        this.health = health;
        this.alive = true;
        this.damage = damage;
        this.heal = heal;
    }

    public void attack(Character character) {
        if(character.isAlive()) {
            character.setHealth(character.getHealth() - getDamage());
            if(character.getHealth() <= 0) {
                character.setAlive(false);
            }
        }
    }

    public void heal(Character character) {
        if(character.isAlive()) {
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }
}
