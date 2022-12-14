package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    @Test
    @DisplayName("should deal damages to character")
    void shouldDealDamageToCharacter() {
        Character attacker = new Character(1, 1000, 100, 100);
        Character defender = new Character(1, 1000, 100, 100);
        attacker.attack(defender);
        assertEquals(900, defender.getHealth());
    }

    @Test
    @DisplayName("should deal damages to character and kill character")
    void shouldDealDamageAndKillCharacter() {
        Character attacker = new Character(1, 1000, 100, 100);
        Character defender = new Character(1, 100, 100, 100);
        attacker.attack(defender);
        assertEquals(0, defender.getHealth());
        assertFalse(defender.isAlive());
    }

    @Test
    @DisplayName("should heal character")
    void shouldHealCharacter() {
        Character healer = new Character(1, 1000, 100, 100);
        Character cured = new Character(1, 100, 100, 100);
        healer.heal(cured);
        assertEquals(200, cured.getHealth());
    }

    @Test
    @DisplayName("should not heal dead character")
    void shouldNotHealDeadCharacter() {
        Character healer = new Character(1, 1000, 100, 100);
        Character cured = new Character(1, 0, 100, 100);
        cured.setAlive(false);
        healer.heal(cured);
        assertEquals(0, cured.getHealth());
        assertFalse(cured.isAlive());
    }

    @Test
    @DisplayName("should not heal full health character")
    void shouldNotHealFullHealthCharacter() {
        Character healer = new Character(1, 1000, 100, 100);
        Character cured = new Character(1, 1000,100, 100);
        healer.heal(cured);
        assertEquals(1000, cured.getHealth());
    }

}