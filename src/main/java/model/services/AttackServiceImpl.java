package model.services;

import model.Character;
import model.Prop;
import model.Target;
import model.abilities.AttackService;

public class AttackServiceImpl implements AttackService {

    @Override
    public void attack(Character attacker, Target target, int range) {
        if (attacker.getFighterType().getRange() >= range) {
            if (target instanceof Prop prop) {
                attack(attacker, prop);
            } else if (target instanceof Character character) {
                if(!this.equals(character) && character.isAlive() && !areAllies(character)) {
                    attack(attacker, character);
                }
            }
        }
    }

    private void attack(Character attacker,Prop prop) {
        prop.setHealth(prop.getHealth() - attacker.getDamage());
        if(prop.getHealth() <= 0) {
            prop.setDestroyed(true);
            prop.setHealth(0);
        }
    }

    private void attack(Character attacker, Character target) {
        double newDamage = target.getDamage();
        if(target.getLevel() - attacker.getLevel() >= 5) {
            newDamage *= 0.5;
        } else if(attacker.getLevel() - target.getLevel()  >= 5) {
            newDamage /= 0.5;
        }
        target.setHealth(target.getHealth() - newDamage);
        if(target.getHealth() <= 0) {
            target.setAlive(false);
            target.setHealth(0);
        }
    }

}
