package model.faction;

import model.Character;

import java.util.ArrayList;
import java.util.List;

public class Faction {

    private final int id;

    private final String name;

    private final List<Character> members;


    public Faction(int id, String name) {
        this.id = id;
        this.name = name;
        this.members = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Character> getMembers() {
        return members;
    }

    public void addMember(Character character) {
        character.joinFaction(this);
        this.members.add(character);
    }

    public void removeMember(Character character) {
        character.leaveFaction(this);
        this.members.remove(character);
    }
}
