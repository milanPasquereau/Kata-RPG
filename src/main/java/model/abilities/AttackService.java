package model.abilities;

import model.Character;
import model.Target;

public interface AttackService {

    void attack(Character attacker, Target target, int range);
}
