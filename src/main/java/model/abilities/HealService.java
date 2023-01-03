package model.abilities;

import model.Character;

public interface HealService {

    void heal(Character healer, Character healed);
}
