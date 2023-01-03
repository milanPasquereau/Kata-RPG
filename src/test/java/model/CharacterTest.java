package model;

import model.faction.Faction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @Test
    @DisplayName("should deal damages to character")
    void shouldDealDamageToCharacter() {
        Character attacker = new Character(1, FighterType.MELEE, 100, 100);
        Character defender = new Character(2, FighterType.MELEE, 100, 100);

        attacker.attack(defender, 1);

        assertEquals(900, defender.getHealth());
    }

    @Test
    @DisplayName("should deal damages to character and kill character")
    void shouldDealDamageAndKillCharacter() {
        Character attacker = new Character(1, FighterType.MELEE, 1000, 100);
        Character defender = new Character(2, FighterType.MELEE, 100, 100);

        attacker.attack(defender, 1);

        assertEquals(0, defender.getHealth());
        assertFalse(defender.isAlive());
    }

    @Test
    @DisplayName("should not deal damages to itself")
    void shouldNotDealDamagesToItself() {
        Character attacker = new Character(1, FighterType.MELEE, 100, 100);

        attacker.attack(attacker, 1);

        assertEquals(1000, attacker.getHealth());
    }

    @Test
    @DisplayName("should heal itself")
    void shouldHealCharacter() {
        Character healer = new Character(1, FighterType.MELEE, 100, 100);
        healer.setHealth(900);
        healer.heal(healer);

        assertEquals(1000, healer.getHealth());
    }

    @Test
    @DisplayName("should not heal itself if dead")
    void shouldNotHealItselfIfDead() {
        Character healer = new Character(1, FighterType.MELEE, 100, 100);
        healer.setHealth(0);
        healer.setAlive(false);

        healer.heal(healer);

        assertEquals(0, healer.getHealth());
        assertFalse(healer.isAlive());
    }

    @Test
    @DisplayName("should not heal full health character")
    void shouldNotHealFullHealthCharacter() {
        Character healer = new Character(1, FighterType.MELEE, 100, 100);

        healer.heal(healer);

        assertEquals(1000, healer.getHealth());
    }

    @Test
    @DisplayName("should not heal another character")
    void shouldNotHealAnotherCharacter() {
        Character healer = new Character(1, FighterType.MELEE, 100, 100);
        Character cured = new Character(2, FighterType.MELEE,100, 100);
        cured.setHealth(800);
        healer.heal(cured);

        assertEquals(800, cured.getHealth());
    }

    @Test
    @DisplayName("should reduce damage by 50 % with less than 5 levels")
    void shouldReduceDamageBy50Percent() {
        Character attacker = new Character(1, FighterType.MELEE, 100, 100);
        Character defender = new Character(2, FighterType.MELEE, 100, 100);
        defender.levelUp(7);
        defender.setHealth(500);
        attacker.attack(defender, 1);

        assertEquals(450, defender.getHealth());
    }

    @Test
    @DisplayName("should increase damage by 50 % with more than 5 levels")
    void shouldIncreasedDamageBy50Percent() {
        Character attacker = new Character(1, FighterType.MELEE, 100, 100);
        Character defender = new Character(2, FighterType.MELEE, 100, 100);
        attacker.levelUp(9);
        defender.setHealth(400);
        attacker.attack(defender, 1);

        assertEquals(200, defender.getHealth());
    }

    @Test
    @DisplayName("should not deal melee damage out or range")
    void shouldNotDealMeleeDamageOutOfRange() {
        Character attacker = new Character(1, FighterType.MELEE, 100, 100);
        Character defender = new Character(2, FighterType.MELEE, 100, 100);
        attacker.attack(defender, 5);

        assertEquals(1000, defender.getHealth());
    }

    @Test
    @DisplayName("should not deal range damage out or range")
    void shouldNotDealRangeDamageOutOfRange() {
        Character attacker = new Character(1, FighterType.RANGED, 100, 100);
        Character defender = new Character(2, FighterType.MELEE, 100, 100);

        attacker.attack(defender, 25);

        assertEquals(1000, defender.getHealth());
    }

    @Test
    @DisplayName("should heal ally")
    void shouldHealAlly() {
        Faction faction = new Faction(1, "Faction");
        Character healer = new Character(1, FighterType.RANGED, 100, 100);
        Character cured = new Character(2, FighterType.MELEE, 100, 100);
        cured.setHealth(400);
        faction.addMember(healer);
        faction.addMember(cured);

        healer.heal(cured);

        assertEquals(500, cured.getHealth());
    }

    @Test
    @DisplayName("should not attack ally")
    void shouldNotAttackAlly() {
        Faction faction = new Faction(1, "Faction");
        Character attacker = new Character(1, FighterType.RANGED, 100, 100);
        Character ally = new Character(2, FighterType.MELEE, 100, 100);
        faction.addMember(attacker);
        faction.addMember(ally);

        attacker.attack(ally, 10);

        assertEquals(1000, ally.getHealth());
    }

    @Test
    @DisplayName("should deal damage and destroy prop")
    void shouldDealDamageAndDestroyProp() {
        Character attacker = new Character(1, FighterType.RANGED, 100, 100);
        Prop prop = new Prop(2,100);
        attacker.attack(prop, 10);

        assertTrue(prop.isDestroyed());
        assertEquals(0, prop.getHealth());
    }
}