package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @Test
    @DisplayName("should deal damages to character")
    void shouldDealDamageToCharacter() {
        Character attacker = new Character(1,1, 1000, 100, 100);
        Character defender = new Character(2, 1, 1000, 100, 100);
        attacker.attack(defender);
        assertEquals(900, defender.getHealth());
    }

    @Test
    @DisplayName("should deal damages to character and kill character")
    void shouldDealDamageAndKillCharacter() {
        Character attacker = new Character(1,1, 1000, 100, 100);
        Character defender = new Character(2,1, 100, 100, 100);
        attacker.attack(defender);
        assertEquals(0, defender.getHealth());
        assertFalse(defender.isAlive());
    }

    @Test
    @DisplayName("should not deal damages to itself")
    void shouldNotDealDamagesToItself() {
        Character attacker = new Character(1,1, 1000, 100, 100);
        attacker.attack(attacker);
        assertEquals(1000, attacker.getHealth());
    }

    @Test
    @DisplayName("should heal itself")
    void shouldHealCharacter() {
        Character healer = new Character(1,1, 900, 100, 100);
        healer.heal(healer);
        assertEquals(1000, healer.getHealth());
    }

    @Test
    @DisplayName("should not heal itself if dead")
    void shouldNotHealItselfIfDead() {
        Character healer = new Character(1,1, 0, 100, 100);
        healer.setAlive(false);
        healer.heal(healer);
        assertEquals(0, healer.getHealth());
        assertFalse(healer.isAlive());
    }

    @Test
    @DisplayName("should not heal full health character")
    void shouldNotHealFullHealthCharacter() {
        Character healer = new Character(1,1, 1000, 100, 100);
        healer.heal(healer);
        assertEquals(1000, healer.getHealth());
    }

    @Test
    @DisplayName("should not heal another character")
    void shouldNotHealAnotherCharacter() {
        Character healer = new Character(1,1, 1000, 100, 100);
        Character cured = new Character(2,1, 800,100, 100);
        healer.heal(cured);
        assertEquals(800, cured.getHealth());
    }

    @Test
    @DisplayName("should reduce damage by 50 % with less than 5 levels")
    void shouldReduceDamageBy50Percent() {
        Character attacker = new Character(1,1, 1000, 100, 100);
        Character defender = new Character(2,7, 500, 100, 100);
        attacker.attack(defender);
        assertEquals(450, defender.getHealth());
    }

    @Test
    @DisplayName("should increase damage by 50 % with more than 5 levels")
    void shouldIncreasedDamageBy50Percent() {
        Character attacker = new Character(1,9, 1000, 100, 100);
        Character defender = new Character(2,1, 400, 100, 100);
        attacker.attack(defender);
        assertEquals(200, defender.getHealth());
    }
}